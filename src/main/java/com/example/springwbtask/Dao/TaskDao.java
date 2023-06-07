package com.example.springwbtask.Dao;

import com.example.springwbtask.Record.ProductRecord;
import com.example.springwbtask.Record.TaskListRecord;
import com.example.springwbtask.Record.UserRecord;

import java.util.List;

public interface TaskDao {
    //ログイン
    public UserRecord Login(String login_id, String password);

    //メニュー一覧
    public List<TaskListRecord> findAll();

    //詳細画面
    public ProductRecord findById(int id);

    //更新
    public int update(ProductRecord productRecord);

    //削除
    public int delete(int id);

}
