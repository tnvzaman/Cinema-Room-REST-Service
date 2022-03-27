package cinema;

public class AvailableSeats extends Seats {

    private int price;

    public AvailableSeats() {

    }

    public AvailableSeats(int row, int column, int price) {
        super(row, column);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
