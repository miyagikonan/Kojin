package com.example.springwbtask.Service;

import com.example.springwbtask.Record.UserRecord;

public interface TaskService {

    public UserRecord Login(String login_id, String password);

}
