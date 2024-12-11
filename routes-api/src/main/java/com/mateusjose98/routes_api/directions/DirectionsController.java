package com.mateusjose98.routes_api.directions;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.maps.model.DirectionsResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/directions")
public class DirectionsController {

    private final DirectionsService directionsService;

    public DirectionsController(DirectionsService directionsService) {
        this.directionsService = directionsService;
    }
    // http://localhost:8080/directions?originId=ChIJIW1_b_CP9gcRR96jWeQCMZg&destinationId=ChIJ9U7IrIeNypIRokqHqAyvx9w
    @GetMapping
    public JsonNode getDirections(String originId, String destinationId) {
       return directionsService.getDirections(originId, destinationId);

    }


}
