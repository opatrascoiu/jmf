package uk.ac.kent.cs.kmf.kmfstudio;

import java.io.*;

import uk.ac.kent.cs.kmf.util.*;
import uk.ac.kent.cs.kmf.kmfstudio.gui.*;

public class KMFStudio {
    //--- Batch main ---
    public static void main(String args[]) {
    	//--- args is empty implies launching the graphic interface
    	if (args.length == 0) {
    		new StudioGUI();
    	//--- args[0] is a ksp file
    	} else if (args.length == 1) {
      		try {
				//--- Create the log file
				ILog console = new FileLog("KMFStudio.log");
      			//--- Create a default naming ---
	        	Project project = new ProjectImpl();
	        	//--- Load the project ---
	        	project.loadSettings(args[0]);
		        //--- Add log message ---
		        console.reportMessage("Generating Java code ...");
		        //--- Generate the code ---
		        if (!console.tooManyViolations()) {
		        	CodeGenerator gen = new CodeGeneratorImpl();
		        	gen.generate(project, new NamingImpl(project, console), console);
		        }
                //--- Pront the log file ---
                console.close();
                BufferedReader in = new BufferedReader(new FileReader("KMFStudio.log"));
                String line;
                while ((line=in.readLine())!=null) {
                    System.out.println(line); 
                }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    //--- Bad usage
        } else {
        	System.out.println("Usage: >KMFStudio projectFile");
        	System.out.println("   - projectFile: the full pathname of a KMFStudio project file");
        }
    }

}