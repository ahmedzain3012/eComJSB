package com.ecom.proj.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.proj.dto.ProductDTO;
import com.ecom.proj.global.GenerateUniqueFileName;
import com.ecom.proj.global.UploadDir;
import com.ecom.proj.model.Product;
import com.ecom.proj.repository.ProductRepository;

@Service
public class ProductSerivce {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public ProductDTO getProductDTOById(Long id) {
        Product product = getProductById(id).get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());

        return productDTO;
    }

    public void addUpdProduct(ProductDTO productDTO,
            @RequestParam("productImage") MultipartFile file,
            @RequestParam("imgName") String imgName) throws IOException {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setDescription(productDTO.getDescription());
        String imageUUID;
        if (!file.isEmpty()) {
            imageUUID = GenerateUniqueFileName.generateUniqueFileName() + file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(UploadDir.getUploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = imgName;
        }

        product.setImageName(imageUUID);
        productRepository.save(product);
    }

    public void delProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllProductsByCategoryId(Integer id) {
        return productRepository.findAllByCategory_Id(id);
    }

}
