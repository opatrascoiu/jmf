package uk.ac.kent.cs.kmf.kmfstudio.generators;

import java.io.*;
import java.util.*;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.*;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.*;

import uk.ac.kent.cs.kmf.kmfstudio.*;
import uk.ac.kent.cs.kmf.templates.java.InvariantsEvaluateAllImplTemplate;
import uk.ac.kent.cs.kmf.templates.java.InvariantsEvaluateAllInterfTemplate;
import uk.ac.kent.cs.kmf.templates.java.InvariantsParseAllImplTemplate;
import uk.ac.kent.cs.kmf.templates.java.InvariantsParseAllInterfTemplate;
import uk.ac.kent.cs.kmf.util.*;

public class InvariantsGenerator 
    implements Runnable
{
	/**
	  * Class members
	  */
    protected Model model;
    protected String modelName;
    protected String modelPackage;
    protected Context context;
    protected String outDirName;
    protected String interfaceName;
    protected String implName;
    protected String className;
    protected File interfDir;
    protected File implDir;
    protected ILog log;
    protected String indent;
    protected Map invariants;
    
	/**
	  * Initialize the members
	  */
    public InvariantsGenerator(Model model, Context context, String outDirName, ILog log) {
    	this.model = model;
    	this.context = context;
    	this.outDirName = outDirName;
    	this.modelName = context.getNaming().getModelName();
    	this.modelPackage = context.getNaming().getModelPackage();
	    // Set the log
	    this.log = log;
	    this.indent = context.getIndent();
	    // Compute invariants
	    computeInvariants();
        invariants = context.getInvariants();
    }
    
    /**
     * 
     *  Compute invariants
     */
	public boolean add(AssociationEnd aend1, AssociationEnd aend2, Classifier cls) {
		if (aend1.getType() == cls && context.getNaming().isNavigable(aend2))
			return true;
		else
			return false;
	}
    
    void computeInvariants() {
    	Set classes = context.getClasses();
    	Iterator it = classes.iterator();
    	while (it.hasNext()) {
    		uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier cls = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier)it.next();
	        // Compute all superclasess and this
	        List superClasses = context.getNaming().allSuperClasses(cls, false);
	        Set addedInvariant = new LinkedHashSet();
	    	// Compute the invariants for the class
			List clsInvariants = new Vector();
           	String selfStr = context.getNaming().removeOffset(context.getPackageOffset(), context.getNaming().getFullInterfaceName(cls));
	        int argNo = 1;
	        for(int i=superClasses.size()-1; i>=0; i--) {
	            uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier superCls = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier)superClasses.get(i);
	            String superClsName = context.getNaming().getClassifierName(superCls);    	
	           	selfStr = selfStr.replaceAll("[.]", "::");
			    // Add implicit invariants
	            Iterator k = context.getAssociations().iterator();
	            while (k.hasNext()) {
	                Association assoc = (Association)k.next();
	                AssociationEnd aend1 = (AssociationEnd)assoc.getConnection().get(0);
	                AssociationEnd aend2 = (AssociationEnd)assoc.getConnection().get(1);
					if (add(aend1, aend2, superCls)) {
	                    String assocName = context.getNaming().getPropertyName(aend2);
	                    if (!addedInvariant.contains(assocName)) {
	             		    // Compute and add 'size' invariant
	                        if (context.getNaming().isCollection(aend2)) {
	                            MultiplicityRange range = (MultiplicityRange)aend2.getMultiplicity().getRange().iterator().next();
	                            int lower = range.getLower().intValue();
	                            int upper = range.getUpper().intValue();
	            		    	String invName = assocName+"_size";
	            		        String context = "context "+selfStr+" inv "+invName+": ";
	            		        String expr = context;
	            		        expr += lower+"<="+assocName+"->size()";
	            		        if (upper != -1) expr += " and "+assocName+"->size()<="+upper;
	            		    	// Add the code for invariants
	            		    	clsInvariants.add(expr);
	                        }
	            		    // Compute and add 'bidirectional' invariant
	            		    if (aend1.getIsNavigable().booleanValue()) {
	            		    	String invName = assocName+"_bidirectional";
	            		        String strContext = "context "+selfStr+" inv "+invName+": ";
	            		    	String expr = strContext;
	            		    	if (!context.getNaming().isCollection(aend1) && !context.getNaming().isCollection(aend2)) 
	            		    	    expr += context.getNaming().getPropertyName(aend2)+"."+context.getNaming().getPropertyName(aend1)+"=self";
	            		    	else if (!context.getNaming().isCollection(aend1) && context.getNaming().isCollection(aend2)) 
	            		    	    expr += context.getNaming().getPropertyName(aend2)+"->forAll(x | x."+context.getNaming().getPropertyName(aend1)+"=self)";
	            		    	else if (context.getNaming().isCollection(aend1) && !context.getNaming().isCollection(aend2)) 
	            		    	    expr += "Set{"+context.getNaming().getPropertyName(aend2)+"}->forAll(x | x."+context.getNaming().getPropertyName(aend1)+"->includes(self))";
	            		    	else if (context.getNaming().isCollection(aend1) && context.getNaming().isCollection(aend2)) 
	            		    	    expr += context.getNaming().getPropertyName(aend2)+"->forAll(x | x."+context.getNaming().getPropertyName(aend1)+"->includes(self))";
	            		    	// Add the code for invariants
	            		    	clsInvariants.add(expr);
	            		    }
	                        addedInvariant.add(assocName);
	                    }
	                }
					if (add(aend2, aend1, superCls)) {
	                    String assocName = context.getNaming().getPropertyName(aend1);
	                    if (!addedInvariant.contains(assocName)) {
	            		    // Compute and add 'size' invariant
	                        if (context.getNaming().isCollection(aend1)) {
	                            MultiplicityRange range = (MultiplicityRange)aend1.getMultiplicity().getRange().iterator().next();
	                            int lower = range.getLower().intValue();
	                            int upper = range.getUpper().intValue();
	            		    	String invName = assocName+"_size";
	            		        String context = "context "+selfStr+" inv "+invName+": ";
	            		        String expr = context;
	            		        expr += lower+"<="+assocName+"->size()";
	            		        if (upper != -1) expr += " and "+assocName+"->size()<="+upper;
	            		    	// Add the code for invariants
	            		    	clsInvariants.add(expr);
	                        }
	            		    // Compute and add 'bidirectional' invariant
	            		    if (aend2.getIsNavigable().booleanValue()) {
	            		    	String invName = assocName+"_bidirectional";
	            		        String strContext = "context "+selfStr+" inv "+invName+": ";
	            		    	String expr = strContext;
	            		    	if (!context.getNaming().isCollection(aend2) && !context.getNaming().isCollection(aend1)) 
	            		    	    expr += context.getNaming().getPropertyName(aend1)+"."+context.getNaming().getPropertyName(aend2)+" = self";
	            		    	else if (!context.getNaming().isCollection(aend2) && context.getNaming().isCollection(aend1)) 
	            		    	    expr += context.getNaming().getPropertyName(aend1)+"->forAll(x | x."+context.getNaming().getPropertyName(aend2)+"=self)";
	            		    	else if (context.getNaming().isCollection(aend2) && !context.getNaming().isCollection(aend1)) 
	            		    	    expr += "Set{"+context.getNaming().getPropertyName(aend1)+"}->forAll(x | x."+context.getNaming().getPropertyName(aend2)+"->includes(self))";
	            		    	else if (context.getNaming().isCollection(aend2) && context.getNaming().isCollection(aend1)) 
	            		    	    expr += context.getNaming().getPropertyName(aend1)+"->forAll(x | x."+context.getNaming().getPropertyName(aend2)+"->includes(self))";
	            		    	// Add the code for invariants
	            		    	clsInvariants.add(expr);
	            		    }
	                        addedInvariant.add(assocName);
	                    }
	               }
	            }             
	            // Explicit invariants
	            Iterator itco = superCls.getConstraint().iterator();
	            while (itco.hasNext()) {
	                uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Constraint constraint = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Constraint)itco.next();
	                String invName = context.getNaming().getConstraintName(constraint);
	                if (!addedInvariant.contains(invName)) {
	                    String expr = constraint.getBody().getBody().replace('\n', ' ');
	    		    	// Add the code for invariants
	       		    	clsInvariants.add(expr);
	                    addedInvariant.add(invName);
	                }
	            }
			    // <<inv>> invariants
		        k = superCls.getFeature().iterator();
		        while (k.hasNext()) {
		            Feature f = (Feature)k.next();
		            if (f instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Operation) {
		                uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Operation op = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Operation)f;
						if (context.getNaming().isStereotype(op, "inv")) {
		                	String invName = "inv_"+context.getNaming().getOperationName(op);
			                if (!addedInvariant.contains(invName)) {
			                    String expr = op.getSpecification().replace('\n', ' ');
	            		    	// Add the code for invariants
	            		    	clsInvariants.add(expr);
			                    addedInvariant.add(invName);
			                }
						}
		            }
		        }
	    		// Set invariants for the class
	    		context.getInvariants().put(cls, clsInvariants);
	        }
    	}
    	// Debug
