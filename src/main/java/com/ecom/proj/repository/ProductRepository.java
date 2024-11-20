package com.ecom.proj.repository;

import com.ecom.proj.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product,Long>{
    List<Product> findAllByCategory_Id(Integer id);
}
