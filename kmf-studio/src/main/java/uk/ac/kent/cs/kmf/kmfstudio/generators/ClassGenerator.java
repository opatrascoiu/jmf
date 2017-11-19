package uk.ac.kent.cs.kmf.kmfstudio.generators;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import uk.ac.kent.cs.kmf.kmfstudio.*;
import uk.ac.kent.cs.kmf.templates.java.ClassInterfTemplate;
import uk.ac.kent.cs.kmf.templates.java.ClassImplTemplate;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.kmf.util.Naming;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Attribute;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Method;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Operation;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Expression;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ProcedureExpression;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package;

public class ClassGenerator implements Runnable {
	/**
	  * Class members
	  */
    protected uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package pkg;
    protected String modelName;
    protected String modelPackage;
    protected String pkgName;
    protected String fullPkgName;
    protected Context context;
	protected Classifier cls;
	protected String className;
	protected String interfaceName;
	protected File interfDir;
	protected File implDir;
	protected static ILog log;
	protected static String indent;
	
	/**
	  * Initialize the members
	  */
    public ClassGenerator(Package pkg, Context context, Classifier cls, File interfDir, File implDir, ILog log) {
    	this.pkg = pkg;
    	this.modelName = context.getNaming().getModelName();
    	this.modelPackage = context.getNaming().getModelPackage();
	    pkgName = context.getNaming().getPackageName(pkg);
        fullPkgName = context.getNaming().getFullPackageName(pkg);
    	this.context = context;
    	this.cls = cls;
	    className = context.getNaming().getClassName(cls);
	    interfaceName = context.getNaming().getInterfaceName(cls);
    	this.interfDir = interfDir;
    	this.implDir = implDir;
    	ClassGenerator.log = log;
		ClassGenerator.indent = context.getIndent();
    }

	/**
	  * Generate the Java code
	  */
    public void run() {
    	// Create the interface
	    createClassInterface(interfDir);
    	// Create the class
    	boolean gen = cls.getIsAbstract().booleanValue();
    	if (!gen) createClassImplementation(implDir);
    }

