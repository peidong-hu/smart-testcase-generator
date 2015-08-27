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
package com.bigtester.ate.tcg.ws.entity;


import java.util.HashSet;
import java.util.Set;

import com.bigtester.ate.tcg.model.domain.PredictedFieldName;

// TODO: Auto-generated Javadoc
/**
 * This class WsScreenNames defines ....
 * @author Peidong Hu
 *
 */
public class WsPredictedFieldNames {
	
	/** The total count. */
	private long totalCount;
	
	/** The incomplete results. */
	private boolean incompleteResults;
	
	/** The screen names. */
	private Set<PredictedFieldName> fieldNames = new HashSet<PredictedFieldName>();
	
	/**
	 * Instantiates a new ws screen names.
	 *
	 * @param totalCount the total count
	 * @param incompleteResult the incomplete result
	 */
	public WsPredictedFieldNames(long totalCount, boolean incompleteResult) {
		this.totalCount = totalCount;
		this.incompleteResults = incompleteResult;
	}
	
	/**
	 * @return the totalCount
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the incompleteResults
	 */
	public boolean isIncompleteResults() {
		return incompleteResults;
	}

	/**
	 * @param incompleteResults the incompleteResults to set
	 */
	public void setIncompleteResults(boolean incompleteResults) {
		this.incompleteResults = incompleteResults;
	}


	/**
	 * @return the fieldNames
	 */
	public Set<PredictedFieldName> getFieldNames() {
		return fieldNames;
	}

	/**
	 * @param fieldNames the fieldNames to set
	 */
	public void setFieldNames(Set<PredictedFieldName> fieldNames) {
		this.fieldNames = fieldNames;
	}
}
