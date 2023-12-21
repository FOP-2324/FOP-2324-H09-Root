package h09.function;

import h09.WithSeats;
import h09.room.Room;

import java.util.function.Function;
import java.util.function.Predicate;

@SuppressWarnings("Convert2MethodRef")
public class RoomFunctions {

    public static Predicate<Object> IS_NULL_PREDICATE = object -> object == null;

    public static <T extends Room> Predicate<T> isInArea(char area) {
        // return crash();
        return room -> room.name().charAt(0) == area;
    }

    public static <T extends Room & WithSeats> Predicate<T> isInAreaAndHasMinimumNumberOfSeats(char area, int number) {
        Predicate<T> isInArea = isInArea(area);
        Predicate<T> hasAsManyOrMoreSeats = room -> room.numberOfSeats() >= number;
        return isInArea.and(hasAsManyOrMoreSeats);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Room> Function<T, T> toRoomTypeOrNull(Class<T> type) {
        return room -> type.isInstance(room) ? (T) room : null;
    }

    private RoomFunctions() {
    }

}
