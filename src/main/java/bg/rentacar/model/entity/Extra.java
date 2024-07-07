package bg.rentacar.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "extras")
public class Extra extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal price;

    @OneToMany(mappedBy = "extra")
    private List<Order> orders;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> rents) {
        this.orders = rents;
    }

}
