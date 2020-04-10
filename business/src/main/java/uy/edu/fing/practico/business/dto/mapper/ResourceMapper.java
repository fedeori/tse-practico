package uy.edu.fing.practico.business.dto.mapper;

import uy.edu.fing.practico.business.dto.ResourceDto;
import uy.edu.fing.practico.business.models.Resource;

public class ResourceMapper {
    public static ResourceDto toResourceDto(Resource resource) {
        ResourceDto resouceDto = new ResourceDto();
        resouceDto.setCode(resource.getCode());
        resouceDto.setId(resource.getId());
        resouceDto.setQuantity(resource.getQuantity());
        resouceDto.setUnitPrice(resource.getUnitPrice());
        resouceDto.setResourceTypeName(resource.getResourceType().getName());
        return resouceDto;
    }
}
