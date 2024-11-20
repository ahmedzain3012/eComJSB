package com.ecom.proj.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ecom.proj.dto.ProductDTO;
import com.ecom.proj.model.Category;
import com.ecom.proj.service.CategoryService;
import com.ecom.proj.service.ProductSerivce;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String Home() {
        return "adminHome";
    }

    // ++++++++++++++++++Start categories++++++++++++++++++
    @Autowired
    CategoryService categoryService;

    // return all / list
    @GetMapping("/admin/categories")
    public String Categories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories";
    }

    // open in create mode
    @GetMapping("/admin/categories/add")
    public String CatAdd(Model model) {
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    // open in update mode
    @GetMapping("/admin/categories/update/{id}")
    public String CatUpd(@PathVariable Integer id, Model model) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "categoriesAdd";
        } else {
            return "404";
        }
    }

    // create or update
    @PostMapping("/admin/categories/add")
    public String CatAddUpd(@ModelAttribute("category") Category category) {
        categoryService.addUpdCategory(category);
        return "redirect:/admin/categories";
    }

    // delete
    @GetMapping("/admin/categories/delete/{id}")
    public String CatDel(@PathVariable Integer id) {
        categoryService.delCategory(id);
        return "redirect:/admin/categories";
    }
    // ++++++++++++++++++End categories++++++++++++++++++

    // ++++++++++++++++++Start Product++++++++++++++++++
    // return all / list
    @Autowired
    ProductSerivce productSerivce;

    @GetMapping("/admin/products")
    public String Products(Model model) {
        model.addAttribute("products", productSerivce.getAllProducts());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String ProdAdd(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "productsAdd";
    }

    // open in update mode
    @GetMapping("/admin/product/update/{id}")
    
    public String ProdUpd(@PathVariable Long id, Model model) {
        ProductDTO productDTO = productSerivce.getProductDTOById(id);
        if (productDTO.getId() != -1l) {
            model.addAttribute("productDTO", productDTO);
            model.addAttribute("categories", categoryService.getAllCategory());
            return "productsAdd";
        } else {
             return "404";
        }

    }

    // create or update
    @PostMapping("/admin/products/add")
    public String ProdAddUpd(@ModelAttribute("productDTO") ProductDTO productDTO,
            @RequestParam("productImage") MultipartFile file,
            @RequestParam("imgName") String imgName) throws IOException {
        productSerivce.addUpdProduct(productDTO, file, imgName);
        return "redirect:/admin/products";
    }

    // delete
    @GetMapping("/admin/product/delete/{id}")
    public String ProdDel(@PathVariable Long id) {
        productSerivce.delProduct(id);
        return "redirect:/admin/products";
    }
    // ++++++++++++++++++End Product++++++++++++++++++
     
}
