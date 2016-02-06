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

import org.eclipse.jdt.annotation.Nullable;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import com.bigtester.ate.tcg.model.relationship.Relations;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class InScreenJumperTrainingRecord extends WebElementTrainingRecord{
	/** The step outs. */
	@JsonIgnore
	@Relationship(type = Relations.STEP_OUT, direction = Relationship.OUTGOING)
	@Nullable
	private AbstractScreenNode stepOut;


	/** The action trigger. */
	private transient boolean actionTrigger;

	
	/** The click times. */
	private int clickTimes;
	
	/**
	 * Instantiates a new screen user click input training record.
	 */
	public InScreenJumperTrainingRecord() {
		super("ScreenUserClickInputTrainingRecord");
	}
	
	/**
	 * Instantiates a new screen user click input training record.
	 *
	 * @param uitr the uitr
	 */
	public InScreenJumperTrainingRecord(UserInputTrainingRecord uitr) {
		super("ScreenUserClickInputTrainingRecord");
		this.setId(uitr.getId());
		this.setInputLabelName(uitr.getInputLabelName());
		this.setInputMLHtmlCode(uitr.getInputMLHtmlCode());
		this.setPioPredictConfidence(uitr.getPioPredictConfidence());
		this.setTestcases(uitr.getTestcases());
		this.setTrainedResult(uitr.getTrainedResult());
		this.setUserInputType(uitr.getUserInputType());
		this.setUserValues(uitr.getUserValues());
		this.setPioPredictLabelResult(uitr.getPioPredictLabelResult());
	}
	/**
	 * @return the stepOut
	 */
	@Nullable
	public AbstractScreenNode getStepOut() {
		return stepOut;
	}
	/**
	 * @param stepOut the stepOut to set
	 */
	public void setStepOut(AbstractScreenNode stepOut) {
		this.stepOut = stepOut;
	}

	/**
	 * @return the clickTimes
	 */
	public int getClickTimes() {
		return clickTimes;
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
	 * @param clickTimes the clickTimes to set
	 */
	public void setClickTimes(int clickTimes) {
		this.clickTimes = clickTimes;
	}
}
