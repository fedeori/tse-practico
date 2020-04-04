package uy.edu.fing.practico.models;

import javax.persistence.*;

@Entity
public class Resource {
    @Id
    @GeneratedValue
    private Integer id;
    private String code;
    private Double unitPrice;
    private Integer quantity;
    @ManyToOne
    private ResourceType resourceType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Resource() {
    }
}
