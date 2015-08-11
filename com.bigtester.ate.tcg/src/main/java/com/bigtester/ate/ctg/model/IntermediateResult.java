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
package com.bigtester.ate.ctg.model;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * This class IntermediateResult defines ....
 * @author Peidong Hu
 *
 */
public class IntermediateResult {
	
	/** The dom strings. */
	private List<HTMLSource> domStrings = new ArrayList<HTMLSource>();
	
	/** The uitrs. */
	private List<UserInputTrainingRecord> uitrs = new ArrayList<UserInputTrainingRecord>();
	
	/**
	 * Instantiates a new intermediate result.
	 */
	public IntermediateResult() { //NOPMD 
		//For spring web services, an empty constructor has to be placed in models receiving web request.
		
	}
	/**
	 * @return the domStrings
	 */
	public List<HTMLSource> getDomStrings() {
		return domStrings;
	}
	/**
	 * @param domStrings the domStrings to set
	 */
	public void setDomStrings(List<HTMLSource> domStrings) {
		this.domStrings = domStrings;
	}
	/**
	 * @return the uitrs
	 */
	public List<UserInputTrainingRecord> getUitrs() {
		return uitrs;
	}
	/**
	 * @param uitrs the uitrs to set
	 */
	public void setUitrs(List<UserInputTrainingRecord> uitrs) {
		this.uitrs = uitrs;
	}
	
	
}
