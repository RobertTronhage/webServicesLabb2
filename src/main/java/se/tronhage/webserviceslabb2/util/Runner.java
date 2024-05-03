/**
 * Runner class implementing CommandLineRunner, entry point of application.
 * @Author: Robert Tronhage
 */
package se.tronhage.webserviceslabb2.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import se.tronhage.webserviceslabb2.io.ConsoleIO;
import se.tronhage.webserviceslabb2.io.ConsoleMenu;

@Configuration
public class Runner implements CommandLineRunner {

    @Autowired
    ConsoleMenu consoleMenu;

    @Override
    public void run(String... args) throws Exception {
        consoleMenu.mainMenu();
    }
}
