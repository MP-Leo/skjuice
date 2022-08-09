package br.com.skjuice.services;

import br.com.skjuice.entities.Product;
import br.com.skjuice.entities.ProductPage;
import br.com.skjuice.entities.ProductRequisition;
import br.com.skjuice.repositories.ProductCriteriaRepositorie;
import br.com.skjuice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCriteriaRepositorie productCriteriaRepositorie;

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
    public Page<Product> findUsingFilters(ProductPage productPage, ProductRequisition productSearchCriteria) {
        return productCriteriaRepositorie.findAllWithFilters(productPage, productSearchCriteria);
    }

    @Override
    public Product createProduct(String name, String description, String flavor, Double price) {
        return null;
    }

}
