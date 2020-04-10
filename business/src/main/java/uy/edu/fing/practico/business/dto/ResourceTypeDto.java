package uy.edu.fing.practico.business.dto;

import java.io.Serializable;

public class ResourceTypeDto implements Serializable {
    private Integer id;
    private String name;
    private String decription;
    private Double referencePrice;

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public ResourceTypeDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Double getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(Double referencePrice) {
        this.referencePrice = referencePrice;
    }


    @Override
    public String toString() {
        return "Id: "+id+"\nName: " + name + "\nReference Price: " + referencePrice + "\nDescription: " + decription;
    }
}
