package com.apap.tutorial4.controller;

import java.util.List;
import java.util.Optional;

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
import com.apap.tutorial4.repository.PilotDB;
import com.apap.tutorial4.service.PilotService;

/*
 * PilotController
 */
@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value="/pilot/add", method=RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}
	
	@RequestMapping(value="/pilot/add", method=RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping(value="/pilot/view")
	public String viewAllFlight(@RequestParam(value = "licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		List<FlightModel> pilotFlight = archive.getPilotFlight();
		model.addAttribute("pilot", archive);
		model.addAttribute("listFlight", pilotFlight);
		return "viewall-pilot";
	}
	
	@RequestMapping(value="/pilot/delete", method=RequestMethod.GET)
	private String delete(@RequestParam(value = "licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		pilotService.deletePilot(archive);
		return "delete";
	}
	
	@RequestMapping(value="/pilot/update", method=RequestMethod.GET)
	public String update(@RequestParam(value = "licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("pilotName", archive.getName());
		model.addAttribute("pilotFlyHour", archive.getFlyHour());
		model.addAttribute("pilotLicenseNumber", archive.getLicenseNumber());
		model.addAttribute("pilot", archive);
		return "updatePilot";
	}
	
	@RequestMapping(value="/pilot/update", method=RequestMethod.POST)
	private String updatePilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.updatePilot(pilot, pilot.getLicenseNumber());
		return "update";
	}
	
}
