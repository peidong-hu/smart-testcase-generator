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
package com.bigtester.ate.tcg.service;//NOPMD

import java.util.Set;

import org.eclipse.jdt.annotation.Nullable;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigtester.ate.tcg.model.IntermediateResult;
import com.bigtester.ate.tcg.model.domain.Neo4jScreenNode;
import com.bigtester.ate.tcg.model.domain.ScreenActionElementTrainingRecord;
import com.bigtester.ate.tcg.model.domain.ScreenUserClickInputTrainingRecord;
import com.bigtester.ate.tcg.model.domain.ScreenUserInputTrainingRecord;
import com.bigtester.ate.tcg.model.domain.TestCase;
import com.bigtester.ate.tcg.model.domain.WindowsSystemFilePickerScreenNode;
import com.bigtester.ate.tcg.model.repository.PredictedFieldNameRepo;
import com.bigtester.ate.tcg.model.repository.ScreenNodeRepo;
import com.bigtester.ate.tcg.model.repository.TestCaseRepo;
import com.bigtester.ate.tcg.model.repository.TestSuiteRepo;
import com.bigtester.ate.tcg.model.repository.UserInputTrainingRecordRepo;
import com.bigtester.ate.tcg.model.repository.UserInputValueRepo;
import com.bigtester.ate.tcg.model.repository.WebDomainRepo;
import com.bigtester.ate.tcg.model.repository.WindowsSystemFilePickerScreenNodeRepo;

// TODO: Auto-generated Javadoc
/**
 * This class ScreenNodeCrud defines ....
 * 
 * @author Peidong Hu
 *
 */
@Service
public class WindowsSystemFilePickerScreenNodeCrud {

	/** The neo4j session. */
	@Autowired
	@Nullable
	private Session neo4jSession;

	/** The windows file picker screen node repo. */
	@Autowired
	@Nullable
	private transient WindowsSystemFilePickerScreenNodeRepo windowsFilePickerScreenNodeRepo;

	/** The screen node repo. */
	@Autowired
	@Nullable
	private transient ScreenNodeRepo screenNodeRepo;
	
	/** The user input value repo. */
	@Autowired
	@Nullable
	private UserInputValueRepo userInputValueRepo;

	/** The predicted field name repo. */
	@Autowired
	@Nullable
	private PredictedFieldNameRepo predictedFieldNameRepo;

	/** The user input training record repo. */
	@Autowired
	@Nullable
	private UserInputTrainingRecordRepo userInputTrainingRecordRepo;

	/** The web domain repo. */
	@Autowired
	@Nullable
	private WebDomainRepo webDomainRepo;

	/** The test case repo. */
	@Autowired
	@Nullable
	private TestCaseRepo testCaseRepo;

	/** The test suite repo. */
	@Autowired
	@Nullable
	private TestSuiteRepo testSuiteRepo;

	/**
	 * @return the userInputValueRepo
	 */
	public UserInputValueRepo getUserInputValueRepo() {
		final UserInputValueRepo userInputValueRepo2 = userInputValueRepo;
		if (userInputValueRepo2 != null) {
			return userInputValueRepo2;
		} else {
			throw new IllegalStateException("userInputValueRepo");
		}
	}

	/**
	 * @param userInputValueRepo
	 *            the userInputValueRepo to set
	 */
	public void setUserInputValueRepo(UserInputValueRepo userInputValueRepo) {
		this.userInputValueRepo = userInputValueRepo;
	}

	/**
	 * @return the predictedFieldNameRepo
	 */
	public PredictedFieldNameRepo getPredictedFieldNameRepo() {
		final PredictedFieldNameRepo predictedFieldNameRepo2 = predictedFieldNameRepo;
		if (predictedFieldNameRepo2 != null) {
			return predictedFieldNameRepo2;
		} else {
			// TODO handle null value
			throw new IllegalStateException("predictedFieldNameRepo");
		}
	}

	/**
	 * @param predictedFieldNameRepo
	 *            the predictedFieldNameRepo to set
	 */
	public void setPredictedFieldNameRepo(
			PredictedFieldNameRepo predictedFieldNameRepo) {
		this.predictedFieldNameRepo = predictedFieldNameRepo;
	}

	/**
	 * @return the userInputTrainingRecordRepo
	 */
	public UserInputTrainingRecordRepo getUserInputTrainingRecordRepo() {
		final UserInputTrainingRecordRepo userInputTrainingRecordRepo2 = userInputTrainingRecordRepo;
		if (userInputTrainingRecordRepo2 != null) {
			return userInputTrainingRecordRepo2;
		} else {
			// TODO handle null value
			throw new IllegalStateException("userInputTrainingRecordRepo");
		}
	}

	/**
	 * @param userInputTrainingRecordRepo
	 *            the userInputTrainingRecordRepo to set
	 */
	public void setUserInputTrainingRecordRepo(
			UserInputTrainingRecordRepo userInputTrainingRecordRepo) {
		this.userInputTrainingRecordRepo = userInputTrainingRecordRepo;
	}

