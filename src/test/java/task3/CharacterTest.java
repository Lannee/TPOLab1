package task3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CharacterTest {

    private Character character;
    private Motor motor;
    private Spaceship spaceship;
    private Space space;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        character = new Character("Alice");
        motor = new Motor(new Context(new Void("Test Location")));
        spaceship = new Spaceship(motor);
        space = new Space("Лунная орбита");
    }

    @Test
    void testConstructor() {
        assertEquals("Alice", character.getName());
    }

    @Test
    void testEnterSpaceship() {
        character.enterSpaceship(spaceship);

        assertTrue(spaceship.isOnBoard(character));
        assertEquals(1, spaceship.getOnBoard().size());
    }

    @Test
    void testGetName() {
        assertEquals("Alice", character.getName());
    }
}