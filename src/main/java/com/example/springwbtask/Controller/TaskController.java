package com.example.springwbtask.Controller;


import com.example.springwbtask.Form.CategoryForm;
import com.example.springwbtask.Form.InsertForm;
import com.example.springwbtask.Form.ProductForm;
import com.example.springwbtask.Form.TaskForm;
import com.example.springwbtask.Record.InsertRecord;
import com.example.springwbtask.Record.ProductRecord;
import com.example.springwbtask.Record.UpdateRecord;
import com.example.springwbtask.Record.UserRecord;
import com.example.springwbtask.Service.TaskService;
import com.example.springwbtask.Service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    @Autowired
    private HttpSession session;

    @Autowired
    TaskServiceImpl taskService;

    //ログイン
    @GetMapping("/index")
    public String index(@ModelAttribute("TaskForm")TaskForm taskForm){
        return "index";
    }

    @PostMapping("/index")
    public String login(@Validated @ModelAttribute("TaskForm") TaskForm taskForm,
                        BindingResult bindingResult,
                        Model model){
        if (bindingResult.hasErrors()){
            return "index";
        }

        String id = taskForm.getLoginId();
        String pass = taskForm.getPass();

        var user = taskService.Login(id,pass);
        System.out.println(user);

        if (user == null){
            model.addAttribute("error", "IDまたはパスワードが不正です");
            return "index";
        }

        session.setAttribute("user", user);
        return "redirect:/menu";
    }

    //メニュー一覧
    @GetMapping("menu")
    public String List(Model model) {
        model.addAttribute("products",taskService.findAll());
        return "menu";
    }


//新規登録画面
    @GetMapping("/insert")
    public String in(@ModelAttribute("InsertForm")InsertForm insertForm,
                     @ModelAttribute("CategoryForm")CategoryForm categoryForm,
                     Model model){
        model.addAttribute("categories",taskService.categoryAll());
        return "insert";
    }

    @PostMapping("/insert")
    public String insert(@Validated @ModelAttribute("InsertForm")InsertForm insertForm,
                            BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "insert";
        }

        System.out.println(insertForm);
        String product_id = insertForm.getProduct_id();
        String name = insertForm.getName();
        int price = insertForm.getPrice();
        int category_id = insertForm.getCategory_id();
        String description = insertForm.getDescription();
        InsertRecord insert = new InsertRecord(product_id,name,price,category_id, description);
        System.out.println(insert);

        taskService.insert(insert);
        return "redirect:/menu";
    }



    //商品詳細画面
    @GetMapping("/detail/{id}")
    public String findId(@PathVariable("id") int id, Model model){
        var result = model.addAttribute("product", taskService.findById(id));
        return "detail";
    }

    //カテゴリ
    @GetMapping("updateInput")
    public String categorylist(Model model){
        var result = model.addAttribute("categories",taskService.categoryAll());
        System.out.println(result);
        return "updateInput";
    }

    //商品の更新
    @GetMapping("/updateInput/{id}")
    public String up(@ModelAttribute("ProductForm") ProductForm productForm,
                     @ModelAttribute("CategoryForm")CategoryForm categoryForm,
                     @PathVariable("id") int id, Model model){
        var result = taskService.findById(id);
        productForm.setImage_path(result.image_path());
        productForm.setProduct_id(result.product_id());
        productForm.setPname(result.pname());
        productForm.setPrice(result.price());
        productForm.setCname(result.cname());
        productForm.setDescription(result.description());
        productForm.setCategory_id(result.category_id());
        categoryForm.setId(result.category_id());

        var res = model.addAttribute("categories",taskService.categoryAll());
//        System.out.println(res);

        var re = model.addAttribute("product", taskService.findById(id));
        return "updateInput";
    }

    @PostMapping("/updateInput/{id}")
    public String update(@Validated @ModelAttribute("ProductForm") ProductForm productForm,
                         BindingResult bindingResult,
                         @PathVariable("id") int id,
                         Model model){

        if (bindingResult.hasErrors()){
            model.addAttribute("product", taskService.findById(id));
//            var findproduct = taskService.findById(id);
//            productForm.setProduct_id(findproduct.product_id());
//            productForm.setPname(findproduct.pname());
//            productForm.setPrice(findproduct.price());
            return "updateInput";
        }

        String image_path = productForm.getImage_path();
        String product_id = productForm.getProduct_id();
        String pname = productForm.getPname();
        int price = productForm.getPrice();
        String cname = productForm.getCname();
        int category_id = productForm.getCategory_id();
        String description = productForm.getDescription();
        ProductRecord record = new ProductRecord(image_path,product_id,pname,price,cname,description,id,category_id);
//        System.out.println(categoryForm);

        taskService.update(record);
        return "redirect:/menu";

    }

    //削除
    @PostMapping("/detail/{id}")
    public String delete(@PathVariable("id") int id){
        var record = taskService.delete(id);
        return "redirect:/menu";
    }

    //ログアウト
}
