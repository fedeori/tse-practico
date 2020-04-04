package uy.edu.fing.practico.controller;

import uy.edu.fing.practico.dto.ResourceDto;
import uy.edu.fing.practico.service.ResourceService;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ResourceController implements ResourceService {
    @Override
    public List<ResourceDto> listResource() {
        return null;
    }

    @Override
    public List<ResourceDto> listResource(Integer resourceTypeId) {
        return null;
    }
}
