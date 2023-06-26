package com.learncode.Service;

import org.springframework.stereotype.Service;

import com.learncode.Entity.Product;


@Service
public interface CartService {
    public int add(Product p);

    public int remove(int id);
}
