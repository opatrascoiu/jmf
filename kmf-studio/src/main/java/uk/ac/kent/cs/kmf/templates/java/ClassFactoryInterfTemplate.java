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
public class ClassFactoryInterfTemplate {
	public ClassFactoryInterfTemplate(Classifier cls, Context context, PrintWriter out) {
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

	}

	/**
	  * Print a factory interface
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(factoryInterfaceName, out);
		// Print the code
		// Print header
		out.println("package "+fullPkgName+";");
		out.println();
		// Compute extended interfaces
		List interfaces = new Vector();
		interfaces.add(context.getNaming().getFullFactoryInterface(modelName));
		// Compute interface header
		String header = "public interface "+factoryInterfaceName+"\n";
		if (interfaces.size() != 0) {
			Iterator j = interfaces.iterator();
			header += "extends\n    "+j.next();
			while (j.hasNext()) header += ",\n    "+j.next();
		}
		header += "\n{";
		// Add header
		out.println(header);
		// Add build functions
		// Stereotype enumerations
		if (context.getNaming().isStereotype(cls, "enumeration")) {
			String fullClassName = context.getNaming().getFullClassifierName(cls);
			out.println(indent+"/** Build object */");
			out.println(indent+"public Object build();");
			out.println("}");
			return;
		}     	
		// Normal type
		// Compute superclasses
		List superClasses = context.getNaming().allSuperClasses(cls, false);
		Set addedAttrib = new LinkedHashSet();
		// Compute parameters
		List params = new Vector(); 
		for(int i=superClasses.size()-1; i>=0; i--) {
			// For each attribute
			Classifier superCls = (Classifier)superClasses.get(i);
			Iterator itf = superCls.getFeature().iterator();
			while (itf.hasNext()) {
				Feature f = (Feature)itf.next();
				if (f instanceof Attribute) {
					Attribute attrib = (Attribute)f;
					String attribName = context.getNaming().getPropertyName(attrib);
					if (!addedAttrib.contains(attribName)) {
						// Compute name and type
						String fieldName = context.getNaming().getPropertyName(attrib);
						String type = context.getNaming().getPropertyType(attrib, attrib.getType(), cls);
						// Add them to args, inits, and initsDef
						params.add(type+" "+fieldName);
						// Add it
						addedAttrib.add(attribName);
					}
				}
			}
		}
		// Default constructor
		out.println(indent+"/** Default builder */");
		out.println(indent+"public Object build();");
		// Full constructor
		if (params.size() != 0) {
			out.println(indent+"/** Specialized builder */");
			out.println(indent+"public Object build("+context.getNaming().toList(params, ", ")+");");
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
}
