package com.learncode.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learncode.Entity.Category;
import com.learncode.Entity.Order;
import com.learncode.Entity.Product;
import com.learncode.Repository.CategoryDAO;
import com.learncode.Repository.OrderDAO;
import com.learncode.Repository.ProductDAO;

@Controller
@RequestMapping("admin")
public class AdminPrdController {

	@Autowired
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
	@PostMapping("/products/create")
	public String createCate(@Validated @ModelAttribute("product") Product product, BindingResult result, Model model) {
		System.out.println("save");
		if (result.hasErrors()) {
			model.addAttribute("message", "Vui lòng sửa các lỗi sau:" + result);
		} else {
			model.addAttribute("message", "Chúc mừng, bạn đã nhập đủ");
			productDAO.save(product);
		}
		return "redirect:/admin/adminProd";
	}
	
	@GetMapping("/products/search")
	public String searchCate(@RequestParam("keyword") String keyword, Model model) {
		System.out.println(keyword);
		model.addAttribute("x", keyword);
    	List<Product> products = productDAO.findByNameContaining(keyword);
        model.addAttribute("products", products);
        Product product = new Product();
    	List<Category> categories = categoryDAO.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
		model.addAttribute("up", false);
		return "admin/adminPro";
	}
	@PostMapping("/adminPro/update")
	public String updateProd(@Valid  Product item, Model model) {
		productDAO.save(item);
		return "redirect:/admin/adminProd";
	}
	@GetMapping("/adminPro/delete/{id}")
	public String deleteProd(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
		try {
			productDAO.deleteById(Long.parseLong(id));
		} catch (Exception e) {
			System.out.println("Lỗi ");
//			model.addAttribute("message", "Không thể xóa");
	        redirectAttributes.addFlashAttribute("message", "Không thể xóa");

		}

		return "redirect:/admin/adminProd";
	}
}
