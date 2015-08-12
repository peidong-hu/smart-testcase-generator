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

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.bigtester.ate.tcg.model.Neo4jScreenNode;
import com.bigtester.ate.tcg.model.UserInputTrainingRecord;

// TODO: Auto-generated Javadoc
/**
 * This class StepInto defines ....
 * @author Peidong Hu
 *
 */
@RelationshipEntity (type=Relations.STEP_INTO)
public class StepInto {
	
	/** The start node. */
	@StartNode
	private Neo4jScreenNode startNode;
	
	/** The end node. */
	@EndNode
	private Neo4jScreenNode endNode;
	
	/** The trigger uitr. */
	private long triggerUITR_ID;
	
	/** The step weight. 
	 *  stepWeight is dynamically changed based on the current execution test case. 
	 *  ML will predict weight value according several factors including, 
	 *  current execution test case, test suite
	 * */
	private long stepWeight; 
}
