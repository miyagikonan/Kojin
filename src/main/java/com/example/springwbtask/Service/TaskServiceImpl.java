package com.example.springwbtask.Service;

import com.example.springwbtask.Dao.TaskDao;
import com.example.springwbtask.Record.ProductRecord;
import com.example.springwbtask.Record.TaskListRecord;
import com.example.springwbtask.Record.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    //ログイン
    @Override
    public UserRecord Login(String login_id, String password){
        return taskDao.Login(login_id,password);
    }

    //メニュー一覧
    @Override
    //メニュー一覧
    public List<TaskListRecord> findAll(){
        return taskDao.findAll();
    }

    //詳細画面
    @Override
    public ProductRecord findById(int id){
        return taskDao.findById(id);
    }

    //更新
    @Override
    public int update(ProductRecord productRecord){
        return taskDao.update(productRecord);
    }

    //削除
    @Override
    public int delete(int id){
        return taskDao.delete(id);
    }
}
