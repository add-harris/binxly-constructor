//package net.binxly.constructor.config.examples;
//
//import io.quarkus.arc.AlternativePriority;
//import io.quarkus.arc.DefaultBean;
//import io.quarkus.arc.profile.IfBuildProfile;
//import io.quarkus.vertx.http.runtime.security.HttpAuthenticationMechanism;
//
//import javax.annotation.Priority;
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.context.Dependent;
//import javax.enterprise.inject.Alternative;
//import javax.enterprise.inject.Produces;
//
//@Dependent
//public class FirebaseAuthConfig {
//
//    @Produces
//    @IfBuildProfile("dev")
//    @AlternativePriority(1)
//    HttpAuthenticationMechanism realMechanism() {
//        return new FirebaseAuthMechanism();
//    }
//
//    @Produces
//    @DefaultBean
//    @AlternativePriority(1)
//    HttpAuthenticationMechanism fakeMechanism() {
//        return new FakeAuthMechanism();
//    }
//
//}
