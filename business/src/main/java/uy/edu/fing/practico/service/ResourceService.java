package uy.edu.fing.practico.service;

import uy.edu.fing.practico.dto.ResourceDto;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ResourceService {
    public List<ResourceDto> listResource();

    public List<ResourceDto> listResource(Integer resourceTypeId);
}
