import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class Main {
    //Scanner sc = new Scanner(System.in);
    // Path currentPath;
    // PrintWriter file;
    // FileWriter history;
    // Path targetPath;
    // Scanner fsc = new Scanner(String.valueOf(history));

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
            /*
            file.append("This file will be updated once it is moved to its new directory!");
            /* Once the file is moved it will display a different message:
               "This file was moved to a new directory!"
            */
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
    /*
    void clearFile(Path targetFile) {
        // Clear the file and write new content to reflect the move
        try (FileOutputStream fos = new FileOutputStream(targetFile.toString(), false)) {
            String newContent = "This file was moved to a new directory!";
            fos.write(newContent.getBytes());
            System.out.println("File cleared and new content written successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

     */
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
            String update = fsc.nextLine();

            fsc.close();  // Close scanner
        } catch (IOException exception) {
            System.out.println("Error!");
            exception.printStackTrace();
        }
    }
}
