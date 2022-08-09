package br.com.skjuice.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "final_price", length = 20, precision = 2)
    private BigDecimal finalPrice;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    @OneToMany
    private List<Order> orders;
    
    private Boolean approved;



    // CONSTURCTORS
    public Purchase() {
    }

    public Purchase(Client client, BigDecimal finalPrice, LocalDateTime purchaseDate) {
        this.client = client;
        this.finalPrice = finalPrice;
        this.purchaseDate = purchaseDate;
    }


    // GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    public Boolean getApproved() {
        return approved;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public LocalDateTime getOrderDate() {
        return purchaseDate;
    }

    public void setOrderDate(LocalDateTime localDateTime) {
        this.purchaseDate = localDateTime;
    }
}
