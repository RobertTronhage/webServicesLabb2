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
    IO io;

    @Autowired
    PersonServiceClient personServiceClient;

    public void mainMenu() {
        int choice = 0;

        do {
            io.addString("""
                    choose option:
                    0 - exit application
                    1 - add new person
                    2 - display persons
                    3 - edit person
                    """);

            choice = io.getValidIntegerInput(0, 3);

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
            io.addString("Enter '0' to abort");
            io.addString("Enter Person id and press enter:\n");
            Long id = io.getValidLongInput(0,Long.MAX_VALUE);

            if (id == 0){
                break;
            }

            io.addString("Enter full name:\n");
            String name = io.getString();

            io.addString("enter age:\n");
            int age = io.getValidIntegerInput(0,Integer.MAX_VALUE);

            io.addString("Enter city:\n");
            String city = io.getString();

            Person p = new Person(id,name,age,city);

            personServiceClient.addNewPerson(p);

            io.addString("Person added successfully");

            io.addString("Do you want to add another person? Enter '1' for Yes, '0' to abort:");
            choice = io.getValidIntegerInput(0, 1);

        } while (choice != 0);
    }

    public void displayPersonMenu() {
        int choice = 0;
        do {
            io.addString("""
                    choose option:
                    0 - Back to main menu
                    1 - Display all persons in database
                    2 - Search by person id
                    3 - Search by name
                    4 - Search by age
                    5 - Search by city
                    """);

            choice = io.getValidIntegerInput(0, 5);

            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> displayAllPersons();
                case 2 -> searchById();
                case 3 -> searchByName();
                case 4 -> searchByAge();
                case 5 -> searchByCity();
            }

        } while (choice != 0);
    }

    private void searchById() {
        io.addString("Enter search:\n");

        Long searchId = io.getValidLongInput(0, Long.MAX_VALUE);

        List<Person> foundPersons = personServiceClient.searchId(searchId);

        for (Person p : foundPersons) {
            io.addString(p.toString());
        }
    }

    private void searchByName() {
        io.addString("Enter search:\n");
        String searchName = io.getString();

        List<Person> foundPersons = personServiceClient.searchName(searchName);

        for (Person p : foundPersons) {
            io.addString(p.toString());
        }
    }

    private void searchByAge() {
        io.addString("Enter search:\n");
        int searchAge = io.getValidIntegerInput(0, Integer.MAX_VALUE);

        try {
            List<Person> foundPersons = personServiceClient.searchAge(searchAge);

            for (Person p : foundPersons) {
                io.addString(p.toString());
            }
        } catch (WebClientResponseException.NotFound e) {
            io.addString("no results found\n");
        }
    }

    private void searchByCity() {
        io.addString("Enter search:\n");
        String searchCity = io.getString();

        List<Person> foundPersons = personServiceClient.searchCity(searchCity);

        for (Person p : foundPersons) {
            io.addString(p.toString());
        }
    }

    private void displayAllPersons() {
        List<Person> allPersons = personServiceClient.allPersons();
        int index = 1;
        for (Person p : allPersons) {
            io.addString(index + ": " + p.toString());
            index ++;
        }
    }

    public Person selectPerson(){
        int choosePersonByIndex = 0;
        Person selectedPerson = null;
        do {
            displayAllPersons();

            io.addString("Type '0' to abort\n" +
                    "Choose number of person you want to edit:\n");
            choosePersonByIndex = io.getValidIntegerInput(0,Integer.MAX_VALUE);

            if (choosePersonByIndex == 0){
                break;
            } else {
                List<Person> allPersons = personServiceClient.allPersons();
                if (choosePersonByIndex > 0 && choosePersonByIndex <= allPersons.size()){
                    selectedPerson = allPersons.get(choosePersonByIndex -1);
                    io.addString("you have selected " + selectedPerson.toString());
                }else {
                    io.addString("invalid input choose a valid number or enter 0 to cancel.\n");
                }
            }

        } while (selectedPerson == null);

        return selectedPerson;
    }

    public void editPerson() {
        int choice = 0;
        do {

            Person personToEdit = selectPerson();

            io.addString("""
                    choose option:
                    0 - Back to main menu
                    1 - Change name
                    2 - Change age
                    3 - Change city
                    4 - Delete person
                    """);

            choice = io.getValidIntegerInput(0, 4);

            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    updateName(personToEdit);
                    return;
                }
                case 2 -> {
                    updateAge(personToEdit);
                    return;
                }
                case 3 -> {
                    updateCity(personToEdit);
                    return;
                }
                case 4 -> {
                    deletePerson(personToEdit);
                    return;
                }
            }

        } while (choice != 0);
    }

    public void updateName(Person p){

        io.addString("choose new name for person:\n");
        String newName = io.getString();
        p.setName(newName);
        personServiceClient.editPerson(p.getId(),p);
    }

    public void updateAge(Person p){
        io.addString("choose new age for person:\n");
        int newAge = io.getValidIntegerInput(0,Integer.MAX_VALUE);
        p.setAge(newAge);
        personServiceClient.editPerson(p.getId(),p);
    }

    public void updateCity(Person p){
        io.addString("choose new city for person:\n");
        String newCity = io.getString();
        p.setCity(newCity);
        personServiceClient.editPerson(p.getId(),p);
    }

    public void deletePerson(Person p){
        io.addString("are you sure you want to delete " + p.getName() + " from database?\n" +
                "type '0' to abort, '1' to delete person..");
        int choice = io.getValidIntegerInput(0,1);
        if (choice == 0){
            return;
        } else {
            personServiceClient.deletePerson(p.getId(),p);
        }
    }

}
