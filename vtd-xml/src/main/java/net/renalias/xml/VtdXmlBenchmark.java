package net.renalias.xml;

import com.ximpleware.NavException;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;

public class VtdXmlBenchmark {

	public void testXml() {
		System.out.println("Hello, world");
	}

	public void run(String sourceFile) throws NavException {
		VTDGen vg = new VTDGen();
		if (vg.parseFile(sourceFile, true)) {
			VTDNav vn = vg.getNav();
			//toElementNS is the namespace aware version of toElement which navigates the cursor
			if (vn.toElementNS(VTDNav.FIRST_CHILD, "someURL", "b")) {
				int i = vn.getText(); // get the VTD record index
				if (i != -1) {
					// convert i into string before printing,
					// toNormalizedString(i) and toRawString(i) are two other options
					System.out.println("the text node value at " + i + " ==> " + vn.toString(i));
				}
			}
		}
	}
}