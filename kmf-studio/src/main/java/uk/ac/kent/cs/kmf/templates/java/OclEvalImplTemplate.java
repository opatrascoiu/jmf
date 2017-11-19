package uk.ac.kent.cs.kmf.templates.java;

import java.io.PrintWriter;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 */
public class OclEvalImplTemplate {
	public OclEvalImplTemplate(Model model, Context context, PrintWriter out) {
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
		this.className = context.getNaming().getOCLClass(modelName);
	}
	
	/**
	  * Print instant evaluator implementation
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(className, out);
		// Print the code
		out.println("package "+modelPackage+".repository;");
		out.println();
		out.println("import java.util.*;");
		out.println("import java.awt.*;");
		out.println("import java.awt.event.*;");
		out.println("import javax.swing.*;");
		out.println("import javax.swing.border.*;");
		out.println();
		out.println("import uk.ac.kent.cs.ocl20.OclProcessor;");
		out.println("import uk.ac.kent.cs.kmf.util.*;");
		out.println();
		out.println("public class "+className);
		out.println(indent+"extends JInternalFrame");
		out.println("{");
		out.println();
		out.println(indent+"//--- CONTEXT ---");
		out.println(indent+"protected Map context = new HashMap();");
		out.println(indent+"//--- OCL processor ---");
		out.println(indent+"protected OclProcessor processor;");
		out.println(indent+"//--- INPUT and OUTPUT text area ---");
		out.println(indent+"protected JTextArea expressionText;");
		out.println(indent+"protected ILog evaluationText;");
		out.println(indent+"//--- EVALUATE button ---");
		out.println(indent+"protected JButton evaluateButton;");
		out.println();
		out.println(indent+"/** Construct evaluator */");
		out.println(indent+"public "+className+"(Map context, OclProcessor processor) {");
		out.println(indent+indent+"//--- Set CONTEXT ---");
		out.println(indent+indent+"this.context = context;");
		out.println(indent+indent+"String strContext = \"context:\\n\";");
		out.println(indent+indent+"Iterator i = context.entrySet().iterator();");
		out.println(indent+indent+"while (i.hasNext()) {");
		out.println(indent+indent+indent+"Map.Entry me = (Map.Entry)i.next();");
		out.println(indent+indent+indent+"strContext+=\"  \"+(String)me.getKey()+\" : \"+me.getValue().toString()+\"\\n\";");
		out.println(indent+indent+"}");
		out.println(indent+indent+"//--- Set processor ---");
		out.println(indent+indent+"this.processor = processor;");
		out.println();
		out.println(indent+indent+"//--- Frame settings ---");
		out.println(indent+indent+"setTitle(\"OCL Evaluator [\"+strContext+\"]\");");
		out.println(indent+indent+"setResizable(true);");
		out.println(indent+indent+"setClosable(true);");
		out.println(indent+indent+"setMaximizable(true);");
		out.println(indent+indent+"setIconifiable(true);");
		out.println();
		out.println(indent+indent+"//--- Panel settings ---");
		out.println(indent+indent+"JPanel mainPanel = new JPanel();");
		out.println(indent+indent+"mainPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));");
		out.println(indent+indent+"mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS) );");
		out.println();
		out.println(indent+indent+"//--- Add INPUT ---");
		out.println(indent+indent+"expressionText = new JTextArea();");
		out.println(indent+indent+"expressionText.setLineWrap(true);");
		out.println(indent+indent+"expressionText.setRows(5);");
		out.println(indent+indent+"mainPanel.add(new JScrollPane(expressionText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));");
		out.println();
		out.println(indent+indent+"//--- Add EVALUATE BUTTON ---");
		out.println(indent+indent+"evaluateButton = new JButton(new AbstractAction(\"Evaluate\") {");
		out.println(indent+indent+indent+"public void actionPerformed(ActionEvent e) {");
		out.println(indent+indent+indent+indent+"evaluateAction(e);");
		out.println(indent+indent+indent+"}");
		out.println(indent+indent+"});");
		out.println(indent+indent+"evaluateButton.setAlignmentX(CENTER_ALIGNMENT);");
		out.println(indent+indent+"mainPanel.add(evaluateButton);");
		out.println();
		out.println(indent+indent+"//--- Add OUTPUT ---");
		out.println(indent+indent+"evaluationText = new ConsoleLog();");
		out.println(indent+indent+"((JTextArea)evaluationText).setRows(5);");
		out.println(indent+indent+"((JTextArea)evaluationText).setLineWrap(true);");
		out.println(indent+indent+"mainPanel.add(new JScrollPane(((JTextArea)evaluationText), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));");
		out.println();
		out.println(indent+indent+"//--- Add the mainPanel ---");
		out.println(indent+indent+"setContentPane(new JScrollPane(mainPanel));");
		out.println(indent+indent+"pack();");
		out.println(indent+"}");
		out.println();
		out.println(indent+"/** Evaluate action */");
		out.println(indent+"protected void evaluateAction(ActionEvent e) {");
		out.println(indent+indent+"try {");
		out.println(indent+indent+indent+"String constraint = expressionText.getText();");
		out.println(indent+indent+indent+"Object host = context.get(\"self\");");
		out.println(indent+indent+indent+"String classPathName = host.toString();");
		out.println(indent+indent+indent+"int pos = classPathName.indexOf(\"'\");");
		out.println(indent+indent+indent+"if (pos != -1) classPathName = classPathName.substring(0, pos-1);");
		out.println(indent+indent+indent+"if (!constraint.startsWith(\"context \")) constraint = \"context \"+classPathName.replaceAll(\"[.]\", \"::\")+\" inv: \"+ constraint;");
		out.println(indent+indent+indent+"java.util.List result = processor.evaluate(constraint, host, evaluationText);");
		out.println(indent+indent+indent+"evaluationText.reportMessage(result.get(0).toString());");
		out.println(indent+indent+"} catch(Exception e1) {");
		out.println(indent+indent+indent+"evaluationText.reportMessage(\"undefined\");");
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
	protected String interfaceName;
	protected String className;
}
