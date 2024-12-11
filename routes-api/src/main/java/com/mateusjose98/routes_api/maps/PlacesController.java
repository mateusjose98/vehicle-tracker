package com.mateusjose98.routes_api.maps;

import com.google.maps.model.GeocodingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/places")
public class PlacesController {

    private final PlacesService placesService;

    public PlacesController(PlacesService placesService) {
        this.placesService = placesService;
    }

    @GetMapping
    public GeocodingResult[] findPlaces(String text) {
       return  placesService.findPlaces(text);
    }
}
