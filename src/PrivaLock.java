import java.io.IOException;
import java.util.Scanner;

/**
 * Created by knnth on 4/29/2016.
 */
public class PrivaLock {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.print("What is your filename :");
        String filename = scan.nextLine();
        LockedFiles test = new LockedFiles(filename);
        test.hasAccess();
        Thread.sleep(10000);
    }
}