	/**
	 * @return the webDomainRepo
	 */
	public WebDomainRepo getWebDomainRepo() {
		final WebDomainRepo webDomainRepo2 = webDomainRepo;
		if (webDomainRepo2 != null) {
			return webDomainRepo2;
		} else {
			// TODO handle null value
			throw new IllegalStateException("webDomainRepo");
		}
	}

	/**
	 * @param webDomainRepo
	 *            the webDomainRepo to set
	 */
	public void setWebDomainRepo(WebDomainRepo webDomainRepo) {
		this.webDomainRepo = webDomainRepo;
	}

	/**
	 * @return the testCaseRepo
	 */
	public TestCaseRepo getTestCaseRepo() {
		final TestCaseRepo testCaseRepo2 = testCaseRepo;
		if (testCaseRepo2 != null) {
			return testCaseRepo2;
		} else {
			// TODO handle null value
			throw new IllegalStateException("testCaseRepo");
		}
	}

	/**
	 * @param testCaseRepo
	 *            the testCaseRepo to set
	 */
	public void setTestCaseRepo(TestCaseRepo testCaseRepo) {
		this.testCaseRepo = testCaseRepo;
	}

	/**
	 * @return the testSuiteRepo
	 */
	public TestSuiteRepo getTestSuiteRepo() {
		final TestSuiteRepo testSuiteRepo2 = testSuiteRepo;
		if (testSuiteRepo2 != null) {
			return testSuiteRepo2;
		} else {
			// TODO handle null value
			throw new IllegalStateException("testSuiteRepo");
		}
	}

	/**
	 * @param testSuiteRepo
	 *            the testSuiteRepo to set
	 */
	public void setTestSuiteRepo(TestSuiteRepo testSuiteRepo) {
		this.testSuiteRepo = testSuiteRepo;
	}

	/**
	 * @return the neo4jSession
	 */
	public Session getNeo4jSession() {
		final Session neo4jSession2 = neo4jSession;
		if (neo4jSession2 == null) {
			throw new IllegalStateException("neo4j session");
		} else {
			return neo4jSession2;
		}
	}

	/**
	 * Create or update.
	 *
	 * @param intermediateResult
	 *            the intermediate result
	 * @return the neo4j screen node
	 */
	public Neo4jScreenNode createOrUpdate(IntermediateResult intermediateResult, boolean commit) {
		
		
		// save screen node
		Neo4jScreenNode prevousScreenNode = null;// NOPMD
		IntermediateResult previousIntermediateResult = intermediateResult
				.getLastScreenNodeIntermediateResult();
		if (null != previousIntermediateResult
				&& !previousIntermediateResult.getScreenName().equals("")) {
			prevousScreenNode = getScreenNodeRepo()
					.getNeo4jScreenNodeByUrlAndName(
							previousIntermediateResult.getScreenUrl(),
							previousIntermediateResult.getScreenName());
			if (null == prevousScreenNode) {
				throw new IllegalStateException("previousScree nNode");
			}
		}

		WindowsSystemFilePickerScreenNode currentNode = getWindowsFilePickerScreenNodeRepo()
				.getWindowsSystemFilePickerScreenNodeByUrlAndName(
						intermediateResult.getScreenUrl(),
						intermediateResult.getScreenName());
		if (null == currentNode) {
			String filePathName = "";
			if (intermediateResult.getUitrs().iterator().hasNext()) {
				intermediateResult.getUitrs().iterator().next().getUserValues().iterator().next().getValue();
			}
			currentNode = new WindowsSystemFilePickerScreenNode(//NOPMD
					intermediateResult.getScreenName(),
					intermediateResult.getScreenUrl(), filePathName);

		} else {
			currentNode.setName(intermediateResult.getScreenName());
			currentNode.setUitrs(intermediateResult.getUitrs());
		}
		if (commit) {
			Transaction trx1 = getNeo4jSession().beginTransaction();
			try {
				currentNode = getWindowsFilePickerScreenNodeRepo().save(currentNode);

				trx1.commit();
				if (null == currentNode || currentNode.getId() == null)
					throw new IllegalStateException("neo4j db access");
			} finally {
				trx1.close();
			}
		}
		if (null != prevousScreenNode) {
			createOrUpdateStepout(prevousScreenNode, currentNode,
					intermediateResult);
			updateTestCaseRelationships(prevousScreenNode, intermediateResult, false);
			update(prevousScreenNode);
			
		}

		return currentNode;
	}

	/**
	 * Update.
	 *
	 * @param screenNode the screen node
	 * @return the neo4j screen node
	 */
	public Neo4jScreenNode update(Neo4jScreenNode screenNode) {
		Neo4jScreenNode tmp;
		Transaction trx = getNeo4jSession().beginTransaction();//NOPMD
		try {

			tmp = getScreenNodeRepo().save(screenNode);
			if (null == tmp)
				throw new IllegalStateException("screenNode update");
			trx.commit();
		} finally {
			trx.close();
		}

		return tmp;
	}

