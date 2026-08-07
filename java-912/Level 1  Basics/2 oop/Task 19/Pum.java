import java.util.Scanner;

public class Pum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        PumGame game = new PumGame(n);
        game.play();
    }
}

class PumGame {
    int n;

    PumGame(int n) {
        this.n = n;
    }

    void play() {
        int number = 1;

        for (int i = 1; i <= n; i++) {
            System.out.println(number + " " + (number + 1) + " " + (number + 2) + " PUM");
            number += 4;
        }
    }
}