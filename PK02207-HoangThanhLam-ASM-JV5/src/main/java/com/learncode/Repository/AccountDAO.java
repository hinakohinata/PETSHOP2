package com.learncode.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learncode.Entity.Account;

@Repository
public interface AccountDAO extends JpaRepository<Account, Long> {
    // Các phương thức truy vấn dữ liệu khác
}

