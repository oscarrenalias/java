package tests;

import net.renalias.xml.VtdXmlBenchmark;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class XmlParserTest {

	@Test
	public void testVtdXml() throws Exception {
		VtdXmlBenchmark m = new VtdXmlBenchmark();

		long startTime = System.currentTimeMillis();
		m.run("src/main/resources/testdata/response.xml");
		long stopTime = System.currentTimeMillis();

		System.out.println("Processing time: " + (stopTime - startTime) + " ms");

		assertTrue(true);
	}
}