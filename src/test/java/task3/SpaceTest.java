package task3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class SpaceTest {

    @Test
    void testConstructor() {
        Space space = new Space("Test Space");
        assertEquals("Test Space", space.getDescription());
        assertEquals(0, space.getObjectCount());
    }

    @Test
    void testGetObjectCount() {
        Space space = new Space("Test Space");
        assertEquals(0, space.getObjectCount());

        space.contain(new Object());
        assertEquals(1, space.getObjectCount());

        space.contain(new Object());
        assertEquals(2, space.getObjectCount());
    }

    @Test
    void testGetDescription() {
        Space space = new Space("Test Space");
        assertEquals("Test Space", space.getDescription());

        Space anotherSpace = new Space("Another Space");
        assertEquals("Another Space", anotherSpace.getDescription());
    }

    @Test
    void testContain() {
        Space space = new Space("Test Space");
        Object obj1 = new Object();
        Object obj2 = new Object();

        space.contain(obj1);
        assertEquals(1, space.getObjectCount());

        space.contain(obj2);
        assertEquals(2, space.getObjectCount());
    }
}