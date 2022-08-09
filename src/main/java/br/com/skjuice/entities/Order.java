package br.com.skjuice.entities;

import br.com.skjuice.entities.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "item_amount")
    private Integer itemAmount;


    //CONSTRUCTOS
    public Order() {
    }

    public Order(Purchase purchase, Product product, Integer itemAmount) {
        this.product = product;
        this.itemAmount = itemAmount;
    }

    //GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(Integer itemAmount) {
        this.itemAmount = itemAmount;
    }
}
