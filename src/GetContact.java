import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetContact {


    ArrayList<String> contents = new ArrayList<>();

    public static List<String> slurp(String filepath){
        Path path = Paths.get(filepath);
        List<String> contents = null;
        try {
            contents = Files.readAllLines(Paths.get(filepath));
            for(String line : contents ){
                System.out.printf("%s \n", line );
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return contents;
    }

    public static void askUser() {
        System.out.println("Welcome to the contacts manager app\n" +
                "1. View Contacts \n" +
                "2. Add a new contact \n" +
                "3. Search a contact by name \n" +
                "4. Delete an existing contact \n" +
                "5. Exit\n" +
                "Enter an option (1,2,3,4,5)");
    }

    public static void addContact() {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> newContact = new ArrayList<>();
        System.out.println("Enter the new contact name");
        String contact = scan.nextLine();
        System.out.println("Enter the contacts phone number");
        String number = scan.nextLine();
        String addContact = contact + " - " + number;
        newContact.add(addContact);
        spit("contacts.txt", newContact, true);
    }

    public static void search() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the name of the contact you want information on: ");
        String input = scan.nextLine();
        Path file = Paths.get("contacts.txt");
        Scanner in = null;
        try {
            in = new Scanner(file);
            while(in.hasNext())
            {
                String line=in.nextLine();
                if(line.contains(input))
                    System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public static void spit(String filename, List<String> contents, boolean append){
        try {
                Files.write(Paths.get(filename), contents, StandardOpenOption.APPEND);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void contactsApp() throws IOException {
        do {
            askUser();
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    slurp("contacts.txt");
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    search();
                    break;
                case 4:
//                    DELETE LINE FROM CONTACTS.TXT BASED ON USER INPUT
                    break;
                case 5:
                    System.out.println("Thanks for using the contacts app");
                    return;
            }

        } while(true);
    }


    public static void main(String[] args) throws IOException {
        contactsApp();
    }

}
