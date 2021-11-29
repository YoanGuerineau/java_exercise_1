import java.util.*;

public class Launcher {

    public static void main(String[] args) {
        System.out.println("Welcome !");

        List<Command> commandList = new ArrayList<>();
        commandList.add(new Quit());
        commandList.add(new Fibo());
        commandList.add(new Freq());
        commandList.add(new Predict());

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
