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

import org.eclipse.jdt.annotation.Nullable;

import com.bigtester.ate.tcg.model.domain.HTMLSource;
import com.bigtester.ate.tcg.model.domain.IndustryCategory;
import com.bigtester.ate.tcg.model.domain.ScreenActionElementTrainingRecord;
import com.bigtester.ate.tcg.model.domain.ScreenUserInputTrainingRecord;
import com.bigtester.ate.tcg.model.domain.ScreenUserClickInputTrainingRecord;
import com.bigtester.ate.tcg.model.domain.TestSuite;
import com.bigtester.ate.tcg.model.domain.WebElementTrainingRecord.UserInputType;

// TODO: Auto-generated Javadoc
/**
 * This class IntermediateResult is the frontend response holder
 * @author Peidong Hu
 *
 */
public class IntermediateResult {
	
	
	/**
	 * The Enum NonHtmlScreen.
	 */
	public enum ScreenType {
		HTML, WINDOWFILEPICKER
	}
	
	/** The same page update. */
	private boolean samePageUpdate;
	
	/** The screen type. */
	private ScreenType screenType = ScreenType.HTML; 
	
	/** The dom strings. */
	private Set<HTMLSource> domStrings = new HashSet<HTMLSource>();
	
	/** The uitrs. */
	private Set<ScreenUserInputTrainingRecord> uitrs = new HashSet<ScreenUserInputTrainingRecord>();
	
	/** The click uitrs. */
	private Set<ScreenUserClickInputTrainingRecord> clickUitrs = new HashSet<ScreenUserClickInputTrainingRecord>();
	
	/** The previous screen trigger uitrs. */
	@Nullable
	private ScreenUserClickInputTrainingRecord previousScreenTriggerClickUitr;
	
	
	/** The action uitrs. */
	private Set<ScreenActionElementTrainingRecord> actionUitrs = new HashSet<ScreenActionElementTrainingRecord>();
	
	/** The test suites map. */
	private List<TestSuite> testSuitesMap = new ArrayList<TestSuite>();
	
	/** The industry categories map. */
	private List<IndustryCategory> industryCategoriesMap = new ArrayList<IndustryCategory>();
	
	/** The test case name. */
	private String testCaseName="";
	
	/** The screen url. */
	
	private String screenUrl="";
	
	/** The domain name. */
	
	private String domainName="";
	
	/** The screen name. */
	private String screenName="";
	
	/** The last screen node. */
	@Nullable
	private IntermediateResult lastScreenNodeIntermediateResult;
	
	/** The last screen node neo4j id. */
	@Nullable
	private Long screenNodeNeo4jId;
	/**
	 * Instantiates a new intermediate result.
	 */
	public IntermediateResult() { //NOPMD 
		//For spring web services, an empty constructor has to be placed in models receiving web request.
		super();
	}
	
	/**
	 * Process uitr.
	 */
	public void processUitr() {
		for (ScreenUserInputTrainingRecord uitr: getUitrs()) {
			if (!uitr.getUserInputType().equals(UserInputType.SCREENJUMPER) || !uitr.getUserInputType().equals(UserInputType.USERINPUT)) {
				uitr = new ScreenUserClickInputTrainingRecord(uitr);
			}
		}
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
	 * @return the testSuitesMap
	 */
	public List<TestSuite> getTestSuitesMap() {
		return testSuitesMap;
	}
	/**
	 * @param testSuitesMap the testSuitesMap to set
	 */
	public void setTestSuitesMap(List<TestSuite> testSuitesMap) {
		this.testSuitesMap = testSuitesMap;
	}
	/**
	 * @return the industryCategoriesMap
	 */
	public List<IndustryCategory> getIndustryCategoriesMap() {
		return industryCategoriesMap;
	}
	/**
	 * @param industryCategoriesMap the industryCategoriesMap to set
	 */
	public void setIndustryCategoriesMap(
			List<IndustryCategory> industryCategoriesMap) {
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
	
	/**
	 * @return the lastScreenNodeIntermediateResult
	 */
	@Nullable
	public IntermediateResult getLastScreenNodeIntermediateResult() {
		return lastScreenNodeIntermediateResult;
	}
	/**
	 * @param lastScreenNodeIntermediateResult the lastScreenNodeIntermediateResult to set
	 */
	public void setLastScreenNodeIntermediateResult(
			IntermediateResult lastScreenNodeIntermediateResult) {
		this.lastScreenNodeIntermediateResult = lastScreenNodeIntermediateResult;
	}
	/**
	 * @return the screenNodeNeo4jId
	 */
	@Nullable
	public Long getScreenNodeNeo4jId() {
		return screenNodeNeo4jId;
	}
	/**
	 * @param screenNodeNeo4jId the screenNodeNeo4jId to set
	 */
	public void setScreenNodeNeo4jId(@Nullable Long screenNodeNeo4jId) {
		this.screenNodeNeo4jId = screenNodeNeo4jId;
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

	/**
	 * @return the screenType
	 */
	public ScreenType getScreenType() {
		return this.screenType;
	}

	/**
	 * @param screenType the screenType to set
	 */
	public void setScreenType(ScreenType screenType) {
		this.screenType = screenType;
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

	/**
	 * @return the samePageUpdate
	 */
	public boolean isSamePageUpdate() {
		return samePageUpdate;
	}

	/**
	 * @param samePageUpdate the samePageUpdate to set
	 */
	public void setSamePageUpdate(boolean samePageUpdate) {
		this.samePageUpdate = samePageUpdate;
	}

	
}
