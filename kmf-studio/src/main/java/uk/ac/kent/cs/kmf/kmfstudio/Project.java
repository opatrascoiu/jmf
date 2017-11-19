package uk.ac.kent.cs.kmf.kmfstudio;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.kent.cs.kmf.quality.metrics.MetricSet;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository;

public interface Project {
	/** Project name */
	public String getProjectName();
	public void setProjectName(String name);

	/** Project path */
	public String getProjectPath();
	public void setProjectPath(String name);

	/** Project type */
	public String getProjectType();
	public void setProjectType(String name);

	/** Project file name */
	public String getProjectFileName();

	/** Input XMI file */
	public String getXmiFileName();
	public void setXmiFileName(String name);

	/** Package offset */
	public String getPackageOffset();
	public void setPackageOffset(String name);

	/** Licence file name  */
	public String getLicenseFileName();
	public void setLicenseFileName(String name);

	/** Model name  */
	public String getModelName();
	public void setModelName(String name);

	/** Generate visitor */
	public boolean isGenerateVisitor();
	public void setGenerateVisitor(boolean b);

	/** Generate HUTN visitor */
	public boolean isGenerateHUTNVisitor();
	public void setGenerateHUTNVisitor(boolean b);

	/** Generate JTREE visitor */
	public boolean isGenerateJTreeVisitor();
	public void setGenerateJTreeVisitor(boolean b);

	/** Generate VIEW/EDIT visitor */
	public boolean isGenerateViewEditVisitor();
	public void setGenerateViewEditVisitor(boolean b);

	/** Generate XMI visitor */
	public boolean isGenerateXMIVisitor();
	public void setGenerateXMIVisitor(boolean b);

	/** Generate observer */
	public boolean isGenerateObserver();
	public void setGenerateObserver(boolean b);

	/** Generate invariants */
	public boolean isGenerateInvariant();
	public void setGenerateInvariant(boolean b);

	/** Generate factory */
	public boolean isGenerateFactory();
	public void setGenerateFactory(boolean b);

	/** Generate ID */
	public boolean isGenerateID();
	public void setGenerateID(boolean b);

	/** Generate repository */
	public boolean isGenerateRepository();
	public void setGenerateRepository(boolean b);

	/** Generate browser */
	public boolean isGenerateBrowser();
	public void setGenerateBrowser(boolean b);

	/** Generate startup */
	public boolean isGenerateStartup();
	public void setGenerateStartup(boolean b);

	/** Generate bidirectinal links */
	public boolean isGenerateBidirectional();
	public void setGenerateBidirectional(boolean b);

	/** Interface prefix */
	public String getInterfacePrefix();
	public void setInterfacePrefix(String s);

	/** Interface suffix */
	public String getInterfaceSuffix();
	public void setInterfaceSuffix(String s);

	/** Class prefix */
	public String getClassPrefix();
	public void setClassPrefix(String s);
	
	/** Class suffix */
	public String getClassSuffix();
	public void setClassSuffix(String s);

	/** Collection interface */
	public String getCollectionInterface();
	public void setCollectionInterface(String s);

	/** Collection class */
	public String getCollectionClass();
	public void setCollectionClass(String s);
		
	/** List interface */
	public String getListInterface();
	public void setListInterface(String s);

	/** List class */
	public String getListClass();
	public void setListClass(String s);

	/** Set interface */
	public String getSetInterface();
	public void setSetInterface(String s);

	/** List class */
	public String getSetClass();
	public void setSetClass(String s);
	
	/** Metrics all metrics*/
	public MetricSet getMetricSet();
	public void setMetricSet(MetricSet set);
	public List getMetrics();
	public void addMetric(Metric m);
	public void removeMetric(Metric m);
	
	/** Model */
	public Model getModel(ILog log);
	public UmlRepository getRepository(ILog log);
	public DefaultMutableTreeNode toJTree(ILog log);
	public Context getContext(ILog log);
	public QualityModel getQualityModel(ILog log);
	
	/** Load the project setting */
	public void loadSettings(String projectFileName, ILog console);
	public void loadSettings(String projectFileName);
	public void loadSettings();

	/** Save a project into a file */
	public void saveSettings();
	
	/** toString */
	public String toString();
}
