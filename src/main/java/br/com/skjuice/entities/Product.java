package br.com.skjuice.entities;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;


    // CONSTRUCTOS

    public Product() {
    }

    public Product(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // CONTRUCTORS AND EQUALS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId()) && Objects.equals(getName(), product.getName()) && Objects.equals(getDescription(), product.getDescription()) && Objects.equals(getPrice(), product.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getPrice());
    }

    // GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
