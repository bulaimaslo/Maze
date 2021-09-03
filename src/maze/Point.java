package maze;


public class Point {
    private final int row;
    private final int col;
    private CellValue cellValue = CellValue.WALL;

    Point(int row, int col) {
        this.row = row;
        this.col = col;
    }

    int getRow() {
        return this.row;
    }

    int getCol() {
        return this.col;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Point tmp = (Point) obj;
        return tmp.getRow() == this.getRow() && this.getCol() == tmp.getCol();
    }

    @Override
    public String toString() {
        return "row: " + this.getRow() + " col: " + this.getCol();
    }

    public void setCellValue(CellValue cellValue) {
        this.cellValue = cellValue;
    }

    public CellValue getCellValue() {
        return cellValue;
    }
}

enum CellValue {
    WALL,
    EMPTY,
    PATH
}
