package cinema;

import io.micrometer.core.ipc.http.HttpSender;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class Controller {

    Cinema allSeats = new Cinema(9, 9);
    CopyOnWriteArrayList<BookedSeats> bookedSeatsList = new CopyOnWriteArrayList<>();
    private final String PASSWORD = "super_secret";


    @GetMapping("/seats")
    public Cinema getCinema() {

        return allSeats;
    }

    @GetMapping("/bookedseats")
    public CopyOnWriteArrayList<BookedSeats> getBookedSeatsList () {
        return bookedSeatsList;
    }

    @PostMapping("/purchase")
    public synchronized ResponseEntity getTickets(@RequestBody Seats purchase) {
        int purchaseRow = purchase.getRow();
        int purchaseColumn = purchase.getColumn();
        int sumRowColumn = purchaseColumn + purchaseRow;

        if ( (purchaseRow > 0 && purchaseRow < 10) && (purchaseColumn > 0 && purchaseColumn < 10)) {
            for (AvailableSeats check : allSeats.getAvailable_seats()) {

                if (purchaseRow == check.getRow() && purchaseColumn == check.getColumn()) {
                    BookedSeats booked = new BookedSeats(check);
                    bookedSeatsList.add(booked);
                    allSeats.getAvailable_seats().remove(check);
                    return new ResponseEntity(
                            Map.of("token", booked.getToken(),
                                    "ticket", booked.getBookedSeat()
                                    ), HttpStatus.OK);
                }

            }
            return new ResponseEntity(Map.of("error", "The ticket has been already purchased!"),
                                      HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(Map.of("error", "The number of a row or a column is out of bounds!"),
                                      HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/return")
    public ResponseEntity returnTicket(@RequestBody Token token) {
        for (BookedSeats seat : bookedSeatsList) {
            if (seat.getToken().compareTo(token.getToken()) == 0) {
                allSeats.getAvailable_seats().add(seat.getBookedSeat());
                bookedSeatsList.remove(seat);
                return new ResponseEntity(Map.of("returned_ticket", seat.getBookedSeat()), HttpStatus.OK);
            }
        }
        return new ResponseEntity(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/stats")
    public ResponseEntity getStats(@RequestParam (required = false) String password) {
        if (password != null && PASSWORD.equals(password)) {
                Statistics stats = new Statistics(
                        allSeats.getAvailable_seats().size(),
                        bookedSeatsList
                );
                return new ResponseEntity(
                        Map.of("current_income", stats.getCurrent_income(),
                                "number_of_available_seats", stats.getNumOfAvailableSeats(),
                                "number_of_purchased_tickets", stats.getNumOfPurchasedTickets()
                        ), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }

    }



}
