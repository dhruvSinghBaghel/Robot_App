package com.example.robot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.robot.dto.RobotWalkDTO;
import com.example.robot.service.RobotService;

@RestController
public class RobotController {

	@Autowired
	private RobotService robotService;

	@GetMapping("/getRemainBattery")
	public String getRemainingBattery(@RequestBody RobotWalkDTO dto) {
		String remainBattery = robotService.getRemainBattery(dto);
		return remainBattery;
	}

	@GetMapping("/scanBarCode")
	public String scanBarCode() {
		return robotService.scanBarCode();
	}
}
