package uy.edu.fing.practico.dto.mapper;

import uy.edu.fing.practico.dto.ResourceTypeDto;
import uy.edu.fing.practico.models.ResourceType;

import javax.ejb.Stateless;

public class ResourceTypeMapper {
    public static ResourceTypeDto toResourceTypeDto(ResourceType resourceType) {
        return new ResourceTypeDto();
    }
}
