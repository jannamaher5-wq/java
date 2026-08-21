import java.io.FileReader;
import java.io.IOException;

public class ThrowsKeyword {

    public static void readFile() throws IOException {

        FileReader reader = new FileReader("data.txt");

        int character;

        while ((character = reader.read()) != -1) {
            System.out.print((char) character);
        }

        reader.close();
    }

    public static void main(String[] args) {

        try {
            readFile();

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
كلمة throws جنب اسم الميثود معناها الميثود دي ممكن ترمي إيرور فاللي هيناديها يعمل try-catch بنفسه  
