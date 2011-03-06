package net.renalias.xml;

import com.ximpleware.AutoPilot;
import com.ximpleware.NavException;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.extended.PilotException;

import java.awt.dnd.Autoscroll;
import java.util.LinkedList;
import java.util.List;

public class VtdXmlBenchmark {

	List<AddressCodeLine> lines;

	public void run(String sourceFile) throws NavException, PilotException {

		lines = new LinkedList<AddressCodeLine>();

		VTDGen vg = new VTDGen();
		if (vg.parseFile(sourceFile, true)) {
			VTDNav vn = vg.getNav();

			AutoPilot ap = new AutoPilot(vn);
			ap.selectElement("*");
			int total=0;
			while (ap.iterate()) {
				String elementName = vn.toString(vn.getCurrentIndex());

				if(elementName.equals("ns2:AddressCodeLine")) {
					int codeInt = vn.getAttrVal("code");
					String code = "";
					if (codeInt != -1) {
						code = vn.toNormalizedString(codeInt);
					}
					int idInt = vn.getAttrVal("id");
					String id = "";
					if (idInt != -1)
						id = vn.toNormalizedString(idInt);

					lines.add(new AddressCodeLine(id, code));

					total++;
				}

				//System.out.println("Id:" + id + " - Code: " + code);
			}

			System.out.println("Total nodes processed:" + total);
		}
	}
}