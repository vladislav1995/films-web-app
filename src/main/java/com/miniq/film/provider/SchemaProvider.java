package com.miniq.film.provider;

import com.miniq.example.graphql.provider.AbstractSchemaProvider;
import com.miniq.film.resolver.MutationResolver;
import com.miniq.film.resolver.QueryResolver;
import graphql.schema.DataFetcher;
import graphql.schema.TypeResolver;
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

    @Override
    protected DataFetcher dataFetcher() {
        return null;
    }

    @Override
    protected TypeResolver typeResolver() {
        return null;
    }

}
