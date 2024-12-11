package com.mateusjose98.routes_api.directions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;
import org.springframework.stereotype.Service;

@Service
public class DirectionsService {

    private final GeoApiContext geoApiContext;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DirectionsService(GeoApiContext geoApiContext) {
        this.geoApiContext = geoApiContext;
    }

    public JsonNode getDirections(String originId,
                                String destinationId) {
        try {
            var response = new DirectionsApiRequest(geoApiContext)
                    .mode(TravelMode.DRIVING)
                    .origin("place_id:" + originId)
                    .destination("place_id:" + destinationId)
                    .await();

            ObjectNode jsonNode = (ObjectNode) objectMapper.readTree(objectMapper.writeValueAsString(response));
            jsonNode.set("request", construirRequest(originId, destinationId, response));
            return jsonNode;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar direções", e);
        }}

    private JsonNode construirRequest(String o, String d, DirectionsResult response) {
        ObjectNode request = objectMapper.createObjectNode();
        request.put("mode", "DRIVING");
        ObjectNode origin = objectMapper.createObjectNode();
        origin.put("place_id", o);
        request.set("origin", origin);
        double lat = response.routes[0].legs[0].steps[0].startLocation.lat;
        double lng = response.routes[0].legs[0].steps[0].startLocation.lng;
        origin.set("location", objectMapper.createObjectNode().put("lat", lat).put("lng", lng));

        ObjectNode destination = objectMapper.createObjectNode();
        destination.put("place_id", d);
        request.set("destination",destination);
        double lat2 = response.routes[0].legs[0].steps[0].endLocation.lat;
        double lng2 = response.routes[0].legs[0].steps[0].endLocation.lng;
        destination.set("location", objectMapper.createObjectNode().put("lat", lat2).put("lng", lng2));

        return request;

    }
}
