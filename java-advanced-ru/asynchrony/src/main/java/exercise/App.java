package exercise;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

class App {

    // BEGIN
    private static Path getAbsolutePath(String filepath) {
        return Paths.get(filepath).toAbsolutePath();
    }

    public static CompletableFuture<String> unionFiles(String pathOfFile1,
                                                       String pathOfFile2,
                                                       String pathForResult) {

        CompletableFuture<String> readFirstFile = CompletableFuture.supplyAsync(() -> {
            Path absolutePath = getAbsolutePath(pathOfFile1);
            String firstResult = "";
            try {
                firstResult = Files.readString(absolutePath);
            } catch (IOException e) {
                throw new CompletionException(e);
            }
            return firstResult;
        });

        CompletableFuture<String> readSecondFile = CompletableFuture.supplyAsync(() -> {
            Path absolutePath = getAbsolutePath(pathOfFile2);
            String secondResult = "";
            try {
                secondResult = Files.readString(absolutePath);
            } catch (IOException e) {
                throw new CompletionException(e);
            }
            return secondResult;
        });

        return readFirstFile.thenCombine(readSecondFile, (firstContext, secondContext) -> {
            Path absoluteResultPath = getAbsolutePath(pathForResult);
            String resultContext = firstContext + secondContext;
            try {
                Files.write(absoluteResultPath, resultContext.getBytes());
            } catch (IOException e) {
                throw new CompletionException(e);
            }
            return resultContext;
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return e.getMessage();
        });
    }
    // END

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // BEGIN
        System.out.println(unionFiles("src/main/resources/file1.txt",
            "src/main/resources/file2.txt",
            "src/main/resources/result.txt").get());
        // END
    }

    public static CompletableFuture<Long> getDirectorySize(String directoryPath) {
        return CompletableFuture.supplyAsync(() -> {
            Path fullPath = getAbsolutePath(directoryPath);
            try {
                return Files.walk(fullPath)
                    .filter(p -> p.toFile().isFile())
                    .mapToLong(p -> p.toFile().length())
                    .sum();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

