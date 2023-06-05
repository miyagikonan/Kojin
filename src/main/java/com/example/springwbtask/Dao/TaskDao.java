package com.example.springwbtask.Dao;

import com.example.springwbtask.Record.UserRecord;

public interface TaskDao {
    public UserRecord Login(String login_id, String password);
}
