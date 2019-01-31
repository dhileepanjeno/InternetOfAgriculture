package com.google.firebase.firebase_admin;
import com.google.firebase.database.IgnoreExtraProperties;
@IgnoreExtraProperties
public class SensorData {
	public double light;
	public double humidity;
	public double temperature;
	public double time_stamp;
	public SensorData() {

	}
	public SensorData(double time_stamp,double light,double temperature,double humidity) {
		this.time_stamp = time_stamp;
		this.light = light;
		this.temperature = temperature;
		this.humidity = humidity;
	}
}
