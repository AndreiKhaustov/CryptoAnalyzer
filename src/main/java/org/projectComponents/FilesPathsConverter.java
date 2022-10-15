package org.projectComponents;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesPathsConverter {

    static Path destinationPath;

    public static Path handleExistingFilesPath(String sourcePathString, NameOfOperation nameOfOperating) {

        switch (nameOfOperating) {

            case ENCODE: {
                int indexOfSplitter = sourcePathString.lastIndexOf("\\.");
                String[] sourcePathCharArray = sourcePathString.split("\\.", indexOfSplitter);
                try {
                    destinationPath = Files.createFile(Path.of(sourcePathCharArray[0] + "(encoded)" + "." + sourcePathCharArray[1]));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            break;
            case DECODE: {
                String[] sourcePathCharArray = sourcePathString.split("\\(encoded\\)");
                ;
                try {
                    destinationPath = Files.createFile(Path.of(sourcePathCharArray[0] + "(decoded)" + sourcePathCharArray[1]));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            break;
        }
        return destinationPath;
    }

    public static Path handleExistingFilesPath(String sourcePathString, int key) {
        String[] sourcePathCharArray = sourcePathString.split("\\(encoded\\)");
        try {
            destinationPath = Files.createFile(Path.of(sourcePathCharArray[0] + " decoded key-" + key + sourcePathCharArray[1]));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return destinationPath;
    }


}
