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
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.bigtester.ate.tcg.model.relationship.Relations;

// TODO: Auto-generated Javadoc
/**
 * This class TestIndustry defines ....
 * @author Peidong Hu
 *
 */
@NodeEntity
public class TestSuite {
	
	/** The gid. */
	@GraphId
	@Nullable
	private Long gid; //NOPMD
	
	/** The name. */
	private String name;
	
	/** The industry categories. */
	@Relationship(type=Relations.CONTAINS_SUB_TEST_SUITE)
	@Nullable
	private Set<TestSuite> subTestSuites= new HashSet<TestSuite>();

	/** The parent industry category. */
	@Relationship(type=Relations.PARENT_TEST_SUITE)
	@Nullable //if null, it's the root industry node
	private TestSuite parentTestSuite;

	/**
	 * Instantiates a new test suite. Constructor for web service call
	 */
	public TestSuite() {
		super();
		this.name = "";
	}
	/**
	 * Instantiates a new test industry.
	 *
	 * @param code the code
	 * @param name the name
	 */
	public TestSuite(String name) {
		this.name = name;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the gid
	 */
	@Nullable
	public Long getGid() {
		return gid;
	}

	/**
	 * @param gid the gid to set
	 */
	public void setGid(Long gid) {
		this.gid = gid;
	}




	/**
	 * @return the parentTestSuite
	 */
	@Nullable
	public TestSuite getParentTestSuite() {
		return parentTestSuite;
	}


	/**
	 * @param parentTestSuite the parentTestSuite to set
	 */
	public void setParentTestSuite(TestSuite parentTestSuite) {
		this.parentTestSuite = parentTestSuite;
	}


	/**
	 * @return the subTestSuites
	 */
	@Nullable
	public Set<TestSuite> getSubTestSuites() {
		return subTestSuites;
	}


	/**
	 * @param subTestSuites the subTestSuites to set
	 */
	public void setSubTestSuites(Set<TestSuite> subTestSuites) {
		this.subTestSuites = subTestSuites;
	}
}