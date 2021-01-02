package it.gualtierotesta.manning.liveproject.businessserver.services;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@WithSecurityContext(factory = CustomSecurityContextFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@interface WithCustomUser {

    String username();

    String[] roles() default {"USER"};
}
