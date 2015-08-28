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

import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.jdt.annotation.Nullable;


// TODO: Auto-generated Javadoc
/**
 * This class RelationshipHashSet defines ....
 * @author Peidong Hu
 * @param <E>
 *
 */
public class RelationshipHashSet<E> extends HashSet<E> {
	
	/** The same node. */
	@Nullable
	private transient Object sameNode; 
	/**
	 * 
	 */
	private static final long serialVersionUID = -495119507019527688L;
	
	/**
	 * {@inheritDoc}
	*/
	@Override
	public boolean contains(@Nullable Object obj) {
		boolean retVal = false;
		if (obj instanceof ATENeo4jNodeComparision) {
			Iterator<E> itr = super.iterator();
			while (itr.hasNext()) {
				Object next = itr.next();
				if (next instanceof ATENeo4jNodeComparision) {
					retVal = ((ATENeo4jNodeComparision) next).sameNode(obj);
				}
				if (retVal) {
					this.sameNode = next;
					break;
				}
			}
		} else {
			retVal = super.contains(obj);
		}
		return retVal;
	}

	/**
	 * @return the sameNode
	 */
	@Nullable
	public Object getSameNode() {
		return sameNode;
	}

	/**
	 * @param sameNode the sameNode to set
	 */
	public void setSameNode(Object sameNode) {
		this.sameNode = sameNode;
	}

}
