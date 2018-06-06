import javax.sound.midi.Soundbank;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class Runner implements Runnable {
    Player player;
    int pid;
    Thread thread;
    int port=8889;
    String ip = "127.0.0.1";

    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    Strategy strategy;

    GameState gameState;

    Runner(){
        thread = new Thread(this);
        thread.start();
        Random rand = new Random();
        gameState = new GameState();
        strategy = new Strategy();

    }



    @Override
    public void run() {
        try {
            clientSocket = new Socket(ip, port);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());

            pid = (int)in.readObject();
            player = new Player(pid);

            System.out.println((String)in.readObject());
            System.out.println((String)in.readObject());

            //first turn
            if (pid==1){

                Move move = new Move(gameState);
                strategy.makeMove(move, player);
                gameState.setMark(player,move.row,move.column);

                out.writeObject(move.row);
                out.writeObject(move.column);
                out.writeObject(player.getpID());

            }

            while (gameState.turns!=9) {
                int row = (int)in.readObject();
                int column = (int)in.readObject();
                int pid = (int)in.readObject();
                gameState.setMark(new Player(pid),row,column);

                if (gameState.turns==9)break;

                Move move = new Move(gameState);
                strategy.makeMove(move, player);
                gameState.setMark(player,move.row,move.column);
                out.writeObject(move.row);
                out.writeObject(move.column);
                out.writeObject(player.getpID());

            }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Runner();
    }

}