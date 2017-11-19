package uk.ac.kent.cs.kmf.templates.java;

import java.io.PrintWriter;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 */
public class ViewEditFrameImplTemplate {
	public ViewEditFrameImplTemplate(Model model, Context context, PrintWriter out) {
		this.model = model;
		this.context = context;
		this.out = out;
		initLocalProperties();
	}
	
	protected void initLocalProperties() {
		this.indent = context.getIndent();
		this.log = context.getLog();
		this.modelName = context.getNaming().getModelName();
		this.modelPackage = context.getNaming().getModelPackage();
		this.viewEditFrameClass = context.getNaming().getViewEditFrameClass(modelName);
		this.invokeMethodClass = context.getNaming().getInvokeMethodClass(modelName);
		this.viewInterface = context.getNaming().getViewVisitorInterface(modelName);
		this.viewClass = context.getNaming().getViewVisitorClass(modelName);
		this.editInterface = context.getNaming().getEditVisitorInterface(modelName);
		this.editClass = context.getNaming().getEditVisitorClass(modelName);
		this.indent = context.getIndent();
	}

	/**
	  * Print EditFrame implementation
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(viewEditFrameClass, out);
		// Print the code
		out.println("package "+modelPackage+".repository;");
		out.println();
		out.println("import java.lang.reflect.*;");
		out.println("import java.util.*;");
		out.println("import java.awt.*;");
		out.println("import javax.swing.*;");
		out.println();
		out.println("import uk.ac.kent.cs.kmf.util.*;");
		out.println();
		out.println("public class "+viewEditFrameClass);
		out.println(indent+"extends JInternalFrame");
		out.println("{");
		String repositoryInterface = context.getNaming().getFullRepositoryInterface(modelName);
		out.println(indent+"//--- Repository and host ---");
		out.println(indent+"protected "+repositoryInterface+" rep;");
		out.println(indent+"protected Object host;");
		out.println(indent+"//--- PRIMITIVE containers and names ---");
		out.println(indent+"protected java.util.List primitiveContainers = new Vector();");
		out.println(indent+"public java.util.List getPrimitiveContainers() { return primitiveContainers; }");
		out.println(indent+"protected java.util.List primitiveNames = new Vector();");
		out.println(indent+"public java.util.List getPrimitiveNames() { return primitiveNames; }");
		out.println(indent+"//--- COLLECTION containers and names ---");
		out.println(indent+"protected java.util.List collectionContainers = new Vector();");
		out.println(indent+"public java.util.List getCollectionContainers() { return collectionContainers; }");
		out.println(indent+"protected java.util.List collectionNames = new Vector();");
		out.println(indent+"public java.util.List getCollectionNames() { return collectionNames; }");
		out.println(indent+"//--- USER containers and names ---");
		out.println(indent+"protected java.util.List userContainers = new Vector();");
		out.println(indent+"public java.util.List getUserContainers() { return userContainers; }");
		out.println(indent+"protected java.util.List userNames = new Vector();");
		out.println(indent+"public java.util.List getUserNames() { return userNames; }");
		out.println(indent+"//--- POSSIBLE VALUES containers and names ---");
		out.println(indent+"protected java.util.List possibleContainers = new Vector();");
		out.println(indent+"public java.util.List getPossibleContainers() { return possibleContainers; }");
		out.println(indent+"protected java.util.List keys = new Vector();");
		out.println(indent+"public java.util.List getKeys() { return keys; }");
		out.println();
		out.println(indent+"/** ViewEdit frame constructor */");
		out.println(indent+"public "+viewEditFrameClass+"(Object host, "+repositoryInterface+" rep) {");
		out.println(indent+indent+"//--- Frame settings ---");
		out.println(indent+indent+"getContentPane().setLayout(new BorderLayout());");
		out.println(indent+indent+"setTitle(host.toString());");
		out.println(indent+indent+"setResizable(true);");
		out.println(indent+indent+"setClosable(true);");
		out.println(indent+indent+"setMaximizable(true);");
		out.println(indent+indent+"setIconifiable(true);");
		out.println(indent+indent+"//--- Repository settings ---");
		out.println(indent+indent+"this.rep = rep;");
		out.println(indent+indent+"this.host = host;");
		out.println();
		out.println(indent+"}");
		out.println();
		out.println(indent+"/** Refresh function */");
		out.println(indent+"public void refreshAction() {");
		out.println(indent+indent+"//--- For each PRIMITIVE ---");
		out.println(indent+indent+"Iterator itField = primitiveContainers.iterator();");
		out.println(indent+indent+"Iterator itName = primitiveNames.iterator();");
		out.println(indent+indent+"while (itField.hasNext()) {");
		out.println(indent+indent+indent+"//--- Get the textField ---");
		out.println(indent+indent+indent+"JTextField textField = (JTextField)itField.next();");
		out.println(indent+indent+indent+"//--- Get the name of the field ---");
		out.println(indent+indent+indent+"String name = (String)itName.next();");
		out.println(indent+indent+indent+"String getter = Naming.getGetter(name);");
		out.println(indent+indent+indent+"//--- Refresh the textField ---");
		out.println(indent+indent+indent+"String strValue = \"-1\";");
		out.println(indent+indent+indent+"try {");
		out.println(indent+indent+indent+indent+"Method method = host.getClass().getMethod(getter, new Class[] {});");
		out.println(indent+indent+indent+indent+"Object value = method.invoke(host, new Object[] {});");
		out.println(indent+indent+indent+indent+"strValue = value == null ? \"null\" : value.toString();");
		out.println(indent+indent+indent+"} catch (Exception e) {");
		out.println(indent+indent+indent+"}");
		out.println(indent+indent+indent+"textField.setText(strValue);");
		out.println(indent+indent+"}");
		out.println(indent+indent+"//--- For each COLLECTION ---");
		out.println(indent+indent+"Iterator itList = collectionContainers.iterator();");
		out.println(indent+indent+"itName = collectionNames.iterator();");
		out.println(indent+indent+"while (itList.hasNext()) {");
		out.println(indent+indent+indent+"//--- Get the list ---");
		out.println(indent+indent+indent+"JList list = (JList)itList.next();");
		out.println(indent+indent+indent+"//--- Get the name of the field ---");
		out.println(indent+indent+indent+"String name = (String)itName.next();");
		out.println(indent+indent+indent+"String getter = Naming.getGetter(name);");
		out.println(indent+indent+indent+"//--- Refresh the list ---");
		out.println(indent+indent+indent+"Vector colValue = new Vector();");
		out.println(indent+indent+indent+"try {");
		out.println(indent+indent+indent+indent+"Method method = host.getClass().getMethod(getter, new Class[] {});");
		out.println(indent+indent+indent+indent+"Object value = method.invoke(host, new Object[] {});");
		out.println(indent+indent+indent+indent+"Collection col = (java.util.Collection)value;");
		out.println(indent+indent+indent+indent+"Iterator it = col.iterator();");
		out.println(indent+indent+indent+indent+"while (it.hasNext()) {;");
		out.println(indent+indent+indent+indent+indent+"colValue.add(it.next());");
		out.println(indent+indent+indent+indent+"};");
		out.println(indent+indent+indent+"} catch (Exception e) {");
		out.println(indent+indent+indent+"}");
		out.println(indent+indent+indent+"list.setListData(colValue);");
		out.println(indent+indent+"}");
		out.println(indent+indent+"//--- For each USER ---");
		out.println(indent+indent+"itList = userContainers.iterator();");
		out.println(indent+indent+"itName = userNames.iterator();");
		out.println(indent+indent+"while (itList.hasNext()) {");
		out.println(indent+indent+indent+"//--- Get the list ---");
		out.println(indent+indent+indent+"JList list = (JList)itList.next();");
		out.println(indent+indent+indent+"//--- Get the name of the field ---");
		out.println(indent+indent+indent+"String name = (String)itName.next();");
		out.println(indent+indent+indent+"String getter = Naming.getGetter(name);");
		out.println(indent+indent+indent+"//--- Refresh the list ---");
		out.println(indent+indent+indent+"Vector colValue = new Vector();");
		out.println(indent+indent+indent+"try {");
		out.println(indent+indent+indent+indent+"Method method = host.getClass().getMethod(getter, new Class[] {});");
		out.println(indent+indent+indent+indent+"Object value = method.invoke(host, new Object[] {});");
		out.println(indent+indent+indent+indent+"colValue.add(value);");
		out.println(indent+indent+indent+"} catch (Exception e) {");
		out.println(indent+indent+indent+"}");
		out.println(indent+indent+indent+"list.setListData(colValue);");
		out.println(indent+indent+"}");
		out.println(indent+indent+"//--- For each PossibleValue ---");
		out.println(indent+indent+"itList = possibleContainers.iterator();");
		out.println(indent+indent+"Iterator itKey = keys.iterator();");
		out.println(indent+indent+"while (itList.hasNext()) {");
		out.println(indent+indent+indent+"//--- Get the list and the key ---");
		out.println(indent+indent+indent+"String key = (String)itKey.next();");
		out.println(indent+indent+indent+"JList list = (JList)itList.next();");
		out.println(indent+indent+indent+"//--- Refresh content ---");
		out.println(indent+indent+indent+"list.setListData((Vector)rep.getInstances(key));");
		out.println(indent+indent+"}");
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
	protected String viewEditFrameClass;
	protected String invokeMethodClass;
	protected String viewInterface;
	protected String viewClass;
	protected String editInterface;
	protected String editClass;
}
