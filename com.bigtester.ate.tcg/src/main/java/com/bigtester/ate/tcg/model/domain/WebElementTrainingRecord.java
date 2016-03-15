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
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import com.bigtester.ate.tcg.model.relationship.Relations;

// TODO: Auto-generated Javadoc
/**
 * This class TrainingRecord defines ....
 * @author Peidong Hu
 *
 */

/**
 * @author ashraf
 *
 */
@NodeEntity
public class WebElementTrainingRecord extends BaseAteNode{
	
	/** The id. */
	@GraphId
	@Nullable
	private Long id;// NOPMD
	
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
	public void setId(@Nullable Long id) {//NOPMD required by neo4j
		this.id = id;
	}
	/** The action trigger. */
	private transient boolean actionTrigger;
	
	
	/** The belong to current test case. */
	private transient boolean belongToCurrentTestCase=true;
	
	/** The input label name. */
	@Property
	private String inputLabelName; // htmlLabelContent

	/** The input ml html code. */
	@Property
	private String inputMLHtmlCode;

	/** The trained result. */
	@Property
	private String trainedResult; // "__ATE__Error___" or succeed with eventid

	
	/** The pio predict label result. */
	@Relationship(type=Relations.PREDICTED_FIELD_NAME)
	private PredictedFieldName pioPredictLabelResult = new PredictedFieldName();	
	///** The user value. */
	//private String userValue;
	
	/** The pio predict confidence. */
	@Property
	private Double pioPredictConfidence = - 1.0;
	
	/** The user values. */
	@Relationship(type=Relations.USER_VALUES)
	private Set<UserInputValue> userValues = new HashSet<UserInputValue>();
	
	/** The testcases. */
	@Relationship(type = Relations.IN_TESTCASE)
	private Collection<TestCase> testcases = new HashSet<TestCase>();
	
	/** The user input type. */
	@Property
	private UserInputType userInputType;
	
	/**
	 * The Enum UserInputType. USERINPUT is pure user data input which doesn't
	 * trigger a new element or new page, it could be any type of html input
	 * element including text input, radio button, check box or selection
	 * dropdown. SREENJUMPER leads to a new screen InSCREENJUMPER triggers a in
	 * page elements changes, for example a new element added in this page
	 * (screen) The difference between screen jumper and inscreen jumper is
	 * really depends on how pio predicts. The phillosophy is that a minor
	 * change, not big enough to let pio recognized as a new screen.
	 * 
	 * VERY IMPORTANT, pio training and prediction will use the combination of
	 * field name and userinputtype as the training/predict result. For example,
	 * a username field and userinput combination is the pio result, which is
	 * labaled as username_userinput. So the pio always returns result as,
	 * FieldName_UserInputType. Engine script needs to decouple the result to
	 * two variables, FieldName and UserInputType. This will improve the
	 * intelligence of the script to know what is expected after the action on a
	 * particular element, either a screen jump or in screen jump or nothing
	 * instead of a input
	 */
	public enum UserInputType {
		
		USERINPUT, SCREENJUMPER, INSCREENJUMPER
	}

	// @Nullable
	// private boolean userFinalizedLabelResult; //user has revised the pio
	// predicted result if !=null.
	/**
	 * Instantiates a new user input training record.
	 */

	public WebElementTrainingRecord(String nodeLabelName) {
		super(nodeLabelName);
		inputLabelName = ""; // htmlLabelContent
		inputMLHtmlCode = "";
		trainedResult = ""; // "__ATE__Error___" or succeed with eventid
		userInputType = UserInputType.USERINPUT;
	}
	
	/**
	 * Instantiates a new web element training record.
	 */
	public WebElementTrainingRecord() {
		super("WebElementTrainingRecord");
		inputLabelName = ""; // htmlLabelContent
		inputMLHtmlCode = "";
		trainedResult = ""; // "__ATE__Error___" or succeed with eventid
		userInputType = UserInputType.USERINPUT;
	}

	/**
	 * Instantiates a new user input training record.
	 *
	 * @param labelName
	 *            the label name
	 * @param mlHtmlCode
	 *            the ml html code
	 */
	public WebElementTrainingRecord(String labelName, String mlHtmlCode, UserInputType userInputType, String nodeLabelName) {
		super(nodeLabelName);
		this.inputLabelName = labelName;
		this.inputMLHtmlCode = mlHtmlCode;
		trainedResult = ""; // "__ATE__Error___" or succeed with eventid
		
		this.userInputType = userInputType;
	}
	
