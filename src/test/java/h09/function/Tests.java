package h09.function;

import h09.TUDa;
import h09.room.LectureHall;
import h09.room.SeminarRoom;
import h09.stack.StackOfObjects;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class Tests {

    @Test
    public void testFilter() {
        // TODO H4.1
        StackOfObjects<LectureHall> filtered = StackOfObjectsOperations.filter(
            TUDa.stackOfLectureHalls(),
            room -> room.numberOfSeats() >= 372
        );
        assertEquals(5, filtered.numberOfObjects());
        assertEquals("S311/08", filtered.get(0).name());
        assertEquals("S206/030", filtered.get(1).name());
        assertEquals("S105/122", filtered.get(2).name());
        assertEquals("S101/A1", filtered.get(3).name());
        assertEquals("L402/1", filtered.get(4).name());
    }

    @Test
    public void testMap() {
        // TODO H4.2
        StackOfObjects<SeminarRoom> rooms = TUDa.stackOfSeminarRooms();
        StackOfObjects<Integer> mapped = StackOfObjectsOperations.map(
            TUDa.stackOfSeminarRooms(),
            SeminarRoom::numberOfSeats
        );
        assertEquals(170, mapped.numberOfObjects());
        for (int i = 0; i < rooms.numberOfObjects(); i++) {
            assertSame(rooms.get(i).numberOfSeats(), mapped.get(rooms.numberOfObjects() - (i + 1)));
        }
    }
}