//    	Iterator i1 = context.getInvariants().keySet().iterator();
//    	while (i1.hasNext()) {
//    		uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier key = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier)i1.next();
//    		Iterator i2 = ((List)context.getInvariants().get(key)).iterator();
//    		System.out.println("Class: "+context.getNaming().getClassifierName(key));
//    		while (i2.hasNext()) {
//    			String inv = (String)i2.next();
//    			System.out.println("\t"+inv);
//    		}
//    	}
    }
    
	/**
	  * Generate the Java code
	  */
    public void run() {
	    // Create the ouput directories
	    interfDir = new File(outDirName+"/"+modelName);
	    interfDir.mkdirs();
	    implDir = new File(outDirName+"/"+modelName);
	    implDir.mkdirs();
	    // Create the parseAll visitor interface
	    createParseAllVisitorInterface(interfDir);
	    // Create the parseAll visitor implementation
	    createParseAllVisitorImplementation(implDir);
	    // Create the evaluateAll visitor interface
	    createEvaluateAllVisitorInterface(interfDir);
	    // Create the evaluateAll visitor implementation
	    createEvaluateAllVisitorImplementation(implDir);
	}  

	/**
	  * Create ParseAll visitor interface
	  */
	protected void createParseAllVisitorInterface(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitor=null;
	    String fileName = context.getNaming().getParseAllVisitorInterface(modelName)+".java";
	    try {
	        visitor = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
		(new InvariantsParseAllInterfTemplate(model, context, invariants, visitor)).generate();
		visitor.close();
	}

	/**
	  * Create ParseAll visitor implementation
	  */
	protected void createParseAllVisitorImplementation(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitor=null;
	    String fileName = context.getNaming().getParseAllVisitorClass(modelName)+".java";
	    try {
	        visitor = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
	    (new InvariantsParseAllImplTemplate(model, context, invariants, visitor)).generate();
	    visitor.close();
	}

	/**
	  * Create EvaluateAll visitor interface
	  */
	protected void createEvaluateAllVisitorInterface(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitor=null;
	    String fileName = context.getNaming().getEvaluateAllVisitorInterface(modelName)+".java";
	    try {
	        visitor = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
		(new InvariantsEvaluateAllInterfTemplate(model, context, invariants, visitor)).generate();
		visitor.close();
	}

	/**
	  * Create EvaluateAll visitor implementation
	  */
	protected void createEvaluateAllVisitorImplementation(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitor=null;
	    String fileName = context.getNaming().getEvaluateAllVisitorClass(modelName)+".java";
	    try {
	        visitor = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
		(new InvariantsEvaluateAllImplTemplate(model, context, invariants, visitor)).generate();
		visitor.close();
	}

}
