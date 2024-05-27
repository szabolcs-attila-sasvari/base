package hu.bme.mit.train.system;

import hu.bme.mit.train.controller.TrainControllerImpl;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.sensor.TrainSensorImpl;
import hu.bme.mit.train.user.TrainUserImpl;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TrainSystem {

	private static TrainController controller = new TrainControllerImpl();
	private static TrainUser user = new TrainUserImpl(controller);
	private TrainSensor sensor = new TrainSensorImpl(controller, user);


	public static void main(String[] args) {
        // Create a single-threaded scheduled executor
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        // Define your task as a Runnable
        Runnable yourTask = () -> {
            // Your task logic here
            user.overrideJoystickPosition(user.getJoystickPosition() + 1);
			controller.followSpeed();
        };

        // Schedule the task to run every second
        scheduler.scheduleAtFixedRate(yourTask, 0, 1, TimeUnit.SECONDS);

        // Shutdown the scheduler when no longer needed
        scheduler.shutdown();
    }



	public TrainController getController() {
		return controller;
	}

	public TrainSensor getSensor() {
		return sensor;
	}

	public TrainUser getUser() {
		return user;
	}

}
