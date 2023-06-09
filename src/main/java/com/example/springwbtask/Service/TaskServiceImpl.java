package com.example.springwbtask.Service;

import com.example.springwbtask.Dao.TaskDao;
import com.example.springwbtask.Record.*;
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

    @Override
    public List<CategoryRecord> categoryAll() {
        return taskDao.categoryAll();
    }

    //更新
    @Override
    public int update(ProductRecord updateRecord){
        return taskDao.update(updateRecord);
    }

    //新規登録
    @Override
    public int insert(InsertRecord insertRecord){
        return taskDao.insert(insertRecord);
    }

    //削除
    @Override
    public int delete(int id){
        return taskDao.delete(id);
    }
}
