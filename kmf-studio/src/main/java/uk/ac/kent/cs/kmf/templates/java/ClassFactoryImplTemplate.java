package uk.ac.kent.cs.kmf.templates.java;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Attribute;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Feature;

/**
 * @author Octavian Patrascoiu
 *
 */
public class ClassFactoryImplTemplate {
	public ClassFactoryImplTemplate(Classifier cls, Context context, PrintWriter out) {
		this.cls = cls;
		this.context = context;
		this.out = out;
		this.log = context.getLog();
		initLocalProperties();
	}
	
	protected void initLocalProperties() {
		this.indent = context.getIndent();
		this.log = context.getLog();
		this.modelName = context.getNaming().getModelName();
		this.fullPkgName = context.getNaming().getFullPackageName(cls);
		this.factoryInterfaceName = context.getNaming().getFactoryInterfaceName(cls);
		this.factoryClassName = context.getNaming().getFactoryClassName(cls);
	}

	/**
	  * Print a factory class
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(factoryClassName, out);
		// Print the code
		// Sterotype enumeration
		if (context.getNaming().isStereotype(cls, "enumeration")) {
			out.println("package "+fullPkgName+";");
			out.println();
			out.println("public class "+factoryClassName);
			out.println(indent+"extends "+context.getNaming().getFullFactoryClass(modelName));
			out.println(indent+"implements "+factoryInterfaceName);
			out.println("{");
			// Add constructor
			out.println(indent+"/** Default factory constructor */");
			out.println(indent+"public "+factoryClassName+"() {");
			out.println(indent+"}");
			if (context.isGenerateRepository()) {
				String repInterface = context.getNaming().getFullRepositoryInterface(modelName);
				out.println(indent+"/** Specialized factory constructor */");
				out.println(indent+"public "+factoryClassName+"("+repInterface+" repository) {");
				out.println(indent+indent+"this.repository = repository;");
				out.println(indent+"}");
			}
			out.println();
			// Default factory
			out.println(indent+"/** Default build method */");
			out.println(indent+"public Object build() {");
			out.println(indent+indent+""+context.getNaming().getInterfaceName(cls)+" obj = new "+context.getNaming().getClassName(cls)+"();");
			if (context.isGenerateID()) {
				out.println(indent+indent+"obj.setId("+context.getNaming().getFullFactoryClass(modelName)+".newId());");
			}
			if (context.isGenerateRepository()) {
				String key = context.getNaming().getFullClassifierName(cls);
				key = context.getNaming().removeOffset(context.getNaming().getRootOffset(), key);
				out.println(indent+indent+"repository.addElement(\""+key+"\", obj);");
			}
			out.println(indent+indent+"return obj;");
			out.println(indent+"}");
			// Add toString
			out.println();
			out.println(indent+"/** Override toString */");
			out.println(indent+"public String toString() {");
			out.println(indent+indent+"return \""+factoryInterfaceName.replace('$', '_')+"\";");
			out.println(indent+"}");
			out.println("}");
			return;
		}
		// Normal type
		out.println("package "+fullPkgName+";");
		out.println();
		out.println("public class "+factoryClassName);
		out.println(indent+"extends "+context.getNaming().getFullFactoryClass(modelName));
		out.println(indent+"implements "+factoryInterfaceName);
		out.println("{");
		// Add constructor
		out.println(indent+"/** Default factory constructor */");
		out.println(indent+"public "+factoryClassName+"() {");
		out.println(indent+"}");
		if (context.isGenerateRepository()) {
			String repInterface = context.getNaming().getFullRepositoryInterface(modelName);
			out.println(indent+"public "+factoryClassName+"("+repInterface+" repository) {");
			out.println(indent+indent+"this.repository = repository;");
			out.println(indent+"}");
		}
		out.println();
		// Compute arguments and parameters for constructors
		List params = new Vector();
		List args = new Vector();
		List signature = new Vector();
		// Compute all ancestors and this
		List superClasses = context.getNaming().allSuperClasses(cls, false);
		Set addedAttrib = new LinkedHashSet();
		for(int i=superClasses.size()-1; i>=0; i--) {
			// For each attribute
			Classifier superClass = (Classifier)superClasses.get(i);
			Iterator j = superClass.getFeature().iterator();
			while (j.hasNext()) {
				Feature f = (Feature)j.next();
				if (f instanceof Attribute) {
					Attribute attrib = (Attribute)f;
					String attribName = context.getNaming().getPropertyName(attrib);
					if (!addedAttrib.contains(attribName)) {
						// Compute name and type
						String name = context.getNaming().getPropertyName(attrib);
						String fullType = context.getNaming().getPropertyFullType(attrib, (Classifier)attrib.getType());
						// Add them to params and args
						params.add(fullType+" "+name);
						args.add(name);
						signature.add(fullType);
						// Add it
						addedAttrib.add(attribName);
					}
				}
			}
		}
		// Default factory
		out.println(indent+"/** Default build method */");
		out.println(indent+"public Object build() {");
		out.println(indent+indent+""+context.getNaming().getInterfaceName(cls)+" obj = new "+context.getNaming().getClassName(cls)+"();");
		if (context.isGenerateID()) {
			out.println(indent+indent+"obj.setId("+context.getNaming().getFullFactoryClass(modelName)+".newId());");
		}
		if (context.isGenerateRepository()) {
			String key = context.getNaming().getFullClassifierName(cls);
			key = context.getNaming().removeOffset(context.getNaming().getRootOffset(), key);
			out.println(indent+indent+"repository.addElement(\""+key+"\", obj);");
		}
		out.println(indent+indent+"return obj;");
		out.println(indent+"}");
		// Full class factory
		if (args.size() != 0) {
			out.println(indent+"/** Specialized build method */");
			out.println(indent+"public Object build("+context.getNaming().toList(params, ", ")+") {");
			out.println(indent+indent+""+context.getNaming().getInterfaceName(cls)+" obj = new "+context.getNaming().getClassName(cls)+"("+context.getNaming().toList(args, ", ")+");");
			if (context.isGenerateID()) {
				out.println(indent+indent+"obj.setId("+context.getNaming().getFullFactoryClass(modelName)+".newId());");
			}
			if (context.isGenerateRepository()) {
				String key = context.getNaming().getFullClassifierName(cls);
				key = context.getNaming().removeOffset(context.getNaming().getRootOffset(), key);
				out.println(indent+indent+"repository.addElement(\""+key+"\", obj);");
			}
			out.println(indent+indent+"return obj;");
			out.println(indent+"}");
		}
		// Add toString
		out.println();
		out.println(indent+"/** Override toString method */");
		out.println(indent+"public String toString() {");
		out.println(indent+indent+"return \""+factoryInterfaceName.replace('$', '_')+"\";");
		out.println(indent+"}");
		// Output accept
		if (context.isGenerateVisitor()) {
			out.println();
			out.println(indent+"/** Accept '"+context.getNaming().getFullVisitorInterfaceName(cls)+"' */");
			out.println(indent+"public Object accept("+context.getNaming().getFullVisitorInterface(modelName)+" v, Object data) {");
			out.println(indent+indent+"return v.visit(this, data);");
			out.println(indent+"}");
		}
		out.println("}");
	}

	//
	// Local properties
	//
	protected Classifier cls;
	protected Context context;
	protected PrintWriter out;
	
	protected ILog log;
	protected String indent;
	protected String modelName;
	protected String fullPkgName;
	protected String factoryInterfaceName;
	protected String factoryClassName;
}
