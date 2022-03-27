package cinema;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Seats {

    private int row;
    private int column;

    public Seats() {

    }

    public Seats(int row, int column) {
        this.row = row;
        this.column = column;
    }


    public void setColumn(int column) {
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }

}
