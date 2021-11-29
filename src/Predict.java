import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.Files.readString;

public class Predict implements Command {

    @Override
    public String name() {
        return "predict";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.print("Path:");
        try {
            String file = readString(Paths.get(scanner.nextLine()));
            String[] words = file.replaceAll("[^a-zA-Z0-9]"," ").toLowerCase().split("\\s+");

            Map<String, List<String>> followers = new HashMap<>();
            for ( int i = 0; i < words.length; i++ ) {
                followers.putIfAbsent( words[i], new ArrayList<>());
                if ( i + 1 < words.length ) {
                    String nextUsedWord = words[i+1];
                    followers.get(words[i]).add(nextUsedWord);
                }
            }
            Map<String,String> definitiveFollowers = new HashMap<>();

            followers.forEach( ( word ,followersList) -> {
                Map<String, Long> followersCount = followersList.stream().collect( Collectors.groupingBy( followingWord -> followingWord, Collectors.counting()));
                Optional<Map.Entry<String, Long>> maxOptional = followersCount.entrySet().stream().max(Map.Entry.comparingByValue( Comparator.comparingLong(Long::longValue)));
                maxOptional.ifPresent(stringLongEntry -> definitiveFollowers.put(word, stringLongEntry.getKey()));
            });

            System.out.print("Word:");
            String inputWord = scanner.nextLine().toLowerCase();

            boolean found = false;
            for (String word : words) {
                if ( inputWord.equals( word ) ) {
                    found = true;
                    break;
                }
            }

            if ( ! found ) {
                System.out.println("Word not found !");
                return false;
            }

            int count = 1;
            System.out.print(inputWord + " ");
            String nextWord = inputWord;
            do {
                nextWord = definitiveFollowers.get(nextWord);
                if ( nextWord != null){
                    System.out.print(nextWord + " ");
                }
                count++;
            } while ( count < 20 && nextWord != null );
            System.out.println();
        } catch ( IOException e ) {
            System.err.println("Unreadable file:\n" + e.getClass().getSimpleName() + "\n" + e.getMessage());
        }

        return false;
    }
}
