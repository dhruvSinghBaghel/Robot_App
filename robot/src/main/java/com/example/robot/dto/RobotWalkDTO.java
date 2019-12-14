package com.example.robot.dto;

public class RobotWalkDTO {
	private double robotWalkPerKM;
	private double robotCarryWeight;

	public RobotWalkDTO() {
	}

	public RobotWalkDTO(double robotWalkPerKM, double robotCarryWeight) {
		super();
		this.robotWalkPerKM = robotWalkPerKM;
		this.robotCarryWeight = robotCarryWeight;
	}

	public double getRobotWalkPerKM() {
		return robotWalkPerKM;
	}

	public void setRobotWalkPerKM(double robotWalkPerKM) {
		this.robotWalkPerKM = robotWalkPerKM;
	}

	public double getRobotCarryWeight() {
		return robotCarryWeight;
	}

	public void setRobotCarryWeight(double robotCarryWeight) {
		this.robotCarryWeight = robotCarryWeight;
	}

}
