package com.ecom.proj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecom.proj.global.GlobalData;
import com.ecom.proj.model.Product;
import com.ecom.proj.service.ProductSerivce;

@Controller
public class CartController {
    @Autowired
    ProductSerivce productSerivce;

    // add product to cart
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable Long id) {
        GlobalData.cart.add(productSerivce.getProductById(id).get());
        return "redirect:/shop";
    }

    // open catr view all
    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);
        return "cart";
    }

    //remove item from cart
    @GetMapping("/cart/removeItem/{index}")
    public String cartRemoveItem(@PathVariable int index) {
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }

    //do checkout
    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        return "checkout";
    }

}
