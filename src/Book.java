import java.io.*;
import java.util.*;

public class Book {
    static Scanner input = new Scanner(System.in);
    static HashMap<String, Contact> addressBook = new HashMap<>();

    static String getName() {
        String name = "";
        while (name.matches("^.*[^a-zA-Z0-9 ].*$")||name.isBlank()) {
            System.out.println("Enter contact name");
            name = input.nextLine();
            if (name.matches("^.*[^a-zA-Z0-9 ].*$")||name.isBlank()) System.out.println("Invalid contact name");
        }
        return name;
    }

    static boolean nameCheck(String name) {
        if(!addressBook.containsKey(name)) {
            System.out.println("Contact not found");
            return false;
        }
        return true;
    }

    static void saveBook() {
        try {
            File file = new File("bookData");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(addressBook);
            oos.flush();
            oos.close();
            fos.close();
        } catch(Exception ignored) {}
    }

    static void loadBook() {
        try {
            File toRead = new File("bookData");
            FileInputStream fis = new FileInputStream(toRead);
            ObjectInputStream ois = new ObjectInputStream(fis);
            addressBook = (HashMap<String,Contact>)ois.readObject();
            ois.close();
            fis.close();
        } catch(Exception ignored) {}
    }

    static void displayBook() {
        System.out.println("Address Book Contents:");
        for (String i : addressBook.keySet()) {
            System.out.println(i);
        }
    }

    static void newContact() {
        String name = getName();
        addressBook.put(name, new Contact(name));
        saveBook();
    }

    static void displayContact() {
        String name = getName();
        if(!nameCheck(name)) return;
        System.out.println("Name: " + addressBook.get(name).name);
        System.out.println("Phone number: " + addressBook.get(name).number);
        System.out.println("Email: " + addressBook.get(name).email);
        System.out.println("Address: " + addressBook.get(name).address);
        System.out.println("Notes: " + addressBook.get(name).notes);
    }

    static void deleteContact() {
        String name = getName();
        if(!nameCheck(name)) return;
        System.out.println("Enter 'DELETE " + name.toUpperCase() + "' to confirm");
        if (input.nextLine().equals("DELETE " + name.toUpperCase())) {
            addressBook.remove(name);
            saveBook();
        }
    }

    static void editContact() {
        String name = getName();
        if(!nameCheck(name)) return;
        System.out.println("What would you like to edit");
        System.out.println("'Phone Number', 'Email', 'Address', 'Notes'");

        ArrayList<String> accepted = new ArrayList<>(Arrays.asList("phone number", "email", "address", "notes"));
        String option = input.nextLine().toLowerCase();
        if (!accepted.contains(option)) return;
        System.out.println("Enter new " + option);
        String newInfo = input.nextLine();

        System.out.println("Enter 'confirm' to save changes");
        if (!input.nextLine().equalsIgnoreCase("confirm")) return;
        switch (option) {
            case "phone number" -> addressBook.get(name).number = newInfo;
            case "email" -> addressBook.get(name).email = newInfo;
            case "address" -> addressBook.get(name).address = newInfo;
            case "notes" -> addressBook.get(name).notes = newInfo;
        }
        saveBook();
    }
}
