package cinema;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cinema {
    private int total_rows;
    private int total_columns;
    private CopyOnWriteArrayList<AvailableSeats> available_seats = new CopyOnWriteArrayList<>();

    public Cinema() {

    }

    public Cinema(int rows, int columns) {
        this.total_rows = rows;
        this.total_columns = columns;
        createSeats();


    }

    private void createSeats() {

        for (int i = 1 ; i <= total_rows; i++) {
            for (int j = 1; j <= total_columns; j++) {
                int k = i > 4 ? 8 : 10;
                AvailableSeats temp = new AvailableSeats(i, j, k);
                this.available_seats.add(temp);
            }
        }
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setAvailable_seats(CopyOnWriteArrayList<AvailableSeats> available_seats) {
        this.available_seats = available_seats;
    }

    public List<AvailableSeats> getAvailable_seats() {
        return available_seats;
    }
}
