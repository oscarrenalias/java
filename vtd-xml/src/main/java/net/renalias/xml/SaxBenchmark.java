package net.renalias.xml;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SaxBenchmark {

	int total = 0;
	List<AddressCodeLine> lines;

	class SAXBenchmarkHandler extends DefaultHandler {
		public SAXBenchmarkHandler() {
			super();
		}

		public void startElement(String uri, String name, String qName, Attributes atts) {
			if ("AddressCodeLine".equals(name)) {
				lines.add(new AddressCodeLine(atts.getValue("id"), atts.getValue("code")));
				total++;
			}
		}
	}

	public void run(String sourceFile) throws SAXException, IOException {
		lines = new LinkedList<AddressCodeLine>();

		XMLReader reader = XMLReaderFactory.createXMLReader();
		SAXBenchmarkHandler handler = new SAXBenchmarkHandler();
		reader.setContentHandler(handler);
		reader.setErrorHandler(handler);

		FileReader fr = new FileReader(sourceFile);
		reader.parse(new InputSource(fr));

		System.out.println("Total nodes processed:" + total);
	}
}
