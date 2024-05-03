package se.tronhage.webserviceslabb2.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import se.tronhage.webserviceslabb2.communication.PersonServiceClient;
import se.tronhage.webserviceslabb2.model.Person;

import java.util.List;

@Component
public class ConsoleMenu {

    @Autowired
    ConsoleIO consoleIO;

    @Autowired
    PersonServiceClient personServiceClient;

    public void mainMenu() {
        int choice = 0;

        do {
            consoleIO.addString("""
                    choose option:
                    0 - exit application
                    1 - add new person
                    2 - display persons
                    3 - edit person
                    """);

            choice = consoleIO.getValidIntegerInput(0, 3);

            switch (choice) {
                case 0 -> System.exit(0);
                case 1 -> addPerson();
                case 2 -> displayPersonMenu();
                case 3 -> editPerson();
            }

        } while (true);
    }

    public void addPerson() {
        int choice = 0;
        do {
            //Logic to add new person

        } while (choice != 0);
    }

    public void displayPersonMenu() {
        int choice = 0;
        do {
            consoleIO.addString("""
                    choose option:
                    0 - Back to main menu
                    1 - Display all persons in database
                    2 - Search by person id
                    3 - Search by name
                    4 - Search by age
                    5 - Search by city
                    """);

            choice = consoleIO.getValidIntegerInput(0, 5);

            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    List<Person> allPersons = personServiceClient.allPersons();
                    for (Person p : allPersons) {
                        consoleIO.addString(p.toString());
                    }
                }
                case 2 -> {
                    consoleIO.addString("Enter search:\n");

                    Long searchId = consoleIO.getValidLongInput(0, Long.MAX_VALUE);

                    List<Person> foundPersons = personServiceClient.searchId(searchId);

                    for (Person p : foundPersons) {
                        consoleIO.addString(p.toString());
                    }
                }
                case 3 -> {
                    consoleIO.addString("Enter search:\n");
                    String searchName = consoleIO.getString();

                    List<Person> foundPersons = personServiceClient.searchName(searchName);

                    for (Person p : foundPersons) {
                        consoleIO.addString(p.toString());
                    }
                }
                case 4 -> {
                    consoleIO.addString("Enter search:\n");
                    int searchAge = consoleIO.getValidIntegerInput(0, Integer.MAX_VALUE);

                    try {
                        List<Person> foundPersons = personServiceClient.searchAge(searchAge);

                        for (Person p : foundPersons) {
                            consoleIO.addString(p.toString());
                        }
                    } catch (WebClientResponseException.NotFound e) {
                        consoleIO.addString("no results found\n");
                    }
                }
                case 5 -> {
                    consoleIO.addString("Enter search:\n");
                    String searchCity = consoleIO.getString();

                    List<Person> foundPersons = personServiceClient.searchCity(searchCity);

                    for (Person p : foundPersons) {
                        consoleIO.addString(p.toString());
                    }
                }
            }

        } while (choice != 0);
    }

    public void editPerson() {
        int choice = 0;
        do {
            consoleIO.addString("""
                    choose option:
                    0 - exit application
                    1 - Change name
                    2 - Change age
                    3 - Change city
                    """);

            choice = consoleIO.getValidIntegerInput(0, 3);

            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> consoleIO.addString("lägga till pers");
                case 2 -> consoleIO.addString("visa pers");
                case 3 -> consoleIO.addString("redigera pers");
            }

        } while (choice != 0);
    }


}
