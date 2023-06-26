package com.learncode.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learncode.Entity.Category;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Long> {
    // Các phương thức truy vấn dữ liệu khác
}

