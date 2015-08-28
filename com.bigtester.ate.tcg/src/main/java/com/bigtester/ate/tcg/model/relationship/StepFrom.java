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
package com.bigtester.ate.tcg.model.relationship;

import org.eclipse.jdt.annotation.Nullable;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.bigtester.ate.tcg.model.ATENeo4jNodeComparision;
import com.bigtester.ate.tcg.model.domain.Neo4jScreenNode;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

// TODO: Auto-generated Javadoc
/**
 * This class StepInto defines ....
 * @author Peidong Hu
 *
 */
@RelationshipEntity (type=Relations.STEP_FROM)
public class StepFrom implements ATENeo4jNodeComparision{
	
	/** The node id. */
	@Nullable 
	@GraphId
	@XStreamOmitField
	private Long nodeId;
	
	/** The start node. */
	@StartNode
	@Nullable
	private Neo4jScreenNode startNode;
	
	/** The end node. */
	@EndNode
	@Nullable
	private Neo4jScreenNode endNode;
	
	/** The trigger uitr. */
	private long triggerUitrId;
	
	/** The step weight. 
	 *  stepWeight is dynamically changed based on the current execution test case. 
	 *  ML will predict weight value according several factors including, 
	 *  current execution test case, test suite
	 * */
	private long stepWeight;

	/**
	 * Instantiates a new step into.
	 *
	 * @param startNode the start node
	 * @param endNode the end node
	 * @param uitrId the uitr id
	 */
	public StepFrom(Neo4jScreenNode startNode, Neo4jScreenNode endNode, long uitrId) {
		this.startNode = startNode;
		this.endNode = endNode;
		this.triggerUitrId = uitrId;
	}
	
	/**
	 * Instantiates a new step into.
	 */
	public StepFrom() {//NOPMD
		super();
	}
	/**
	 * @return the startNode
	 */
	@Nullable
	public Neo4jScreenNode getStartNode() {
		return startNode;
	}

	/**
	 * @param startNode the startNode to set
	 */
	public void setStartNode(Neo4jScreenNode startNode) {
		this.startNode = startNode;
	}

	/**
	 * @return the endNode
	 */
	@Nullable
	public Neo4jScreenNode getEndNode() {
		return endNode;
	}

	/**
	 * @param endNode the endNode to set
	 */
	public void setEndNode(Neo4jScreenNode endNode) {
		this.endNode = endNode;
	}


	/**
	 * @return the stepWeight
	 */
	public long getStepWeight() {
		return stepWeight;
	}

	/**
	 * @param stepWeight the stepWeight to set
	 */
	public void setStepWeight(long stepWeight) {
		this.stepWeight = stepWeight;
	}

	/**
	 * @return the nodeId
	 */
	@Nullable
	public Long getNodeId() {
		return nodeId;
	}

	/**
	 * @param nodeId the nodeId to set
	 */
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * @return the triggerUitrId
	 */
	public long getTriggerUitrId() {
		return triggerUitrId;
	}

	/**
	 * @param triggerUitrId the triggerUitrId to set
	 */
	public void setTriggerUitrId(long triggerUitrId) {
		this.triggerUitrId = triggerUitrId;
	}

	/**
	 * {@inheritDoc}
	*/
	@Override
	public boolean sameNode(Object obj) {
		boolean retVal = false;
		if (obj instanceof StepFrom) {
			Neo4jScreenNode startNodeObj = ((StepFrom) obj).getStartNode();
			Neo4jScreenNode endNodeObj = ((StepFrom) obj).getEndNode();
			if (startNodeObj == null && this.startNode == null && endNodeObj == null && this.endNode == null) retVal = true;
			else if (startNodeObj != null && this.startNode != null 
					&& endNodeObj != null && this.endNode != null)
			{
			retVal = startNodeObj.sameNode(this.startNode)
					&& endNodeObj.sameNode(this.endNode)
					&& ((StepFrom) obj).getTriggerUitrId()==this.getTriggerUitrId();
			}
		}
		return retVal;
	} 
}
