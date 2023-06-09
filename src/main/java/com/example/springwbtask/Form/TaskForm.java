package com.example.springwbtask.Form;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TaskForm {
    //ログイン
    @NotEmpty(message = "IDは必須です")
    private String loginId;
    @NotEmpty(message = "パスワードは必須です")
    private String pass;

}
