package com.bigtester.ate.tcg.ws;//NOPMD

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.bigtester.ate.tcg.controller.PredictionIOTrainer;
import com.bigtester.ate.tcg.model.IntermediateResult;
import com.bigtester.ate.tcg.model.domain.HTMLSource;
import com.bigtester.ate.tcg.model.domain.InScreenJumperTrainingRecord;
import com.bigtester.ate.tcg.model.domain.Neo4jScreenNode;
import com.bigtester.ate.tcg.model.domain.PredictedFieldName;
import com.bigtester.ate.tcg.model.domain.UserInputValue;
import com.bigtester.ate.tcg.model.domain.WebDomain;
import com.bigtester.ate.tcg.model.domain.WebElementTrainingRecord;
import com.bigtester.ate.tcg.model.domain.WebElementTrainingRecord.UserInputType;
import com.bigtester.ate.tcg.model.domain.WindowsSystemFilePickerScreenNode;
import com.bigtester.ate.tcg.utils.GlobalUtils;
import com.bigtester.ate.tcg.utils.exception.Html2DomException;
import com.bigtester.ate.tcg.ws.entity.WsPredictedFieldNames;
import com.bigtester.ate.tcg.ws.entity.WsScreenNames;
import com.bigtester.ate.tcg.ws.entity.WsUserInputValues;

/**
 * The Class GreetingController.
 */
@RestController
public class GreetingController extends BaseWsController{

	/**
	 * Predict.
	 *
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 */
	@CrossOrigin
	@RequestMapping("/predict")
	public List<WebElementTrainingRecord> predict() throws IOException,
			ClassNotFoundException {

		// traverseFrameTraining(firefox, null);

		// System.out.println("\n=======****FILE PARSING IS DONE for: "
		// + TEST_HTML_FILES[j] + "****=========\n");

		com.bigtester.ate.tcg.controller.UserInputsTrainer trainer = new com.bigtester.ate.tcg.controller.UserInputsTrainer();
		List<WebElementTrainingRecord> trainedRecords = trainer.train();

		com.bigtester.ate.tcg.controller.TrainingFileDB.writeCacheCsvFile(
				com.bigtester.ate.tcg.controller.UserInputsTrainer.CACHEPATH
						+ "firsttest" + ".txt", "begin", "end", trainedRecords,
				true);
		return trainedRecords;
	}

