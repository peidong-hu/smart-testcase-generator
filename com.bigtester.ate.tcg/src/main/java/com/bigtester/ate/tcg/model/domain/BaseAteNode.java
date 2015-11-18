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
 
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;




/**
 * This class AbstractAteNode defines ....
 * @author Peidong Hu
 *
 */
@NodeEntity
public class BaseAteNode {
	
	/** The node label name. */
	@Property
	private String nodeLabelName;
	
	/**
	 * Instantiates a new base ate node.
	 *
	 * @param nodeLblName the node label name
	 */
	public BaseAteNode(String nodeLblName) {
		nodeLabelName = nodeLblName;
	}
	
	
	/**
	 * @return the nodeLabelName
	 */
	public String getNodeLabelName() {
		return nodeLabelName;
	}

	/**
	 * @param nodeLabelName the nodeLabelName to set
	 */
	public void setNodeLabelName(String nodeLabelName) {
		this.nodeLabelName = nodeLabelName;
	}
	

}
