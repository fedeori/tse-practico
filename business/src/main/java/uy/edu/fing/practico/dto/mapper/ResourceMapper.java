package uy.edu.fing.practico.dto.mapper;

import uy.edu.fing.practico.models.ResourceType;

public class ResourceMapper {
    public static ResourceType toResourceDto(ResourceType resourceType) {
        return new ResourceType();
    }
}
