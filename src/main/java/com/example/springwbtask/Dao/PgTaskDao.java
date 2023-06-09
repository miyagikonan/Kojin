package com.example.springwbtask.Dao;
import com.example.springwbtask.Record.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import java.util.List;

@Repository
public class PgTaskDao implements TaskDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    //ログイン
    @Override
    public UserRecord Login(String login_id, String password){
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("login_id",login_id);
        parameterSource.addValue("password",password);
        System.out.println(login_id);
        System.out.println(password);

        var list = jdbcTemplate.query("SELECT * FROM users0 WHERE login_id = :login_id AND password = :password",
                                       parameterSource, new DataClassRowMapper<>(UserRecord.class));
        return list.isEmpty() ? null : list.get(0);
    }

    //メニュー一覧
    @Override
    public List<TaskListRecord> findAll() {
        var result = jdbcTemplate.query("SELECT p.product_id, p.name as pname, p.price, c.name as cname, p.id FROM products0 p  JOIN categories0 c ON p.category_id = c.id",
                new DataClassRowMapper<>(TaskListRecord.class));
        return result;
    }

    //詳細画面
    @Override
    public ProductRecord findById(int id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);

        var list= jdbcTemplate.query("SELECT p.id, p.image_path, p.product_id, p.name as pname, p.price, c.name as cname, p.description, p.category_id FROM products0 p  JOIN categories0 c ON p.category_id = c.id WHERE p.id = :id;",
                parameterSource, new DataClassRowMapper<>(ProductRecord.class));
        return list.get(0);
    }

    //カテゴリ
    @Override
    public List<CategoryRecord> categoryAll(){
        var result = jdbcTemplate.query("SELECT id, name FROM categories0;",
                new DataClassRowMapper<>(CategoryRecord.class));
        return result;
    }

    //更新
    @Override
    public int update(ProductRecord updateRecord){
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("image_path",updateRecord.image_path());
        parameterSource.addValue("product_id",updateRecord.product_id());
        parameterSource.addValue("pname",updateRecord.pname());
        parameterSource.addValue("price",updateRecord.price());
        parameterSource.addValue("cname",updateRecord.cname());
        parameterSource.addValue("description",updateRecord.description());
        parameterSource.addValue("category_id",updateRecord.category_id());
        parameterSource.addValue("id",updateRecord.id());
        int update = jdbcTemplate.update("UPDATE products0 SET product_id = :product_id, name = :pname, price = :price, category_id = :category_id, description = :description WHERE id = :id;",
                parameterSource);
        return update;
    }

    //削除
    @Override
    public int delete(int id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);
        var result = jdbcTemplate.update("DELETE FROM products0 WHERE id = :id",parameterSource);
        return result;
    }

    //新規登録
    @Override
    public int insert(InsertRecord insertRecord){
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("product_id",insertRecord.product_id());
        parameterSource.addValue("name",insertRecord.name());
        parameterSource.addValue("price",insertRecord.price());
        parameterSource.addValue("category_id",insertRecord.category_id());
        parameterSource.addValue("description",insertRecord.description());
        var result = jdbcTemplate.update("insert into products0 (product_id, name, price, category_id, description) values(:product_id, :name, :price, :category_id, :description);",
                parameterSource);
        return result;
    }
}
