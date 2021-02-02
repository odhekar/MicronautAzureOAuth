package com.odhekar;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;

@Controller("/hello")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class HelloController {
    private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);

    public HttpResponse anonymous(@Nullable Authentication authentication) {
        LOG.info("Hello anonymous! :-(");
        LOG.info("Authentication == null: {}", authentication == null);
        return HttpResponse.ok("Hello anonymous! :-(");
    }

    @Get("authenticated")
    @Secured({"HelloRole", "WorldRole"})
    public HttpResponse authenticated(@Nullable Authentication authentication) {
        LOG.info("Hello Authenticated! :-)");
        LOG.info("Name: {}", authentication.getName());
        LOG.info("Audience: {}", authentication.getAttributes().get("aud"));
        LOG.info("Azp: {}", authentication.getAttributes().get("azp"));
        LOG.info("Roles: {}", authentication.getAttributes().get("roles"));
        return HttpResponse.ok("Hello Authenticated! :-)");
    }
}
