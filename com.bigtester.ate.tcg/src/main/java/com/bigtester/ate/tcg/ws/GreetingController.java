package com.bigtester.ate.tcg.ws;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.data.neo4j.template.Neo4jTemplate;
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
import com.bigtester.ate.tcg.model.domain.Neo4jScreenNode;
import com.bigtester.ate.tcg.model.domain.UserInputTrainingRecord;
import com.bigtester.ate.tcg.model.repository.ScreenNodeRepo;
import com.bigtester.ate.tcg.utils.GlobalUtils;
import com.bigtester.ate.tcg.utils.exception.Html2DomException;
import com.bigtester.ate.tcg.ws.entity.WsScreenNames;
import com.thoughtworks.xstream.XStream;

// TODO: Auto-generated Javadoc
/**
 * The Class GreetingController.
 */
@RestController
public class GreetingController {

	/** The template. */
	@Autowired
	@Nullable
	private Neo4jOperations template;

	/** The screen node repo. */
	@Autowired
	@Nullable
	private ScreenNodeRepo screenNodeRepo;
	
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
	public List<UserInputTrainingRecord> predict() throws IOException,
			ClassNotFoundException {

		// traverseFrameTraining(firefox, null);

		// System.out.println("\n=======****FILE PARSING IS DONE for: "
		// + TEST_HTML_FILES[j] + "****=========\n");

		com.bigtester.ate.tcg.controller.UserInputsTrainer trainer = new com.bigtester.ate.tcg.controller.UserInputsTrainer();
		List<UserInputTrainingRecord> trainedRecords = trainer.train();

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
	public List<UserInputTrainingRecord> pioPredict(
			@RequestBody List<UserInputTrainingRecord> records)
			throws IOException, ClassNotFoundException, ExecutionException,
			InterruptedException {

		for (UserInputTrainingRecord record : records) {
			if (null != record)
				PredictionIOTrainer.queryEntity(record);
		}
		return records;
	}

	/**
	 * Gets the screen names.
	 *
	 * @param domainIndustryCode the domain industry code
	 * @return the screen names
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 * @throws ExecutionException the execution exception
	 * @throws InterruptedException the interrupted exception
	 */
	@CrossOrigin
	@RequestMapping(value = "/queryScreenNames", method = RequestMethod.GET)
	public WsScreenNames queryScreenNames(
			@RequestParam(value="q", required=false) String queryStr, @RequestParam(required=false, value="domainIndustryCode") String domainIndustryCode)
			throws IOException, ClassNotFoundException, ExecutionException,
			InterruptedException {

		Iterable<Neo4jScreenNode> allNodes = getScreenNodeRepo().findAll();
		WsScreenNames retVal = new WsScreenNames(0, false);
		for (Neo4jScreenNode node: allNodes) {
			retVal.getScreenNames().add(retVal.new ScreenName(node.getName(), ""));
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
	public List<UserInputTrainingRecord> saveIntermediateResult(
			@RequestBody IntermediateResult intermediateResult)
			throws IOException, ClassNotFoundException, ExecutionException,
			InterruptedException {
		boolean trainedAlready = false;
		for (UserInputTrainingRecord uitr : intermediateResult.getUitrs()) {
			uitr.setPioPredictConfidence(1.0);
			if (!StringUtils.isEmpty(uitr.getTrainedResult())) {
				trainedAlready = true;
			}
		}
		
		if (!trainedAlready)
		trainInputPIO(intermediateResult.getUitrs());
		Neo4jScreenNode firstNode = new Neo4jScreenNode("firstNode", "http://helloworld.com",
				intermediateResult);
		XStream xstream = new XStream();

	    Neo4jScreenNode secondNode = (Neo4jScreenNode) xstream.fromXML(xstream.toXML(firstNode));
	    secondNode.setName("secondNode");
		firstNode.steppedInto(
				secondNode, 1);
		
		getTemplate().save(
				secondNode);
		getTemplate().save(
				firstNode);
		return intermediateResult.getUitrs();
		
	}

	// @CrossOrigin
	// @RequestMapping(value = "/saveResult", method = RequestMethod.POST)
	// public List<UserInputTrainingRecord> saveResult(@RequestBody
	// List<UserInputTrainingRecord> records) throws IOException,
	// ClassNotFoundException, ExecutionException, InterruptedException {
	//
	// for (UserInputTrainingRecord record : records) {
	// PredictionIOTrainer.quertEntity(record);
	// }
	// return records;
	// }

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
	public List<UserInputTrainingRecord> trainInputPIO(
			@RequestBody List<UserInputTrainingRecord> records)
			throws ExecutionException, InterruptedException, IOException {
		// List<Greeting> greetings = new ArrayList<Greeting>();
		for (UserInputTrainingRecord record : records) {
			if (null != record) {
				String eventId = PredictionIOTrainer.sentTrainingEntity(record);
				record.setTrainedResult(eventId);
			}
		}
		return records;
	}
	
	

	/**
	 * Preprocessing.
	 *
	 * @param dom the dom
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws TransformerException the transformer exception
	 * @throws Html2DomException 
	 */
	@CrossOrigin
	@RequestMapping(value = "/preprocessing", method = RequestMethod.POST)
	public List<UserInputTrainingRecord> preprocessing(
			@RequestBody List<HTMLSource> dom) throws IOException,
			ParserConfigurationException, TransformerException, Html2DomException {
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

		List<UserInputTrainingRecord> retVal = new ArrayList<UserInputTrainingRecord>();
		for (int i = 0; i < csvStrings.size(); i++) {
			UserInputTrainingRecord uitr = new UserInputTrainingRecord();
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
	 * @param screenNodeRepo the screenNodeRepo to set
	 */
	public void setScreenNodeRepo(ScreenNodeRepo screenNodeRepo) {
		this.screenNodeRepo = screenNodeRepo;
	}

}
