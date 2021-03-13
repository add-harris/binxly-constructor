//package net.binxly.constructor.config.examples;
//
//import io.quarkus.security.identity.IdentityProviderManager;
//import io.quarkus.security.identity.SecurityIdentity;
//import io.quarkus.security.identity.request.AuthenticationRequest;
//import io.quarkus.smallrye.jwt.runtime.auth.JWTAuthMechanism;
//import io.quarkus.vertx.http.runtime.security.ChallengeData;
//import io.quarkus.vertx.http.runtime.security.HttpAuthenticationMechanism;
//import io.quarkus.vertx.http.runtime.security.HttpCredentialTransport;
//import io.smallrye.mutiny.Uni;
//import io.vertx.ext.web.RoutingContext;
//
//import javax.annotation.Priority;
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.inject.Alternative;
//import javax.inject.Inject;
//import java.util.Set;
//
//@Alternative
//@Priority(1)
//@ApplicationScoped
//public class FakeAuthMechanism implements HttpAuthenticationMechanism {
//
//    @Inject
//    JWTAuthMechanism delegate;
//
//    @Override
//    public Uni<SecurityIdentity> authenticate(RoutingContext context, IdentityProviderManager identityProviderManager) {
//        System.out.println("%%%%%% Fake Auth %%%%%%");
//
//        return delegate.authenticate(context, identityProviderManager);
//    }
//
//    @Override
//    public Uni<ChallengeData> getChallenge(RoutingContext context) {
//        return delegate.getChallenge(context);
//    }
//
//    @Override
//    public Set<Class<? extends AuthenticationRequest>> getCredentialTypes() {
//        return delegate.getCredentialTypes();
//    }
//
//    @Override
//    public HttpCredentialTransport getCredentialTransport() {
//        return delegate.getCredentialTransport();
//    }
//}
