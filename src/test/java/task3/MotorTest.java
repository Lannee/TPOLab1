package task3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MotorTest {

    private Motor motor;
    private Context context;
    private Location location;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        location = new Void("Test Location");
        context = new Context(location);
        motor = new Motor(context);
    }

    @Test
    void testConstructor() {
        assertNotNull(motor);
        assertFalse(motor.isRunning());
        assertEquals(Motor.ExhaustState.ABSENT, motor.getExhaustState());
    }

    @Test
    void testStart() {
        motor.start();

        assertTrue(motor.isRunning());
        assertEquals(Motor.ExhaustState.EMMITED, motor.getExhaustState());
        assertEquals(location, motor.getExhaustState().getEmmitedLocation());
    }

    @Test
    void testSetHighRotations() {
        motor.start();
        motor.setHighRotations();

        assertEquals(Volume.ROAR, motor.getExhaustState().getVolume());
    }

    @Test
    void testSetHighRotationsWithoutStart() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> motor.setHighRotations());
        assertEquals("Нельзя увеличить обороты без запущенного двигателя", exception.getMessage());
    }

    @Test
    void testIsRunning() {
        assertFalse(motor.isRunning());
        motor.start();
        assertTrue(motor.isRunning());
    }

    @Test
    void testGetExhaustState() {
        assertEquals(Motor.ExhaustState.ABSENT, motor.getExhaustState());
        motor.start();
        assertEquals(Motor.ExhaustState.EMMITED, motor.getExhaustState());
    }

    @Test
    void testExhaustStateGetEmmitedLocation() {
        motor.start();
        assertEquals(location, motor.getExhaustState().getEmmitedLocation());
    }

    @Test
    void testExhaustStateGetEmmitedLocationForAbsentState() {
        Motor.ExhaustState state = Motor.ExhaustState.ABSENT;

        RuntimeException exception = assertThrows(RuntimeException.class, () -> state.getEmmitedLocation());
        assertEquals("Нельзя получить локацию состояния отличного от EMMITING", exception.getMessage());
    }

    @Test
    void testExhaustStateSetEmmitedLocationForAbsentState() {
        Motor.ExhaustState state = Motor.ExhaustState.ABSENT;

        RuntimeException exception = assertThrows(RuntimeException.class, () -> state.setEmmitedLocation(location));
        assertEquals("Нельзя задать локацию для состояния отличного от EMMITING", exception.getMessage());
    }

    @Test
    void testExhaustStateToString() {
        assertEquals("Отсутствующий выхлоп", Motor.ExhaustState.ABSENT.toString());
        assertEquals("Вбрасываемый выхлопной газ", Motor.ExhaustState.EMMITED.toString());
    }
}