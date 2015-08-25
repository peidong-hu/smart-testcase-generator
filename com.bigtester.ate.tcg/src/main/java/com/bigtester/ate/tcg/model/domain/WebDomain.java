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
public class WebDomain {
	
	/** The gid. */
	@GraphId
	@Nullable
	private Long gid; //NOPMD
	
	/** The name. */
	private String domainName;
	
	/** The testcases. */
	@Relationship(type = Relations.CONTAINS_SCREEN)
	private Collection<Neo4jScreenNode> screens = new HashSet<Neo4jScreenNode>();
	
	/**
	 * Instantiates a new web domain.
	 */
	public WebDomain() {
		super();
		domainName = "";
	}
	
	/**
	 * Instantiates a new test industry.
	 *
	 * @param code the code
	 * @param name the name
	 */
	public WebDomain(String domainName) {
		this.domainName = domainName;
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
	 * @return the domainName
	 */
	public String getDomainName() {
		return domainName;
	}
	/**
	 * @param domainName the domainName to set
	 */
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	/**
	 * @return the screens
	 */
	public Collection<Neo4jScreenNode> getScreens() {
		return screens;
	}

	/**
	 * @param screens the screens to set
	 */
	public void setScreens(Collection<Neo4jScreenNode> screens) {
		this.screens = screens;
	}

}
