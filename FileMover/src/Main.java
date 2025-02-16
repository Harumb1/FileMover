import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String PATH = "Movable.txt";

        Path currentPath = Path.of(PATH).toAbsolutePath();
        System.out.println("Current dir: " + "\n" + currentPath);
        System.out.println("New Path: ");
        Path newPath = Paths.get(sc.nextLine());

        getDirectory(PATH);
        moveFile(currentPath, newPath); // Now correctly moves and updates the file
        historyFile(newPath);

    }

    static void getDirectory(String path) {
        try {
            PrintWriter file = new PrintWriter(path);
            file.close();
        } catch (IOException exception) {
            System.out.println("Error!");
        }
    }

    static void moveFile(Path currentPath, Path newPath) throws IOException {
        // Ensure the new path includes the file name, not just the directory
        Path targetPath = newPath.resolve(currentPath.getFileName());

        if (!Files.exists(targetPath)) {
            System.out.println("File moved successfully!");
            Files.move(currentPath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            historyFile(newPath);
            // Update file contents after moving
        } else {
            System.out.println("This file already exists in this directory!");
        }
    }

    static void historyFile(Path newPath) {
        try {
            // Write newPath to History.txt
            FileWriter history = new FileWriter("History.txt");
            history.append(String.valueOf(newPath));
            history.close();

            File file = new File("History.txt");
            Scanner fsc = new Scanner(file);

            if (fsc.hasNextLine()) {
                System.out.println("History content: " + fsc.nextLine());
            }

            fsc.close();  // Close scanner
        } catch (IOException exception) {
            System.out.println("Error!");
            exception.printStackTrace();
        }
    }
}
