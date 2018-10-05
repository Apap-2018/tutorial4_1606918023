package com.apap.tutorial4.service;

import java.util.Date;

import com.apap.tutorial4.model.FlightModel;

/*
 * FlightService
 */
public interface FlightService {
	void addFlight(FlightModel flight);
	void deleteFlight(FlightModel flight);
	void updateFlight(FlightModel flight, Long id);
	FlightModel getFlightById(Long id);
}