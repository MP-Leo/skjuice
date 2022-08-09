package br.com.skjuice.controllers;

import br.com.skjuice.entities.product.Product;

import br.com.skjuice.entities.product.ProductPage;
import br.com.skjuice.entities.product.ProductRequisition;
import br.com.skjuice.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;


    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(ProductPage productPage,
                                                     ProductRequisition productSearchCriteria){

        Page<Product> productsPage = productService.findUsingFilters(productPage, productSearchCriteria);
        return ResponseEntity.ok(productsPage);
    }

}
