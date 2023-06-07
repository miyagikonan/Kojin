package com.example.springwbtask.Dao;
import com.example.springwbtask.Record.ProductRecord;
import com.example.springwbtask.Record.UserRecord;
import com.example.springwbtask.Record.TaskListRecord;
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

        var list= jdbcTemplate.query("SELECT p.id, p.image_path, p.product_id, p.name as pname, p.price, c.name as cname, p.description FROM products0 p  JOIN categories0 c ON p.category_id = c.id WHERE p.id = :id;",
                parameterSource, new DataClassRowMapper<>(ProductRecord.class));
        return list.get(0);
    }

    //更新
    @Override
    public int update(ProductRecord productRecord){
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",productRecord.id());
        parameterSource.addValue("product_id",productRecord.product_id());
        parameterSource.addValue("pname",productRecord.pname());
        parameterSource.addValue("price",productRecord.price());
        parameterSource.addValue("cname",productRecord.cname());
        parameterSource.addValue("description",productRecord.description());
        int update = jdbcTemplate.update("UPDATE products0 p JOIN categories0 c ON p.category_id = c.id SET p.product_id = :prduct_id , p.name = :pname, p.price = :price, c.name = :pname,p.description = :description WHERE p.id = :id;",
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
}
