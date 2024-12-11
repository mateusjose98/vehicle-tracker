package com.mateusjose98.routes_api.routes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mateusjose98.routes_api.directions.DirectionsService;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class RoutesService {

    private final MongoTemplate mongoTemplate;
    private final DirectionsService directionsService;
    private static final String COLLECTION = "routes";
    private final ObjectMapper objectMapper = new ObjectMapper();
    public RoutesService(MongoTemplate mongoTemplate, DirectionsService directionsService) {
        this.mongoTemplate = mongoTemplate;
        this.directionsService = directionsService;
    }

    public Document insert(CreateRouteDto json) throws JsonProcessingException {
        JsonNode directions = directionsService.getDirections(json.sourceId(), json.destinationId());
        var legs = directions.get("routes").get(0).get("legs").get(0);
        var duration = legs.get("duration");
        var distance = legs.get("distance");

        ObjectNode toInsert = objectMapper.createObjectNode()
                .put("name", json.name())
                .put("sourceId", json.sourceId());
        toInsert.put("destinationId", json.destinationId());
        toInsert.set("duration", duration);
        toInsert.set("distance", distance);
        toInsert.set("direction", directions);
        Document document = Document.parse(objectMapper.writeValueAsString(toInsert));
        return mongoTemplate.save(document, COLLECTION);
    }

    public String find(String id) {
        Document document = mongoTemplate.findById(id, Document.class, COLLECTION);
        return document != null ? document.toJson() : null;
    }
}
