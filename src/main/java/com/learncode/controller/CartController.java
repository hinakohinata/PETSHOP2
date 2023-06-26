package com.learncode.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learncode.Entity.Account;
import com.learncode.Entity.Cart;
import com.learncode.Entity.Product;
import com.learncode.Repository.AccountDAO;
import com.learncode.Repository.CartDAO;
import com.learncode.Repository.ProductDAO;

@Controller
//@RequestMapping("cart")
public class CartController {
	@Autowired
	HttpSession session;
	@Autowired
	CartDAO cartDAO;
	@Autowired
	ProductDAO productDAO;
	@Autowired
	AccountDAO accountDAO;

	@RequestMapping("add-to-cart/{id}")
	public String Add(@PathVariable("id") Long id, Model model) {
		
		Long cusId = (Long) session.getAttribute("userId");
		String cusName = (String) session.getAttribute("username");
		System.out.println(cusId + "  " + id);
		
		Cart cart1 = cartDAO.findByCustomerIdAndProductId(cusId, id);
		
		
		Account cus = (Account) accountDAO.findByUsername(cusName);
		Product prod = productDAO.getById(id);
		System.out.println("check "+cart1 == null+"  ");
		if (cart1 == null) {
			Cart newCart = new Cart(cus, prod, 1);
			cartDAO.save(newCart);
		}else {
			System.out.println("sửa");
			System.out.println("check1 "+cart1 == null+"  ");
			Cart newCart = new Cart(cart1.getId(),cus, prod, cart1.getQuantity()+1);
			cartDAO.save(newCart);
		}
//        CartDto carts = (CartDto) httpSession.getAttribute("cart");
		return "redirect:/cart";
	}

	@RequestMapping("remove-to-cart/{id}")
	public String remove(@PathVariable("id") Long id, Model model) {
		try {
			cartDAO.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "redirect:/cart";
	}

	@RequestMapping("del-to-cart/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		
		Cart cart1 = cartDAO.getById(id);
		
		if (cart1.getQuantity()==1) {
			cartDAO.delete(cart1);
		}else {
			System.out.println("sửa");
			System.out.println("check1 "+cart1 == null+"  ");
			Cart newCart = new Cart(cart1.getId(),cart1.getCustomer(), cart1.getProduct(), cart1.getQuantity()-1);
			cartDAO.save(newCart);
		}
//        CartDto carts = (CartDto) httpSession.getAttribute("cart");
		return "redirect:/cart";
	}
}
