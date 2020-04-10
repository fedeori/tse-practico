package uy.edu.fing.practico.business.service;

import uy.edu.fing.practico.business.dto.ResourceDto;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ResourceService {
    void addResource(ResourceDto resourceDto, Integer resourceTypeId) ;

    List<ResourceDto> listResource();

    List<ResourceDto> listResource(Integer resourceTypeId);
}
