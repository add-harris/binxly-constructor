package net.binxly.constructor.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;

@Provider
@PreMatching
public class AuthorizationFilter implements ContainerRequestFilter {

    @Inject
    @ConfigProperty(name = "firebase.auth.active", defaultValue = "true")
    Instance<Boolean> authActive;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        System.out.println("Auth active: " + authActive.get());


        if (authActive.get()) {
            System.out.println("Authenticating Request");
            var authHeader = requestContext.getHeaderString("Authorization");
            var token = authHeader.substring(7);
            System.out.println(token);

            FirebaseToken decodedToken = null;
            String uid;
            try {
                decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
                uid = decodedToken.getUid();
                requestContext.setSecurityContext(buildSecurityContext(uid, true));
            } catch (FirebaseAuthException e) {
                e.printStackTrace();
                requestContext.setSecurityContext(buildSecurityContext(null, false));
            }

        } else {
            System.out.println("Fake auth done");
            requestContext.setSecurityContext(buildSecurityContext("fakeuser", true));
        }

    }

    Principal buildPrinciple(String name) {
        return new Principal() {
            @Override
            public String getName() {
                return name;
            }
        };
    }

    SecurityContext buildSecurityContext(String id, Boolean authenticated) {
        return new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return buildPrinciple(id);
            }

            @Override
            public boolean isUserInRole(String s) {
                return true;
            }

            @Override
            public boolean isSecure() {
                return authenticated;
            }

            @Override
            public String getAuthenticationScheme() {
                return "basic";
            }
        };
    }
}
