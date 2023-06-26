package com.learncode.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learncode.Entity.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long> {
	// Các phương thức truy vấn dữ liệu khác
	List<Product> findByNameContaining(String keyword);
}
