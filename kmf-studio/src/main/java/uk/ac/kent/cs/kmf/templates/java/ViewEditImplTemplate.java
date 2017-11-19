package uk.ac.kent.cs.kmf.templates.java;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.kmfstudio.JavaType;
import uk.ac.kent.cs.kmf.kmfstudio.Naming;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Association;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Attribute;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Feature;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 */
public class ViewEditImplTemplate {
	public ViewEditImplTemplate(Model model, Context context, boolean edit, PrintWriter out) {
		this.model = model;
		this.context = context;
		this.edit = edit;
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
	  * Print EditVisitor implementation
	  */
	public void generate() {
		// Print generation stamp
		if (edit) {
			context.getNaming().putStamp(editClass, out);
		} else {
			context.getNaming().putStamp(viewClass, out);
		}
		// Print the code
		out.println("package "+modelPackage+".repository;");
		out.println();
		out.println("import java.util.*;");
		out.println("import java.awt.*;");
		out.println("import java.awt.event.*;");
		out.println("import javax.swing.*;");
		out.println("import javax.swing.border.*;");
		out.println();
		out.println("import uk.ac.kent.cs.kmf.util.*;");
		out.println("import uk.ac.kent.cs.ocl20.OclProcessor;");
		out.println();
		if (edit) {
			out.println("public class "+editClass);
			out.println(indent+"implements "+editInterface);
		} else {
			out.println("public class "+viewClass);
			out.println(indent+"implements "+viewInterface);
		}
		out.println("{");
		// Output constructor
		out.println(indent+"/** Constructor */");
		if (edit) {
			out.println(indent+"public "+editClass+"(OclProcessor oclProcessor) {");
		} else {
			out.println(indent+"public "+viewClass+"(OclProcessor oclProcessor) {");
		}
		out.println(indent+indent+"this.oclProcessor = oclProcessor;");
		out.println(indent+"}");
		out.println();
		out.println(indent+"/** OclProcessor */");
		out.println(indent+"protected OclProcessor oclProcessor;");
		out.println(indent+"public OclProcessor getOclProcessor() { return oclProcessor; }");
		out.println(indent+"public void setOclProcessor(OclProcessor processor) { oclProcessor = processor;	}");
		out.println();	
		// Output visit method for default factory
		if (context.isGenerateFactory()) {
			out.println(indent+"/** Visit factory for '"+context.getNaming().getFactoryInterface(modelName)+"' */");
			out.println(indent+"public Object visit("+modelPackage+"."+context.getNaming().getFactoryInterface(modelName)+" host, Object data) {");
			out.println(indent+indent+"JFrame frame = new JFrame(\""+modelName+"."+context.getNaming().getFactoryInterface(modelName)+"\");");
			out.println(indent+indent+"return frame;");
			out.println(indent+"}");
		}
		// Output visit methods for each normal class
		Iterator i = context.getClasses().iterator();
		while (i.hasNext()) {
			Classifier cls = (Classifier)i.next();
			// Primitive types
			if (context.getNaming().isStereotype(cls, "primitive")) {
				log.reportMessage("No View visitor implementation for "+cls.getName().getBody_()+" it is <<\"primitive\">>");
			// External types
			} else if (context.getNaming().isStereotype(cls, "external")) {
				log.reportMessage("No View visitor implementation for "+cls.getName().getBody_()+" it is <<\"external\">>");
			// Stereotype enumerators
			} else if (context.getNaming().isStereotype(cls, "enumeration")) {
				out.println(indent+"/** Visit factory for '"+context.getNaming().getFullInterfaceName(cls)+"' */");
				out.println(indent+"public Object visit("+context.getNaming().getFullFactoryInterfaceName(cls)+" host, Object data) {");
				out.println(indent+indent+"JFrame frame = new JFrame(\""+context.getNaming().getFullFactoryInterfaceName(cls)+"\");");
				out.println(indent+indent+"return frame;");
				out.println(indent+"}");
				// Class
				out.println(indent+"/** Visit class for '"+context.getNaming().getFullInterfaceName(cls)+"' */");
				out.println(indent+"public Object visit("+context.getNaming().getFullPackageName(cls)+"."+context.getNaming().getInterfaceName(cls)+" host, Object data) {");
				out.println(indent+indent+"String str = \""+context.getNaming().getFullPackageName(cls)+"."+context.getNaming().getInterfaceName(cls)+"\";");
				// Compare current value to each enumerator
				List ancest = context.getNaming().allSuperClasses(cls, false);
				for(int j=ancest.size()-1; j>=0; j--) {
					Classifier a_cls = (Classifier)ancest.get(j);
					String a_clsName = context.getNaming().getClassifierName(a_cls);
					Iterator k = a_cls.getFeature().iterator();
					while (k.hasNext()) {
						Feature f = (Feature)k.next();
						if (f instanceof Attribute) {
							Attribute attrib = (Attribute)f;
							String attribName = context.getNaming().getPropertyName(attrib);
							String fullAttribName = context.getNaming().getFullClassName(cls)+"."+attribName.toUpperCase();
							out.println(indent+indent+"if (host == "+fullAttribName+") str = \""+fullAttribName+"\";");
						}
					}
				}
				out.println(indent+indent+"return str;");
				out.println(indent+"}");
			// Ordinary classes
			} else {
				// Factory
				// Abstract types
				if (cls.getIsAbstract().booleanValue() || !context.isGenerateFactory()) {
					log.reportMessage("No View visitor for "+cls.getName().getBody_()+" it is \"abstract\".");
				// Non-Abstract types
				} else {
					out.println(indent+"/** Visit factory for '"+context.getNaming().getFullInterfaceName(cls)+"' */");
					out.println(indent+"public Object visit("+context.getNaming().getFullFactoryInterfaceName(cls)+" host, Object data) {");
					out.println(indent+indent+"JFrame frame = new JFrame(\""+context.getNaming().getFullFactoryInterfaceName(cls)+"\");");
					out.println(indent+indent+"return frame;");
					out.println(indent+"}");
				}
				// Class
				String repositoryInterface = context.getNaming().getFullRepositoryInterface(modelName);
				out.println(indent+"/** Visit class for '"+context.getNaming().getFullInterfaceName(cls)+"' */");
				out.println(indent+"public Object visit("+context.getNaming().getFullPackageName(cls)+"."+context.getNaming().getInterfaceName(cls)+" host, Object data) {");
				out.println(indent+indent+"//--- Get the repository ---");
				out.println(indent+indent+"final "+repositoryInterface+" rep = ("+repositoryInterface+")data;");
				out.println();
				out.println(indent+indent+"//--- Make a final copy of host ---");
				out.println(indent+indent+"final "+context.getNaming().getFullPackageName(cls)+"."+context.getNaming().getInterfaceName(cls)+" finalHost = host;");
				out.println();
				out.println(indent+indent+"//--- Frame settings ---");
				out.println(indent+indent+"final "+viewEditFrameClass+" frame = new "+viewEditFrameClass+"(host, rep);");
				out.println(indent+indent+"//--- Get refresh info to be stored ---");
				out.println(indent+indent+"java.util.List primitiveContainers = frame.getPrimitiveContainers();");
				out.println(indent+indent+"java.util.List primitiveNames = frame.getPrimitiveNames();");
				out.println(indent+indent+"java.util.List collectionContainers = frame.getCollectionContainers();");
				out.println(indent+indent+"java.util.List collectionNames = frame.getCollectionNames();");
				out.println(indent+indent+"java.util.List userContainers = frame.getUserContainers();");
				out.println(indent+indent+"java.util.List userNames = frame.getUserNames();");
				out.println(indent+indent+"java.util.List possibleContainers = frame.getPossibleContainers();");
				out.println(indent+indent+"java.util.List keys = frame.getKeys();");
				out.println();
				out.println(indent+indent+"//--- Create VIEW-EDIT panel ---");
				out.println(indent+indent+"JPanel editPanel = new JPanel(new GridBagLayout());");
				out.println(indent+indent+"//--- Give the panel a border gap of 5 pixels ---");
				out.println(indent+indent+"editPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));");
				out.println(indent+indent+"//--- Get the constraints ---");
				out.println(indent+indent+"GridBagConstraints c = new GridBagConstraints();");
				out.println(indent+indent+"//--- Add space around all components to avoid clutter ---");
				out.println(indent+indent+"c.insets = new Insets(2, 2, 2, 2);");
				out.println(indent+indent+"//--- Anchor all components WEST ---");
				out.println(indent+indent+"c.anchor = GridBagConstraints.WEST;");
				out.println(indent+indent+"//--- Initialize ---");
				out.println(indent+indent+"String name;");
				out.println(indent+indent+"Object value;");
				out.println(indent+indent+"int line = 0;");
				// Add properties
				List ancest = context.getNaming().allSuperClasses(cls, false);
				Set addedAttrib = new LinkedHashSet();
				Set addedAssoc = new LinkedHashSet();
				for(int j=ancest.size()-1; j>=0; j--) {
					Classifier a_cls = (Classifier)ancest.get(j);
					// Add all attributes
					Iterator k = a_cls.getFeature().iterator();
					while (k.hasNext()) {
						Feature f = (Feature)k.next();
						if (f instanceof Attribute) {
							Attribute attrib = (Attribute)f;
							String attribName = context.getNaming().getPropertyName(attrib);
							out.println(indent+indent+"//--- Add attribute '"+attribName+"' ---");
							if (!addedAttrib.contains(attribName)) {
								// Add the Swing components
								Naming n = context.getNaming();
								String filter = context.getNaming().getTypeName(attrib.getType(), null); 
								filter = n.removeOffset(context.getPackageOffset(), filter);
								outputViewEditVisitorType(
									modelName, 
									attribName, 
									context.getNaming().getPropertyType(attrib, (Classifier)attrib.getType(), null),
									filter, 
									edit, 
									out);
								// Add to set
								addedAttrib.add(attribName);
							}
						}
					}
					// Add all associations
					k = context.getAssociations().iterator();
					while (k.hasNext()) {
						Association assoc = (Association)k.next();
						AssociationEnd aend1 = (AssociationEnd)assoc.getConnection().get(0);
						AssociationEnd aend2 = (AssociationEnd)assoc.getConnection().get(1);
						if (context.getNaming().add(aend1, aend2, a_cls)) {
							String assocName = context.getNaming().getPropertyName(aend2);
							out.println(indent+indent+"//--- Add association '"+assocName+"' ---");
							if (!addedAssoc.contains(assocName)) {
								// Add the Swing components
								Naming n = context.getNaming();
								String filter = n.getTypeName(aend2.getType(), null);
								filter = n.removeOffset(context.getPackageOffset(), filter);
								outputViewEditVisitorType(
									modelName, 
									assocName, 
									context.getNaming().getPropertyType(aend2, (Classifier)aend2.getType(), null), 
									filter, 
									edit, 
									out);
								// Add to set
								addedAssoc.add(assocName);
							}
						}
						if (context.getNaming().add(aend2, aend1, a_cls)) {
							String assocName = context.getNaming().getPropertyName(aend1);
							out.println(indent+indent+"//--- Add association '"+assocName+"' ---");
							if (!addedAssoc.contains(assocName)) {
								// Add the Swing components
								Naming n = context.getNaming();
								String filter = context.getNaming().getTypeName(aend1.getType(), null); 
								filter = n.removeOffset(context.getPackageOffset(), filter);
								outputViewEditVisitorType(
									modelName, 
									assocName, 
									context.getNaming().getPropertyType(aend1, (Classifier)aend1.getType(), null), 
									filter, 
									edit, 
									out);
								// Add to set
								addedAssoc.add(assocName);
							}
						}
					}
				}
				out.println();
				String modelClassName = context.getNaming().removeOffset(context.getPackageOffset(), context.getNaming().getFullInterfaceName(cls));
				out.println(indent+indent+"//--- Create EVALUATE panel ---");
				out.println(indent+indent+"JPanel evalPanel = createEvalPanel(\""+modelClassName+"\", finalHost);");
				out.println();
				out.println(indent+indent+"//--- Create REFRESH panel ---");
				out.println(indent+indent+"JPanel refreshPanel = createRefreshPanel(frame);");
				out.println();
				out.println(indent+indent+"//--- Add EDIT, EVALUATE, and REFRESH panels ---");
				out.println(indent+indent+"addPanels(frame, refreshPanel, editPanel, evalPanel);");
				out.println(indent+indent+"return frame;");
				out.println(indent+"}");
			}
		}
		// Add common funtions
		out.println();
		out.println(indent+"//--- Create EVALUATE panel ---");
		out.println(indent+"protected JPanel createEvalPanel(final String classPathName, final Object finalHost) {");
		out.println(indent+indent+"JPanel evalPanel = new JPanel();");
		out.println(indent+indent+"evalPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));");
		out.println(indent+indent+"evalPanel.setLayout(new BoxLayout(evalPanel, BoxLayout.Y_AXIS) );");
		out.println(indent+indent+"//--- Create INPUT ---");
		out.println(indent+indent+"final JTextArea expressionText = new JTextArea();");
		out.println(indent+indent+"expressionText.setRows(5);");
		out.println(indent+indent+"expressionText.setLineWrap(true);");
		out.println(indent+indent+"//--- Create OUTPUT ---");
		out.println(indent+indent+"final ILog evaluationText = new ConsoleLog();");
		out.println(indent+indent+"((JTextArea)evaluationText).setRows(5);");
		out.println(indent+indent+"((JTextArea)evaluationText).setLineWrap(true);");
		out.println(indent+indent+"//--- Create EVALUATE BUTTON ---");
		out.println(indent+indent+"JButton evaluateButton = new JButton(new AbstractAction(\"Evaluate\") {");
		out.println(indent+indent+indent+"public void actionPerformed(ActionEvent e) {");
		out.println(indent+indent+indent+indent+"try {");
		out.println(indent+indent+indent+indent+indent+"String constraint = expressionText.getText();");
		out.println(indent+indent+indent+indent+indent+"if (!constraint.startsWith(\"context \")) constraint = \"context \"+classPathName.replaceAll(\"[.]\", \"::\")+\" inv: \"+ constraint;");
		out.println(indent+indent+indent+indent+indent+"java.util.List result = oclProcessor.evaluate(constraint, finalHost, evaluationText);");
		out.println(indent+indent+indent+indent+indent+"evaluationText.reportMessage(result.get(0).toString());");
		out.println(indent+indent+indent+indent+"} catch(Exception e1) {");
		out.println(indent+indent+indent+indent+indent+"evaluationText.reportMessage(\"undefined\");");
		out.println(indent+indent+indent+indent+"}");
		out.println(indent+indent+indent+"}");
		out.println(indent+indent+"});");
		out.println(indent+indent+"evaluateButton.setAlignmentX(JScrollPane.CENTER_ALIGNMENT);");
		out.println(indent+indent+"//--- Add INPUT, EVALUATE BUTTON, and OUTPUT ---");
		out.println(indent+indent+"evalPanel.add(new JScrollPane(expressionText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));");
		out.println(indent+indent+"evalPanel.add(evaluateButton);");
		out.println(indent+indent+"evalPanel.add(new JScrollPane(((JTextArea)evaluationText), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));");
		out.println(indent+indent+"return evalPanel;");
		out.println(indent+"}");
		out.println();
		out.println(indent+"//--- Create REFRESH panel ---");
		out.println(indent+"protected JPanel createRefreshPanel(final "+viewEditFrameClass+" frame) {");
		out.println(indent+indent+"JPanel refreshPanel = new JPanel();");
		out.println(indent+indent+"refreshPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));");
		out.println(indent+indent+"refreshPanel.setLayout(new BoxLayout(refreshPanel, BoxLayout.Y_AXIS) );");
		out.println(indent+indent+"//--- Create REFRESH BUTTON ---");
		out.println(indent+indent+"JButton refreshButton = new JButton(new AbstractAction(\"Refresh\") {");
		out.println(indent+indent+indent+"public void actionPerformed(ActionEvent e) {");
		out.println(indent+indent+indent+indent+"frame.refreshAction();");
		out.println(indent+indent+indent+"}");
		out.println(indent+indent+"});");
		out.println(indent+indent+"refreshButton.setAlignmentX(JScrollPane.CENTER_ALIGNMENT);");
		out.println(indent+indent+"//--- Add REFRESH BUTTON ---");
		out.println(indent+indent+"refreshPanel.add(refreshButton);");
		out.println(indent+indent+"return refreshPanel;");
		out.println(indent+"}");
		out.println(indent+indent+"//--- Add EDIT, EVALUATE, and REFRESH panels ---");
		out.println(indent+"protected void addPanels(final "+viewEditFrameClass+" frame, JPanel refreshPanel, JPanel editPanel, JPanel evalPanel) {");
		out.println(indent+indent+"frame.getContentPane().add(new JScrollPane(refreshPanel), BorderLayout.NORTH);");
		out.println(indent+indent+"frame.getContentPane().add(new JScrollPane(editPanel), BorderLayout.CENTER);");
		out.println(indent+indent+"frame.getContentPane().add(new JScrollPane(evalPanel), BorderLayout.SOUTH);");
		out.println(indent+indent+"frame.pack();");
		out.println(indent+"}");
		out.println(indent+"//--- Set the grid ---");
		out.println(indent+"void setGrid() {");
		out.println(indent+"}");	        
		out.println();
		//
		//--- Add labels and fields ---
		//
		out.println(indent+"//--- Add labels name and = ---");
		out.println(indent+"protected void addLabels(String name, JPanel editPanel, GridBagConstraints c, int line) {");
		out.println(indent+indent+"//--- Add property name to panel ---");
		out.println(indent+indent+"JLabel label = new JLabel(name);");
		out.println(indent+indent+"label.setBorder(border);");
		out.println(indent+indent+"c.gridx = 0;");
		out.println(indent+indent+"c.gridy = line;");
		out.println(indent+indent+"c.ipadx = 5;");
		out.println(indent+indent+"c.ipady = 5;");
		out.println(indent+indent+"c.weightx = 0.0;");
		out.println(indent+indent+"c.weighty = 0.0;");
		out.println(indent+indent+"editPanel.add(label, c);");
		out.println(indent+indent+"//--- Add = to panel---");
		out.println(indent+indent+"label = new JLabel(\" = \");");
		out.println(indent+indent+"c.gridx = 1;");
		out.println(indent+indent+"label.setBorder(border);");
		out.println(indent+indent+"editPanel.add(label, c);");
		out.println(indent+"}");
		//
		//--- Add SWING components for a primitive type ---
		//
		String repositoryInterface = context.getNaming().getFullRepositoryInterface(modelName);
		out.println(indent+"//--- Add SWING components for a primitive field ---");
		out.println(indent+"protected void addJs4Primitive(final Object finalHost, final String name, final Object value, "+repositoryInterface+" rep, String filter, JPanel editPanel, GridBagConstraints c, int line, "+viewEditFrameClass+" refreshFrame) {");
		out.println(indent+indent+"//--- Add VALUE to panel ---");
		out.println(indent+indent+"final JTextField textField = new JTextField(value == null ? \"null\" : value.toString(), 20);");
		out.println(indent+indent+"textField.setFont(font);");
		out.println(indent+indent+"textField.setPreferredSize(fieldSize);");
		if (edit) {
			out.println(indent+indent+"textField.setEditable(true);");
		} else {
			out.println(indent+indent+"textField.setEditable(false);");
		}
		out.println(indent+indent+"c.gridx = 2;");
		out.println(indent+indent+"editPanel.add(textField, c);");
		if (edit) {
			out.println(indent+indent+"//--- Add SET button to panel ---");
			out.println(indent+indent+"JButton setButton = new JButton(new AbstractAction(\"Set\") {");
			out.println(indent+indent+indent+"public void actionPerformed(ActionEvent e) {");
			out.println(indent+indent+indent+indent+"//--- Get value from JTextField ---");
			out.println(indent+indent+indent+indent+"String strValue = textField.getText();");
			out.println(indent+indent+indent+indent+"Class cls = finalHost.getClass();");
			out.println(indent+indent+indent+indent+"try {");
			out.println(indent+indent+indent+indent+indent+"String getter = Naming.getGetter(name);");
			out.println(indent+indent+indent+indent+indent+"String setter = Naming.getSetter(name);");
			out.println(indent+indent+indent+indent+indent+"java.lang.reflect.Method getMethod = cls.getMethod(getter, new Class[]{});");
			out.println(indent+indent+indent+indent+indent+"java.lang.reflect.Method setMethod = cls.getMethod(setter, new Class[]{getMethod.getReturnType()});");
			out.println(indent+indent+indent+indent+indent+"if (value instanceof String) {");
			out.println(indent+indent+indent+indent+indent+indent+"setMethod.invoke(finalHost, new Object[] {strValue});");
			out.println(indent+indent+indent+indent+indent+"} else if (value instanceof Integer) {");
			out.println(indent+indent+indent+indent+indent+indent+"setMethod.invoke(finalHost, new Object[] {Integer.valueOf(strValue)});");
			out.println(indent+indent+indent+indent+indent+"} else if (value instanceof Double) {");
			out.println(indent+indent+indent+indent+indent+indent+"setMethod.invoke(finalHost, new Object[] {Double.valueOf(strValue)});");
			out.println(indent+indent+indent+indent+indent+"} else if (value instanceof Boolean) {");
			out.println(indent+indent+indent+indent+indent+indent+"setMethod.invoke(finalHost, new Object[] {Boolean.valueOf(strValue)});");
			out.println(indent+indent+indent+indent+indent+"}");
			out.println(indent+indent+indent+indent+"} catch (Exception e1) {");
			out.println(indent+indent+indent+indent+"}");
			out.println(indent+indent+indent+"};");
			out.println(indent+indent+"});");
			out.println(indent+indent+"c.gridx = 5;");
			out.println(indent+indent+"editPanel.add(setButton, c);");
		}
		//--- Store JLists to be refreshed ---
		out.println(indent+indent+"//--- Store refresh info ---");
		out.println(indent+indent+"refreshFrame.getPrimitiveContainers().add(textField);");
		out.println(indent+indent+"refreshFrame.getPrimitiveNames().add(name);");
		out.println(indent+"}");
		//
		//--- Add SWING components for a collection
		//
		out.println(indent+indent+"//--- Add SWING components for a collection field ---");
		out.println(indent+"protected void addJs4Collection(String name, final Object value, "+repositoryInterface+" rep, String filter, JPanel editPanel, GridBagConstraints c, int line, "+viewEditFrameClass+" frame) {");
		out.println(indent+indent+"//--- Add VALUE to panel ---");
		out.println(indent+indent+"final Vector objects1 = new Vector();");
		out.println(indent+indent+"Collection col = (java.util.Collection)value;");
		out.println(indent+indent+"Iterator it = col.iterator();");
		out.println(indent+indent+"while (it.hasNext()) {");
		out.println(indent+indent+indent+"Object obj = it.next();");
		out.println(indent+indent+indent+"objects1.add(obj);");
		out.println(indent+indent+"}");
		out.println(indent+indent+"final JList list1 = new JList(objects1);");
		out.println(indent+indent+"list1.setFont(font);");
		out.println(indent+indent+"JScrollPane scroller1 = new JScrollPane(list1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);");
		out.println(indent+indent+"scroller1.setPreferredSize(listSize);");
		out.println(indent+indent+"c.gridx = 2;");
		out.println(indent+indent+"editPanel.add(scroller1, c);");
		if (edit) {
			out.println(indent+indent+"//--- Add REMOVE button to panel ---");
			out.println(indent+indent+"JButton removeButton = new JButton(new AbstractAction(\"Remove\") {");
			out.println(indent+indent+indent+"public void actionPerformed(ActionEvent e) {");
			out.println(indent+indent+indent+indent+"//--- Get selection ---");
			out.println(indent+indent+indent+indent+"Object selectedValues[] = list1.getSelectedValues();");
			out.println(indent+indent+indent+indent+"//--- Delete selected objects ---");
			out.println(indent+indent+indent+indent+"if (selectedValues != null) {");
			out.println(indent+indent+indent+indent+indent+"for(int i=0; i<selectedValues.length; i++) {");
			out.println(indent+indent+indent+indent+indent+indent+"//--- Delete the element from list ---");
			out.println(indent+indent+indent+indent+indent+indent+"Object object = selectedValues[i];");
			out.println(indent+indent+indent+indent+indent+indent+"objects1.remove(object);");
			out.println(indent+indent+indent+indent+indent+indent+"//--- Delete the element value ---");
			out.println(indent+indent+indent+indent+indent+indent+"((java.util.Collection)value).remove(object);");
			out.println(indent+indent+indent+indent+indent+"}");
			out.println(indent+indent+indent+indent+"}");
			out.println(indent+indent+indent+indent+"//--- Update the container ---");
			out.println(indent+indent+indent+indent+"list1.setListData(objects1);");
			out.println(indent+indent+indent+indent+"list1.setSelectedIndices(new int[] {});");
			out.println(indent+indent+indent+"};");
			out.println(indent+indent+"});");
			out.println(indent+indent+"c.gridx = 3;");
			out.println(indent+indent+"editPanel.add(removeButton, c);");
			out.println(indent+indent+"//--- Add UNIVERSAL SET to panel ---");
			out.println(indent+indent+"Vector objects2 = (Vector)rep.getInstances(filter);");
			out.println(indent+indent+"final JList list2 = new JList(objects2 == null ? new Vector() : objects2);");
			out.println(indent+indent+"list2.setFont(font);");
			out.println(indent+indent+"JScrollPane scroller2 = new JScrollPane(list2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);");
			out.println(indent+indent+"scroller2.setPreferredSize(listSize);");
			out.println(indent+indent+"c.gridx = 4;");
			out.println(indent+indent+"editPanel.add(scroller2, c);");
			out.println(indent+indent+"//--- Add ADD button to panel ---");
			out.println(indent+indent+"JButton addButton = new JButton(new AbstractAction(\"Add\") {");
			out.println(indent+indent+indent+"public void actionPerformed(ActionEvent e) {");
			out.println(indent+indent+indent+indent+"//--- Get selection ---");
			out.println(indent+indent+indent+indent+"Object selectedValues[] = list2.getSelectedValues();");
			out.println(indent+indent+indent+indent+"//--- Add selected objects ---");
			out.println(indent+indent+indent+indent+"if (selectedValues != null) {");
			out.println(indent+indent+indent+indent+indent+"for(int i=0; i<selectedValues.length; i++) {");
			out.println(indent+indent+indent+indent+indent+indent+"//--- Add the element to list and value ---");
			out.println(indent+indent+indent+indent+indent+indent+"Object object = selectedValues[i];");
			out.println(indent+indent+indent+indent+indent+indent+"if (value instanceof java.util.Set) {");
			out.println(indent+indent+indent+indent+indent+indent+indent+"if (!objects1.contains(object)) objects1.add(object);");
			out.println(indent+indent+indent+indent+indent+indent+"} else {");
			out.println(indent+indent+indent+indent+indent+indent+indent+"objects1.add(object);");
			out.println(indent+indent+indent+indent+indent+indent+"}");
			out.println(indent+indent+indent+indent+indent+indent+"((java.util.Collection)value).add(object);");
			out.println(indent+indent+indent+indent+indent+"}");
			out.println(indent+indent+indent+indent+"}");
			out.println(indent+indent+indent+indent+"//--- Update the container ---");
			out.println(indent+indent+indent+indent+"list1.setListData(objects1);");
			out.println(indent+indent+indent+indent+"list2.setSelectedIndices(new int[] {});");
			out.println(indent+indent+indent+"};");
			out.println(indent+indent+"});");
			out.println(indent+indent+"c.gridx = 5;");
			out.println(indent+indent+"editPanel.add(addButton, c);");
		}
		//--- Store JLists to be refreshed ---
		out.println(indent+indent+"//--- Store refresh info ---");
		out.println(indent+indent+"frame.getCollectionContainers().add(list1);");
		out.println(indent+indent+"frame.getCollectionNames().add(name);");
		if (edit) {
			out.println(indent+indent+"frame.getPossibleContainers().add(list2);");
			out.println(indent+indent+"frame.getKeys().add(filter);");
		}
		out.println(indent+"}");
		//
		//--- Add SWING components for a user type
		//
		out.println(indent+indent+"//--- Add SWING components for a user field ---");
		out.println(indent+"protected void addJs4User(final Object finalHost, final String name, final Object value, "+repositoryInterface+" rep, String filter, JPanel editPanel, GridBagConstraints c, int line, "+viewEditFrameClass+" frame) {");
		out.println(indent+indent+"//--- Add VALUE to panel ---");
		out.println(indent+indent+"final Vector objects1 = new Vector();");
		out.println(indent+indent+"objects1.add(value);");
		out.println(indent+indent+"final JList list1 = new JList(objects1);");
		out.println(indent+indent+"list1.setFont(font);");
		out.println(indent+indent+"list1.setVisibleRowCount(1);");
		out.println(indent+indent+"JScrollPane scroller1 = new JScrollPane(list1, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);");
		out.println(indent+indent+"scroller1.setPreferredSize(fieldSize);");
		out.println(indent+indent+"c.gridx = 2;");
		out.println(indent+indent+"editPanel.add(scroller1, c);");
		if (edit) {
			out.println(indent+indent+"//--- Add UNIVERSAL SET to panel ---");
			out.println(indent+indent+"Vector objects2 = (Vector)rep.getInstances(filter);");
			out.println(indent+indent+"final JList list2 = new JList(objects2 == null ? new Vector() : objects2);");
			out.println(indent+indent+"list2.setFont(font);");
			out.println(indent+indent+"JScrollPane scroller2 = new JScrollPane(list2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);");
			out.println(indent+indent+"scroller2.setPreferredSize(listSize);");
			out.println(indent+indent+"c.gridx = 4;");
			out.println(indent+indent+"editPanel.add(scroller2, c);");
			out.println(indent+indent+"//--- Add SET button to panel ---");
			out.println(indent+indent+"JButton setButton = new JButton(new AbstractAction(\"Set\") {");
			out.println(indent+indent+indent+"public void actionPerformed(ActionEvent e) {");
			out.println(indent+indent+indent+indent+"//--- Get selection ---");
			out.println(indent+indent+indent+indent+"Object selectedValue = list2.getSelectedValue();");
			out.println(indent+indent+indent+indent+"//--- Set the property ---");
			out.println(indent+indent+indent+indent+"objects1.clear();");
			out.println(indent+indent+indent+indent+"objects1.add(selectedValue);");
			out.println(indent+indent+indent+indent+"Class cls = finalHost.getClass();");
			out.println(indent+indent+indent+indent+"try {");
			out.println(indent+indent+indent+indent+indent+"String getter = Naming.getGetter(name);");
			out.println(indent+indent+indent+indent+indent+"String setter = Naming.getSetter(name);");
			out.println(indent+indent+indent+indent+indent+"java.lang.reflect.Method getMethod = cls.getMethod(getter, new Class[]{});");
			out.println(indent+indent+indent+indent+indent+"java.lang.reflect.Method setMethod = cls.getMethod(setter, new Class[]{getMethod.getReturnType()});");
			out.println(indent+indent+indent+indent+indent+"setMethod.invoke(finalHost, new Object[] {selectedValue});");
			out.println(indent+indent+indent+indent+"} catch (Exception e1) {");
			out.println(indent+indent+indent+indent+"}");
			out.println(indent+indent+indent+indent+"//--- Update the container ---");
			out.println(indent+indent+indent+indent+"list1.setListData(objects1);");
			out.println(indent+indent+indent+indent+"list2.setSelectedIndices(new int[] {});");
			out.println(indent+indent+indent+"};");
			out.println(indent+indent+"});");
			out.println(indent+indent+"c.gridx = 5;");
			out.println(indent+indent+"editPanel.add(setButton, c);");
		}
		//--- Store JLists to be refreshed ---
		out.println(indent+indent+"//--- Store refresh info ---");
		out.println(indent+indent+"frame.getUserContainers().add(list1);");
		out.println(indent+indent+"frame.getUserNames().add(name);");
		if (edit) {
			out.println(indent+indent+"frame.getPossibleContainers().add(list2);");
			out.println(indent+indent+"frame.getKeys().add(filter);");
		}
		out.println("}");

		//
		//--- Add common variables ---
		//
		out.println(indent+"//--- Look settings ---");
		String browserClass = context.getNaming().getFullBrowserClass(modelName);
		out.println(indent+"final EmptyBorder border = new EmptyBorder(new Insets(0, 0, 0, 10));");
		out.println(indent+"final Font font = "+browserClass+".font;");
		out.println(indent+"final Dimension listSize = "+browserClass+".listSize;");
		out.println(indent+"final Dimension fieldSize = "+browserClass+".fieldSize;;");
		out.println("}");
	}

	/**
	  * Add the frame components for 'name' property
	  */
	protected void outputViewEditVisitorType(String modelName, String name, String type, String filter, boolean edit, PrintWriter out) {
		//--- Add labels ---
		out.println(indent+indent+"//--- Add labels ---");
		out.println(indent+indent+"addLabels(\""+name+"\", editPanel, c, line++);");
		// Mutator name
		if (name.length() == 0) return;
		String getter = context.getNaming().getGetter(name);
		String setter = context.getNaming().getSetter(name);
		//--- Primitive types ---
		if (JavaType.isPrimitiveType(type)) {
			//--- Add primitive type'c JComponents ---
			out.println(indent+indent+"//--- Add VALUE to panel ---");
			out.println(indent+indent+"addJs4Primitive(finalHost, \""+name+"\", host."+getter+"(), rep, \""+filter+"\", editPanel, c, line, frame);");
		//--- Collection ---
		} else if (JavaType.isCollectionType(type)) {
			//--- Add collection's JComponents ---
			out.println(indent+indent+"//--- Add VALUE to panel ---");
			out.println(indent+indent+"addJs4Collection(\""+name+"\", host."+getter+"(), rep, \""+filter+"\", editPanel, c, line, frame);");
		} else {
			//--- Add user type's JComponents ---
			out.println(indent+indent+"//--- Add VALUE to panel ---");
			out.println(indent+indent+"addJs4User(finalHost, \""+name+"\", host."+getter+"(), rep, \""+filter+"\", editPanel, c, line, frame);");
		}
	}

	//
	// Local properties
	//
	protected Model model;
	protected Context context;
	protected boolean edit;
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
