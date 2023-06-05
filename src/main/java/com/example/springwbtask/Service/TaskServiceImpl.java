package com.example.springwbtask.Service;

import com.example.springwbtask.Dao.TaskDao;
import com.example.springwbtask.Record.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    //ログイン
    @Override
    public UserRecord Login(String login_id, String password);

}
