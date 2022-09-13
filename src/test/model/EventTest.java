package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EventTest {
    private Event e;
    private Date d;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("Added Bitcoin");   // (1)
        d = Calendar.getInstance().getTime();   // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Added Bitcoin", e.getDescription());
        assertEquals(d, e.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Added Bitcoin", e.toString());
    }

    @Test
    public void testEqualsNull() {
        Object anObject = null;
        assertFalse(e.equals(anObject));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(e.equals(new Cryptocurrency(50, "Not an Event")));
    }

    @Test
    public void testHashCode() {
        Event e2 = new Event("Added Bitcoin");
        Event e3 = new Event("asdasd");
        assertEquals(e2.hashCode(), e.hashCode());
        assertFalse(e.hashCode() == e3.hashCode());
    }
}
