package com.learncode.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learncode.Entity.Account;
import com.learncode.Entity.Cart;
import com.learncode.Entity.Category;
import com.learncode.Entity.Product;
import com.learncode.Repository.AccountDAO;
import com.learncode.Repository.CartDAO;
import com.learncode.Repository.CategoryDAO;
import com.learncode.Repository.ProductDAO;

//import com.example.asm.domain.Category;
//import com.example.asm.domain.Products;
//import com.example.asm.service.CategoryService;
//import com.example.asm.service.ProductService;

@Controller
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

	@Autowired
	AccountDAO accountDAO;
	@Autowired
	CartDAO cartDAO;
	@Autowired
	ProductDAO prodDAO;
	@Autowired
	CategoryDAO cateDAO;
	@Autowired
	HttpSession session;

	@GetMapping(value={"index.html",""})
	public String homePage() {
		session.setAttribute("role", "");
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
	public String product(Model model, @RequestParam(value = "p", defaultValue = "0") int p) {
		Pageable pageable = PageRequest.of(p, 6);
		Page<Product> page = prodDAO.findAll(pageable);
		model.addAttribute("page", page);
		model.addAttribute("valueP", false);
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
	@GetMapping("login")
	public String login(Model model) {
		return "login";
	}
	@GetMapping("register")
	public String register(Model model) {
		return "register";
	}

	@GetMapping("cart")
	public String cart(Model model) {

		Long cusId=(Long) session.getAttribute("userId");
        ArrayList<Cart> carts =(ArrayList<Cart>) cartDAO.findByCustomerId(cusId);
        double total=(double) 0;
		for (Cart cart : carts) {
			total+= cart.getProduct().getPrice().doubleValue()*cart.getQuantity();
		}
        model.addAttribute("carts", carts);
        model.addAttribute("total", total);
		return "cart";
	}

	@GetMapping("prod_detal/{id}")
	public String testimonial1(@PathVariable("id") Long id, Model model) {
        Product carts = prodDAO.findById(id).orElse(null);
        model.addAttribute("product", carts);
		return "prod_detal";
	}

	@GetMapping("/products/search")
	public String searchCate(@RequestParam("keyword") String keyword, Model model) {
		System.out.println(keyword);
		model.addAttribute("x", keyword);
    	List<Product> products = prodDAO.findByNameContaining(keyword);
        model.addAttribute("products", products);
		model.addAttribute("valueP", true);
		return "product.html";
	}
	@GetMapping("/products/sort")
	public String sort(@RequestParam("sort") Optional<String> field, Model model) {
		System.out.println(field);
		Sort sort = Sort.by(field.orElse("price"));
		model.addAttribute("field", field.orElse("price").toUpperCase());
		List<Product> items = prodDAO.findAll(sort);
        model.addAttribute("products", items);
        model.addAttribute("valueP", true);
		
		return "product.html";
	}
}
