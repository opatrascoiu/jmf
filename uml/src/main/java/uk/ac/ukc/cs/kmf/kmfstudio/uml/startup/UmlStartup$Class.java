/**
 *
 *  Class umlStartup.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:48
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.startup;

public class UmlStartup$Class
	implements UmlStartup
{
	//--- The repository ---
	protected uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository rep;
	public uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository getRepository() {
		return rep;
	}
	public void setRepository(uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository rep) {
		this.rep = rep;
	}

	//--- Replace the default lifecycle handlers ---
	protected void replaceDefaultLifecycles() {
	}

	//--- Initialise population ---
	protected void initialisePopulation() {
	}

	//--- Start the 'uml' browser ---
	protected void startBrowser() {
		new uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlBrowser$Class(null, "C:/D/Work/Java Projects/UMLModel-2.0/src/UMLModel.xmi");
		uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlBrowser$Class browser = new uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlBrowser$Class(null, "C:/D/Work/Java Projects/UMLModel-2.0/src/UMLModel.xmi");
		browser.setRep(rep);
		browser.show();
	}

	//--- run ---
	public void run(uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository rep) {
		setRepository(rep);
		replaceDefaultLifecycles();
		initialisePopulation();
		startBrowser();
	}

	//--- Main ---
	public static void main(String args[]) {
		(new UmlStartup$Class()).run(new uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository$Class());
	}
}
