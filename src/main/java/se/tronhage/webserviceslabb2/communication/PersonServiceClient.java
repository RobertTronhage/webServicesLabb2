package se.tronhage.webserviceslabb2.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import se.tronhage.webserviceslabb2.io.IO;
import se.tronhage.webserviceslabb2.model.Person;

import java.util.List;

@Component
public class PersonServiceClient {
//                    1 - Display all persons in database
//                    2 - Search by free text
//                    3 - Search by age
//                    4 - Search by city

    @Autowired
    IO io;
    WebClient client = WebClient.create("http://localhost:8080");

    public List<Person> allPersons(){
        Flux<Person> personFlux = client
                .get()
                .uri("/rs/getall")
                .retrieve()
                .bodyToFlux(Person.class);

        return personFlux.collectList().block();
    }

    public List<Person> searchName(String name){
        Flux<Person> personFlux = client
                .get()
                .uri("/rs/search/name/{name}", name)
                .retrieve()
                .bodyToFlux(Person.class);

        return personFlux.collectList().block();
    }
}
