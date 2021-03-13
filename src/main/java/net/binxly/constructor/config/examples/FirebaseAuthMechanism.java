//package net.binxly.constructor.config.examples;
//
//import io.quarkus.security.identity.IdentityProviderManager;
//import io.quarkus.security.identity.SecurityIdentity;
//import io.quarkus.security.identity.request.AuthenticationRequest;
//import io.quarkus.security.runtime.QuarkusSecurityIdentity;
//import io.quarkus.smallrye.jwt.runtime.auth.JWTAuthMechanism;
//import io.quarkus.vertx.http.runtime.security.ChallengeData;
//import io.quarkus.vertx.http.runtime.security.HttpAuthenticationMechanism;
//import io.quarkus.vertx.http.runtime.security.HttpCredentialTransport;
//import io.smallrye.mutiny.Uni;
//import io.vertx.ext.web.RoutingContext;
//import org.eclipse.microprofile.config.inject.ConfigProperty;
//
//import javax.annotation.Priority;
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.inject.Alternative;
//import javax.enterprise.inject.Instance;
//import javax.inject.Inject;
//import java.security.Principal;
//import java.util.Set;
//
//// this effectively acts as HTTP filter
//// and implements a custom authentication mechanism
//// article: https://quarkus.io/guides/security-customization
//
//@Alternative
//@Priority(1)
//@ApplicationScoped
//public class FirebaseAuthMechanism implements HttpAuthenticationMechanism {
//
//    @Inject
//    @ConfigProperty(name = "firebase.auth.active", defaultValue = "true")
//    Instance<Boolean> authActive;
//
//    @Inject
//    JWTAuthMechanism delegate;
//
//    @Override
//    public Uni<SecurityIdentity> authenticate(RoutingContext context, IdentityProviderManager identityProviderManager) {
//        System.out.println("$$$ Auth Mechanism $$$");
//        System.out.println(authActive);
//
//        if (authActive.get()) {
//            // check firebase auth
//            // build identity
//        }
//
//        QuarkusSecurityIdentity identity = QuarkusSecurityIdentity.builder()
//                .setPrincipal(buildPrinciple("user"))
//                .addRole("user")
//                .build();
//        return Uni.createFrom().item(identity);
//
////        return delegate.authenticate(context, identityProviderManager);
//    }
//
//    Principal buildPrinciple(String name) {
//        return new Principal() {
//            @Override
//            public String getName() {
//                return name;
//            }
//        };
//    }
//
//    @Override
//    public Uni<ChallengeData> getChallenge(RoutingContext context) {
//        return null;
//    }
//
//    @Override
//    public Set<Class<? extends AuthenticationRequest>> getCredentialTypes() {
//        return null;
//    }
//
//    @Override
//    public HttpCredentialTransport getCredentialTransport() {
//        return null;
//    }
//
//}