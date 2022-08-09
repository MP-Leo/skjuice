package br.com.skjuice.services;

import br.com.skjuice.entities.product.Product;
import br.com.skjuice.entities.product.ProductPage;
import br.com.skjuice.entities.product.ProductRequisition;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findByProductId(Long id);

    Page<Product> findUsingFilters(ProductPage productPage, ProductRequisition productSearchCriteria);

    Product createProduct(String name, String description, String flavor, Double price);
}
