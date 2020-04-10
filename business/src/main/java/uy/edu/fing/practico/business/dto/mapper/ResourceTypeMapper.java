package uy.edu.fing.practico.business.dto.mapper;

import uy.edu.fing.practico.business.dto.ResourceTypeDto;
import uy.edu.fing.practico.business.models.ResourceType;


public class ResourceTypeMapper {
    public static ResourceTypeDto toResourceTypeDto(ResourceType resourceType) {
        ResourceTypeDto resourceTypeDto = new ResourceTypeDto();
        resourceTypeDto.setId(resourceType.getId());
        resourceTypeDto.setName(resourceType.getName());
        resourceTypeDto.setReferencePrice(resourceType.getReferencePrice());
        resourceTypeDto.setDecription(resourceType.getDescription());
        return resourceTypeDto;
    }
}
