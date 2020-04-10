package uy.edu.fing.practico.business.controller;

import uy.edu.fing.practico.business.dto.ResourceTypeDto;
import uy.edu.fing.practico.business.dto.mapper.ResourceTypeMapper;
import uy.edu.fing.practico.business.models.ResourceType;
import uy.edu.fing.practico.business.service.ResourceTypeServiceRemote;
import uy.edu.fing.practico.business.service.ResourceTypeService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ResourceTypeController implements ResourceTypeServiceRemote, ResourceTypeService {
    @EJB
    private Database database;

    @Override
    public List<ResourceTypeDto> listResourceTypes() {
        List<ResourceTypeDto> resultList = new ArrayList<>();
        database.getResourceTypesMap().forEach((integer, resource) -> {
            resultList.add(ResourceTypeMapper.toResourceTypeDto(resource));
        });
        return resultList;
    }

    @Override
    public void addResourceType(ResourceTypeDto resourceTypeDto) {
        ResourceType resourceType = new ResourceType();
        resourceType.setName(resourceTypeDto.getName());
        resourceType.setDescription(resourceTypeDto.getDecription());
        resourceType.setReferencePrice(resourceTypeDto.getReferencePrice());
        resourceType.setId(database.getResourceTypeId());
        database.getResourceTypesMap().put(resourceType.getId(), resourceType);
    }
}
