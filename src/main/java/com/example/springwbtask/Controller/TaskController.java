package com.example.springwbtask.Controller;


import com.example.springwbtask.Form.ProductForm;
import com.example.springwbtask.Form.TaskForm;
import com.example.springwbtask.Record.ProductRecord;
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



    //商品詳細画面
    @GetMapping("/detail/{id}")
    public String findId(@PathVariable("id") int id, Model model){
        var result = model.addAttribute("product", taskService.findById(id));
        return "detail";
    }

    //商品の更新
    @GetMapping("/updateInput/{id}")
    public String up(@ModelAttribute("TaskForm") ProductForm productForm, int id, Model model){
        productForm = new ProductForm();
        var result = taskService.findById(id);
        productForm.setImage_path(result.image_path());
        productForm.setProduct_id(result.product_id());
        productForm.setPname(result.pname());
        productForm.setPrice(result.price());
        productForm.setCname(result.cname());
        productForm.setDescription(result.description());

        return "updateInput";
    }

    @RequestMapping(value = "/updateInput/{id}", params = "updateInput",method = RequestMethod.POST)
    public String update(@ModelAttribute("ProductForm") ProductForm productForm,
                         @PathVariable("id") int id,
                         Model model){
        String image_path = productForm.getImage_path();
        String product_id = productForm.getProduct_id();
        String pname = productForm.getPname();
        int price = productForm.getPrice();
        String cname = productForm.getCname();
        String description = productForm.getDescription();
        ProductRecord record = new ProductRecord(image_path,product_id,pname,price,cname,description,id);

        taskService.update(record);
        return "redirect:/menu";

    }

    //削除
    @GetMapping("/detail")
    public String delete(@PathVariable("id") int id){
        var record = taskService.delete(id);
        System.out.println("エラー");
        return "redirect:/menu";
    }

    //権限による制御

    //ログアウト
}
