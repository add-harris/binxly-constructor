package net.binxly.constructor.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;


@ApplicationScoped
public class FirebaseAdmin {

    @Inject
    @ConfigProperty(name = "firebase.auth.active", defaultValue = "true")
    Instance<Boolean> authActive;

    void startup(@Observes StartupEvent event) throws IOException {

        System.out.println("Auth active: " + authActive.get());

        if (authActive.get()) {
            // this is extra resilience to deal with hot reloading
            // to eventually be removed
            try {
                FirebaseApp.getInstance();
                System.out.println("Firebase App already exists");
            } catch (IllegalStateException e) {
                System.out.println("Initializing Firebase App");
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.getApplicationDefault())
                        .build();
                FirebaseApp.initializeApp(options);
            }

        }
    }

}
