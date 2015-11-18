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

import org.eclipse.jdt.annotation.Nullable;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigtester.ate.tcg.model.IntermediateResult;
import com.bigtester.ate.tcg.model.domain.AbstractScreenNode;
import com.bigtester.ate.tcg.model.domain.Neo4jScreenNode;
import com.bigtester.ate.tcg.model.domain.InScreenJumperTrainingRecord;
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
	private transient Session neo4jSession;

	/** The windows file picker screen node repo. */
	@Autowired
	@Nullable
	private transient WindowsSystemFilePickerScreenNodeRepo windowsFilePickerScreenNodeRepo;

	/** The screen node repo. */
	@Autowired
	@Nullable
	private transient ScreenNodeRepo screenNodeRepo;
	
	/** The screen node crud. */
	@Autowired
	@Nullable
	private ScreenNodeCrud screenNodeCrud;
	
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
	public WindowsSystemFilePickerScreenNode createOrUpdate(IntermediateResult intermediateResult, boolean commit) {
		
		
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

		
		InScreenJumperTrainingRecord previousScreenTriggerClickUitr = intermediateResult.getPreviousScreenTriggerClickUitr();
		String filePathName = "";
		if (null != previousScreenTriggerClickUitr && previousScreenTriggerClickUitr.getUserValues().iterator().hasNext()) {
			filePathName = previousScreenTriggerClickUitr.getUserValues().iterator().next().getValue();
		} else {
			throw new IllegalStateException("previousScreenTriggerClickUitr");
		}
		WindowsSystemFilePickerScreenNode currentNode = getWindowsFilePickerScreenNodeRepo()
				.getWindowsSystemFilePickerScreenNodeByUrlAndName(
						intermediateResult.getScreenUrl(),
						intermediateResult.getScreenName());
		if (null == currentNode) {
			currentNode = new WindowsSystemFilePickerScreenNode(//NOPMD
				intermediateResult.getScreenName(),
				intermediateResult.getScreenUrl(), filePathName);
			currentNode.setPreviousScreenTriggerClickUitr(previousScreenTriggerClickUitr);
		} else {
			currentNode.setName(intermediateResult.getScreenName());
			currentNode.setFilePathName(filePathName);
			InScreenJumperTrainingRecord existingClickUitr = currentNode.getPreviousScreenTriggerClickUitr();
			if (null != existingClickUitr ) {
				Long gid = existingClickUitr.getId();
				if (null != gid&& !gid.equals(previousScreenTriggerClickUitr.getId()))
					currentNode.setPreviousScreenTriggerClickUitr(previousScreenTriggerClickUitr);
			}
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
			getScreenNodeCrud().updateTestCaseRelationships(prevousScreenNode, intermediateResult, false);
			getScreenNodeCrud().update(prevousScreenNode);
			
		} else {
			throw new IllegalStateException("Previous Screen Node should exist.");
		}

		return currentNode;
	}

	/**
	 * Update.
	 *
	 * @param screenNode the screen node
	 * @return the neo4j screen node
	 */
	public WindowsSystemFilePickerScreenNode update(WindowsSystemFilePickerScreenNode screenNode) {
		WindowsSystemFilePickerScreenNode tmp;
		Transaction trx = getNeo4jSession().beginTransaction();//NOPMD
		try {

			tmp = getWindowsFilePickerScreenNodeRepo().save(screenNode);
			if (null == tmp)
				throw new IllegalStateException("windowsfilepickerscreenNode update");
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
	public WindowsSystemFilePickerScreenNode updateTestCaseRelationships(
			WindowsSystemFilePickerScreenNode screenNode, IntermediateResult intermediateResult,
			boolean commit) {
		TestCase testcaseNode = getTestCaseRepo().getTestCaseByName(
				intermediateResult.getTestCaseName());
		if (null == testcaseNode) throw new IllegalStateException("please update/create test case node first");
		if (!screenNode.getTestcases().contains(testcaseNode))
			screenNode.getTestcases().add(testcaseNode);
		
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

		InScreenJumperTrainingRecord startClickUitr = endNode.getPreviousScreenTriggerClickUitr();
				

		if (null == startClickUitr) 
			throw new IllegalStateException("start click uitr");
		else {
			
				AbstractScreenNode oldStepOut = startClickUitr.getStepOut();
				if (oldStepOut != null) {
					Transaction trx1 = getNeo4jSession().beginTransaction();
					try {
						getNeo4jSession().delete(oldStepOut);

						trx1.commit();
						
					} finally {
						trx1.close();
					}
					
				}
				startClickUitr.setStepOut(endNode);
			
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

	/**
	 * @return the screenNodeCrud
	 */
	public ScreenNodeCrud getScreenNodeCrud() {
		final ScreenNodeCrud screenNodeCrud2 = screenNodeCrud;
		if (screenNodeCrud2 != null) {
			return screenNodeCrud2;
		} else {
			throw new IllegalStateException("screenNodeCrud");
		}
	}

	/**
	 * @param screenNodeCrud the screenNodeCrud to set
	 */
	public void setScreenNodeCrud(ScreenNodeCrud screenNodeCrud) {
		this.screenNodeCrud = screenNodeCrud;
	}

}
