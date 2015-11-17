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
public class IndustryCategory extends BaseAteNode{
	
	/** The gid. */
	@GraphId
	@Nullable
	private Long gid; //NOPMD
	
	/** The code. */
	private String code = ""; 
	
	/** The name. */
	private String name;
	
	/** The industry categories. */
	@Relationship(type=Relations.CONTAINS_SUB_INDUSTRY_CATEGORY)
	@Nullable
	private Set<IndustryCategory> industryCategories= new HashSet<IndustryCategory>();

	/** The parent industry category. */
	@Relationship(type=Relations.PARENT_INDUSTRY_CATEGORY)
	@Nullable //if null, it's the root industry node
	private Set<IndustryCategory> parentIndustryCategory = new HashSet<IndustryCategory>();

	/**
	 * Instantiates a new industry category.
	 */
	public IndustryCategory() {
		super("IndustryCategory");
		this.name = "";
	}
	
	/**
	 * Instantiates a new test industry.
	 *
	 * @param code the code
	 * @param name the name
	 */
	public IndustryCategory(String name) {
		super("IndustryCategory");
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @return the industryCategories
	 */
	@Nullable
	public Set<IndustryCategory> getIndustryCategories() {
		return industryCategories;
	}

	/**
	 * @param industryCategories the industryCategories to set
	 */
	public void setIndustryCategories(Set<IndustryCategory> industryCategories) {
		this.industryCategories = industryCategories;
	}

	/**
	 * @return the parentIndustryCategory
	 */
	@Nullable
	public Set<IndustryCategory> getParentIndustryCategory() {
		return parentIndustryCategory;
	}

	/**
	 * @param parentIndustryCategory the parentIndustryCategory to set
	 */
	public void setParentIndustryCategory(
			Set<IndustryCategory> parentIndustryCategory) {
		this.parentIndustryCategory = parentIndustryCategory;
	}

	
}
