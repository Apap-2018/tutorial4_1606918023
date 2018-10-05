package com.apap.tutorial4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.FlightService;
import com.apap.tutorial4.service.PilotService;

/*
 * FlightController
 */
@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value="/flight/add/{licenseNumber}", method=RequestMethod.GET)
	private String add(@PathVariable(value="licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		model.addAttribute("flight", flight);
		return "addFlight";
	}
	
	@RequestMapping(value="/flight/add", method=RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
	
	@RequestMapping(value="/flight/delete", method=RequestMethod.GET)
	private String delete(@RequestParam(value="id") Long id, Model model) {
		FlightModel flight = flightService.getFlightById(id);
		flightService.deleteFlight(flight);
		return "delete";
	}
	
	@RequestMapping(value="/flight/update", method=RequestMethod.GET)
	private String update(@RequestParam(value="id") Long id, Model model) {
		FlightModel archive = flightService.getFlightById(id);
		model.addAttribute("flightId", archive.getId());
		model.addAttribute("flightFlightNumber", archive.getFlightNumber());
		model.addAttribute("flightOrigin", archive.getOrigin());
		model.addAttribute("flightDestination", archive.getDestination());
		model.addAttribute("flightTime", archive.getTime());
		model.addAttribute("flight", archive);
		return "updateFlight";
	}
	
	@RequestMapping(value="/flight/update", method=RequestMethod.POST)
	private String updateFlight(@ModelAttribute FlightModel flight) {
		flightService.updateFlight(flight, flight.getId());
		return "update";
	}
	
	@RequestMapping(value="/flight/view")
	private String viewAllPilot(@RequestParam(value = "id") Long id, Model model) {
		FlightModel flight = flightService.getFlightById(id);
		model.addAttribute("flight", flight);
		model.addAttribute("pilotName", flight.getPilot().getName());
		model.addAttribute("pilotLicenseNumber", flight.getPilot().getLicenseNumber());
		model.addAttribute("pilotFlyHour", flight.getPilot().getFlyHour());
		return "viewall-flight";
	}
	
}
