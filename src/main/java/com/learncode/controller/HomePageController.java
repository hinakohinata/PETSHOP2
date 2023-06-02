package com.learncode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

//import com.example.asm.domain.Category;
//import com.example.asm.domain.Products;
//import com.example.asm.service.CategoryService;
//import com.example.asm.service.ProductService;

@Controller
@RequestMapping("")
public class HomePageController {
//
//    @Autowired
//    ProductService productService;
//
//    @Autowired
//    CategoryService categoryService;
//
//    @ModelAttribute("categorys")
//    public List<Category> listCategories() {
//        List<Category> categories = categoryService.findAll();
//        return categories;
//    }
//
//    @ModelAttribute("products")
//    public List<Products> listProduct() {
//        List<Products> products = productService.findAll();
//        return products;
//    }
    

    @GetMapping("index.html")
    public String homePage() {
        return "index";
    }
    @GetMapping("about.html")
    public String about(Model model) {
        return "about";
    }
    @GetMapping("blog.html")
    public String blog(Model model) {
        return "blog";
    }
    @GetMapping("contact.html")
    public String contact(Model model) {
        return "contact";
    }
    @GetMapping("detail.html")
    public String detail(Model model) {
        return "detail";
    }
    @GetMapping("price.html")
    public String price(Model model) {
        return "price";
    }
    @GetMapping("product.html")
    public String product(Model model) {
        return "product";
    }
    @GetMapping("service.html")
    public String service(Model model) {
        return "service";
    }
    @GetMapping("team.html")
    public String team(Model model) {
        return "team";
    }
    @GetMapping("testimonial.html")
    public String testimonial(Model model) {
        return "testimonial";
    }

    @GetMapping("shop")
    public String shop(Model model) {
        return "shop";
    }

    @GetMapping("checkout")
    public String checkout(Model model) {
        return "checkout";
    }

}
