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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.annotation.Nullable;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.bigtester.ate.tcg.model.ATENeo4jNodeComparision;
import com.bigtester.ate.tcg.model.IntermediateResult;
import com.bigtester.ate.tcg.model.relationship.Relations;

// TODO: Auto-generated Javadoc
/**
 * This class Neo4jScreenNode defines ....
 * 
 * @author Peidong Hu
 *
 */
@NodeEntity
public class FilePickerScreenNode extends AbstractScreenNode  implements ATENeo4jNodeComparision {

	
	/** The file path name. */
	private String filePathName = "";
	
	/**
	 * Instantiates a new neo4j screen node. no-arg constructor for restful
	 * call.
	 */
	public FilePickerScreenNode() {
		super();
	}

	/**
	 * Instantiates a new neo4j screen node.
	 *
	 * @param name
	 *            the name
	 * @param url
	 *            the url
	 * @param iResult
	 *            the i result
	 */
	public FilePickerScreenNode(String name, String url, String filePathName) {
		super(name, url);
		this.filePathName = filePathName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean sameNode(@Nullable Object obj) {
		boolean retVal = false;//NOPMD
		if (obj instanceof FilePickerScreenNode) {
			retVal = ((FilePickerScreenNode) obj).getName() == this.getName()
					&& ((FilePickerScreenNode) obj).getUrl() == this.getUrl();
		}
		return retVal;
	}

	/**
	 * @return the filePathName
	 */
	public String getFilePathName() {
		return filePathName;
	}

	/**
	 * @param filePathName the filePathName to set
	 */
	public void setFilePathName(String filePathName) {
		this.filePathName = filePathName;
	}

}
