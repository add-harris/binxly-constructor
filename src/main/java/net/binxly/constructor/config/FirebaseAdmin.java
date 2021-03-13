package net.binxly.constructor.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import io.quarkus.runtime.StartupEvent;

import java.io.IOException;


@ApplicationScoped
public class FirebaseAdmin {

    void startup(@Observes StartupEvent event) throws IOException {
        System.out.println("Initializing Firebase App");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .build();

        FirebaseApp.initializeApp(options);
    }



}
