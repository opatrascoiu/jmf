package test;

import java.io.FileReader;

import uk.ac.kent.cs.yatl.syntax.parser.YatlParser;
import uk.ac.kent.cs.yatl.syntax.parser.YatlParserImpl;
import uk.ac.kent.cs.yatl.syntax.transformations.Unit;
import uk.ac.kent.cs.kmf.util.*;

/**
 * @author Octavian Patrascoiu
 *
 */
public class TestParserFromFile {
	public static void main(String[] args) {
		try {
			ILog log = new OutputStreamLog(System.out);
			YatlParser parser = new YatlParserImpl();
			FileReader input = new FileReader("src/test/scripts/tests.ktl");
			Unit unit = parser.parse(input, new OutputStreamLog(System.out), true);
			// Display the unit
			String unitStr = "null";
			if (unit != null) {
				unitStr = unit.toString();
			}
			//--- Report message ---
			log.reportMessage("KTL Model:");
			log.reportMessage(unitStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
