import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static void menu() {
        if (!Book.addressBook.isEmpty()) {
            System.out.println("\n__Menu__\n1 for displayBook\n2 for new\n3 for displayContact\n4 for delete\n5 for edit\n6 for stop");
            switch (input.nextLine()) {
                case "1" -> Book.displayBook();
                case "2" -> Book.newContact();
                case "3" -> Book.displayContact();
                case "4" -> Book.deleteContact();
                case "5" -> Book.editContact();
                case "6" -> System.exit(0);
            }
        } else {
            Book.newContact();
        }
    }

    public static void main(String[] args) {
        Book.loadBook();
        while (true) menu();
    }
}