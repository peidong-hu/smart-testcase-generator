package com.bigtester.ate.tcg.ws;

import org.neo4j.ogm.session.Session;
import org.eclipse.jdt.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.data.neo4j.template.Neo4jTemplate;
import com.bigtester.ate.tcg.model.repository.ActionElementTrainingRecordRepo;
import com.bigtester.ate.tcg.model.repository.PredictedFieldNameRepo;
import com.bigtester.ate.tcg.model.repository.ScreenNodeRepo;
import com.bigtester.ate.tcg.model.repository.TestCaseRepo;
import com.bigtester.ate.tcg.model.repository.TestSuiteRepo;
import com.bigtester.ate.tcg.model.repository.UserInputTrainingRecordRepo;
import com.bigtester.ate.tcg.model.repository.UserInputValueRepo;
import com.bigtester.ate.tcg.model.repository.WebDomainRepo;
import com.bigtester.ate.tcg.service.ScreenNodeCrud;
import com.bigtester.ate.tcg.service.TestCaseNodeCrud;
import com.bigtester.ate.tcg.service.TestSuiteNodeCrud;
import com.bigtester.ate.tcg.service.WebDomainCrud;
import com.bigtester.ate.tcg.service.WindowsSystemFilePickerScreenNodeCrud;

/**
 * The Class GreetingController.
 */
public class BaseWsController {

	/** The template. */
	@Autowired
	@Nullable
	private transient Neo4jOperations template;

	/** The screen node repo. */
	@Autowired
	@Nullable
	private ScreenNodeRepo screenNodeRepo;

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
	
	/** The user click input training record repo. */
	@Autowired
	@Nullable
	private UserInputTrainingRecordRepo userClickInputTrainingRecordRepo;
	
	@Autowired
	@Nullable
	/** The action element training record repo. */
	private ActionElementTrainingRecordRepo actionElementTrainingRecordRepo;

	/** The web domain repo. */
	@Autowired
	@Nullable
	private WebDomainRepo webDomainRepo;

	/** The test case repo. */
	@Autowired
	@Nullable
	private TestCaseRepo testCaseRepo;

	/** The screen node crud. */
	@Autowired
	@Nullable
	private ScreenNodeCrud screenNodeCrud;
	
	/** The windows system file picker screen node crud. */
	@Autowired
	@Nullable
	private WindowsSystemFilePickerScreenNodeCrud windowsSystemFilePickerScreenNodeCrud; //NOPMD


	/** The web domain crud. */
	@Autowired
	@Nullable
	private WebDomainCrud webDomainCrud;

	/** The test case crud. */
	@Autowired
	@Nullable
	private TestCaseNodeCrud testCaseCrud;
	/** The test suite repo. */
	@Autowired
	@Nullable
	private TestSuiteRepo testSuiteRepo;

	/** The test suite crud. */
	@Autowired
	@Nullable
	private TestSuiteNodeCrud testSuiteCrud;

	/** The neo4j session. */
	@Autowired
	@Nullable
	private transient Session neo4jSession;



	/**
	 * @return the template
	 */

	public Neo4jOperations getTemplate() {
		final Neo4jOperations template2 = template;
		if (template2 == null) {
			throw new IllegalStateException("template not initialized");
		} else {
			return template2;

		}

	}

	/**
	 * @param template
	 *            the template to set
	 */
	public void setTemplate(Neo4jTemplate template) {
		this.template = template;
	}

	/**
	 * @return the screenNodeRepo
	 */
	public ScreenNodeRepo getScreenNodeRepo() {
		final ScreenNodeRepo screenNodeRepo2 = screenNodeRepo;
		if (screenNodeRepo2 == null) {
			throw new IllegalStateException("screenNodeRepo Initialization");
		} else {
			return screenNodeRepo2;
		}
	}

	/**
	 * @param screenNodeRepo
	 *            the screenNodeRepo to set
	 */
	public void setScreenNodeRepo(ScreenNodeRepo screenNodeRepo) {
		this.screenNodeRepo = screenNodeRepo;
	}

