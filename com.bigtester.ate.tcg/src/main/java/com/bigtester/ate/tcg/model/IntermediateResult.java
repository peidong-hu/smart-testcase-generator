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
package com.bigtester.ate.tcg.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bigtester.ate.tcg.model.domain.HTMLSource;
import com.bigtester.ate.tcg.model.domain.IndustryCategory;
import com.bigtester.ate.tcg.model.domain.TestSuite;
import com.bigtester.ate.tcg.model.domain.UserInputTrainingRecord;

// TODO: Auto-generated Javadoc
/**
 * This class IntermediateResult is the frontend response holder
 * @author Peidong Hu
 *
 */
public class IntermediateResult {
	
	/** The dom strings. */
	private Set<HTMLSource> domStrings = new HashSet<HTMLSource>();
	
	/** The uitrs. */
	private Set<UserInputTrainingRecord> uitrs = new HashSet<UserInputTrainingRecord>();
	
	/** The test suites map. */
	private Set<TestSuite> testSuitesMap = new HashSet<TestSuite>();
	
	/** The industry categories map. */
	private Set<IndustryCategory> industryCategoriesMap = new HashSet<IndustryCategory>();
	
	/** The test case name. */
	private String testCaseName="";
	
	/** The screen url. */
	
	private String screenUrl="";
	
	/** The domain name. */
	
	private String domainName="";
	
	/** The screen name. */
	private String screenName="";
	/**
	 * Instantiates a new intermediate result.
	 */
	public IntermediateResult() { //NOPMD 
		//For spring web services, an empty constructor has to be placed in models receiving web request.
		super();
	}
	/**
	 * @return the domStrings
	 */
	public Set<HTMLSource> getDomStrings() {
		return domStrings;
	}
	/**
	 * @param domStrings the domStrings to set
	 */
	public void setDomStrings(Set<HTMLSource> domStrings) {
		this.domStrings = domStrings;
	}
	/**
	 * @return the uitrs
	 */
	public Set<UserInputTrainingRecord> getUitrs() {
		return uitrs;
	}
	/**
	 * @param uitrs the uitrs to set
	 */
	public void setUitrs(Set<UserInputTrainingRecord> uitrs) {
		this.uitrs = uitrs;
	}
	/**
	 * @return the testSuitesMap
	 */
	public Set<TestSuite> getTestSuitesMap() {
		return testSuitesMap;
	}
	/**
	 * @param testSuitesMap the testSuitesMap to set
	 */
	public void setTestSuitesMap(Set<TestSuite> testSuitesMap) {
		this.testSuitesMap = testSuitesMap;
	}
	/**
	 * @return the industryCategoriesMap
	 */
	public Set<IndustryCategory> getIndustryCategoriesMap() {
		return industryCategoriesMap;
	}
	/**
	 * @param industryCategoriesMap the industryCategoriesMap to set
	 */
	public void setIndustryCategoriesMap(
			Set<IndustryCategory> industryCategoriesMap) {
		this.industryCategoriesMap = industryCategoriesMap;
	}
	/**
	 * @return the testCaseName
	 */
	public String getTestCaseName() {
		return testCaseName;
	}
	/**
	 * @param testCaseName the testCaseName to set
	 */
	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}
	/**
	 * @return the screenUrl
	 */
	public String getScreenUrl() {
		return screenUrl;
	}
	/**
	 * @param screenUrl the screenUrl to set
	 */
	public void setScreenUrl(String screenUrl) {
		this.screenUrl = screenUrl;
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
	 * @return the screenName
	 */
	public String getScreenName() {
		return screenName;
	}
	/**
	 * @param screenName the screenName to set
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
	
}
