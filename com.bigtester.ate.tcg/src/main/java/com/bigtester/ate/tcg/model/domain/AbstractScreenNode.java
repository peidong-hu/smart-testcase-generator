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
package com.bigtester.ate.tcg.model.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.annotation.Nullable;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Relationship;

import com.bigtester.ate.tcg.model.relationship.Relations;

// TODO: Auto-generated Javadoc
/**
 * This class Neo4jScreenNode defines ....
 * 
 * @author Peidong Hu
 *
 */
public abstract class AbstractScreenNode{//NOPMD

	/** The id. */
	@GraphId
	@Nullable
	private Long id; // NOPMD

	/** The name. */
	private String name = "";

	/** The url. */
	private String url = "";

	/** The testcases. */
	@Relationship(type = Relations.IN_TESTCASE)
	private Collection<TestCase> testcases = new HashSet<TestCase>();

	/** The predicted user input results. */
	@Relationship(type = Relations.PREDICTED_USER_INPUT_RESULTS)
	private Set<ScreenUserInputTrainingRecord> uitrs = new HashSet<ScreenUserInputTrainingRecord>();
	
	/** The previous screen trigger uitrs. */
	@Nullable
	private ScreenUserClickInputTrainingRecord previousScreenTriggerClickUitr;
	
	/**
	 * Instantiates a new neo4j screen node. no-arg constructor for restful
	 * call.
	 */
	public AbstractScreenNode() {
		super();
	}

	/**
	 * Instantiates a new neo4j screen node.
	 *
	 * @param name
	 *            the name
	 * @param url
	 *            the url
	 * @param iResult
	 *            the i result
	 */
	public AbstractScreenNode(String name, String url) {
		this.name = name;
		this.url = url;
	}

	/**
	 * @return the id
	 */
	@Nullable
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {// NOPMD
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
	 * @return the testcases
	 */
	public Collection<TestCase> getTestcases() {
		return testcases;
	}

	/**
	 * @param testcases
	 *            the testcases to set
	 */
	public void setTestcases(Collection<TestCase> testcases) {
		this.testcases = testcases;
	}
	/**
	 * @return the uitrs
	 */
	public Set<ScreenUserInputTrainingRecord> getUitrs() {
		return uitrs;
	}

	/**
	 * @param uitrs the uitrs to set
	 */
	public void setUitrs(Set<ScreenUserInputTrainingRecord> uitrs) {
		this.uitrs = uitrs;
	}

	/**
	 * @return the previousScreenTriggerClickUitr
	 */
	@Nullable
	public ScreenUserClickInputTrainingRecord getPreviousScreenTriggerClickUitr() {
		return previousScreenTriggerClickUitr;
	}

	/**
	 * @param previousScreenTriggerClickUitr the previousScreenTriggerClickUitr to set
	 */
	public void setPreviousScreenTriggerClickUitr(
			ScreenUserClickInputTrainingRecord previousScreenTriggerClickUitr) {
		this.previousScreenTriggerClickUitr = previousScreenTriggerClickUitr;
	}
}
