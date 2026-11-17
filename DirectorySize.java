
package lt.vcd;

import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;
import java.util.stream.Stream;

public class DirectorySize {

    // Calculate total size of a directory and its subdirectories
    public static long getDirectorySize(Path directory) throws IOException {

        try (Stream<Path> paths = Files.walk(directory)) {

            return paths
                    .filter(Files::isRegularFile)
                    .mapToLong(path -> {
                        try {
                            return Files.size(path);
                        } catch (IOException e) {
                            return 0;
                        }
                    })
                    .sum();
        }
    }

    // Display size in bytes, KB and MB
    public static void displaySize(String name, long bytes) {

        double kb = bytes / 1024.0;
        double mb = kb / 1024.0;

        System.out.println("\nDirectory: " + name);
        System.out.printf("Size: %.2f MB%n", mb);
        System.out.printf("Size: %.2f KB%n", kb);
        System.out.println("Size: " + bytes + " bytes");
    }

    // Display individual directory sizes
    public static void displaySubdirectories(Path directory)
            throws IOException {

        displaySize(directory.toString(), getDirectorySize(directory));

        try (Stream<Path> paths = Files.list(directory)) {

            paths.filter(Files::isDirectory)
                    .forEach(subdirectory -> {
                        try {
                            displaySubdirectories(subdirectory);
                        } catch (IOException e) {
                            System.out.println(
                                    "Cannot access: " + subdirectory);
                        }
                    });
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter directory path: ");

        String input = scanner.nextLine();

        Path directory = Paths.get(input);

        // Check whether directory exists
        if (!Files.exists(directory) || !Files.isDirectory(directory)) {

            System.out.println("Error: Directory does not exist.");

        } else {

            try {
                long totalSize = getDirectorySize(directory);

                System.out.println("\nTotal directory size:");
                displaySize(directory.toString(), totalSize);

                System.out.println("\nIndividual directory sizes:");

                displaySubdirectories(directory);

            } catch (IOException e) {

                System.out.println("Error reading directory: "
                        + e.getMessage());
            }
        }

        scanner.close();
    }
}
