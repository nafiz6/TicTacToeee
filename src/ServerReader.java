import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerReader implements Runnable {
    ObjectInputStream ois;
    ObjectOutputStream oos;
    Thread thread;

    ServerReader( ObjectOutputStream oos, ObjectInputStream ois){
                this.oos = oos;
        this.ois = ois;
        thread = new Thread(this);
        thread.start();
    }



    @Override
    public void run() {
            try {
                while (true) {
                    Object o = ois.readObject();
                    oos.writeObject(o);
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


    }
}
