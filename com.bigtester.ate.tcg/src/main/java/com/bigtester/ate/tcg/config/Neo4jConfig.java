/*******************************************************************************
 * ATE, Automation Test Engine
 *
 * Copyright 2015, Montreal PROT, or individual contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Montreal PROT.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.bigtester.ate.tcg.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.GraphRepositoryFactory;
import org.springframework.data.neo4j.rest.SpringCypherRestGraphDatabase;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.support.mapping.Neo4jMappingContext;

// TODO: Auto-generated Javadoc
/**
 * This class Neo4jConfig defines ....
 * @author Peidong Hu
 *
 */
@Configuration
@EnableNeo4jRepositories(basePackages = "com.bigtester.ate.tcg")
public class Neo4jConfig extends Neo4jConfiguration {

    /**
     * Graph database service.
     *
     * @return the graph database service
     */
    @Bean
    public GraphDatabaseService graphDatabaseService() {
        return new SpringCypherRestGraphDatabase("http://172.16.173.50:7474/db/data/", "neo4j", "hello1234567");
    }
    @Bean
    public Neo4jTemplate neo4jTemplate() {
        return new Neo4jTemplate(graphDatabaseService());
    }

    @Bean
    public GraphRepositoryFactory graphRepositoryFactory() {
        return new GraphRepositoryFactory(neo4jTemplate(), neo4jMappingContext());
    }

    @Bean
    public Neo4jMappingContext neo4jMappingContext() {
        return new Neo4jMappingContext();
    }
    
}
