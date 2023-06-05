package com.example.springwbtask.Dao;
import com.example.springwbtask.Record.UserRecord;
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

        var list = jdbcTemplate.query("SELECT * FROM users0 WHERE login_id = :login_id AND password = :password",
                                       parameterSource, new DataClassRowMapper<>(UserRecord.class));
        return list.get(0);
    }
}
