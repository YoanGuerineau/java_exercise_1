import java.util.Scanner;

public class Fibo implements Command {
    @Override
    public String name() {
        return "fibo";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.print("Value:");
        int value = scanner.nextInt();
        scanner.nextLine();
        System.out.println(fibonacci(value));
        return false;
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
