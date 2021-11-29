import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.readString;

public class Freq implements Command {
    @Override
    public String name() {
        return "freq";
    }

    @Override
    public boolean run(Scanner scanner) {
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
                    .forEach( m -> System.out.print(m.getKey() + " "));
            System.out.println();
        } catch ( Exception e ) {
            System.err.println("Unreadable file:\n" + e.getClass().getSimpleName() + "\n" + e.getMessage());
        }
        return false;
    }
}
