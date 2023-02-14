package com.sda.OnlineShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @GetMapping("/addProduct")
    public String addProductGet() {
        return "addProduct";            // here we add the view name from spring MVC
    }

    @PostMapping("/addProduct")
    public String addProductPost() {
        System.out.println("addProduct was called!");
        return "addProduct";
    }
}
