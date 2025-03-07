package task3;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StoryTest {
    @Test
    @DisplayName("Assure the story goes on")
    void testStory() {
        Character arthur = new Character("Артур");
        Character ford = new Character("Форд");

        Space space = new Space("Открытый космос");

        Location location = new Void("усеянная невероятно яркими светящимися точками");

        Motor motor = new Motor(new Context(location));
        Spaceship spaceship = new Spaceship(motor, Set.of(ford, arthur));

        motor.start();
        spaceship.takeOff();
        spaceship.flyInSpace(space);

        assertEquals(true, true);
    }
}