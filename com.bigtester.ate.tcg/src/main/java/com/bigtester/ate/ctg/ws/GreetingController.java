package com.bigtester.ate.ctg.ws;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.bigtester.ate.ctg.controller.PredictionIOTrainer;
import com.bigtester.ate.ctg.model.Greeting;
import com.bigtester.ate.ctg.model.UserInputTrainingRecord;
import com.bigtester.ate.ctg.utils.GlobalUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class GreetingController.
 */
@RestController
public class GreetingController {

	/** The counter. */
	private final transient AtomicLong counter = new AtomicLong();

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

		com.bigtester.ate.ctg.controller.UserInputsTrainer trainer = new com.bigtester.ate.ctg.controller.UserInputsTrainer();
		List<UserInputTrainingRecord> trainedRecords = trainer.train();

		com.bigtester.ate.ctg.controller.TrainingFileDB.writeCacheCsvFile(
				com.bigtester.ate.ctg.controller.UserInputsTrainer.CACHEPATH
						+ "firsttest" + ".txt", "begin", "end", trainedRecords,
				true);
		return trainedRecords;
	}

	@CrossOrigin
	@RequestMapping(value = "/pioPredict", method = RequestMethod.POST)
	public List<UserInputTrainingRecord> pioPredict(@RequestBody List<UserInputTrainingRecord> records) throws IOException,
			ClassNotFoundException, ExecutionException, InterruptedException {

		for (UserInputTrainingRecord record : records) {
			PredictionIOTrainer.quertEntity(record);
		}
		return records;
	}

	
	@CrossOrigin
	@RequestMapping(value = "/trainIntoPIO", method = RequestMethod.POST)
	public List<UserInputTrainingRecord> trainInputPIO(@RequestBody List<UserInputTrainingRecord> records) throws ExecutionException, InterruptedException, IOException {
		//List<Greeting> greetings = new ArrayList<Greeting>();
		for (UserInputTrainingRecord record : records) {
			String eventId = PredictionIOTrainer.sentTrainingEntity(record);
			record.setTrainedResult(eventId);
		}
		return records;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/preprocessing", method = RequestMethod.POST)
	public Greeting preprocessing(@RequestBody List<HTMLSource> dom)
			throws IOException, ParserConfigurationException,
			TransformerException {
		List<String> csvStrings = new ArrayList<String>();
		for (int i = 0; i < dom.size(); i++) {
			Document doc = GlobalUtils.html2Dom(dom.get(i).getDomDoc());

			com.bigtester.ate.ctg.controller.WebFormUserInputsCollector col;
			GlobalUtils.printDocument(doc.getDocumentElement(), System.out);
			col = new com.bigtester.ate.ctg.controller.WebFormUserInputsCollector(
					doc, dom.get(0).getXpathOfFrame());
			System.out.println("\n*******************\n");
			System.out.println("\n*******************\n");

			for (com.bigtester.ate.ctg.model.UserInputDom inputDom : col
					.getUserInputs()) {
				String temp = "";
				for (Node node : inputDom.getMachineLearningDomHtmlPointers()) {
					ByteArrayOutputStream stringOutput = new ByteArrayOutputStream();
					GlobalUtils.printDocument(node, stringOutput);
					stringOutput.toString();
					temp = temp + stringOutput.toString();
				}
				if (StringUtils.isNotEmpty(temp))
					csvStrings.add(temp);

				System.out.println("\n--above Node print----\n");

				List<Node> nodes = inputDom.getMachineLearningDomHtmlPointers();
				if (nodes != null)
					for (Node node : nodes)
						GlobalUtils.printDocument(node, System.out);
				System.out.println("\n------above node ML code---------\n");
				GlobalUtils.printDocument(inputDom.getLabelDomPointer(),
						System.out);
				System.out
						.println("\n------above node lable code-----------------------\n");

				List<Node> nodes2 = inputDom.getAdditionalInfoNodes();
				if (nodes2 != null)
					for (Node node2 : nodes2)
						GlobalUtils.printDocument(node2, System.out);
				System.out
						.println("\n=======above node additional info code=========\n");

			}
		}
		com.bigtester.ate.ctg.controller.TrainingFileDB.cleanTestCsvFile();

		com.bigtester.ate.ctg.controller.TrainingFileDB.writeTestCsvFile(
				csvStrings, true);

		// List<WebElement> iframes = webD.findElements(By.tagName("iframe"));
		// List<WebElement> frames = webD.findElements(By.tagName("frame"));
		// iframes.addAll(frames);
		// for (WebElement frame : iframes) {
		// String xpathOfChildFrame = getAbsoluteXPath(frame, webD);
		// webD.switchTo().frame(frame);
		// traverseFrameTraining(webD, xpathOfChildFrame);
		// }
		// webD.switchTo().parentFrame();
		return new Greeting(counter.incrementAndGet(), "Done");

	}

}
