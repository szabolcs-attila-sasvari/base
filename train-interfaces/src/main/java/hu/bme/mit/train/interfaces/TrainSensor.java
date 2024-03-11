package hu.bme.mit.train.interfaces;

import com.google.common.collect.Table;
import com.google.common.collect.HashBasedTable;

public interface TrainSensor {

	int getSpeedLimit();

	void overrideSpeedLimit(int speedLimit);

	public void putData(Integer time, Integer joystickPosn, Integer refSpd);

	public Integer isIncreased();
}
