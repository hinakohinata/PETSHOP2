package com.learncode.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learncode.Entity.Order;
import com.learncode.Repository.OrderDAO;

@Controller
@RequestMapping("admin")
public class AdminHomeController {

	private final OrderDAO orderDAO;

    AdminHomeController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
    @GetMapping("adminOrder")
    public String acc(Model model) {
    	List<Order> orders = orderDAO.findAll();
        model.addAttribute("orders", orders);
        Order order = new Order();
        model.addAttribute("order", order);
		model.addAttribute("up", false);
        return "admin/adminOrder";
    }
}
