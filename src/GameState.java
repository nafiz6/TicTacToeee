public class GameState {
    int board[][];
    public int turns;

    GameState(){
        board = new int[4][4];
        turns = 0;
    }

    void setMark(Player player, int row, int column){
        if (board[row][column]==0){
            board[row][column] = player.getpID();
            turns++;
        }
        printBoard();
        checkWinner();

    }


    void printBoard(){
        for (int i=1;i<4;i++){
            for (int j=1;j<4;j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    void checkWinner(){
        for (int i=1;i<3;i++){
            int id = board[i][1];
            if (board[i][2]==board[i][3] && board[i][2]==id && id!=0){
                System.out.println("Player " + id + " wins");
                turns=9;
                return;
            }
            id = board[1][i];
            if (board[2][i]==board[3][i] && board[2][i]==id && id!=0) {
                System.out.println("Player " + id + " wins");
                turns = 9;
                return;
            }
        }
        int id = board[1][1];
        if (id == board[2][2] && id==board[3][3] && id!=0){
            System.out.println("Player " + id + " wins");
            turns=9;
            return;
        }
        id = board[1][3];
        if (id==board[2][2] && id==board[3][1] && id!=0){
            System.out.println("Player " + id + " wins");
            turns=9;
            return;

        }


    }

}
