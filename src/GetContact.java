import java.io.File;
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

//    RUN APP AND PROMPT USER

    public static void askUser() {
        System.out.println("Welcome to the contacts manager app\n" +
                "1. View Contacts \n" +
                "2. Add a new contact \n" +
                "3. Search a contact by name \n" +
                "4. Delete an existing contact \n" +
                "5. Exit\n" +
                "Enter an option (1,2,3,4,5)");
    }


////    PRINT CONTACTS FOR USER TO SEE

    public static void showContacts() throws FileNotFoundException {
        for (int i = 0; i < contactList().size(); i++) {
            System.out.println(i + ". " + contactList().get(i));
        }
    }

//    CONVERTS CONTACTS.TXT TO ARRAY LIST

    public static ArrayList<String> contactList() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("contacts.txt"));
        ArrayList<String> list = new ArrayList<String>();
        while (scan.hasNext()){
            list.add(scan.next());
        }
        return list;
    }

//    ADD A CONTACT

    public static void addContact() {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> newContact = new ArrayList<>();
        System.out.println("Enter the new contact name");
        String contact = scan.nextLine();
        System.out.println("Enter the contacts phone number");
        String number = scan.nextLine();
        String addContact = contact +"_"+ number;
        newContact.add(addContact);
        spit("contacts.txt", newContact, true);
    }

//    SEARCH FOR A CONTACT

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
                if(line.toLowerCase().contains(input.toLowerCase()))
                    System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


//    DELETE A CONTACT
    public static void deleteContact() throws IOException {
        for(int i = 0;i<contactList().size();i++){
            System.out.println(i + ". " + contactList().get(i));
        }
        System.out.println("Please enter the corresponding number of the contact that you'd like to delete ");
        Scanner scan = new Scanner(System.in);
        int deletedContact = scan.nextInt();
        ArrayList<String> temp = contactList();
        temp.remove(deletedContact);
        Files.write(Paths.get("contacts.txt"), temp);
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
            String choice = scan.nextLine();
            if (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5")) {
                System.out.println("That\'s not an option, try again.");
            } else {
                switch (choice) {
                    case "1":
                        showContacts();
                        break;
                    case "2":
                        addContact();
                        break;
                    case "3":
                        search();
                        break;
                    case "4":
                        deleteContact();
                        break;
                    case "5":
                        System.out.println("Thanks for using the contacts app");
                        return;
                }
            }

            } while (true) ;
        }


    public static void main(String[] args) throws IOException {
       contactsApp();
    }

}
