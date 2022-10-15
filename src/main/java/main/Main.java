package main;


import org.projectComponents.Encoder;
import org.projectComponents.FilesPathsConverter;
import org.projectComponents.KeyFinder;
import org.projectComponents.NameOfOperation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {

        String srcText;
        String nameOfCommand = args[0];
        String sourcePathString = args[1];
        Path srcPath = Path.of(sourcePathString);
        Path destinationPath = null;
        int key = 0;
        String referenceText = null;
        String convertedText = null;

        /**
         * в зависимости от типа операции присваиваем введенный аргумент либо в качестве ключа, либо в качестве второго текста для сравнения в случае брутфорса.
         */
        if (nameOfCommand.equalsIgnoreCase(NameOfOperation.BRUTEFORCE.getName())) {
            try {
                referenceText = Files.readString(Path.of(args[2]));
            } catch (IOException e) {
                System.out.println("Try to enter valid path with extension .txt");
                throw new RuntimeException(e);
            }
        } else {
            key = Integer.parseInt(args[2]);
        }

        /**
         * считываем текст из аргумента
         */
        try {
            srcText = Files.readString(srcPath);
        } catch (IOException e) {
            System.out.println("Try to enter valid path with extension .txt");
            throw new RuntimeException(e);
        }

        /**
         * В зависимости от введенной операции выбираем, что будем делать.
         */
        if (nameOfCommand.equalsIgnoreCase(NameOfOperation.ENCODE.getName())) {
            convertedText = Encoder.encodeText(srcText, key);
            destinationPath = FilesPathsConverter.handleExistingFilesPath(sourcePathString, NameOfOperation.ENCODE);
        } else if (nameOfCommand.equalsIgnoreCase(NameOfOperation.DECODE.getName())) {
            convertedText = Encoder.decodeText(srcText, key);
            destinationPath = FilesPathsConverter.handleExistingFilesPath(sourcePathString, NameOfOperation.DECODE);
        } else if (nameOfCommand.equalsIgnoreCase(NameOfOperation.BRUTEFORCE.getName())) {
            key = KeyFinder.findKey(srcText, referenceText);
            convertedText = Encoder.decodeText(srcText, key);
            destinationPath = FilesPathsConverter.handleExistingFilesPath(sourcePathString, key);
        }
        /**
         * Записываем файл сконвертированным текстом.
         */
        try {
            Files.writeString(destinationPath, convertedText);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
