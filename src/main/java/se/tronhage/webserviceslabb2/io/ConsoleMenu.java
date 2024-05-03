package se.tronhage.webserviceslabb2.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.tronhage.webserviceslabb2.communication.PersonServiceClient;
import se.tronhage.webserviceslabb2.model.Person;

import java.util.List;

@Component
public class ConsoleMenu {

    @Autowired
    ConsoleIO consoleIO;

    @Autowired
    PersonServiceClient personServiceClient;

    public void mainMenu(){
        int choice = 0;

        do {
            consoleIO.addString("""
                    choose option:
                    0 - exit application
                    1 - add new person
                    2 - display persons
                    3 - edit person
                    """);

            choice = consoleIO.getValidIntegerInput(0,3);

            switch (choice){
                case 0 -> System.exit(0);
                case 1 -> addPerson();
                case 2 -> displayPersonMenu();
                case 3 -> editPerson();
            }

        }while (true);
    }

    public void addPerson(){
        int choice = 0;
        do {
            //Logic to add new person

        }while (choice != 0);
    }

    public void displayPersonMenu(){
        int choice = 0;
        do {
            consoleIO.addString("""
                    choose option:
                    0 - Back to main menu
                    1 - Display all persons in database
                    2 - Search by person-number
                    3 - Search by free text
                    4 - Search by age
                    5 - Search by city
                    """);

            choice = consoleIO.getValidIntegerInput(0,5);

            switch (choice){
                case 0 -> {
                    return;
                }
                case 1 -> {
                    List<Person> allPersons = personServiceClient.allPersons();
                    for (Person p : allPersons){
                        consoleIO.addString(p.toString());
                    }
                }
                case 2 -> consoleIO.addString("fritextsök");
                case 3 -> consoleIO.addString("sök på personnummer");
                case 4 -> consoleIO.addString("sök på ålder");
                case 5 -> consoleIO.addString("sök på stad");
            }

        }while (choice != 0);
    }

    public void editPerson(){
        int choice = 0;
        do {
            consoleIO.addString("""
                    choose option:
                    0 - exit application
                    1 - Change name
                    2 - Change age
                    3 - Change city
                    """);

            choice = consoleIO.getValidIntegerInput(0,3);

            switch (choice){
                case 0 -> {
                    return;
                }
                case 1 -> consoleIO.addString("lägga till pers");
                case 2 -> consoleIO.addString("visa pers");
                case 3 -> consoleIO.addString("redigera pers");
            }

        }while (choice != 0);
    }


}
