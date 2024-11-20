package com.ecom.proj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecom.proj.global.GlobalData;
import com.ecom.proj.service.CategoryService;
import com.ecom.proj.service.ProductSerivce;
import com.ecom.proj.service.RoleService;

@Controller
public class HomeController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductSerivce productSerivce;
    @Autowired
    RoleService roleService;

    @GetMapping({ "/", "/home" })
    public String home(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        
        model.addAttribute("adminCheck", GlobalData.user.getFirstName());
        
        return "index";
    }

    // open view shop get all product
    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productSerivce.getAllProducts());
        return "shop";
    }

    // open view shop fet all product filter by category
    @GetMapping("/shop/category/{id}")
    public String shopCategory(Model model, @PathVariable Integer id) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productSerivce.getAllProductsByCategoryId(id));
        return "shop";
    }

    // open view product
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProd(Model model, @PathVariable Long id) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("product", productSerivce.getProductById(id).get());
        return "viewProduct";
    }

}
