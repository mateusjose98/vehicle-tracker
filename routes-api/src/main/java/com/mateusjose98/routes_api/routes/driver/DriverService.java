package com.mateusjose98.routes_api.routes.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverService {


    private final RouteDriverRepository routeDriverRepository;

    @Autowired
    public DriverService(RouteDriverRepository routeDriverRepository) {
        this.routeDriverRepository = routeDriverRepository;
    }

    public RouteDriver processRoute(String routeId, double lat, double lng) {

        RouteDriver existingRouteDriver = routeDriverRepository.findByRouteId(routeId);

        if (existingRouteDriver == null) {

            RouteDriver newRouteDriver = new RouteDriver();
            newRouteDriver.setRouteId(routeId);

            Point newPoint = new Point();
            newPoint.setLocation(new Coord(lat, lng));
            newRouteDriver.setPoints(new ArrayList<>(List.of(newPoint)));

            return routeDriverRepository.save(newRouteDriver);
        } else {

            Point newPoint = new Point();
            newPoint.setLocation(new Coord(lat, lng));

            if (existingRouteDriver.getPoints() == null) {
                existingRouteDriver.setPoints(new ArrayList<>());
            }
            existingRouteDriver.getPoints().add(newPoint);

            return routeDriverRepository.save(existingRouteDriver);
        }
    }

}