	/**
	 * Instantiates a new web element training record.
	 *
	 * @param labelName the label name
	 * @param mlHtmlCode the ml html code
	 * @param userInputType the user input type
	 */
	public WebElementTrainingRecord(String labelName, String mlHtmlCode, UserInputType userInputType) {
		super("WebElementTrainingRecord");
		this.inputLabelName = labelName;
		this.inputMLHtmlCode = mlHtmlCode;
		trainedResult = ""; // "__ATE__Error___" or succeed with eventid
		
		this.userInputType = userInputType;
	}

	/**
	 * @return the inputLabelName
	 */
	public final String getInputLabelName() {
		return inputLabelName;
	}

	/**
	 * @param inputLabelName
	 *            the inputLabelName to set
	 */
	public final void setInputLabelName(String inputLabelName) {
		this.inputLabelName = inputLabelName;
	}

	/**
	 * @return the inputMLHtmlCode
	 */
	public final String getInputMLHtmlCode() {
		return inputMLHtmlCode;
	}

	/**
	 * @param inputMLHtmlCode
	 *            the inputMLHtmlCode to set
	 */
	public final void setInputMLHtmlCode(String inputMLHtmlCode) {
		this.inputMLHtmlCode = inputMLHtmlCode;
	}

	/**
	 * @return the trainedResult
	 */
	public String getTrainedResult() {
		return trainedResult;
	}

	/**
	 * @param trainedResult
	 *            the trainedResult to set
	 */
	public void setTrainedResult(String trainedResult) {
		this.trainedResult = trainedResult;
	}

	/**
	 * @return the pioPredictLabelResult
	 */
	public PredictedFieldName getPioPredictLabelResult() {
		return pioPredictLabelResult;
	}

	/**
	 * @param pioPredictLabelResult
	 *            the pioPredictLabelResult to set
	 */
	public void setPioPredictLabelResult(PredictedFieldName pioPredictLabelResult) {
		this.pioPredictLabelResult = pioPredictLabelResult;
	}


	/**
	 * @return the userFinalizedLabelResult
	 */
	// public boolean isUserFinalizedLabelResult() {
	// return userFinalizedLabelResult;
	// }
	// /**
	// * @param userFinalizedLabelResult the userFinalizedLabelResult to set
	// */
	// public void setUserFinalizedLabelResult(boolean userFinalizedLabelResult)
	// {
	// this.userFinalizedLabelResult = userFinalizedLabelResult;
	// }

	/**
	 * @return the userInputType
	 */
	public UserInputType getUserInputType() {
		return userInputType;
	}

	/**
	 * @param userInputType the userInputType to set
	 */
	public void setUserInputType(UserInputType userInputType) {
		this.userInputType = userInputType;
	}

	/**
	 * @return the userValues
	 */
	public Set<UserInputValue> getUserValues() {
		return userValues;
	}

	/**
	 * @param userValues the userValues to set
	 */
	public void setUserValues(Set<UserInputValue> userValues) {
		this.userValues = userValues;
	}

	/**
	 * @return the pioPredictConfidence
	 */
	public Double getPioPredictConfidence() {
		return pioPredictConfidence;
	}

	/**
	 * @param pioPredictConfidence the pioPredictConfidence to set
	 */
	public void setPioPredictConfidence(Double pioPredictConfidence) {
		this.pioPredictConfidence = pioPredictConfidence;
	}

	/**
	 * @return the testcases
	 */
	public Collection<TestCase> getTestcases() {
		return testcases;
	}

	/**
	 * @param testcases the testcases to set
	 */
	public void setTestcases(Collection<TestCase> testcases) {
		this.testcases = testcases;
	}

	/**
	 * @return the actionTrigger
	 */
	public boolean isActionTrigger() {
		return actionTrigger;
	}

	/**
	 * @param actionTrigger the actionTrigger to set
	 */
	public void setActionTrigger(boolean actionTrigger) {
		this.actionTrigger = actionTrigger;
	}

	/**
	 * @return the belongToCurrentTestCase
	 */
	public boolean isBelongToCurrentTestCase() {
		return belongToCurrentTestCase;
	}

	/**
	 * @param belongToCurrentTestCase the belongToCurrentTestCase to set
	 */
	public void setBelongToCurrentTestCase(boolean belongToCurrentTestCase) {
		this.belongToCurrentTestCase = belongToCurrentTestCase;
	}
}
