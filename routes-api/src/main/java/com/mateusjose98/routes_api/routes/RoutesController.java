package com.mateusjose98.routes_api.routes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mateusjose98.routes_api.directions.DirectionsService;
import org.bson.Document;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/routes")
public class RoutesController {

    private final RoutesService routesService;
    private final ObjectMapper objectMapper = new ObjectMapper();


    public RoutesController(RoutesService routesService) {
        this.routesService = routesService;
    }

    @GetMapping("/{id}")
    public String getRoute(@PathVariable String id) {
        return routesService.find(id);
    }

    @PostMapping
    public Document insertRoute(@RequestBody CreateRouteDto json) throws JsonProcessingException {
       return routesService.insert(json);
    }
}
