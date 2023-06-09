package com.example.springwbtask.Dao;

import com.example.springwbtask.Record.*;

import java.util.List;

public interface TaskDao {
    //ログイン
    public UserRecord Login(String login_id, String password);

    //メニュー一覧
    public List<TaskListRecord> findAll();

    //詳細画面
    public ProductRecord findById(int id);

    //カテゴリ
    public List<CategoryRecord> categoryAll();

    //更新
    public int update(ProductRecord updateRecord);

    //新規登録
    public int insert(InsertRecord insertRecord);

    //削除
    public int delete(int id);

}