	/**
	 * Pio predict.
	 *
	 * @param records
	 *            the records
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@CrossOrigin
	@RequestMapping(value = "/pioPredict", method = RequestMethod.POST)
	public List<WebElementTrainingRecord> pioPredict(
			@RequestBody List<WebElementTrainingRecord> records)
			throws IOException, ClassNotFoundException, ExecutionException,
			InterruptedException {

		for (int i = 0; i < records.size(); i++) {
			WebElementTrainingRecord record = records.get(i);
			if (null != record) {
				PredictionIOTrainer.queryEntity(record);
				String tmpMLHtmlCode = record.getInputMLHtmlCode();
				Iterable<? extends WebElementTrainingRecord> existingRecord;
				if (record.getUserInputType().equals(UserInputType.USERINPUT)) {
					existingRecord = getUserInputTrainingRecordRepo()
							.findByInputMLHtmlCode(tmpMLHtmlCode);
				} else if (record.getUserInputType().equals(UserInputType.INSCREENJUMPER)) {
					existingRecord = getUserClickInputTrainingRecordRepo()
							.findByInputMLHtmlCode(tmpMLHtmlCode);
				}
				else {
					existingRecord = getActionElementTrainingRecordRepo()
							.findByInputMLHtmlCode(tmpMLHtmlCode);
					// for (java.util.Iterator<Neo4jScreenNode> itr =
					// ((ScreenActionElementTrainingRecord)
					// existingRecord.iterator().next()).getStepOuts().iterator();
					// itr.hasNext();) {
					// for
					// (java.util.Iterator<ScreenActionElementTrainingRecord>
					// itr2 = itr.next().getActionUitrs().iterator();
					// itr2.hasNext();) {
					// if (itr2.next().getId() ==
					// existingRecord.iterator().next().getId()) {
					// //loop stepOut, so we will need delete this record
					// temporarily for the bug in the spring ws reponse (not
					// handling loop object)
					// }
					// }
					// }

				}
				// Double confidencetmp = record.getPioPredictConfidence();
				if (existingRecord.iterator().hasNext()) {
					WebElementTrainingRecord tmpWetr = existingRecord.iterator().next();
					if (records.get(i).getUserInputType() ==tmpWetr.getUserInputType())
						records.set(i, tmpWetr);
					// records.get(i).setPioPredictConfidence(confidencetmp);
					// records.get(i).getPioPredictLabelResult()
					// .setValue(tmpLabel);
				} else {
					String tmpLabel = record.getPioPredictLabelResult()
							.getValue();

					Iterable<? extends WebElementTrainingRecord> allSameFieldUitrs = getUserInputTrainingRecordRepo()
							.findByPioPredictLabelResultValue(tmpLabel);
					for (java.util.Iterator<? extends WebElementTrainingRecord> itr = allSameFieldUitrs
							.iterator(); itr.hasNext();) {
						record.getUserValues().addAll(
								itr.next().getUserValues());
					}

					Iterable<? extends WebElementTrainingRecord> allSameFieldActionUitrs = this
							.getActionElementTrainingRecordRepo()
							.findByPioPredictLabelResultValue(tmpLabel);
					for (java.util.Iterator<? extends WebElementTrainingRecord> itr = allSameFieldActionUitrs
							.iterator(); itr.hasNext();) {
						record.getUserValues().addAll(
								itr.next().getUserValues());
					}
				}
			}
		}
		return records;
	}

	/**
	 * Page predict.
	 *
	 * @param pageFrames
	 *            the page frames
	 * @return the map
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@CrossOrigin
	@RequestMapping(value = "/pagePredict", method = RequestMethod.POST)
	public Map<String, Double> pagePredict(
			@RequestBody Set<HTMLSource> pageFrames) throws IOException,
			ClassNotFoundException, ExecutionException, InterruptedException {

		Map<String, Double> retVal = new HashMap<String, Double>();// NOPMD

		if (pageFrames.isEmpty()) {
			retVal.put("", 0.0);
		} else {
			retVal = PredictionIOTrainer.queryEntity(pageFrames);

		}

		return retVal;
	}

	/**
	 * Gets the screen names.
	 *
	 * @param domainIndustryCode
	 *            the domain industry code
	 * @return the screen names
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@CrossOrigin
	@RequestMapping(value = "/queryScreenNames", method = RequestMethod.GET)
	public WsScreenNames queryScreenNames(
			@RequestParam(value = "q", required = false) String queryStr,
			@RequestParam(required = false, value = "domainIndustryCode") String domainIndustryCode)
			throws IOException, ClassNotFoundException, ExecutionException,
			InterruptedException {

		Iterable<Neo4jScreenNode> allNodes = getScreenNodeRepo().findAll();
		WsScreenNames retVal = new WsScreenNames(0, false);
		for (Neo4jScreenNode node : allNodes) {
			retVal.getScreenNames().add(
					retVal.new ScreenName(node.getName(), ""));
		}
		return retVal;
	}

	/**
	 * Query user input values.
	 *
	 * @param queryStr
	 *            the query str
	 * @param domainIndustryCode
	 *            the domain industry code
	 * @return the ws user input values
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@CrossOrigin
	@RequestMapping(value = "/queryUserInputValues", method = RequestMethod.GET)
	public WsUserInputValues queryUserInputValues(
			@RequestParam(value = "q", required = false) String queryStr,
			@RequestParam(required = false, value = "domainIndustryCode") String domainIndustryCode)
			throws IOException, ClassNotFoundException, ExecutionException,
			InterruptedException {

		Iterable<UserInputValue> allNodes = getUserInputValueRepo().findAll();
		WsUserInputValues retVal = new WsUserInputValues(0, false);
		for (UserInputValue node : allNodes) {
			retVal.getUserValues().add(new UserInputValue(node.getValue(), ""));
		}
		return retVal;
	}

	/**
	 * Query pio predicted field names.
	 *
	 * @param queryStr
	 *            the query str
	 * @param domainIndustryCode
	 *            the domain industry code
	 * @return the ws predicted field names
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@CrossOrigin
	@RequestMapping(value = "/queryPioPredictedFieldNames", method = RequestMethod.GET)
	public WsPredictedFieldNames queryPioPredictedFieldNames(
			@RequestParam(value = "q", required = false) String queryStr,
			@RequestParam(required = false, value = "domainIndustryCode") String domainIndustryCode)
			throws IOException, ClassNotFoundException, ExecutionException,
			InterruptedException {

		Iterable<PredictedFieldName> allNodes = getPredictedFieldNameRepo()
				.findAll();
		WsPredictedFieldNames retVal = new WsPredictedFieldNames(0, false);
		for (PredictedFieldName node : allNodes) {
			retVal.getFieldNames().add(
					new PredictedFieldName(node.getValue(), ""));
		}
		return retVal;
	}

	/**
	 * Save intermediate result.
	 *
	 * @param intermediateResult
	 *            the intermediate result
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@CrossOrigin
	@RequestMapping(value = "/saveIntermediateResult", method = RequestMethod.POST)
	public IntermediateResult saveIntermediateResult(
			@RequestBody IntermediateResult intermediateResult)
			throws IOException, ClassNotFoundException, ExecutionException,
			InterruptedException {
		
		for (WebElementTrainingRecord uitr : intermediateResult.getUitrs()) {
			// uitr.setPioPredictConfidence(1.0);
			if (StringUtils.isEmpty(uitr.getTrainedResult())) {
				trainInputPIO(uitr);
			}
			
		}
		
		for (WebElementTrainingRecord uitr : intermediateResult.getActionUitrs()) {
			// uitr.setPioPredictConfidence(1.0);
			if (StringUtils.isEmpty(uitr.getTrainedResult())) {
				trainInputPIO(uitr);
			}
			
		}
		for (WebElementTrainingRecord uitr : intermediateResult.getClickUitrs()) {
			// uitr.setPioPredictConfidence(1.0);
			if (StringUtils.isEmpty(uitr.getTrainedResult())) {
				trainInputPIO(uitr);
			}
			if (uitr instanceof InScreenJumperTrainingRecord && ((InScreenJumperTrainingRecord) uitr).isActionTrigger()) {
				int clickTimes = ((InScreenJumperTrainingRecord) uitr).getClickTimes() + 1;
				((InScreenJumperTrainingRecord) uitr).setClickTimes(clickTimes);
				//((InScreenJumperTrainingRecord) uitr).setActionTrigger(false);
			}
		}

//		if (!trainedAlready) {
//			trainInputPIO(intermediateResult.getUitrs());
//			trainInputPIO(intermediateResult.getActionUitrs());
//		}

		PredictionIOTrainer.sentTrainingEntity(
				intermediateResult.getDomStrings(),
				intermediateResult.getScreenName());

		// 1. query db for existing root test suite
		// intermediateResult.getTestSuitesMap().get(0);
		// 2. add the testSuitesMap into the existing test suite tree if there
		// is.
		// 3. or create a new test suite node tree (graph) in db.
		// 4. above step could be done by create a repository method, ex. save

		// Iterator<TestSuite> itr =
		// intermediateResult.getTestSuitesMap().iterator();
		// TestSuite existingSuite;
		// TestSuite newSuite;
		// while (itr.hasNext()) {
		// String testSuiteName = itr.next().getName();
		// existingSuite = getTestSuiteRepo().getTestSuiteByName(testSuiteName);
		// if (null == existingSuite) {
		// newSuite = new TestSuite(testSuiteName);
		// }
		// }
		//

		getTestSuiteCrud().createOrUpdate(intermediateResult, true);

		Neo4jScreenNode currentNode = getScreenNodeCrud().createOrUpdate(
				intermediateResult, false); //false

		// save industry categories map, similar with save test suite
		getTestCaseCrud().createOrUpdate(intermediateResult, true);

		currentNode = getScreenNodeCrud().updateTestCaseRelationships(
				currentNode, intermediateResult, false);//false
		//

		WebDomain domainNode = getWebDomainCrud().createOrUpdate(
				intermediateResult, false);//false
		// if (!domainNode.getScreens().contains(currentNode))
		// domainNode.getScreens().add(currentNode);
		domainNode = getWebDomainCrud().updateScreenNodes(domainNode,
				currentNode, true);

		Long tmp = currentNode.getId();
		if (null == tmp)
			throw new IllegalStateException("node id");
		intermediateResult.setScreenNodeNeo4jId(tmp);
		return intermediateResult;
	}

	/**
	 * Save intermediate result for windows file picker.
	 *
	 * @param intermediateResult the intermediate result
	 * @return the intermediate result
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 * @throws ExecutionException the execution exception
	 * @throws InterruptedException the interrupted exception
	 */
	@CrossOrigin
	@RequestMapping(value = "/saveIntermediateResultForWindowsFilePicker", method = RequestMethod.POST)
	public IntermediateResult saveIntermediateResultForWindowsFilePicker(
			@RequestBody IntermediateResult intermediateResult)
			throws IOException, ClassNotFoundException, ExecutionException,
			InterruptedException {
		
		getTestSuiteCrud().createOrUpdate(intermediateResult, true);

		WindowsSystemFilePickerScreenNode currentNode = getWindowsSystemFilePickerScreenNodeCrud().createOrUpdate(
				intermediateResult, false); //false

		getTestCaseCrud().createOrUpdate(intermediateResult, true);

		currentNode = getWindowsSystemFilePickerScreenNodeCrud().updateTestCaseRelationships(
				currentNode, intermediateResult, false);//false
		
		WebDomain domainNode = getWebDomainCrud().createOrUpdate(
				intermediateResult, false);//false
		
		domainNode = getWebDomainCrud().updateScreenNodes(domainNode,
				currentNode, true);

		Long tmp = currentNode.getId();
		if (null == tmp)
			throw new IllegalStateException("node id");
		intermediateResult.setScreenNodeNeo4jId(tmp);
		return intermediateResult;
	}

	
	/**
	 * Train input pio.
	 *
	 * @param records
	 *            the records
	 * @return the list
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@CrossOrigin
	@RequestMapping(value = "/trainIntoPIO", method = RequestMethod.POST)
	public Set<? extends WebElementTrainingRecord> trainInputPIO(
			@RequestBody Set<? extends WebElementTrainingRecord> records)
			throws ExecutionException, InterruptedException, IOException {
		
		for (WebElementTrainingRecord record : records) {
			if (null != record) {
				trainInputPIO(record);
			}
		}
		return records;
	}
	
	private WebElementTrainingRecord trainInputPIO(
			WebElementTrainingRecord record)
			throws ExecutionException, InterruptedException, IOException {
		// List<Greeting> greetings = new ArrayList<Greeting>();
		
			if (null != record) {
				String eventId = PredictionIOTrainer.sentTrainingEntity(record);
				record.setTrainedResult(eventId);
			}
		
		return record;
	}

	/**
	 * Preprocessing.
	 *
	 * @param dom
	 *            the dom
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws TransformerException
	 *             the transformer exception
	 * @throws Html2DomException
	 */
	@CrossOrigin
	@RequestMapping(value = "/preprocessing", method = RequestMethod.POST)
	public List<WebElementTrainingRecord> preprocessing(
			@RequestBody List<HTMLSource> dom) throws IOException,
			ParserConfigurationException, TransformerException,
			Html2DomException {
		List<String> csvStrings = new ArrayList<String>();
		for (int i = 0; i < dom.size(); i++) {
			Document doc = GlobalUtils.html2Dom(dom.get(i).getDomDoc());

			com.bigtester.ate.tcg.controller.WebFormUserInputsCollector col;
			// GlobalUtils.printDocument(doc.getDocumentElement(), System.out);
			col = new com.bigtester.ate.tcg.controller.WebFormUserInputsCollector(
					doc, dom.get(0).getXpathOfFrame());
			// System.out.println("\n*******************\n");
			// System.out.println("\n*******************\n");

			for (com.bigtester.ate.tcg.model.UserInputDom inputDom : col
					.getUserInputs()) {
				StringBuffer temp = new StringBuffer("");
				for (Node node : inputDom.getMachineLearningDomHtmlPointers()) {
					if (null != node) {
						ByteArrayOutputStream stringOutput = new ByteArrayOutputStream();
						GlobalUtils.printDocument(node, stringOutput);
						stringOutput.toString();
						temp.append(stringOutput.toString());
					}
				}
				if (StringUtils.isNotEmpty(temp))
					csvStrings.add(temp.toString());

				// System.out.println("\n--above Node print----\n");

				// List<Node> nodes =
				// inputDom.getMachineLearningDomHtmlPointers();
				// if (nodes != null)
				// for (Node node : nodes)
				// GlobalUtils.printDocument(node, System.out);
				// System.out.println("\n------above node ML code---------\n");
				// GlobalUtils.printDocument(inputDom.getLabelDomPointer(),
				// System.out);
				// System.out
				// .println("\n------above node lable code-----------------------\n");

				// List<Node> nodes2 = inputDom.getAdditionalInfoNodes();
				// if (nodes2 != null)
				// for (Node node2 : nodes2)
				// GlobalUtils.printDocument(node2, System.out);
				// System.out
				// .println("\n=======above node additional info code=========\n");

			}
		}
		com.bigtester.ate.tcg.controller.TrainingFileDB.cleanTestCsvFile();

		com.bigtester.ate.tcg.controller.TrainingFileDB.writeTestCsvFile(
				csvStrings, true);

		List<WebElementTrainingRecord> retVal = new ArrayList<WebElementTrainingRecord>();
		for (int i = 0; i < csvStrings.size(); i++) {
			WebElementTrainingRecord uitr = new WebElementTrainingRecord();
			StringBuffer temp = new StringBuffer(csvStrings.get(i));
			String temp2 = temp.toString();
			if (null == temp2)
				uitr.setInputMLHtmlCode("");
			else
				uitr.setInputMLHtmlCode(temp2);

			retVal.add(uitr);

		}
		// List<WebElement> iframes = webD.findElements(By.tagName("iframe"));
		// List<WebElement> frames = webD.findElements(By.tagName("frame"));
		// iframes.addAll(frames);
		// for (WebElement frame : iframes) {
		// String xpathOfChildFrame = getAbsoluteXPath(frame, webD);
		// webD.switchTo().frame(frame);
		// traverseFrameTraining(webD, xpathOfChildFrame);
		// }
		// webD.switchTo().parentFrame();
		return retVal;
	}
}
