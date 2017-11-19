package uk.ac.kent.cs.kmf.kmfstudio;

import java.util.*;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.*;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Extension_Mechanisms.*;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.*;

import uk.ac.kent.cs.kmf.util.FileLog;
import uk.ac.kent.cs.kmf.util.ILog;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.bridge4kmf.*;

public class Context {
	/** Constructor */
    public Context(Project project, Naming naming, ILog log) {
    	this.project = project;
    	this.model = project.getModel(log);
    	this.naming = naming;
		this.log = log;
    	setIndent("\t");
	    // Compute packages, classes, associations, stereotypes, and enumerators from model 
    	compute(model);
        // Mark <<external>> packages and classes
        markExternalElements(model);
        // Init the invariants
        invariants = new HashMap();
        // Set OclProcessor
        oclProcessor = new KmfOclProcessorImpl(model, new FileLog("oclProcessor.log"));
    }

	/** Compute packages, classes, associations, stereotypes, and enumerations from model */
    protected void compute(Namespace pkg) {
	    // For each element within a package
	    Iterator i = pkg.getOwnedElement().iterator();
	    packages.add(pkg);
	    while (i.hasNext()) {
	        Object o = i.next();
	        if (o instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_) {
	        	// Add the class to container
	        	classes.add(o);
	        }
			else if (o instanceof Enumeration_) {
				classes.add(o);
			}
	        else if (o instanceof Association) {
	        	associations.add(o);
    		}
	        else if (o instanceof Stereotype) stereotypes.add(o);
	        else if (o instanceof Package) {
	        	// Link to father for XMI 1.2
	        	((Package)o).setNamespace_(pkg); 
				// Process the nested package	        	
	        	compute((Package)o);
	        }
	    }
	}

	/** Mark <<external>> packages and classes */
    protected void markExternalElements(Package pkg) {
    	// Create <<external>> stereotype
    	Name name = new Name$Class();
    	name.setBody_("external");
    	Stereotype external = new Stereotype$Class();
    	external.setName(name);
	    // For each element within a package
	    Iterator i = pkg.getOwnedElement().iterator();
	    packages.add(pkg);
	    while (i.hasNext()) {
	        Object o = i.next();
	        if (o instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_) {
	        	// Add <<external>> stereotype to a class
	        	if (naming.isStereotype(pkg, "external")) 
	        		((ModelElement)o).getStereotype().add(external);
	        }
	        else if (o instanceof Association) associations.add(o);
	        else if (o instanceof Stereotype) stereotypes.add(o);
	        else if (o instanceof Package) {
	        	// Add <<external>> stereotype to a package
	        	String pkgName = pkg.getName().getBody_();
	        	boolean is = naming.isStereotype(pkg, "external");
	        	if (naming.isStereotype(pkg, "external")) 
	        		((ModelElement)o).getStereotype().add(external);
	        	String oName = ((Package)o).getName().getBody_();
	        	boolean isO = naming.isStereotype((ModelElement)o, "external");
				// Process the nested package	        	
	        	markExternalElements((Package)o);
	        }
	    }
	}
	
	/** XMI file name */
	public String getXMIFileName() {
		return project.getXmiFileName();
	}

	/** License file name */
	public String getLicenseFileName() {
		return project.getLicenseFileName();
	}
	
	/** MODEL name */
	public String getModelName() {
		return project.getModelName();
	}

	/** Package offset */
	public String getPackageOffset() {
		return project.getPackageOffset();
	}
	 	
	/** PACKAGES contained into the model */
	protected final Set packages = new LinkedHashSet();
	public Set getPackages() { return packages; }

	/** CLASSES contained into the model */
	protected final Set classes = new LinkedHashSet();
	public Set getClasses() { return classes; };

	/** ASSOCIATIONS contained into the model */
	protected final Set associations = new LinkedHashSet();
	public Set getAssociations() { return associations; };

	/** STEREOTYPES into the model */
	protected final Set stereotypes = new LinkedHashSet();
	public Set getStereotypes() { return stereotypes; };

	/** Generate visitor */
	public boolean isGenerateVisitor() { return project.isGenerateVisitor(); }

	/** Generate visitor */
	public boolean isGenerateHUTNVisitor() { return project.isGenerateHUTNVisitor(); }

	/** Generate visitor */
	public boolean isGenerateJTreeVisitor() { return project.isGenerateJTreeVisitor(); }

	/** Generate visitor */
	public boolean isGenerateViewEditVisitor() { return project.isGenerateViewEditVisitor(); }

	/** Generate visitor */
	public boolean isGenerateXMIVisitor() { return project.isGenerateXMIVisitor(); }
	
 	/** Generate observer */
	public boolean isGenerateObserver() { return project.isGenerateObserver(); }

	/** Generate invariant */
	public boolean isGenerateInvariant() { return project.isGenerateInvariant(); }

	/** Generate factory */
	public boolean isGenerateFactory() { return project.isGenerateFactory(); }

	/** Generate factory */
	public boolean isGenerateID() { return project.isGenerateID(); }

	/** Generate repository */
	public boolean isGenerateRepository() { return project.isGenerateRepository(); }

	/** Generate browser */
	public boolean isGenerateBrowser() { return project.isGenerateBrowser(); }

	/** Generate startup */
	public boolean isGenerateStartup() { return project.isGenerateStartup(); }

	/** Generate bidirectional links */
	public boolean isGenerateBidirectional() { return project.isGenerateBidirectional(); }

	/** Indent */
	protected String indent;
	public String getIndent() { return indent; }
	public void setIndent(String str) { indent = str; }

	/** Invariants */
	protected Map invariants;
	public Map getInvariants() { return invariants; }
	public void setInvariants(Map inv) { invariants = inv; }
		
	/** OclProcessor */
	protected OclProcessor oclProcessor;
	public OclProcessor getOclProcessor() { return oclProcessor; }
	public void setOclProcessor(OclProcessor p) { oclProcessor = p; }

	/** Naming */
	protected Naming naming;
	public Naming getNaming() { return naming; }
	public void setNaming(Naming n) { naming = n; }	

	/** Log */
	protected ILog log;
	public ILog getLog() { return log; }
	public void setLog(ILog l) { log = l; }	

	//
	// Local properties
	//
	protected Model model;
	protected Model metaModel;
	protected Project project;
}
