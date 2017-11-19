package uk.ac.kent.cs.kmf.templates.java;

import java.io.PrintWriter;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 */
public class AdapterFactoryImplTemplate {
	public AdapterFactoryImplTemplate(Model model, Context context, PrintWriter out) {
		this.model = model;
		this.context = context;
		this.out = out;
		this.log = context.getLog();
		initLocalProperties();
	}
	
	protected void initLocalProperties() {
		this.indent = context.getIndent();
		this.log = context.getLog();
		this.modelName = context.getNaming().getModelName();
		this.modelPackage = context.getNaming().getModelPackage();
		this.adapterFactoryClass = context.getNaming().getAdapterFactoryClass(modelName);
		this.readerAdapterClass = context.getNaming().getReaderAdapterClass(modelName);
	}

	/**
	  * Print AdapterFactory implementation
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(adapterFactoryClass, out);
		// Print the code
		out.println("package "+modelPackage+".repository;");
		out.println();
		out.println("import uk.ac.kent.cs.kmf.xmi.*;");
		out.println("import uk.ac.kent.cs.kmf.util.*;");
		out.println();
		out.println("public class "+adapterFactoryClass);
		out.println(indent+"extends AdapterFactory");
		out.println(indent+"implements IAdapterFactory");
		out.println("{");
		out.println(indent+"/** The repository */");
		out.println(indent+context.getNaming().getFullRepositoryInterface(modelName)+" rep;");
		out.println(indent+"/** The log */");
		out.println(indent+"ILog log;");
		out.println();
		String repositoryInterface = context.getNaming().getFullRepositoryInterface(modelName);
		out.println(indent+"/** AdapterFactory constructor */");
		out.println(indent+"public "+adapterFactoryClass+"("+repositoryInterface+" rep, ILog log) {");
		out.println(indent+indent+"this.rep = rep;");
		out.println(indent+indent+"this.log = log;");
		out.println(indent+"}");
		out.println();
		out.println(indent+"/** Create a ReaderAdapter */");
		out.println(indent+"public ReaderAdapter createReaderAdapter() {");
		out.println(indent+indent+"return new "+readerAdapterClass+"(rep, log);");
		out.println(indent+"}");
		out.println();
		out.println(indent+"/** Create a WriterAdapter */");
		out.println(indent+"public WriterAdapter createWriterAdapter() {");
		out.println(indent+indent+"return new WriterAdapter();");
		out.println(indent+"}");
		out.println("}");
	}

	//
	// Local properties
	//
	protected Model model;
	protected Context context;
	protected PrintWriter out;
	
	protected ILog log;
	protected String indent;
	protected String modelName;
	protected String modelPackage;
	protected String adapterFactoryClass;
	protected String readerAdapterClass;
}
