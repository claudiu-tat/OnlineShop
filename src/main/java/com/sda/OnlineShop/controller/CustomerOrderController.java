package com.sda.OnlineShop.controller;

import com.sda.OnlineShop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerOrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/confirmation")
    public String launchOrderPost(Authentication authentication) {

        orderService.launchOrder(authentication.getName());
        return "confirmation";
    }
}
