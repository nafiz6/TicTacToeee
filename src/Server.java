import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{

    int playerCount=0;

    private ServerSocket serverSocket ;
    int portNumber=8889;

    private Socket playerOne;
    private Socket playerTwo;

    private ObjectOutputStream outOne;
    private ObjectOutputStream outTwo ;

    private ObjectInputStream inOne;
    private ObjectInputStream inTwo ;

    Thread thread;



    Server(){
        try {
            serverSocket = new ServerSocket(portNumber);

            playerOne = serverSocket.accept();
            outOne = new ObjectOutputStream(playerOne.getOutputStream());
            inOne = new ObjectInputStream(playerOne.getInputStream());
            outOne.writeObject(1);
            outOne.writeObject("Connected to server. You are player one. Waiting for second player to join.");


            playerTwo = serverSocket.accept();
            outTwo = new ObjectOutputStream(playerTwo.getOutputStream());
            inTwo = new ObjectInputStream(playerTwo.getInputStream());
            outTwo.writeObject(2);
            outTwo.writeObject("Connected to server. You are player two. Game will start now.");

            new ServerReader(outTwo,inOne);
            new ServerReader(outOne,inTwo);

            outOne.writeObject("Game has started");
            outTwo.writeObject("Game has started");


        }catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public void serverWrite(Object o, ObjectOutputStream oos) throws IOException {
        oos.writeObject(o);
    }

    public static void main(String[] args) {
        new Server();
    }
}
