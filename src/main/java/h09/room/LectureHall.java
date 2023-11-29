package h09.room;

import h09.WithSeats;

public record LectureHall(
    String name,
    int numberOfSeats
) implements Room, WithSeats {


}
