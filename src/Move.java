public class Move {
    public int row, column;

    private GameState gameState;

    Move(GameState gS){
        gameState = gS;
    }

    public void mark(int r, int c){
        row = r;
        column = c;
    }

    boolean isValid(int r,int c){
        if (gameState.board[r][c]==0)return true;
        else return false;
    }

    public int[][] getBoard(){
        return gameState.board;
    }



}
