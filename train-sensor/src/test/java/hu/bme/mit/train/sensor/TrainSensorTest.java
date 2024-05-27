package hu.bme.mit.train.sensor;

import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

import static org.mockito.Mockito.*;

public class TrainSensorTest {

	private TrainController controller;
	private TrainUser user;
    private TrainSensorImpl trainSensorImpl;

    @Before
    public void before() {
        controller = mock(TrainController.class);
	    user = mock(TrainUser.class);
	    trainSensorImpl = new TrainSensorImpl(controller, user);
    }

    @Test
    public void setSpeedLimit_belowZero_alarm() {
        trainSensorImpl.overrideSpeedLimit(-1);

        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void setSpeedLimit_too_low_value_alarm() {
        trainSensorImpl.overrideSpeedLimit(1);

        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void setSpeedLimit_no_alarm() {
        trainSensorImpl.overrideSpeedLimit(3);

        verify(user, times(0)).setAlarmState(true);
    }

    @Test
    public void setSpeedLimit_above500_alarm() {
        trainSensorImpl.overrideSpeedLimit(501);

        verify(user, times(1)).setAlarmState(true);
    }
}
