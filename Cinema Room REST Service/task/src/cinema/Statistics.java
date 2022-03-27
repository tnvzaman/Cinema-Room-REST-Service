package cinema;

import java.util.concurrent.CopyOnWriteArrayList;

public class Statistics {
    private int current_income;
    private int numOfAvailableSeats;
    private int numOfPurchasedTickets;

    Statistics() {

    }

    Statistics(int availableSeatsNum, CopyOnWriteArrayList<BookedSeats> seat) {
        this.numOfAvailableSeats = availableSeatsNum;
        this.numOfPurchasedTickets = seat.size();
        calculateIncome(seat);

    }

    private void calculateIncome(CopyOnWriteArrayList<BookedSeats> seat) {
        for (BookedSeats purchased : seat) {
            current_income = current_income + purchased.getBookedSeat().getPrice();
        }
    }

    public int getCurrent_income() {
        return current_income;
    }

    public void setCurrent_income(int current_income) {
        this.current_income = current_income;
    }

    public int getNumOfAvailableSeats() {
        return numOfAvailableSeats;
    }

    public void setNumOfAvailableSeats(int numOfAvailableSeats) {
        this.numOfAvailableSeats = numOfAvailableSeats;
    }

    public int getNumOfPurchasedTickets() {
        return numOfPurchasedTickets;
    }

    public void setNumOfPurchasedTickets(int numOfPurchasedTickets) {
        this.numOfPurchasedTickets = numOfPurchasedTickets;
    }
}
