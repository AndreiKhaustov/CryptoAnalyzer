package Main;


import org.projectComponents.Decode;
import org.projectComponents.DecodeByBruteForce;
import org.projectComponents.Encode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<String> text = new ArrayList<>();
    public static List<String> textForComparing = new ArrayList<>();
    public static List<String> encodingLines = new ArrayList<>();
    public static List<String> decodingLines = new ArrayList<>();
    public static List<String> decodingByBruteForce = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        try {
            text = Files.readAllLines(Path.of(args[1]));
        } catch (IOException e) {
            System.out.println("Try to enter valid link!");
        }
        if(args[0].equalsIgnoreCase("encode")) {
            int key = Integer.parseInt(args[2]);
           encodingLines = Encode.textEncode(text, key);
            String link = args[1];
            String[] ArrayLink = link.split("\\.");
            Files.deleteIfExists(Path.of(ArrayLink[0] + "(encoded)" + "." + ArrayLink[1]));
            Path path = Files.createFile(Path.of(ArrayLink[0] + "(encoded)" + "." + ArrayLink[1]));
            Files.write(path, encodingLines);
        }
        else if(args[0].equalsIgnoreCase("decode")){
            int key = Integer.parseInt(args[2]);
            decodingLines = Decode.textDecode(text, key);
            String link = args[1];
            String[] ArrayLink = link.split("\\(encoded\\)");
            Files.deleteIfExists(Path.of(ArrayLink[0] + "(decoded)" + ArrayLink[1]));
            Path path = Files.createFile(Path.of(ArrayLink[0] + "(decoded)" + ArrayLink[1]));
            Files.write(path, decodingLines);
        }
        else if(args[0].equalsIgnoreCase("bruteForce")) {
             textForComparing = Files.readAllLines(Path.of(args[2]));
             decodingByBruteForce = DecodeByBruteForce.startBruteForce(text, textForComparing);
             int key = DecodeByBruteForce.searchingKey(DecodeByBruteForce.getStatisticData(Decode.toCharArray(text)), DecodeByBruteForce.getStatisticData(Decode.toCharArray(textForComparing)));
            String link = args[1];
            String[] ArrayLink = link.split("\\(encoded\\)");
            Files.deleteIfExists(Path.of(ArrayLink[0] + " decoded key-" + key + ArrayLink[1]));
            Path path = Files.createFile(Path.of(ArrayLink[0] + " decoded key-" + key + ArrayLink[1]));
            Files.write(path, decodingByBruteForce);
        }

    }
}