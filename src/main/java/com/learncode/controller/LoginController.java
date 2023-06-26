package com.learncode.controller;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learncode.Entity.Account;
import com.learncode.Entity.Cart;
import com.learncode.Entity.Product;
import com.learncode.Repository.AccountDAO;
import com.learncode.Repository.CartDAO;
import com.learncode.Repository.ProductDAO;
import com.learncode.Service.AccountService;
import com.learncode.Service.MailService;
//import com.learncode.Service.CustomUserDetailsService;

//import com.example.asm.domain.Category;
//import com.example.asm.domain.Products;
//import com.example.asm.service.CategoryService;
//import com.example.asm.service.ProductService;

@Controller
public class LoginController {
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
	HttpSession session;

	@Autowired
	private MailService mailService;
//	@Autowired
//	private AccountService accountService;

	@GetMapping("login1")
	public String login(Model model) {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			RedirectAttributes redirectAttributes) throws MessagingException {
		System.out.println(username + password);
		Account check = accountDAO.findByUsername(username);
		if (check != null && check.getPassword().equals(password)) {
			String role = "";
			session.setAttribute("userId", check.getId());
			session.setAttribute("username", check.getUsername());
			session.setAttribute("email", check.getEmail());

			// mailService.sendEmail("truongvan6322@gmail.com", "test sendmail", "<h1> hello
			// </h1>");
			if (check.getRole().equals("ADMIN")) {
				role = "admin";
				System.err.println("adminnnnn " + check);
				session.setAttribute("role", role);
				mailService.sendAsHtml(check.getEmail(), "Hello " + check.getUsername(),
						"<div >\n" + "<p>You have successfully logged into our 'Pet shop' system. "
								+ "\".</p><p>Have a great day!</p>" + "</div>"

				);
				return "redirect:/admin/adminAcc";
			} else {
				role = "user";
				session.setAttribute("role", role);
				return "redirect:/";
			}

			// session.setAttribute("username", check.getUsername());
			// session.setAttribute("role", role);
			// if (session.getAttribute("request-url") != null) {
			// return "redirect:" + session.getAttribute("request-url");
			// }
			// return "redirect:/dashboard/home";
		} else if (check == null) {

			redirectAttributes.addFlashAttribute("error", "Username or password incorrect");
		}
		return "redirect:/login";
	}
}
