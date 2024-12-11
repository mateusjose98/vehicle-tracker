package com.mateusjose98.routes_api.maps;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
public class PlacesService {


    private final GeoApiContext context;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PlacesService.class);
    public PlacesService(GeoApiContext context) {
        this.context = context;
    }

    public GeocodingResult[] findPlaces(String text) {
        log.info("Searching for places with text: {}", text);
        try {
            return GeocodingApi.geocode(context, text).await();
        } catch (Exception e) {
            log.error("Error while geocoding address: {}", text, e);
            return new GeocodingResult[0];
        }
    }

}
