package com.example.springwbtask.Form;


import lombok.Data;

@Data
public class ProductForm {

    //更新
    private String image_path;
    private String product_id;
    private String pname;
    private int price;
    private String cname;
    private String description;
}
