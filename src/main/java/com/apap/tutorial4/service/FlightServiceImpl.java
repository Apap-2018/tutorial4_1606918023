package com.apap.tutorial4.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.repository.FlightDB;

/*
 * PilotServiceImpl
 */
@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	private FlightDB flightDb;
	
	@Override
	public void addFlight(FlightModel flight) {
		flightDb.save(flight);
	}

	@Override
	public void deleteFlight(FlightModel flight) {
		flightDb.delete(flight);
	}

	@Override
	public void updateFlight(FlightModel flight, Long id) {
		FlightModel archive = flightDb.getOne(id);
		archive.setFlightNumber(flight.getFlightNumber());
		archive.setOrigin(flight.getOrigin());
		archive.setDestination(flight.getDestination());
		archive.setTime(flight.getTime());
		flightDb.save(archive);
	}

	@Override
	public FlightModel getFlightById(Long id) {
		return flightDb.getOne(id);
	}

}
