package com.sample_keygen.sample_keygen.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppRootController {

    @GetMapping("/")
    public String root(Model model) {
        return "app";
    }
}
