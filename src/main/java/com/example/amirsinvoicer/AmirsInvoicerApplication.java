package com.example.amirsinvoicer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
public class AmirsInvoicerApplication {


    public static void main(String[] args) {
      SpringApplication.run(AmirsInvoicerApplication.class, args);
    }

}
@Component
class SwaggerUiLauncher {

    @EventListener(ApplicationReadyEvent.class)
    public void launchSwaggerUi() {
        try {
            String url = "http://localhost:8080/swagger-ui/index.html";
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                System.out.println("Desktop browse not supported. Open manually: " + url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}