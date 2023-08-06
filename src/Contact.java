import java.util.Scanner;
import java.io.Serializable;

public class Contact implements Serializable{
    static Scanner input = new Scanner(System.in);
    String name, number, email, address, notes;

    Contact(String name) {
        this.name = name;
        System.out.println("Enter phone number");
        this.number = input.nextLine();
        System.out.println("Enter email");
        this.email = input.nextLine();
        System.out.println("Enter address");
        this.address = input.nextLine();
        System.out.println("Enter notes");
        this.notes = input.nextLine();
    }
}
