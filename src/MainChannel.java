import java.util.concurrent.Exchanger;

public class MainChannel {
    public static void main(String[] args) {
        Exchanger<Data> exchanger = new Exchanger<>();

        Thread userThread = new Thread(new User(exchanger));
        Thread service = new Thread(new Service(exchanger));

        userThread.start();
        service.start();
    }
}
