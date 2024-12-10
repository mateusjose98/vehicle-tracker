package com.mateusjose98.routes_api.maps;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PlacesService {

    public PlacesResponse findPlaces(String text) {
        log.info("Searching for places with text: {}", text);
        return new PlacesResponse();
    }
}
