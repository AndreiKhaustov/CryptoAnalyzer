package org.example;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<String> text = new ArrayList<>();
    public static List<String> encodingLines = new ArrayList<>();
    public static List<String> decodingLines = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        int key = Integer.parseInt(args[2]);
        try {
            text = Files.readAllLines(Path.of(args[1]));
        } catch (IOException e) {
            System.out.println("Try to enter valid link!");
        }
        if(args[0].equalsIgnoreCase("encode")) {
           encodingLines = Encode.textEncode(text, key);
            String link = args[1];
            String[] ArrayLink = link.split("\\.");
            Path path = Files.createFile(Path.of(ArrayLink[0] + "(encoded)" + "." + ArrayLink[1]));
            Files.write(path, encodingLines);
        }
        else if(args[0].equalsIgnoreCase("decode")){
            decodingLines = Decode.textDecode(text, key);
            String link = args[1];
            String[] ArrayLink = link.split("\\(encoded\\)");
            Path path = Files.createFile(Path.of(ArrayLink[0] + "(decoded)" + ArrayLink[1]));
            Files.write(path, decodingLines);
        }

    }
}