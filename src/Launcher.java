import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.readString;

public class Launcher {

    public static void main(String[] args) {
        System.out.println("Welcome !");

        List<Command> commandList = new ArrayList<>();
        commandList.add(new Quit());
        commandList.add(new Fibo());
        commandList.add(new Freq());

        Scanner scanner = new Scanner(System.in);
        String stringInput;

        boolean stopped = false;
        do {
            stringInput = scanner.nextLine();
            String finalStringInput = stringInput;
            final Command[] currentCommand = {null};
            commandList.forEach( command -> {
                if ( command.name().equals(finalStringInput) ) {
                    currentCommand[0] = command;
                }
            });
            if ( currentCommand[0] == null ) {
                System.out.println("Unknown command");
            } else {
                stopped = currentCommand[0].run(scanner);
            }
        } while ( ! stopped );
    }
}
