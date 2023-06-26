package com.learncode.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learncode.Entity.Cart;

@Repository
public interface CartDAO extends JpaRepository<Cart, Long> {
    // Các phương thức truy vấn dữ liệu khác
    List<Cart> findByCustomerId(Long customerId);
	Cart findByCustomerIdAndProductId(Long customerId, Long productId);
}

