package com.example.springwbtask.Controller;


import com.example.springwbtask.Form.TaskForm;
import com.example.springwbtask.Record.UserRecord;
import com.example.springwbtask.Service.TaskService;
import com.example.springwbtask.Service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {

    @Autowired
    private HttpSession session;

    @Autowired
    TaskServiceImpl taskService;

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
        return "/menu";
    }
}
