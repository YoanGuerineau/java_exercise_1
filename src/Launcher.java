import java.util.Scanner;

public class Launcher {

    public static void main(String[] args) {
        System.out.println("Welcome !");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while ( ! input.equals("quit") ) {
            if ( input.equals("fibo") ) {
                System.out.print("Value:");
                int value = scanner.nextInt();
                scanner.nextLine();
                System.out.println(fibonacci(value));
            } else {
                System.out.println("Unknown command");
            }
            input = scanner.nextLine();
        }
    }

    private static int fibonacci(int n) {
        if ( n == 0 ) {
            return 0;
        } else if ( n == 1 ) {
            return 1;
        } else {
            return fibonacci(n-1 ) + fibonacci( n-2 );
        }
    }

}
