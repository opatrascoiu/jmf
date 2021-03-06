/**
 *
 *  Class JavaModelInvokeMethod$Class.java
 *
 *  Generated by KMFStudio at 22 February 2004 15:13:03
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package javaModel.repository;

import java.util.*;
import java.lang.reflect.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class JavaModelInvokeMethod$Class
	 extends JInternalFrame
{
	//--- Repository, console, instance, method, and types ---
	protected javaModel.repository.JavaModelRepository rep;
	protected javaModel.repository.JavaModelBrowser$Class brow;
	protected Object instance;
	protected Method method;
	protected Class types[];
	protected JComponent values[];
	protected java.util.List indices = new Vector();
	protected java.util.List filters = new Vector();

	/** Construct invoker */
	public JavaModelInvokeMethod$Class(javaModel.repository.JavaModelRepository rep, javaModel.repository.JavaModelBrowser$Class brow, Object instance, Method method) {
		//--- Init members ---
		this.brow = brow;
		this.rep = rep;
		this.instance = instance;
		this.method = method;
		indices = new Vector();
		filters = new Vector();
		if (rep == null || instance == null || method == null) return;

		//--- Frame settings ---
		setTitle("Instant Lifecycle ["+instance+"]");
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);

		//--- Panel settings ---
		JPanel mainPanel = new JPanel(new BorderLayout());

		//--- Get the names of parameters ---
		String key = method.toString();
		int pos = key.indexOf('(');
		String key1 = new String();
		String key2 = new String();
		if (pos != -1) {
			key1 = key.substring(0, pos+1);
			key2 = key.substring(pos+1, key.length());
			pos = key1.lastIndexOf('.');
			if (pos != -1) key1 = key1.substring(pos+1, key1.length());
			key = key1 + key2;
		}
		String paramNames[] = new String[] {};
		try {
			Method getter = instance.getClass().getMethod("getParamNames", new Class[] {String.class});
			paramNames = (String [])getter.invoke(instance, new Object[] {key});
		} catch (Exception e) {
		}

		//--- Get the types of parameters ---
		types = method.getParameterTypes();
		if (types.length != 0) {
			//--- Create INVOKE panel ---
			JPanel invokePanel = new JPanel(new BorderLayout());
			invokePanel.setLayout(new GridBagLayout());
			//--- Give the panel a border gap of 5 pixels ---
			invokePanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
			//--- Get the constraints ---
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 0;
			c.ipadx = 5;
			c.ipady = 5;
			c.weightx = 0.0;
			c.weighty = 0.0;
			//--- Add space around all components to avoid clutter ---
			c.insets = new Insets(2, 2, 2, 2);
			//--- Anchor all components WEST ---
			c.anchor = GridBagConstraints.WEST;
			//--- Preferred sizes ---
			Dimension listSize = javaModel.repository.JavaModelBrowser$Class.listSize;
			Dimension fieldSize = javaModel.repository.JavaModelBrowser$Class.fieldSize;
			//--- Add labels and values ---
			values = new JComponent[types.length];
			for(int i=0; i<types.length; i++) {
				String strType = types[i].toString();
				//--- Add label ---
				String strLabel = new String();
				if (paramNames != null && i<paramNames.length) strLabel = paramNames[i];
				strLabel += " : ";
				pos = strType.lastIndexOf('.');
				if (pos != -1) strLabel += strType.substring(pos+1, strType.length());
				JLabel label = new JLabel(strLabel);
				c.gridx = 0;
				c.gridy = i;
				invokePanel.add(label, c);
				//--- Get typeName ---
				String typeName = strType;
				if (typeName.indexOf("interface ") == 0) typeName = typeName.replaceFirst("interface ", "");
				//--- Add FIELD or UNIVERSAL SET ---
				c.gridx = 1;
				c.gridy = i;
				if (typeName.endsWith("Boolean") || typeName.endsWith("Integer") || typeName.endsWith("Real") || 
					typeName.endsWith("String") ||	typeName.endsWith("StringBuffer")) {
					values[i] = new JTextField("");
					values[i].setFont(javaModel.repository.JavaModelBrowser$Class.font);
					values[i].setPreferredSize(fieldSize);
					invokePanel.add(values[i], c);
				} else if (typeName.endsWith("Set") || typeName.endsWith("List") || typeName.endsWith("Collection")) {
					Vector objects = (Vector)rep.getInstances(typeName);
					values[i] = new JList(objects == null ? new Vector() : objects);
					values[i].setFont(javaModel.repository.JavaModelBrowser$Class.font);
					JScrollPane scroller = new JScrollPane(values[i]);
					scroller.setPreferredSize(listSize);
					invokePanel.add(scroller, c);
					//--- Store index and name for refresh ---
					indices.add(new Integer(i));
					filters.add(typeName);
				} else {
					Vector objects = (Vector)rep.getInstances(typeName);
					values[i] = new JList(objects == null ? new Vector() : objects);
					((JList)values[i]).setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					values[i].setFont(javaModel.repository.JavaModelBrowser$Class.font);
					JScrollPane scroller = new JScrollPane(values[i]);
					scroller.setPreferredSize(listSize);
					invokePanel.add(scroller, c);
					//--- Store index and name for refresh ---
					indices.add(new Integer(i));
					filters.add(typeName);
				}
			}
			//--- Add INVOKE panel ---
			mainPanel.add(invokePanel, BorderLayout.CENTER);

			//--- Create REFRESH panel ---
			JPanel refreshPanel = new JPanel();
			refreshPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
			refreshPanel.setLayout(new BoxLayout(refreshPanel, BoxLayout.Y_AXIS) );
			//--- Create REFRESH BUTTON ---
			JButton refreshButton = new JButton(new AbstractAction("Refresh") {
				public void actionPerformed(ActionEvent e) {
					refreshAction();
				}
			});
			refreshButton.setAlignmentX(JScrollPane.CENTER_ALIGNMENT);
			refreshPanel.add(refreshButton);
			//--- Add REFRESH ---
			getContentPane().add(new JScrollPane(refreshPanel), BorderLayout.NORTH);
		 }

		 //--- Create BUTTON panel ---
		 JPanel buttonPanel = new JPanel();
		 JButton invokeButton = new JButton(new AbstractAction("Invoke") {
			public void actionPerformed(ActionEvent e) {
				invokeAction();
			}
		 });
		 buttonPanel.add(invokeButton);
		 JButton cancelButton = new JButton(new AbstractAction("Cancel") {
			public void actionPerformed(ActionEvent e) {
				 cancelAction();
			}
		 });
		 buttonPanel.add(cancelButton);
		 //--- Add BUTTON panel ---
		 mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		 //--- Add MAIN PANEL to frame ---
		 getContentPane().add(new JScrollPane(mainPanel));
		 pack();
	}

	/** Invoke action a method using reflection */
	protected void invokeAction() {
		Object arguments[] = new Object[types.length];
		for(int i=0; i<types.length; i++) {
			//--- Get typeName ---
			String typeName = types[i].toString();
			if (typeName.indexOf("interface ") == 0) typeName = typeName.replaceFirst("interface ", "");
			if (typeName.indexOf("class ") == 0) typeName = typeName.replaceFirst("class ", "");

			//--- Get argument ---
			if (typeName.endsWith("java.lang.Boolean")) {
				String strValue = ((JTextField)values[i]).getText();
				arguments[i] = strValue.length()==0 ? new Boolean(false) : Boolean.valueOf(strValue);
			} else if (typeName.endsWith("java.lang.Byte")) {
				String strValue = ((JTextField)values[i]).getText();
				arguments[i] = strValue.length()==0 ? new Byte("0") : Byte.valueOf(strValue);
			} else if (typeName.endsWith("java.lang.Character")) {
				String strValue = ((JTextField)values[i]).getText();
				arguments[i] = strValue.length()==0 ? new Character('0') : new Character(strValue.charAt(0));
			} else if (typeName.endsWith("java.lang.Double")) {
				String strValue = ((JTextField)values[i]).getText();
				arguments[i] = strValue.length()==0 ? new Double(0.0) : Double.valueOf(strValue);
			} else if (typeName.endsWith("java.lang.Float")) {
				String strValue = ((JTextField)values[i]).getText();
				arguments[i] = strValue.length()==0 ? new Float(0.0) : Float.valueOf(strValue);
			} else if (typeName.endsWith("java.lang.Integer")) {
				String strValue = ((JTextField)values[i]).getText();
				arguments[i] = strValue.length()==0 ? new Integer(0) : Integer.valueOf(strValue);
			} else if (typeName.endsWith("java.lang.Long")) {
				String strValue = ((JTextField)values[i]).getText();
				arguments[i] = strValue.length()==0 ? new Long(0) : Long.valueOf(strValue);
			} else if (typeName.endsWith("java.lang.Short")) {
				String strValue = ((JTextField)values[i]).getText();
				arguments[i] = strValue.length()==0 ? new Short("0") : Short.valueOf(strValue);
			} else if (typeName.endsWith("java.lang.String")) {
				String strValue = ((JTextField)values[i]).getText();
				arguments[i] = strValue;
			} else if (typeName.endsWith("java.lang.StringBuffer")) {
				String strValue = ((JTextField)values[i]).getText();
				arguments[i] = strValue;
			} else if (typeName.endsWith("java.util.Collection")) {
				Object selValues[] = ((JList)values[i]).getSelectedValues();
				Collection colValue = new Vector();
				if (selValues != null)
					for(int j=0; j<selValues.length; j++) colValue.add(selValues[j]);
				arguments[i] = colValue;
			} else if (typeName.endsWith("java.util.Set")) {
				Object selValues[] = ((JList)values[i]).getSelectedValues();
				Set colValue = new HashSet();
				if (selValues != null)
					for(int j=0; j<selValues.length; j++) colValue.add(selValues[j]);
				arguments[i] = colValue;
			} else if (typeName.endsWith("java.util.List")) {
				Object selValues[] = ((JList)values[i]).getSelectedValues();
				Collection colValue = new Vector();
				if (selValues != null)
					for(int j=0; j<selValues.length; j++) colValue.add(selValues[j]);
				arguments[i] = colValue;
			} else {
				arguments[i] = ((JList)values[i]).getSelectedValue();
			}
		}
		try {
			//--- Invoke METHOD ---
			method.invoke(instance, arguments);
			//--- Update CONSOLE ---
			brow.getLog().reportMessage("Invoke method '"+method.toString()+"'");
		} catch (Exception except) {
			//--- Update CONSOLE ---
			brow.getLog().reportMessage("Error while invoking method '"+method.toString()+"'");
		}
	}

	/** Cancel action */
	protected void cancelAction() {
		dispose();
	}

	/** Refresh action */
	protected void refreshAction() {
		Iterator itIndex = indices.iterator();
		Iterator itFilter = filters.iterator();
		while (itIndex.hasNext()) {
			int index = ((Integer)itIndex.next()).intValue();
			String filter = (String)itFilter.next();
			//--- Update UNIVERSAL SET ---
			((JList)values[index]).setListData((Vector)rep.getInstances(filter));
		}
	}
}
