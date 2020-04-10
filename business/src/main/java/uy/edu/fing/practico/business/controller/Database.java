package uy.edu.fing.practico.business.controller;


import uy.edu.fing.practico.business.models.Resource;
import uy.edu.fing.practico.business.models.ResourceType;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

@Startup
@Singleton
public class Database {
    private HashMap<Integer, Resource> resourcesMap = new HashMap<>();
    private HashMap<Integer, ResourceType> resourceTypesMap = new HashMap<>();
    private Integer resourceId = 0;
    private Integer resourceTypeId = 0;

    public HashMap<Integer, Resource> getResourcesMap() {
        return resourcesMap;
    }

    public HashMap<Integer, ResourceType> getResourceTypesMap() {
        return resourceTypesMap;
    }

    @PostConstruct
    public void init() {
        ResourceType resourceType = new ResourceType();
        resourceType.setName("Resource 1");
        resourceType.setDescription("Description 1");
        resourceType.setId(getResourceTypeId());
        resourceType.setReferencePrice((double) 50);

        ResourceType resourceType2 = new ResourceType();
        resourceType2.setDescription("Description 2");
        resourceType2.setName("Resource 2");
        resourceType2.setId(getResourceTypeId());
        resourceType2.setReferencePrice((double) 70);

        this.resourceTypesMap.put(resourceType.getId(), resourceType);
        this.resourceTypesMap.put(resourceType2.getId(), resourceType2);

        for (int i = 0; i < 10; i++) {
            double randomDouble = ThreadLocalRandom.current().nextDouble(100, 1000);
            int randomInt = ThreadLocalRandom.current().nextInt(100, 1000);
            Resource resource = new Resource();
            resource.setCode("code-" + randomInt);
            resource.setQuantity(randomInt);
            resource.setUnitPrice(randomDouble);
            resource.setId(getResourceId());
            if (i % 2 == 0) {
                resource.setResourceType(resourceType);
            } else {
                resource.setResourceType(resourceType2);
            }
            resourcesMap.put(resource.getId(), resource);
        }
    }

    public Integer getResourceId() {
        resourceId++;
        return resourceId;
    }

    public Integer getResourceTypeId() {
        resourceTypeId++;
        return resourceTypeId;
    }
}
