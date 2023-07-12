package com.example.graphql.film.application;

import com.example.graphql.film.resource.GraphQLResource;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Uladik
 */
public class Application extends ResourceConfig {

    public Application() {
        register(GraphQLResource.class);
    }

}
