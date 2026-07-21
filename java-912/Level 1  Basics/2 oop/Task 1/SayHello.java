import java.util.Scanner;

public class SayHello {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        if (input.hasNext()) {
            String s = input.next();
            Greeter greeter = new Greeter(s);
            greeter.sayHello();
        }
        
        input.close();
    }
}

class Greeter {
    private String name;

    public Greeter(String name) {
        this.name = name;
    }

    public void sayHello() {
        System.out.println("Hello, " + name);
    }
}
