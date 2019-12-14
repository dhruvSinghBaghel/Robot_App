package com.example.robot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.robot.dto.RobotWalkDTO;

@Service
public class RobotServiceImpl implements RobotService {

	@Value("${robot.walk.per.battery.charge}")
	private double robotWalkKMPerBattery;

	@Value("${robot.can.cary.max.weight}")
	private double robotCanCarryMaxWeight;

	@Value("${robot.alert.when.battery.less.than}")
	private double robotBatteryLessAlert;

	@Value("${robot.battery.consumption.extra.per.kg}")
	private double robotExtraBatteryConsumpPerKg;

	@Value("${robot.scan.bar.code}")
	private boolean robotScanBarCode;

	@Value("${robot.scan.dumy.price}")
	private double robotScanBarCodePrice;

	@Override
	public String getRemainBattery(RobotWalkDTO dto) {
		String response = "";
		// Null check for DTO
		if (null == dto) {
			return "Provide JSON Messages is Null or Blank";
		}
		double robotWalkKM = dto.getRobotWalkPerKM();
		double robotCarryWeight = dto.getRobotCarryWeight();
		double batteryConsumptionPerMtr = 0;
		if (0 != robotCarryWeight) {
			// Weight validation
			response = validateWeight(robotWalkKM, robotCarryWeight);
			if (!"".equals(response)) {
				return response;
			}
			batteryConsumptionPerMtr = calculateWithWeightAndKM(robotCarryWeight);
		} else {
			if (0 == robotWalkKM) {
				return "Please provide walk KM Or walk KM can not be blanck or null";
			}
			batteryConsumptionPerMtr = calculateWithoutWeight();
		}
		response = calculateRemainBattery(batteryConsumptionPerMtr, robotWalkKM, robotCarryWeight);

		return response;
	}

	private String calculateRemainBattery(double batteryConsumption, double robotWalkKM, double robotCarryWeight) {
		int meter = 0;
		double i = 100;
		while (i >= batteryConsumption && meter < (robotWalkKM * 1000)) {
			meter = meter + 1;
			if (i - batteryConsumption < robotBatteryLessAlert) {
				printAlert("Low Battery..........");
			}
			System.out.println(
					"Robot Walked Meter =" + meter + " " + "Battery Remaining is =" + (i - batteryConsumption) + " %");
			i = i - batteryConsumption;
		}

		if (meter != robotWalkKM * 1000) {
			return "Battery is Not Sufficient to Walk " + robotWalkKM + " KM with weight " + robotCarryWeight;
		}

		return "Battery Remain is " + String.valueOf(i) + " %";

	}

	private double calculateWithoutWeight() {
		return 100 / (robotWalkKMPerBattery * 1000);
	}

	private double calculateWithWeightAndKM(double robotCarryWeight) {
		return ((100 + robotExtraBatteryConsumpPerKg) / (robotWalkKMPerBattery * 1000)) * robotCarryWeight;
	}

	private String validateWeight(double robotWalkPerKM, double robotCarryWeight) {
		if (robotCarryWeight > robotCanCarryMaxWeight) {
			return printAlert("Overweight");

		}
		return "";
	}

	private String printAlert(String alert) {

		System.out.println("Alert!!!!!" + " " + alert);
		return ("Alert!!!!!" + " " + alert);
	}

	@Override
	public String scanBarCode() {
		if (robotScanBarCode) {
			return "Price " + robotScanBarCodePrice;
		}
		return "Scan Failure";
	}

}
