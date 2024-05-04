package se.tronhage.webserviceslabb2.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import se.tronhage.webserviceslabb2.io.IO;
import se.tronhage.webserviceslabb2.model.Person;

import java.util.List;

@Component
public class PersonServiceClient {

    @Autowired
    IO io;
    WebClient client = WebClient.create("http://localhost:8080");

    public List<Person> allPersons() {
        Flux<Person> personFlux = client
                .get()
                .uri("/rs/getall")
                .retrieve()
                .bodyToFlux(Person.class);

        return personFlux.collectList().block();
    }

    public List<Person> searchName(String name) {
        Flux<Person> personFlux = client
                .get()
                .uri("/rs/search/name/{name}", name)
                .retrieve()
                .bodyToFlux(Person.class);

        return personFlux.collectList().block();
    }

    public List<Person> searchId(Long id) {
        Flux<Person> personFlux = client
                .get()
                .uri("/rs/search/id/{id}", id)
                .retrieve()
                .bodyToFlux(Person.class);

        return personFlux.collectList().block();
    }

    public List<Person> searchAge(int age) {
        Flux<Person> personFlux = client
                .get()
                .uri("/rs/search/age/{age}", age)
                .retrieve()
                .bodyToFlux(Person.class);

        return personFlux.collectList().block();
    }

    public List<Person> searchCity(String city) {
        Flux<Person> personFlux = client
                .get()
                .uri("/rs/search/city/{city}", city)
                .retrieve()
                .bodyToFlux(Person.class);

        return personFlux.collectList().block();
    }

    public void addNewPerson(Person p) {
        try {

            client.post()
                    .uri("rs/addperson")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(p))
                    .retrieve()
                    .bodyToMono(Person.class)
                    .subscribe(response -> {
                        io.addString("Person added successfully: " + response.toString());
                    }, error -> {
                        io.addString("Failed to add person: " + error.getMessage());
                    });

        } catch (Exception e) {
            io.addString("An error occurred: " + e.getMessage());
        }
    }

    public void editPerson(Long id, Person p){
        try {

            client.put()
                    .uri("/rs/update/{id}", id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(p))
                    .retrieve()
                    .toBodilessEntity()
                    .subscribe(response -> {
                        io.addString("Person with id " + id + " updated successfully");
                    }, error -> {
                        io.addString("Failed to update person: " + error.getMessage());
                    });
        } catch (Exception e) {
            io.addString("An error occurred: " + e.getMessage());
        }
    }

}
