package br.com.skjuice.services;

import br.com.skjuice.entities.Product;
import br.com.skjuice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Product findByProductId(Long id) {
        return null;
    }

    @Override
    public Product createProduct(String name, String description, String flavor, Double price) {
        return null;
    }
}
