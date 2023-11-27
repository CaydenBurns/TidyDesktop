import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class DesktopCleaner {

    public static void main(String[] args) {
        try {
            // Specify the source and destination directories
            String desktopPath = System.getProperty("user.home") + "/Desktop";
            String destinationPath = System.getProperty("user.home") + "/Desktop/Cleaned";

            // Create the destination directory if it doesn't exist
            Files.createDirectories(Path.of(destinationPath));

            // List all files on the desktop
            File desktop = new File(desktopPath);
            File[] files = desktop.listFiles();

            if (files != null) {
                for (File file : files) {
                    // Skip directories
                    if (file.isDirectory()) {
                        continue;
                    }

                    // Define new path for the file in the destination directory
                    Path destination = Path.of(destinationPath, file.getName());

                    // Move the file to the destination directory
                    Files.move(file.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

                    System.out.println("Moved: " + file.getName());
                }

                System.out.println("Desktop cleaning completed!");
            } else {
                System.out.println("No files found on the desktop.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
