import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
    public static void main(String[] args) {

        try {
            File file = new File("data.txt");
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                System.out.println(input.nextLine());
            }

            input.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }
}
