package bot.wordament;

/**
 * This class simply bundles two integers together, one for column and
 * one for row.  This is complete; you don't need to make any changes
 * to it.
 */
public class BoardLocation {

    private int row, column;
    /**
     * Construct a new BoardLocation from two coordinates.
     */
    public BoardLocation (int column, int row) {
        this.row = row;
        this.column = column;
    }

    /**
     * Return the row coordinate.
     */
    public int row () {
        return row;
    }

    /**
     * Return the column coordinate.
     */
    public int column () {
        return column;
    }
}
