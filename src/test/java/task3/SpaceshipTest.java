package task3;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpaceshipTest {

    private Motor motor;
    private Character character1;
    private Character character2;
    private Spaceship spaceship;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        motor = new Motor(new Context(new Void("Test Location")));
        character1 = new Character("Alice");
        character2 = new Character("Bob");
        spaceship = new Spaceship(motor);
    }

    @Test
    void testConstructorWithMotor() {
        assertNotNull(spaceship);
        assertFalse(spaceship.isFlying());
        assertTrue(spaceship.getOnBoard().isEmpty());
    }

    @Test
    void testConstructorWithMotorAndCharacters() {
        Set<Character> characters = new HashSet<>();
        characters.add(character1);
        characters.add(character2);

        Spaceship customSpaceship = new Spaceship(motor, characters);
        assertNotNull(customSpaceship);
        assertFalse(customSpaceship.isFlying());
        assertEquals(2, customSpaceship.getOnBoard().size());
        assertTrue(customSpaceship.isOnBoard(character1));
        assertTrue(customSpaceship.isOnBoard(character2));
    }

    @Test
    void testTakeOffSuccess() {
        motor.start();
        spaceship.addToBoard(character1);

        spaceship.takeOff();
        assertTrue(spaceship.isFlying());
        assertTrue(motor.isRunning());
    }

    @Test
    void testTakeOffWithMotorOff() {
        spaceship.addToBoard(character1);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> spaceship.takeOff());
        assertEquals("Нельзя взлетель с выключенным мотором", exception.getMessage());
        assertFalse(spaceship.isFlying());
    }

    @Test
    void testTakeOffWithNoCharacters() {
        motor.start();

        RuntimeException exception = assertThrows(RuntimeException.class, () -> spaceship.takeOff());
        assertEquals("Нельзя взлетель без людей на борту", exception.getMessage());
        assertFalse(spaceship.isFlying());
    }

    @Test
    void testFlyInSpaceSuccess() {
        motor.start();
        spaceship.addToBoard(character1);
        spaceship.takeOff();

        Space space = new Space("Лунная орбита");
        assertDoesNotThrow(() -> spaceship.flyInSpace(space));
    }

    @Test
    void testFlyInSpaceWithoutTakeOff() {
        Space space = new Space("Лунная орбита");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> spaceship.flyInSpace(space));
        assertEquals("Нельзя полететь в космос не взлетев", exception.getMessage());
    }

    @Test
    void testAddToBoard() {
        spaceship.addToBoard(character1);
        assertTrue(spaceship.isOnBoard(character1));
        assertEquals(1, spaceship.getOnBoard().size());
    }

    @Test
    void testIsOnBoard() {
        spaceship.addToBoard(character1);
        assertTrue(spaceship.isOnBoard(character1));
        assertFalse(spaceship.isOnBoard(character2));
    }

    @Test
    void testToString() {
        assertEquals("Космический корабль", spaceship.toString());
    }
}