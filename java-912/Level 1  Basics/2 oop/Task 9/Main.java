import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char c = scanner.next().charAt(0);

        Alphabet alphabet = new Alphabet(c);
        alphabet.nextCharacter();
    }
}

class Alphabet {
    char c;

    Alphabet(char c) {
        this.c = c;
    }

    void nextCharacter() {
        if (c == 'z') {
            System.out.println('a');
        } else {
            System.out.println((char)(c + 1));
        }
    }
}