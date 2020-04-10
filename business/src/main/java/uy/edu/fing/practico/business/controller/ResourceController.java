package uy.edu.fing.practico.business.controller;

import uy.edu.fing.practico.business.dto.ResourceDto;
import uy.edu.fing.practico.business.dto.mapper.ResourceMapper;
import uy.edu.fing.practico.business.models.Resource;
import uy.edu.fing.practico.business.models.ResourceType;
import uy.edu.fing.practico.business.service.ResourceServiceRemote;
import uy.edu.fing.practico.business.service.ResourceService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ResourceController implements ResourceServiceRemote, ResourceService {
    @EJB
    private Database database;

    @Override
    public void addResource(ResourceDto resourceDto, Integer resourceTypeId) throws InvalidParameterException{
        ResourceType resourceType = database.getResourceTypesMap().get(resourceTypeId);
        if (resourceType == null) {
            throw new InvalidParameterException("ResourceType not found");
        }
        if (resourceType.getReferencePrice() * 1.10 < resourceDto.getUnitPrice()) {
            throw new InvalidParameterException("To expensive, over 10% over ref. price.");
        }

        Resource resource = new Resource();
        resource.setCode(resourceDto.getCode());
        resource.setQuantity(resourceDto.getQuantity());
        resource.setUnitPrice(resourceDto.getUnitPrice());
        resource.setResourceType(resourceType);
        resource.setId(database.getResourceId());
        database.getResourcesMap().put(resource.getId(), resource);
    }

    @Override
    public List<ResourceDto> listResource() {
        List<ResourceDto> resultList = new ArrayList<>();
        database.getResourcesMap().forEach((integer, resource) -> {
            resultList.add(ResourceMapper.toResourceDto(resource));
        });
        return resultList;
    }

    @Override
    public List<ResourceDto> listResource(Integer resourceTypeId) {
        List<ResourceDto> resultList = new ArrayList<>();
        database.getResourcesMap().forEach((integer, resource) -> {
            if (resource.getResourceType().getId().equals(resourceTypeId)) {
                resultList.add(ResourceMapper.toResourceDto(resource));
            }
        });
        return resultList;
    }
}