	/**
	 * @return the webDomainRepo
	 */
	public WebDomainRepo getWebDomainRepo() {
		final WebDomainRepo webDomainRepo2 = webDomainRepo;
		if (webDomainRepo2 == null) {
			throw new IllegalStateException("webdomainrepo");
		} else {
			return webDomainRepo2;
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
	 * @param template
	 *            the template to set
	 */
	public void setTemplate(Neo4jOperations template) {
		this.template = template;
	}

	/**
	 * @return the testCaseRepo
	 */
	public TestCaseRepo getTestCaseRepo() {
		final TestCaseRepo testCaseRepo2 = testCaseRepo;
		if (testCaseRepo2 == null) {
			throw new IllegalStateException("test case repo");
		} else {
			return testCaseRepo2;

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
	 * @param neo4jSession
	 *            the neo4jSession to set
	 */
	public void setNeo4jSession(Session neo4jSession) {
		this.neo4jSession = neo4jSession;
	}

	/**
	 * @return the testSuiteRepo
	 */
	public TestSuiteRepo getTestSuiteRepo() {
		final TestSuiteRepo testSuiteRepo2 = testSuiteRepo;
		if (testSuiteRepo2 == null) {
			throw new IllegalStateException("testsuiterepo");
		} else {
			return testSuiteRepo2;
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
	 * @return the userInputValueRepo
	 */
	public UserInputValueRepo getUserInputValueRepo() {
		final UserInputValueRepo userInputValueRepo2 = userInputValueRepo;
		if (userInputValueRepo2 == null) {
			throw new IllegalStateException("userinputvaluerepo");

		} else {
			return userInputValueRepo2;
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
		if (predictedFieldNameRepo2 == null) {
			throw new IllegalStateException("PredictedFieldnameRepo");
		} else {
			return predictedFieldNameRepo2;

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
		if (userInputTrainingRecordRepo2 == null) {
			throw new IllegalStateException("userinputtrainingrecordrepo");
		} else {
			return userInputTrainingRecordRepo2;
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
	 * @param screenNodeCrud
	 *            the screenNodeCrud to set
	 */
	public void setScreenNodeCrud(ScreenNodeCrud screenNodeCrud) {
		this.screenNodeCrud = screenNodeCrud;
	}

	/**
	 * @return the webDomainCrud
	 */
	public WebDomainCrud getWebDomainCrud() {
		final WebDomainCrud webDomainCrud2 = webDomainCrud;
		if (webDomainCrud2 != null) {
			return webDomainCrud2;
		} else {
			throw new IllegalStateException("webdomaincrud");
		}
	}

	/**
	 * @param webDomainCrud
	 *            the webDomainCrud to set
	 */
	public void setWebDomainCrud(WebDomainCrud webDomainCrud) {
		this.webDomainCrud = webDomainCrud;
	}

	/**
	 * @return the actionElementTrainingRecordRepo
	 */
	public ActionElementTrainingRecordRepo getActionElementTrainingRecordRepo() {
		final ActionElementTrainingRecordRepo actionElementTrainingRecordRepo2 = actionElementTrainingRecordRepo;
		if (actionElementTrainingRecordRepo2 != null) {
			return actionElementTrainingRecordRepo2;
		} else {
			throw new IllegalStateException("actionelementtrainingrecordrepo");
		}
	}

	/**
	 * @param actionElementTrainingRecordRepo
	 *            the actionElementTrainingRecordRepo to set
	 */
	public void setActionElementTrainingRecordRepo(
			ActionElementTrainingRecordRepo actionElementTrainingRecordRepo) {
		this.actionElementTrainingRecordRepo = actionElementTrainingRecordRepo;
	}

	/**
	 * @return the testSuiteCrud
	 */
	public TestSuiteNodeCrud getTestSuiteCrud() {
		final TestSuiteNodeCrud testSuiteCrud2 = testSuiteCrud;
		if (testSuiteCrud2 != null) {
			return testSuiteCrud2;
		} else {
			throw new IllegalStateException("test suite crud");
		}
	}

	/**
	 * @param testSuiteCrud
	 *            the testSuiteCrud to set
	 */
	public void setTestSuiteCrud(TestSuiteNodeCrud testSuiteCrud) {
		this.testSuiteCrud = testSuiteCrud;
	}

	/**
	 * @return the testCaseCrud
	 */
	public TestCaseNodeCrud getTestCaseCrud() {
		final TestCaseNodeCrud testCaseCrud2 = testCaseCrud;
		if (testCaseCrud2 != null) {
			return testCaseCrud2;
		} else {
			throw new IllegalStateException("test case crud");
		}
	}

	/**
	 * @param testCaseCrud
	 *            the testCaseCrud to set
	 */
	public void setTestCaseCrud(TestCaseNodeCrud testCaseCrud) {
		this.testCaseCrud = testCaseCrud;
	}

	/**
	 * @return the userClickInputTrainingRecordRepo
	 */
	public UserInputTrainingRecordRepo getUserClickInputTrainingRecordRepo() {
		final UserInputTrainingRecordRepo userClickInputTrainingRecordRepo2 = userClickInputTrainingRecordRepo;
		if (userClickInputTrainingRecordRepo2 != null) {
			return userClickInputTrainingRecordRepo2;
		} else {
			throw new IllegalStateException("userclickinputtrainingrecordrepo");
		}
	}

	/**
	 * @param userClickInputTrainingRecordRepo the userClickInputTrainingRecordRepo to set
	 */
	public void setUserClickInputTrainingRecordRepo(
			UserInputTrainingRecordRepo userClickInputTrainingRecordRepo) {
		this.userClickInputTrainingRecordRepo = userClickInputTrainingRecordRepo;
	}

	/**
	 * @return the windowsSystemFilePickerScreenNodeCrud
	 */
	public WindowsSystemFilePickerScreenNodeCrud getWindowsSystemFilePickerScreenNodeCrud() {
		final WindowsSystemFilePickerScreenNodeCrud windowsSystemFilePickerScreenNodeCrud2 = windowsSystemFilePickerScreenNodeCrud;//NOPMD
		if (windowsSystemFilePickerScreenNodeCrud2 != null) {
			return windowsSystemFilePickerScreenNodeCrud2;
		} else {
			throw new IllegalStateException("windowssystemfilepickerScreenNodeCrud");
		}
	}

	/**
	 * @param windowsSystemFilePickerScreenNodeCrud the windowsSystemFilePickerScreenNodeCrud to set
	 */
	public void setWindowsSystemFilePickerScreenNodeCrud(
			WindowsSystemFilePickerScreenNodeCrud windowsSystemFilePickerScreenNodeCrud) {//NOPMD
		this.windowsSystemFilePickerScreenNodeCrud = windowsSystemFilePickerScreenNodeCrud;
	}

}
