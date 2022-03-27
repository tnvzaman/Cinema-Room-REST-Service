package cinema;

import java.util.UUID;

public class BookedSeats {
    private UUID token;
    private AvailableSeats bookedSeat;

    public BookedSeats () {

    }
    BookedSeats(AvailableSeats seat) {
        this.bookedSeat = seat;
        this.token = UUID.randomUUID();

    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public AvailableSeats getBookedSeat() {
        return bookedSeat;
    }

    public void setBookedSeat(AvailableSeats bookedSeat) {
        this.bookedSeat = bookedSeat;
    }
}
