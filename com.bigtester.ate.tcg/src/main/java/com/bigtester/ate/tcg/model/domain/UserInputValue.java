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
import org.neo4j.ogm.annotation.GraphId;

// TODO: Auto-generated Javadoc
/**
 * This class UserInputValue defines ....
 * @author Peidong Hu
 *
 */
public class UserInputValue {
	
	/** The value id. */
	@GraphId
	@Nullable
	private Long valueId;
	
	/** The value. */
	private String value = "";
	
	/** The desc. */
	private String desc = "";
	
	/**
	 * Instantiates a new user input value.
	 */
	public UserInputValue() {
		super();
	}
	
	/**
	 * Instantiates a new user input value.
	 *
	 * @param value the value
	 */
	public UserInputValue(String value) {
		this.setValue(value);
	}
	
	/**
	 * Instantiates a new user input value.
	 *
	 * @param value the value
	 * @param desc the desc
	 */
	public UserInputValue(String value, String desc) {
		this.setValue(value);
		this.setDesc(desc);
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
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

	/**
	 * @return the valueId
	 */
	@Nullable
	public Long getValueId() {
		return valueId;
	}

	/**
	 * @param valueId the valueId to set
	 */
	public void setValueId(Long valueId) {
		this.valueId = valueId;
	}
}
