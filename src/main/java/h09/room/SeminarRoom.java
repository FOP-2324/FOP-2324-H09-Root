package h09.room;

import h09.WithSeats;

public record SeminarRoom(
    String name,
    int numberOfSeats
) implements Room, WithSeats {
}
