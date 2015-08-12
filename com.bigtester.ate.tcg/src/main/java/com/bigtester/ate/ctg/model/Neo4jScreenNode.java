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
package com.bigtester.ate.ctg.model;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

// TODO: Auto-generated Javadoc
/**
 * This class Neo4jScreenNode defines ....
 * 
 * @author Peidong Hu
 *
 */
@NodeEntity
public class Neo4jScreenNode {

	/** The id. */
	@GraphId
	private Long id;

	/** The name. */
	@Indexed(indexType = IndexType.FULLTEXT, indexName = "screenName")
	private String name;

	/** The url. */
	@Indexed(indexType = IndexType.FULLTEXT, indexName = "screenUrl")
	private String url;
	
	private String testS = "testabc";
	
	/** The intermediate result. */
	private IntermediateResult intermediateResult;

	@RelatedTo(type = "STEP_INTO", direction = Direction.INCOMING)
	private Set<Neo4jScreenNode> screenNodes;

	public Neo4jScreenNode() {
		
	}
	
	public Neo4jScreenNode(String name, String url, IntermediateResult iResult) {
		this.name = name;
		this.url = url;
		this.intermediateResult = iResult;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the intermediateResult
	 */
	public IntermediateResult getIntermediateResult() {
		return intermediateResult;
	}

	/**
	 * @param intermediateResult
	 *            the intermediateResult to set
	 */
	public void setIntermediateResult(IntermediateResult intermediateResult) {
		this.intermediateResult = intermediateResult;
	}

	/**
	 * @return the screenNodes
	 */
	public Set<Neo4jScreenNode> getScreenNodes() {
		return screenNodes;
	}

	/**
	 * @param screenNodes
	 *            the screenNodes to set
	 */
	public void setScreenNodes(Set<Neo4jScreenNode> screenNodes) {
		this.screenNodes = screenNodes;
	}

	/**
	 * @return the testS
	 */
	public String getTestS() {
		return testS;
	}

	/**
	 * @param testS the testS to set
	 */
	public void setTestS(String testS) {
		this.testS = testS;
	}

	// @RelatedToVia(type = "RATED")
	// Iterable<Rating> ratings;
	//
	// @Query("start movie=node({self}) match
	// movie-->genre<--similar return similar")
	// Iterable<Movie> similarMovies;
}
