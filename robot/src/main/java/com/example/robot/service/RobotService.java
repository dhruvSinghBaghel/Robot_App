package com.example.robot.service;

import com.example.robot.dto.RobotWalkDTO;


public interface RobotService {
	public String getRemainBattery(RobotWalkDTO dto);
	public String scanBarCode();
}
