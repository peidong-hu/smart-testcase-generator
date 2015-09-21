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

import java.util.HashSet;
import java.util.Set;
import org.eclipse.jdt.annotation.Nullable;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import com.bigtester.ate.tcg.model.ATENeo4jNodeComparision;
import com.bigtester.ate.tcg.model.IntermediateResult;
import com.bigtester.ate.tcg.model.relationship.Relations;

// TODO: Auto-generated Javadoc
/**
 * This class Neo4jScreenNode defines ....
 * 
 * @author Peidong Hu
 *
 */
@NodeEntity
public class Neo4jScreenNode extends AbstractScreenNode  implements ATENeo4jNodeComparision {

//	/** The id. */
//	@GraphId
//	@Nullable
//	private Long id; // NOPMD
//
//	/** The name. */
//	private String name = "";
//
//	/** The url. */
//	private String url = "";

	/** The sourcing doms. */
	@Relationship(type = Relations.SOURCING_DOMS)
	private Set<HTMLSource> sourcingDoms = new HashSet<HTMLSource>();

	/** The click uitrs. */
	@Relationship(type = Relations.PREDICTED_USER_INPUT_RESULTS)
	private Set<ScreenUserClickInputTrainingRecord> clickUitrs = new HashSet<ScreenUserClickInputTrainingRecord>();
	
	/** The action uitrs. */
	@Relationship(type = Relations.PREDICTED_USER_ACTIONELEMENT_RESULTS)
	private Set<ScreenActionElementTrainingRecord> actionUitrs = new HashSet<ScreenActionElementTrainingRecord>();

//	/** The testcases. */
//	@Relationship(type = Relations.IN_TESTCASE)
//	private Collection<TestCase> testcases = new HashSet<TestCase>();

	
	/**
	 * Instantiates a new neo4j screen node. no-arg constructor for restful
	 * call.
	 */
	public Neo4jScreenNode() {
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
	public Neo4jScreenNode(String name, String url, IntermediateResult iResult) {
//		this.name = name;
//		this.url = url;
		super(name, url);
		this.sourcingDoms = iResult.getDomStrings();
		setUitrs(iResult.getUitrs());
		this.clickUitrs = iResult.getClickUitrs();
		this.actionUitrs = iResult.getActionUitrs();
	}

//	/**
//	 * @return the id
//	 */
//	@Nullable
//	public Long getId() {
//		return id;
//	}

//	/**
//	 * @param id
//	 *            the id to set
//	 */
//	public void setId(Long id) {// NOPMD
//		this.id = id;
//	}

//	/**
//	 * @return the name
//	 */
//	public String getName() {
//		return name;
//	}
//
//	/**
//	 * @param name
//	 *            the name to set
//	 */
//	public void setName(String name) {
//		this.name = name;
//	}

//	/**
//	 * @return the url
//	 */
//	public String getUrl() {
//		return url;
//	}
//
//	/**
//	 * @param url
//	 *            the url to set
//	 */
//	public void setUrl(String url) {
//		this.url = url;
//	}

	/**
	 * @return the sourcingDoms
	 */
	public Set<HTMLSource> getSourcingDoms() {
		return sourcingDoms;
	}

	/**
	 * @param sourcingDoms
	 *            the sourcingDoms to set
	 */
	public void setSourcingDoms(Set<HTMLSource> sourcingDoms) {
		this.sourcingDoms = sourcingDoms;
	}

//	/**
//	 * @return the testcases
//	 */
//	public Collection<TestCase> getTestcases() {
//		return testcases;
//	}
//
//	/**
//	 * @param testcases
//	 *            the testcases to set
//	 */
//	public void setTestcases(Collection<TestCase> testcases) {
//		this.testcases = testcases;
//	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean sameNode(@Nullable Object obj) {
		boolean retVal = false;//NOPMD
		if (obj instanceof Neo4jScreenNode) {
			retVal = ((Neo4jScreenNode) obj).getName() == this.getName()
					&& ((Neo4jScreenNode) obj).getUrl() == this.getUrl();
		}
		return retVal;
	}


	/**
	 * @return the actionUitrs
	 */
	public Set<ScreenActionElementTrainingRecord> getActionUitrs() {
		return actionUitrs;
	}

	/**
	 * @param actionUitrs the actionUitrs to set
	 */
	public void setActionUitrs(Set<ScreenActionElementTrainingRecord> actionUitrs) {
		this.actionUitrs = actionUitrs;
	}



	/**
	 * @return the clickUitrs
	 */
	public Set<ScreenUserClickInputTrainingRecord> getClickUitrs() {
		return clickUitrs;
	}

	/**
	 * @param clickUitrs the clickUitrs to set
	 */
	public void setClickUitrs(Set<ScreenUserClickInputTrainingRecord> clickUitrs) {
		this.clickUitrs = clickUitrs;
	}

}
