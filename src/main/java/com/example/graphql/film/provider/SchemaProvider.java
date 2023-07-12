package com.example.graphql.film.provider;

import com.example.graphql.provider.AbstractSchemaProvider;
import com.example.graphql.film.resolver.MutationResolver;
import com.example.graphql.film.resolver.QueryResolver;
import graphql.schema.idl.RuntimeWiring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

/**
 * @author Uladik
 */
@Component
public class SchemaProvider extends AbstractSchemaProvider {

    @Autowired
    private QueryResolver queryResolver;

    @Autowired
    private MutationResolver mutationResolver;

    @Autowired
    public SchemaProvider(@Qualifier("schemaFileName") String schemaFileName) throws IOException {
        super(schemaFileName);
    }

    @Override
    protected RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                    .dataFetcher("getFilmById", queryResolver.getFilmById())
                    .dataFetcher("getFilmsByName", queryResolver.getFilmsByName()))
                .type(newTypeWiring("Mutation")
                    .dataFetcher("putFilm", mutationResolver.putFilm()))
                .build();
    }

}
