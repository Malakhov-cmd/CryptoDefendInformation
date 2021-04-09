import java.util.Scanner;
import java.util.concurrent.Exchanger;

public class MainChannel {
    public static void main(String[] args) {
        Exchanger<Data> exchanger = new Exchanger<>();

        Scanner in = new Scanner(System.in);
        System.out.println("Enter password");
        String password = in.next();
        System.out.println("Enter your mail");
        String message = in.next();

        Thread userThread = new Thread(new User(exchanger, password, message));
        Thread service = new Thread(new Service(exchanger));

        userThread.start();
        service.start();
    }
}
