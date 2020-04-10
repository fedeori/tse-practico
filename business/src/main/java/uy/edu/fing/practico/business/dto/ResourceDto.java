package uy.edu.fing.practico.business.dto;

import java.io.Serializable;

public class ResourceDto implements Serializable {
    private Integer id;
    private String code;
    private Double unitPrice;
    private Integer quantity;

    public String getResourceTypeName() {
        return resourceTypeName;
    }

    public void setResourceTypeName(String resourceTypeName) {
        this.resourceTypeName = resourceTypeName;
    }

    private String resourceTypeName;

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

    public ResourceDto() {
    }

    @Override
    public String toString() {
        return "Codigo: " + code + "\nPrice: " + unitPrice + "\nQuantity: " + quantity;
    }
}
