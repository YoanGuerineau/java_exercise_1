import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.readString;

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
            } else if ( input.equals("freq") ) {
                System.out.print("Path:");
                try {
                    String file = readString(Paths.get(scanner.nextLine()));
                    String[] words = file.replaceAll("[^a-zA-Z0-9]"," ").toLowerCase().split("\\s+");
                    Stream.of(words)
                            .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                            .entrySet()
                            .stream()
                            .sorted(Map.Entry.<String,Long>comparingByValue().reversed())
                            .limit(3)
                            .forEach(System.out::println);
                } catch ( Exception e ) {
                    System.err.println("Unreadable file:\n" + e.getClass().getSimpleName() + "\n" + e.getMessage());
                }
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
