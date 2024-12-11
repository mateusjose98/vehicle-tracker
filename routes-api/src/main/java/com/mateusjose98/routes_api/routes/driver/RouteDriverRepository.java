package com.mateusjose98.routes_api.routes.driver;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RouteDriverRepository extends MongoRepository<RouteDriver, String> {
    RouteDriver findByRouteId(String routeId);
}
