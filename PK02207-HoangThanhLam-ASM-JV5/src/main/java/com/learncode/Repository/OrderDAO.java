package com.learncode.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learncode.Entity.Order;

@Repository
public interface OrderDAO extends JpaRepository<Order, Long> {
    // Các phương thức truy vấn dữ liệu khác
}

