package com.project.flightbooking.integration;


import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;

import java.net.URI;

/**
 * Created by mrflick72 on 06/06/17.
 */

abstract class AbstractIntegrationService {

    RequestEntity newRequestEntity(URI uri){
        return RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
    }

}