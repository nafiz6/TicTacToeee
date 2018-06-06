import java.util.Random;

public class Strategy {

    public void makeMove(Move move, Player player){
        Random rand = new Random();
        boolean valid = false;
        int r,c;
        r=1;
        c=1;
        while(!valid){
            r = rand.nextInt(3)+1;
            c = rand.nextInt(3)+1;
            valid = move.isValid(r,c);
        }
        move.mark(r,c);
    }


}
