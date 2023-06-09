package com.example.springwbtask.Form;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InsertForm {

    @NotEmpty(message = "商品IDは必須入力です")
    private String product_id;
    @NotEmpty(message = "商品名は必須入力です")
    private String name;
    @NotNull(message = "値段は必須入力です")
    private int price;
    private int category_id;
    private String description;
}
