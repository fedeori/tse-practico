package uy.edu.fing.practico.controller;

import uy.edu.fing.practico.dto.ResourceTypeDto;
import uy.edu.fing.practico.service.ResourceTypeService;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ResourceTypeController implements ResourceTypeService {
    @Override
    public List<ResourceTypeDto> listResourceTypes() {
        return null;
    }
}