	/**
	 * Update test case relationships.
	 *
	 * @param screenNode the screen node
	 * @param intermediateResult the intermediate result
	 * @param commit the commit
	 * @return the neo4j screen node
	 */
	public Neo4jScreenNode updateTestCaseRelationships(
			Neo4jScreenNode screenNode, IntermediateResult intermediateResult,
			boolean commit) {
		TestCase testcaseNode = getTestCaseRepo().getTestCaseByName(
				intermediateResult.getTestCaseName());
		if (null == testcaseNode) throw new IllegalStateException("please update/create test case node first");
		if (!screenNode.getTestcases().contains(testcaseNode))
			screenNode.getTestcases().add(testcaseNode);
		
		Set<ScreenUserInputTrainingRecord> uitrs = screenNode.getUitrs();
		for (java.util.Iterator<? extends ScreenUserInputTrainingRecord> itr = uitrs
				.iterator(); itr.hasNext();) {
			ScreenUserInputTrainingRecord uitr = itr.next();
			if (!uitr.getTestcases().contains(testcaseNode)) {
				uitr.getTestcases().add(testcaseNode);
			}
		}

		Set<ScreenUserClickInputTrainingRecord> clickUitrs = screenNode.getClickUitrs();
		for (java.util.Iterator<ScreenUserClickInputTrainingRecord> itr = clickUitrs
				.iterator(); itr.hasNext();) {
			ScreenUserClickInputTrainingRecord uitr = itr.next();
			if (!uitr.getTestcases().contains(testcaseNode)) {
				uitr.getTestcases().add(testcaseNode);
			}
		}
		
		Set<ScreenActionElementTrainingRecord> actionUitrs = screenNode
				.getActionUitrs();
		for (java.util.Iterator<ScreenActionElementTrainingRecord> itr = actionUitrs
				.iterator(); itr.hasNext();) {
			ScreenActionElementTrainingRecord uitr = itr.next();
			if (!uitr.getTestcases().contains(testcaseNode)) {
				uitr.getTestcases().add(testcaseNode);
			}
		}
		if (!commit)
			return screenNode;//NOPMD
		else
			return update(screenNode);
	}

	/**
	 * Stepped into.
	 *
	 * @param endNode
	 *            the end node
	 * @param uitrId
	 *            the uitr id
	 * @return the step into
	 */
	public void createOrUpdateStepout(Neo4jScreenNode startNode,
			WindowsSystemFilePickerScreenNode endNode, IntermediateResult iResult) {
		// TODO, add test case filter after finish job application code

		Set<ScreenUserClickInputTrainingRecord> startActionUitrs = startNode
				.getClickUitrs();

		if (startActionUitrs.isEmpty() || startActionUitrs.size() > 1)
			throw new IllegalStateException("start action uitrs");
		else {
			for (java.util.Iterator<ScreenUserClickInputTrainingRecord> itr = startActionUitrs
					.iterator(); itr.hasNext();) {
				ScreenActionElementTrainingRecord first = itr.next();
				if (first.getStepOuts().isEmpty()
						|| !first.getStepOuts().contains(endNode)) {
					// create
					first.getStepOuts().add(endNode);

				}
				break;// NOPMD
			}
		}
	}

	/**
	 * @param neo4jSession the neo4jSession to set
	 */
	public void setNeo4jSession(Session neo4jSession) {
		this.neo4jSession = neo4jSession;
	}

	/**
	 * @return the windowsFilePickerScreenNodeRepo
	 */
	public WindowsSystemFilePickerScreenNodeRepo getWindowsFilePickerScreenNodeRepo() {
		final WindowsSystemFilePickerScreenNodeRepo windowsFilePickerScreenNodeRepo2 = windowsFilePickerScreenNodeRepo;
		if (windowsFilePickerScreenNodeRepo2 != null) {
			return windowsFilePickerScreenNodeRepo2;
		} else {
			throw new IllegalStateException("windowsFilepickerScreenNodeRepo");
		}
	}

	/**
	 * @param windowsFilePickerScreenNodeRepo the windowsFilePickerScreenNodeRepo to set
	 */
	public void setWindowsFilePickerScreenNodeRepo(
			WindowsSystemFilePickerScreenNodeRepo windowsFilePickerScreenNodeRepo) {
		this.windowsFilePickerScreenNodeRepo = windowsFilePickerScreenNodeRepo;
	}

	/**
	 * @return the screenNodeRepo
	 */
	public ScreenNodeRepo getScreenNodeRepo() {
		final ScreenNodeRepo screenNodeRepo2 = screenNodeRepo;
		if (screenNodeRepo2 != null) {
			return screenNodeRepo2;
		} else {
			throw new IllegalStateException("screenNodeRepo");
		}
	}

	/**
	 * @param screenNodeRepo the screenNodeRepo to set
	 */
	public void setScreenNodeRepo(ScreenNodeRepo screenNodeRepo) {
		this.screenNodeRepo = screenNodeRepo;
	}

}
