package com.learncode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learncode.Entity.Category;
import com.learncode.Entity.Order;
import com.learncode.Entity.Product;
import com.learncode.Repository.CategoryDAO;
import com.learncode.Repository.OrderDAO;
import com.learncode.Repository.ProductDAO;

@Controller
@RequestMapping("admin")
public class AdminPrdController {

	private final ProductDAO productDAO;
	@Autowired
	private final CategoryDAO categoryDAO;

    AdminPrdController(ProductDAO productDAO) {
        this.productDAO = productDAO;
		this.categoryDAO = null;
    }
    @GetMapping("adminProd")
    public String acc(Model model) {
    	List<Product> products = productDAO.findAll();
        model.addAttribute("products", products);
        Product product = new Product();
    	List<Category> categories = categoryDAO.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
		model.addAttribute("up", false);
        return "admin/adminPro";
    }
    @GetMapping("adminPro/{id}")
    public String cate1(Model model, @PathVariable("id") String id) {
    	List<Product> products = productDAO.findAll();
        model.addAttribute("products", products);
    	List<Category> categories = categoryDAO.findAll();
        model.addAttribute("categories", categories);
        Product product = productDAO.findById(Long.parseLong(id)).orElse(null);
        model.addAttribute("product", product);
		model.addAttribute("up", true);
        return "admin/adminPro";
    }
}
