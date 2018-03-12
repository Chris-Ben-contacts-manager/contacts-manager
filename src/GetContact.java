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


    public static void spit(String filename, List<String> contents, boolean append){
        try {
                Files.write(Paths.get(filename), contents, StandardOpenOption.APPEND);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }






    public static void contactsApp(){
        do {
            Scanner scan = new Scanner(System.in);
            System.out.println("Welcome to the contacts manager app\n" +
                    "1. View Contacts \n" +
                    "2. Add a new contact \n" +
                    "3. Search a contact by name \n" +
                    "4. Delete an existing contact \n" +
                    "5. Exit\n" +
                    "Enter an option (1,2,3,4,5)");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    slurp("contacts.txt");
                    break;
                case 2:
                    ArrayList<String> newContact = new ArrayList<>();
                    System.out.println("Enter the new contact name");
                    scan.nextLine();
                    String contact = scan.nextLine();
                    System.out.println("Enter the contacts phone number");
                    String number = scan.nextLine();
                    String addContact = contact + " - " + number;
                    newContact.add(addContact);
                    spit("contacts.txt", newContact, true);
                    break;
                case 3:
//                    SEARCH CONTACTS.TXT BASED ON USER INPUT
                    break;

                case 4:
//                    DELETE LINE FROM CONTACTS.TXT BASED ON USER INPUT
                    break;

                case 5:
                    System.out.println("Thanks for the contact app");
                    return;
            }

        } while(true);
    }


    public static void main(String[] args) {
        contactsApp();
    }




}
