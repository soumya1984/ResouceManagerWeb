package com.sjsu.courseapp.jms;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlStringConvertor {
	public static com.sjsu.courseapp.loadbalancer.ResourceRequest procesor(
			String xmlString) throws Exception {
		DocumentBuilder db = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(xmlString));
		com.sjsu.courseapp.loadbalancer.ResourceRequest requestInstance = new com.sjsu.courseapp.loadbalancer.ResourceRequest();
		Document doc = db.parse(is);
		NodeList nodes = doc.getElementsByTagName("request");

		for (int i = 0; i < nodes.getLength(); i++) {
			Element element = (Element) nodes.item(i);

			NodeList name = element.getElementsByTagName("email");
			Element line = (Element) name.item(0);
			System.out.println("email: " + getCharacterDataFromElement(line));

			NodeList memory = element.getElementsByTagName("memory");
			line = (Element) memory.item(0);
			System.out.println("memory: " + getCharacterDataFromElement(line));
			requestInstance.setMemory(Integer
					.parseInt(getCharacterDataFromElement(line)));

			NodeList requestId = element.getElementsByTagName("requestId");
			line = (Element) requestId.item(0);
			System.out.println("requestId: "
					+ getCharacterDataFromElement(line));

			requestInstance.setRequestId(Integer
					.parseInt(getCharacterDataFromElement(line)));

			NodeList userId = element.getElementsByTagName("userId");
			line = (Element) userId.item(0);
			System.out.println("userId: "
					+ getCharacterDataFromElement(line));

			requestInstance.setUserId(Integer
					.parseInt(getCharacterDataFromElement(line)));

			NodeList cpu = element.getElementsByTagName("cpu");
			line = (Element) cpu.item(0);
			System.out.println("cpu: " + getCharacterDataFromElement(line));
			requestInstance.setCpu_units(Integer
					.parseInt(getCharacterDataFromElement(line)));
			
			NodeList storage = element.getElementsByTagName("storage");
			line = (Element) storage.item(0);
			System.out.println("storage: " + getCharacterDataFromElement(line));
			requestInstance.setStorage(Integer
					.parseInt(getCharacterDataFromElement(line)));
			
			NodeList osType = element.getElementsByTagName("osType");
			line = (Element) osType.item(0);
			System.out.println("osType: " + getCharacterDataFromElement(line));
			requestInstance.setType(getCharacterDataFromElement(line));
			
			
			NodeList os = element.getElementsByTagName("os");
			line = (Element) os.item(0);
			System.out.println("os: " + getCharacterDataFromElement(line));
			requestInstance.setOs(getCharacterDataFromElement(line));

			NodeList country = element.getElementsByTagName("country");
			line = (Element) country.item(0);
			System.out.println("country: " + getCharacterDataFromElement(line));
			requestInstance.setGeolocation(getCharacterDataFromElement(line));

			
			NodeList algoritm = element.getElementsByTagName("algorithm");
			line = (Element) algoritm.item(0);
			System.out.println("algorithm: " + getCharacterDataFromElement(line));
			requestInstance.setAlgorithm(getCharacterDataFromElement(line));
			
			NodeList count = element.getElementsByTagName("count");
			line = (Element) count.item(0);
			System.out.println("count: " + getCharacterDataFromElement(line));
			requestInstance.setTotalCount(Integer.parseInt(getCharacterDataFromElement(line)));
		}
		return requestInstance;
	}

	public static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}
}
