package com.book_catalog_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

//  @RequestMapping(method = RequestMethod.GET, value = "/home") // <=== cara ke-1
    @GetMapping("/home") // cara kedua
    public String displayHome(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        return "home";
    }
}
