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
package com.bigtester.ate.tcg.service;

import java.util.List;
import java.util.Set;

import org.eclipse.jdt.annotation.Nullable;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigtester.ate.tcg.model.IntermediateResult;
import com.bigtester.ate.tcg.model.domain.Neo4jScreenNode;
import com.bigtester.ate.tcg.model.domain.ScreenActionElementTrainingRecord;
import com.bigtester.ate.tcg.model.domain.TestSuite;
import com.bigtester.ate.tcg.model.repository.TestCaseRepo;
import com.bigtester.ate.tcg.model.repository.TestSuiteRepo;

// TODO: Auto-generated Javadoc
/**
 * This class ScreenNodeCrud defines ....
 * 
 * @author Peidong Hu
 *
 */
@Service
public class TestSuiteNodeCrud {

	/** The neo4j session. */
	@Autowired
	@Nullable
	private Session neo4jSession;

	/** The test case repo. */
	@Autowired
	@Nullable
	private TestCaseRepo testCaseRepo;

	/** The test suite repo. */
	@Autowired
	@Nullable
	private TestSuiteRepo testSuiteRepo;

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
	public List<TestSuite> createOrUpdate(
			IntermediateResult intermediateResult, boolean commit) {
		List<TestSuite> testSuiteMap = intermediateResult.getTestSuitesMap();
		for (int i = 0; i< testSuiteMap.size(); i++) {

			TestSuite previous = testSuiteMap.get(i);
			TestSuite tmpSuite = getTestSuiteRepo().getTestSuiteByName(previous.getName());
			if (tmpSuite != null) {
				previous = tmpSuite;
				testSuiteMap.set(i, tmpSuite);
			}
			if (i < testSuiteMap.size() - 1) {
				TestSuite next = testSuiteMap.get(i + 1);
				tmpSuite = getTestSuiteRepo().getTestSuiteByName(next.getName());
				if (tmpSuite != null) {
					next = tmpSuite;
					testSuiteMap.set(i + 1, tmpSuite);
				}
				// next.setParentTestSuite(previous);
				if (!previous.getSubTestSuites().contains(next))
					previous.getSubTestSuites().add(next);
			}
			
			// TestSuite existingSuite = getTestSuiteRepo().getTestSuiteByName(
			// next.getName());
			//
			// if (null != existingSuite) {
			// testcaseNode = new
			// TestCase(intermediateResult.getTestCaseName());// NOPMD
			//
			// } else {
			// testcaseNode.setName(intermediateResult.getTestCaseName());
			// }
			
		}
		if (commit) {
			Transaction trx = getNeo4jSession().beginTransaction();
			try {

				getTestSuiteRepo().save(testSuiteMap.get(0));
				trx.commit();
			} finally {
				trx.close();
			}
		}

		return testSuiteMap;
	}

	// public Set<TestSuite> updateTestCaseNode(List<TestSuite> testSuites,
	// boolean commit) {
	//
	// TestSuite tmpSuite = testSuites.get(testSuites.size() - 1);
	// Set<TestCase> tests =
	//
	// }

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
			Neo4jScreenNode endNode, IntermediateResult iResult) {
		// TODO, add test case filter after finish job application code

		Set<ScreenActionElementTrainingRecord> startActionUitrs = startNode
				.getActionUitrs();

		if (startActionUitrs.isEmpty() || startActionUitrs.size() > 1)
			throw new IllegalStateException("start action uitrs");
		else {
			for (java.util.Iterator<ScreenActionElementTrainingRecord> itr = startActionUitrs
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

}
