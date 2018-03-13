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
    private static final String DASHES =
            "----------------------------------------"
                    + "----------------------------------------";



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


        System.out.println(DASHES);
        System.out.printf("%-33s | %5s" , "NAME", "PHONE NUMBER\n");
        System.out.println(DASHES);


        for (int i = 0; i < contactList().size(); i++) {
            int removeDash = contactList().get(i).indexOf("_");
            int removeHash = contactList().get(i).indexOf("#");
            String firstName = contactList().get(i).substring(0,removeDash);
            String lastName = contactList().get(i).substring(removeDash+1,removeHash);
            String fullName = firstName + " " + lastName;
            String phoneNumber = contactList().get(i).substring(removeHash+1);
            if(phoneNumber.length()==7 || phoneNumber.length()==10) {
                if (phoneNumber.length() == 7) {
                    String first3 = phoneNumber.substring(0, 3);
                    String last4 = phoneNumber.substring(3);
                    phoneNumber = first3 + "-" + last4;
                }
                if (phoneNumber.length() == 10) {
                    String first3 = phoneNumber.substring(0, 3);
                    String mid3 = phoneNumber.substring(3, 6);
                    String last4 = phoneNumber.substring(6);
                    phoneNumber = first3 + "-" + mid3 + "-" + last4;
                }
            }

            System.out.printf((i+1) + ". %-30s | %-15s\n",fullName,phoneNumber);

            System.out.println(DASHES);
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
        String number;
        Scanner scan = new Scanner(System.in);
        ArrayList<String> newContact = new ArrayList<>();
        System.out.println("Enter the contacts first name");
        String firstName = scan.nextLine();
        System.out.println("Enter the contacts last name");
        String lastName = scan.nextLine();
        do {
            System.out.println("Enter " + firstName + " " + lastName + "'s phone number with no dashes. Should be between 7 and 10 digits.");
            number = scan.nextLine();
        } while(number.length()<7 || number.length()>10);
        String addContact = firstName + "_" + lastName + "#" + number;
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
        showContacts();
        int deletedContact;
        ArrayList<String> temp = contactList();
        do {
            System.out.println("Please enter the corresponding number of the contact that you'd like to delete ");
            Scanner scan = new Scanner(System.in);
            deletedContact = scan.nextInt();
        }while(deletedContact>temp.size() || deletedContact<=0);
        temp.remove(deletedContact-1);
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
