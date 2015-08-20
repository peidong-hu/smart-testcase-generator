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

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * This class WsScreenNames defines ....
 * @author Peidong Hu
 *
 */
public class WsScreenNames {
	
	/** The total count. */
	private long totalCount;
	
	/** The incomplete results. */
	private boolean incompleteResults;
	
	/** The screen names. */
	private List<ScreenName> screenNames = new ArrayList<ScreenName>();
	
	/**
	 * Instantiates a new ws screen names.
	 *
	 * @param totalCount the total count
	 * @param incompleteResult the incomplete result
	 */
	public WsScreenNames(long totalCount, boolean incompleteResult) {
		this.totalCount = totalCount;
		this.incompleteResults = incompleteResult;
	}
	
	/**
	 * The Class ScreenName.
	 */
	public class ScreenName {
		
		/** The name. */
		private String name = "";
		
		/** The desc. */
		private String desc = "";
		
		/**
		 * Instantiates a new screen name.
		 *
		 * @param name the name
		 * @param desc the desc
		 */
		public ScreenName (String name, String desc) {
			this.name = name;
			this.desc = desc;
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
		 * @return the desc
		 */
		public String getDesc() {
			return desc;
		}

		/**
		 * @param desc the desc to set
		 */
		public void setDesc(String desc) {
			this.desc = desc;
		}
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
	 * @return the screenNames
	 */
	public List<ScreenName> getScreenNames() {
		return screenNames;
	}

	/**
	 * @param screenNames the screenNames to set
	 */
	public void setScreenNames(List<ScreenName> screenNames) {
		this.screenNames = screenNames;
	}
}
