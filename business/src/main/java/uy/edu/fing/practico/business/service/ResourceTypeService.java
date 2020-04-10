package uy.edu.fing.practico.business.service;

import uy.edu.fing.practico.business.dto.ResourceTypeDto;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ResourceTypeService {
    List<ResourceTypeDto> listResourceTypes();
    void addResourceType(ResourceTypeDto resourceTypeDto);
}
