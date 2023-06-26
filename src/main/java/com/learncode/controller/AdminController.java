package com.learncode.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learncode.Entity.Account;
import com.learncode.Entity.Category;
import com.learncode.Repository.AccountDAO;
import com.learncode.Repository.CategoryDAO;


@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	private final AccountDAO accountDAO;

	@Autowired
	private final CategoryDAO categoryDAO;
	


	@RequestMapping("/admin/index.html")
	public String mngAcc() {
		return "index1";
	}

	@GetMapping("adminAcc")
	public String acc(Model model) {
		List<Account> accounts = accountDAO.findAll();
		model.addAttribute("accounts", accounts);
		Account account = new Account();
		model.addAttribute("account", account);
		model.addAttribute("up", false);
		return "admin/admin";
	}

	@GetMapping("adminCate")
	public String Cate(Model model) {
		List<Category> categories = categoryDAO.findAll();
		model.addAttribute("categories", categories);
		Category category = new Category();
		model.addAttribute("category", category);
		model.addAttribute("up", false);
		return "admin/adminCate";
	}

	@GetMapping("adminAcc/{id}")
	public String acc1(Model model, @PathVariable("id") String id) {
		List<Account> accounts = accountDAO.findAll();
		model.addAttribute("accounts", accounts);
		Account acc = accountDAO.findById(Long.parseLong(id)).orElse(null);
		System.out.println(acc.getUsername());
		model.addAttribute("account", acc);
		model.addAttribute("up", true);
		return "admin/admin";
	}

	@GetMapping("adminCate/{id}")
	public String cate1(Model model, @PathVariable("id") String id) {
		List<Category> categories = categoryDAO.findAll();
		model.addAttribute("categories", categories);
		Category cate = categoryDAO.findById(Long.parseLong(id)).orElse(null);
		model.addAttribute("category", cate);
		model.addAttribute("up", true);
		return "admin/adminCate";
	}

	public AdminController(AccountDAO accountDAO, CategoryDAO categoryDAO) {
		this.accountDAO = accountDAO;
		this.categoryDAO = categoryDAO;
	}

	@PostMapping("/accounts/create")
	public String createAccount(@Validated @ModelAttribute("account") Account account, BindingResult result,
			Model model) {
		System.out.println("save");
		if (result.hasErrors()) {
			model.addAttribute("message", "Vui lòng sửa các lỗi sau:" + result);
		} else {
			model.addAttribute("message", "Chúc mừng, bạn đã nhập đủ");
			accountDAO.save(account);
		}
		return "redirect:/admin/adminAcc";
	}

	@PostMapping("/cagerogies/create")
	public String createCate(@Validated @ModelAttribute("category") Category cate, BindingResult result, Model model) {
		System.out.println("save");
		if (result.hasErrors()) {
			model.addAttribute("message", "Vui lòng sửa các lỗi sau:" + result);
		} else {
			model.addAttribute("message", "Chúc mừng, bạn đã nhập đủ");
			categoryDAO.save(cate);
		}
		return "redirect:/admin/adminCate";
	}

	@PostMapping("/adminAcc/update")
	public String update(@Valid Account item, Model model) {
		System.out.println(item.getId()+"abc"+item.getUsername());
//		accountDAO.save(item);
		return "redirect:/admin/adminAcc";
	}

	@PostMapping("/adminCate/update")
	public String updateCate(@Valid Category item, Model model) {
		categoryDAO.save(item);
		return "redirect:/admin/adminCate";
	}

	@GetMapping("/adminAcc/delete/{id}")
	public String delete(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
		try {
			accountDAO.deleteById(Long.parseLong(id));
		} catch (Exception e) {
			System.out.println("Lỗi ");
//			model.addAttribute("message", "Không thể xóa");
	        redirectAttributes.addFlashAttribute("message", "Không thể xóa");
		}

		return "redirect:/admin/adminAcc";
	}

	@GetMapping("/adminCate/delete/{id}")
	public String deleteCate(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
		try {
			categoryDAO.deleteById(Long.parseLong(id));
		} catch (Exception e) {
			System.out.println("Lỗi ");
//			model.addAttribute("message", "Không thể xóa");
	        redirectAttributes.addFlashAttribute("message", "Không thể xóa");

		}

		return "redirect:/admin/adminCate";
	}

}
