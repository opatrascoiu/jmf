/**
 *
 *  Class edocStartup.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:38
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.startup;

public class EdocStartup$Class
	implements EdocStartup
{
	//--- The repository ---
	protected edoc.repository.EdocRepository rep;
	public edoc.repository.EdocRepository getRepository() {
		return rep;
	}
	public void setRepository(edoc.repository.EdocRepository rep) {
		this.rep = rep;
	}

	//--- Replace the default lifecycle handlers ---
	protected void replaceDefaultLifecycles() {
	}

	//--- Initialise population ---
	protected void initialisePopulation() {
	}

	//--- Start the 'edoc' browser ---
	protected void startBrowser() {
		new edoc.repository.EdocBrowser$Class("C:/D/Work/Java Projects/YATL4KMF/src/test/scripts/EDOC.xmi");
		edoc.repository.EdocBrowser$Class browser = new edoc.repository.EdocBrowser$Class("C:/D/Work/Java Projects/YATL4KMF/src/test/scripts/EDOC.xmi");
		browser.setRep(rep);
		browser.show();
	}

	//--- run ---
	public void run(edoc.repository.EdocRepository rep) {
		setRepository(rep);
		replaceDefaultLifecycles();
		initialisePopulation();
		startBrowser();
	}

	//--- Main ---
	public static void main(String args[]) {
		(new EdocStartup$Class()).run(new edoc.repository.EdocRepository$Class());
	}
}