package com.example.springwbtask.Form;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductForm {

    //更新
    private String image_path;
    @NotEmpty
    private String product_id;
    @NotEmpty
    private String pname;
    @NotNull
    private int price;
    private String cname;
    private int category_id;
    private String description;

    public ProductForm(){

    }

}
