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

import org.eclipse.jdt.annotation.Nullable;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.data.neo4j.template.Neo4jTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// TODO: Auto-generated Javadoc
/**
 * This class Neo4jConfig defines ....
 * @author Peidong Hu
 *
 */
@Configuration
@EnableNeo4jRepositories(basePackages = "com.bigtester.ate.tcg")
@EnableTransactionManagement
public class Neo4jConfig extends Neo4jConfiguration {
	
	/** The Constant NEO4JSERVER. */
	public static final String NEO4JSERVER_URL = "http://172.16.173.140:7474";

    
    /**
     * {@inheritDoc}
     * @throws Exception 
    */
    @Bean
    public Neo4jTemplate neo4jTemplate() throws Exception {//NOPMD
        return new Neo4jTemplate(getSession());
    }


	/**
	 * {@inheritDoc}
	*/
	@Override
	public SessionFactory getSessionFactory() {
		return new SessionFactory("com.bigtester.ate.tcg.model.domain", "com.bigtester.ate.tcg.model.relationship");
	}

	/**
	 * {@inheritDoc}
	*/
	@Override
	public Neo4jServer neo4jServer() {
		return new RemoteServer(NEO4JSERVER_URL);
	}
	
	/**
	 * {@inheritDoc}
	*/
	@Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public @Nullable Session getSession() throws Exception {//NOPMD
        return super.getSession();
    }
}
