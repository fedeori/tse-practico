package uy.edu.fing.practico.dto;

import java.util.List;

public class ResourceTypeDto {
    private Integer id;
    private String name;
    private Double price;
    private Double referencePrice;
    private List<ResourceDto> resourceDtos;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(Double referencePrice) {
        this.referencePrice = referencePrice;
    }

    public List<ResourceDto> getResourceDtos() {
        return resourceDtos;
    }

    public void setResourceDtos(List<ResourceDto> resourceDtos) {
        this.resourceDtos = resourceDtos;
    }
}
