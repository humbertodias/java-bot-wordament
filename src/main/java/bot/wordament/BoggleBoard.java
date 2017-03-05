package bot.wordament;

import java.util.LinkedList;

/**
 * This is the class that will represent a Boggle board at a certain
 * state during "play".  It keeps track of where the letters are on
 * the board, as well as which are taken/untaken.
 */
public class BoggleBoard {
        private int width, height;
        private String[][] boggleBoard;
        private BoardLocation[][] boardLocations;

    /**
     * Constructor.  It takes a string of letters.  You should make
     * sure the string has a square number of letters, and then
     * construct your board from the string by putting one letter in
     * each square.
     */
    public BoggleBoard (String letters){
        letters = letters.toUpperCase();
        char[] inputLetters = letters.toCharArray();
        width = height = (int)Math.sqrt(inputLetters.length);
        boggleBoard = new String[height][width];
        boardLocations = new BoardLocation[height][width];
        int counter = 0;
        for(int i = 0; i<height; i++){
                for(int j=0; j<width; j++){
                        boggleBoard[i][j] = ""+inputLetters[counter++];
                        boardLocations[i][j] = new BoardLocation(i,j);
                }
        }
    }

    /**
     * Return the dimensionality of the board.
     */
    public int dimensions(){
        return height;
    }

    /**
     * Print out the board.
     */
    public void printBoard () {
        String str = "\n";
        for(int i = 0; i<height; i++){
                str+= "|";
                for(int j=0; j<width; j++){
                        str += boggleBoard[i][j]+" ";
                }
                str+= "|";
                str+="\n";
        }
        System.out.println(str);
    }

    /**
     * Make a copy of the board.  This is important for getting your
     * recursion to work correctly (pass by value instead of by
     * reference).
     */
    public BoggleBoard makeCopy(){
        String str ="";
        for(int i = 0; i < boggleBoard.length; i++){
                for(int j = 0; j < boggleBoard[i].length; j++){
                        str += boggleBoard[i][j];
                }
        }
        BoggleBoard newBoggleBoard = new BoggleBoard(str);
        return newBoggleBoard;
    }

    /**
     * Return the letter at a particular location.
     */
    public String letterAtLocation(BoardLocation location) {
                String letterAtLocation = boggleBoard[location.column()][location.row()];
        return letterAtLocation;
    }

    /**
     * Mark a particular letter on
     *  the board as being taken.
     */
    public void markAsTaken(BoardLocation takenLocation) {
        boggleBoard[takenLocation.column()][takenLocation.row()] = "@";
    }

    /**
     * Return a list of board locations that are (1) on the board, (2)
     * adjacent to a given location, and (3) not already taken.
     */
    public BoardLocation[] getAdjacentUntakens(BoardLocation location)  {
        LinkedList<BoardLocation> locs = new LinkedList<BoardLocation>();
        int y = location.row();
        int x = location.column();
        if(x-1 >= 0){
                if(y-1 >= 0){
                        if(!this.boggleBoard[x-1][y-1].equals("@")){
                                locs.add(this.boardLocations[x-1][y-1]);
                        }
                }
                if(!this.boggleBoard[x-1][y].equals("@")){
                        locs.add(this.boardLocations[x-1][y]);
                }
                if(y+1 < height){
                        if(!this.boggleBoard[x-1][y+1].equals("@")){
                                locs.add(this.boardLocations[x-1][y+1]);
                        }
                }
        }
        if(x+1 < width){
                if(y-1 >= 0){
                        if(!this.boggleBoard[x+1][y-1].equals("@")){
                                locs.add(this.boardLocations[x+1][y-1]);
                        }
                }
                if(!this.boggleBoard[x+1][y].equals("@")){
                        locs.add(this.boardLocations[x+1][y]);
                }
                if(y+1 < height){
                        if(!this.boggleBoard[x+1][y+1].equals("@")){
                                locs.add(this.boardLocations[x+1][y+1]);
                        }
                }
        }
        if(y-1 >= 0){
                if(!this.boggleBoard[x][y-1].equals("@")){
                        locs.add(this.boardLocations[x][y-1]);
                }
        }
        if(y+1 < height){
                if(!this.boggleBoard[x][y+1].equals("@")){
                        locs.add(this.boardLocations[x][y+1]);
                }
        }
        BoardLocation[] bLocs = new BoardLocation[locs.size()];
        for(int i = 0; i < locs.size(); i++){
                bLocs[i] = locs.get(i);
        }

        return bLocs;
    }
}
