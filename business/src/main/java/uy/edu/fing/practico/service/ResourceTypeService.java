package uy.edu.fing.practico.service;

import uy.edu.fing.practico.dto.ResourceTypeDto;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ResourceTypeService {
    public List<ResourceTypeDto> listResourceTypes();
}