	/**
	  * Create Interface
	  */
	protected void createClassInterface(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter interf=null;
	    String fileName = interfaceName+".java";
	    try {
	        interf = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot find file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
	    (new ClassInterfTemplate(cls, context, interf)).generate();
	    interf.close();
	}

	/**
	  * Create Implementation
	  */
	protected void createClassImplementation(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter classf=null;
	    String fileName = className+".java";
	    try {
	        classf = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot find file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
		(new ClassImplTemplate(cls, context, classf)).generate();
		classf.close();
	}

         
	/**
	  * Print Operations
	  */
	/** Get declaration of an operation */
	public static String getOperationDeclaration(Context context, Operation op, Classifier src, Classifier dst) {
		String result = new String();
		// Do not generate "static" operations in an interface
	    if (context.getNaming().isStereotype(op, "static")) ;
	    else if (context.getNaming().isStereotype(op, "create")) ;
	    else if (context.getNaming().isStereotype(op, "destroy")) ;
	    else if (context.getNaming().isStereotype(op, "reconcile")) ;
        else if (context.getNaming().isStereotype(op, "spec")) {
        	result += getOperationSignature(context, op, src, dst, true) + ";";
        } else if (op.getIsQuery() != null && op.getIsQuery().booleanValue()) {
        	result += getOperationSignature(context, op, src, dst, true) + ";";
        } else {
        	result += getOperationSignature(context, op, src, dst, true) + ";";
        }
        return result;
	}
	/** Get an operation definition */
	public static String getOperationDefinition(Context context, Operation op, Classifier src, Classifier dst) {
		String result = new String();
	    if (context.getNaming().isStereotype(op, "create")) ;
	    else if (context.getNaming().isStereotype(op, "destroy")) ;
	    else if (context.getNaming().isStereotype(op, "reconcile")) ;
        else if (context.getNaming().isStereotype(op, "spec")) {
            result += getOperationSignature(context, op, src, dst, false);
            result += getOperationBody(context, op, dst);
        } else if (op.getIsQuery() != null && op.getIsQuery().booleanValue()) {
            result += getOperationSignature(context, op, src, dst, false);
            result += getOperationBody(context, op, dst);
        } else {
            result += getOperationSignature(context, op, src, dst, false);
            result += getOperationBody(context, op, dst);
        }
        return result;
	}
	/** Get the signature of an operation */
	public static String getOperationSignature(Context context, Operation op, Classifier src, Classifier dst, boolean inInterface) {
		// Get the names
		String name = context.getNaming().getOperationName(op);
		String returnType = context.getNaming().getReturnTypeName(op, dst);
		List paramTypes = context.getNaming().getParamTypeNames(op, dst);
		List paramNames = context.getNaming().getParamNames(op);
		// Print the code
		String result = new String();
	    result += indent + "public ";
	    if (!inInterface && context.getNaming().isStereotype(op, "static")) result += "static ";
	    if (returnType.equals("")) result += "void";
	    else result += returnType;
	    if (context.getNaming().isStereotype(op, "create")) result += " build" + name + "(";
	    else result += " "+name+"(";
	    for(int i=0;i<paramTypes.size();i++) {
	        if(i!=0) result += ", ";
	        result += paramTypes.get(i)+" "+paramNames.get(i);
	    }
	    result += ")";
	    return result;
	}
	/** Get the body of an OCL expression */
	public static String getOclExpression(Context context, Operation op) {
		//--- Query ---
        if (op.getIsQuery() != null && op.getIsQuery().booleanValue()) {
        	Iterator i = op.getMethod().iterator();
        	if (i.hasNext()) {
		    	Method m = (Method)i.next();
		    	if (m != null) {
		        	Expression exp = m.getBody();
		        	if (exp instanceof ProcedureExpression) {
		            	ProcedureExpression proc = (ProcedureExpression)exp;
		            	return proc.getBody();
		        	}
		    	}
		    }
		//--- <<spec>>
        } else if (context.getNaming().isStereotype(op, "spec")) {
        	return op.getSpecification();
        }
	    log.reportError("No method body expression for operation "+op.getName().getBody_());
	    return "?no method body expression?";
	}
	/** Get the body of an operation */
	public static String getOperationBody(Context context, Operation op, Classifier dst) {
		// Get the prototype
		String opSign = new String();
		String name = context.getNaming().getOperationName(op);
		String returnType = context.getNaming().getReturnTypeName(op, dst);
		List paramTypes = context.getNaming().getParamTypeNames(op, dst);
		List paramNames = context.getNaming().getParamNames(op);
		// Compute the list of parameters
	    if (returnType.equals("")) opSign += "void";
	    else opSign += returnType;
	    opSign += " "+name+"(";
	    for(int i=0;i<paramTypes.size();i++) {
	        if(i!=0) opSign += ", ";
	        opSign += paramTypes.get(i)+" "+paramNames.get(i);
	    }
	    opSign += ")";
	    // Compute then result
		String result = new String();
	    result += indent+"{"+"\n";
	    // Query
	    if (op.getIsQuery() != null && op.getIsQuery().booleanValue()) {
	    	// Compute the list of arguments and values
	        String exp = getOclExpression(context, op).replace('\n',' ').trim();
	        List args = context.getNaming().getParamNames(op);
	        String argNames = new String();
	        String argValues = new String();
	        for(int i=0; i<args.size(); i++) {
	    	    if (i!=0) {
	    		    argNames += ",";
	    		    argValues += ",";
	    	    }
	    	    argNames += "\""+args.get(i)+"\"";
	    	    argValues += args.get(i);
	        }
	        if (!argNames.equals("")) {
	    	    argNames = ", "+argNames;
	    	    argValues = ", "+argValues;
	        }
//	        result += indent + "IOclExpression exp = OCL.Expression(\"" + exp + "\", new String[] {\"self\""+argNames+"}, new Object[] {this"+argValues+"} );" + "\n";
//	        result += indent + "return (" + CodeGenerator.getReturnTypeName(op, dst) + ")exp.evaluate();" + "\n";
	        result += indent + indent + "return null;" + "\n";
	    // <<spec>>
	    } else if (context.getNaming().isStereotype(op, "spec")) {
	    	// Compute the list of arguments and values
	        String exp = getOclExpression(context, op).replace('\n',' ').trim();
	        List args = context.getNaming().getParamNames(op);
	        String argNames = new String();
	        String argValues = new String();
	        for(int i=0; i<args.size(); i++) {
	    	    if (i!=0) {
	    		    argNames += ",";
	    		    argValues += ",";
	    	    }
	    	    argNames += "\""+args.get(i)+"\"";
	    	    argValues += args.get(i);
	        }
	        if (!argNames.equals("")) {
	    	    argNames = ", "+argNames;
	    	    argValues = ", "+argValues;
	        }
	        result += indent + "IOclExpression exp = OCL.Expression(\"" + exp + "\", new String[] {\"self\""+argNames+"}, new Object[] {this"+argValues+"} );" + "\n";
	        result += indent + "return (" + context.getNaming().getReturnTypeName(op, dst) + ")exp.evaluate();" + "\n";
	        result += indent + "return null;" + "\n";
	    // Other operation
	    } else {
	    	if (returnType.equals("") || returnType.equals("void"));
	    	else result += indent+indent+"return null;" + "\n";
	    }
	    result += indent + "}";
	    return result;
	}

    /**
      *  Attribute Functions
      */
    /** Get the declaration of an attribute */
	public static String getPropertyDecl(Context context, Attribute attrib, Classifier src, Classifier dst) {
		String name = context.getNaming().getPropertyName(attrib);
		String type = context.getNaming().getPropertyType(attrib, src, dst);
		String defaultValue = context.getNaming().getPropertyDefaultValue(attrib);
		String result = indent + "protected "+ type + " " + name;
//		result += " = " + defaultValue;
		result += ";";		
		return result;
	}
    /** Get the accessor of an attribute */
	public static String getPropertyAccessor(Context context, Attribute attrib, Classifier src, Classifier dst) {
		String result = indent + getPropertyAccessorSignature(context, attrib, src, dst) + " " + getPropertyAccessorBody(context, attrib, src, dst);
		return result;
	}
    /** Get the accessor signature of an attribute */
	public static String getPropertyAccessorSignature(Context context, Attribute attrib, Classifier src, Classifier dst) {
		String type = context.getNaming().getPropertyType(attrib, src, dst);
		String name = context.getNaming().getPropertyName(attrib);
		String result = indent + "public " + type + " " + Naming.getGetter(name) + "()";
		return result;
	}
    /** Get the accessor body of an attribute */
	public static String getPropertyAccessorBody(Context context, Attribute attrib, Classifier src, Classifier dst) {
		String name = context.getNaming().getPropertyName(attrib);
		String result = new String();
		result += "{\n";
		result += indent+indent + "return " + name + ";\n";
		result += indent+"}";
		return result;
	}
    /** Get the mutator of an attribute */
	public static String getPropertyMutator(Context context, Attribute attrib, Classifier src, Classifier dst) {
		String result = indent + getPropertyMutatorSignature(context, attrib, src, dst) + " " + getPropertyMutatorBody(context, attrib, src, dst);
		return result;
	}
    /** Get the mutator signature of an attribute */
	public static String getPropertyMutatorSignature(Context context, Attribute attrib, Classifier src, Classifier dst) {
		String type = context.getNaming().getPropertyType(attrib, src, dst);
		String name = context.getNaming().getPropertyName(attrib);
		String result = indent + "public void " + Naming.getSetter(name) + "(" + type + " " + name + ")";
		return result;
//        out.println(indent+indent+"Object old = "+fname+";");
//        out.println(indent+indent+"fireChange(this,\""+name+"\",old,"+name+");");
	}
    /** Get the mutator body of an attribute */
	public static String getPropertyMutatorBody(Context context, Attribute attrib, Classifier src, Classifier dst) {
		String name = context.getNaming().getPropertyName(attrib);
		String result = new String();
		result += "{\n";
		result += indent+indent + "this." + name + " = " + name + ";\n";
		result += indent+"}";
//        out.println(indent+indent+"Object old = "+fname+";");
//        out.println(indent+indent+"fireChange(this,\""+name+"\",old,"+name+");");
		return result;
	}

    /**
      *  AssociationEnds Functions
      */
    /** Get the declaration of an association end */
	public static String getPropertyDecl(Context context, AssociationEnd aend, Classifier src, Classifier dst) {
		String type = context.getNaming().getPropertyType(aend, src, dst);
		String name = context.getNaming().getPropertyName(aend);
		String defaultValue = context.getNaming().getPropertyDefaultValue(aend);
		String result = indent + "protected "+ type + " " + name;
//		result += " = " + defaultValue;
		result += ";";		
		return result;
	}
    /** Get the accessor of an association end */
	public static String getPropertyAccessor(Context context, AssociationEnd aend, Classifier src, Classifier dst) {
		String result = getPropertyAccessorSignature(context, aend, src, dst) + " " + getPropertyAccessorBody(context, aend, src, dst);
		return result;
	}
	/** Get the accesor signature of an association end */
	public static String getPropertyAccessorSignature(Context context, AssociationEnd aend, Classifier src, Classifier dst) {
		String type = context.getNaming().getPropertyType(aend, src, dst);
		String name = context.getNaming().getPropertyName(aend);
		String result = indent + "public " + type + " " + Naming.getGetter(name) + "()";
		return result;
	}
    /** Get the accessor boby of an association end */
	public static String getPropertyAccessorBody(Context context, AssociationEnd aend, Classifier src, Classifier dst) {
		String name = context.getNaming().getPropertyName(aend);
		String result = new String();
		result += "{\n";
		result += indent+indent + "return " + name + ";\n";
		result += indent+"}";
		return result;
	}
	/** Get the mutator of an association end */
	public static String getPropertyMutator(Context context, AssociationEnd aend, Classifier src, Classifier dst) {
		String result = getPropertyMutatorSignature(context, aend, src, dst) + " " + getPropertyMutatorBody(context, aend, src, dst);
		return result;
	}
	/** Get the mutator signature of an association end */
	public static String getPropertyMutatorSignature(Context context, AssociationEnd aend, Classifier src, Classifier dst) {
		String type = context.getNaming().getPropertyType(aend, src, dst);
		String name = context.getNaming().getPropertyName(aend);
		String result = indent + "public void " + Naming.getSetter(name) + "(" + type + " " + name + ")";
		return result;
	}
    /** Get the mutator body of an association end */
	public static String getPropertyMutatorBody(Context context, AssociationEnd aend, Classifier src, Classifier dst) {
		String name = context.getNaming().getPropertyName(aend);
		String result = new String();
		result += "{ \n";
		result += indent+indent + "this." + name + " = " + name + ";\n";
		result += indent+"}";
//        out.println(indent+indent+"Object old = "+fname+";");
//        out.println(indent+indent+"fireChange(this,\""+name+"\",old,"+name+");");
		return result;
	}
}
