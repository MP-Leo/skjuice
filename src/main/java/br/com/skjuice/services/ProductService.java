package br.com.skjuice.services;

import br.com.skjuice.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findByProductId(Long id);

    Product createProduct(String name, String description, String flavor, Double price);
}
