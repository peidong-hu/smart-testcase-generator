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

import org.eclipse.jdt.annotation.Nullable;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

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
public class UserInputTrainingRecord  {
	@GraphId
	private Long id;
	/** The input label name. */
	private String inputLabelName; //htmlLabelContent
	
	/** The input ml html code. */
	private String inputMLHtmlCode;
	
	private String trainedResult; //"__ATE__Error___" or succeed with eventid
	
	private String pioPredictLabelResult; 
	
	private Double pioPredictConfidence;
	
//	@Nullable
//	private boolean userFinalizedLabelResult; //user has revised the pio predicted result if !=null.
//		
	public UserInputTrainingRecord() {
	}
	/**
	 * Instantiates a new user input training record.
	 *
	 * @param labelName the label name
	 * @param mlHtmlCode the ml html code
	 */
	public UserInputTrainingRecord(String labelName, String mlHtmlCode) {
		this.inputLabelName = labelName;
		this.inputMLHtmlCode = mlHtmlCode;
	}
	/**
	 * @return the inputLabelName
	 */
	public final String getInputLabelName() {
		return inputLabelName;
	}
	/**
	 * @param inputLabelName the inputLabelName to set
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
	 * @param inputMLHtmlCode the inputMLHtmlCode to set
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
	 * @param trainedResult the trainedResult to set
	 */
	public void setTrainedResult(String trainedResult) {
		this.trainedResult = trainedResult;
	}
	/**
	 * @return the pioPredictLabelResult
	 */
	public String getPioPredictLabelResult() {
		return pioPredictLabelResult;
	}
	/**
	 * @param pioPredictLabelResult the pioPredictLabelResult to set
	 */
	public void setPioPredictLabelResult(String pioPredictLabelResult) {
		this.pioPredictLabelResult = pioPredictLabelResult;
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
	 * @return the userFinalizedLabelResult
	 */
//	public boolean isUserFinalizedLabelResult() {
//		return userFinalizedLabelResult;
//	}
//	/**
//	 * @param userFinalizedLabelResult the userFinalizedLabelResult to set
//	 */
//	public void setUserFinalizedLabelResult(boolean userFinalizedLabelResult) {
//		this.userFinalizedLabelResult = userFinalizedLabelResult;
//	}
}

