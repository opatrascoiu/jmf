/**
 *
 *  Class EdocViewEditFrame$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:37
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.repository;

import java.lang.reflect.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

import uk.ac.kent.cs.kmf.util.*;

public class EdocViewEditFrame$Class
	extends JInternalFrame
{
	//--- Repository and host ---
	protected edoc.repository.EdocRepository rep;
	protected Object host;
	//--- PRIMITIVE containers and names ---
	protected java.util.List primitiveContainers = new Vector();
	public java.util.List getPrimitiveContainers() { return primitiveContainers; }
	protected java.util.List primitiveNames = new Vector();
	public java.util.List getPrimitiveNames() { return primitiveNames; }
	//--- COLLECTION containers and names ---
	protected java.util.List collectionContainers = new Vector();
	public java.util.List getCollectionContainers() { return collectionContainers; }
	protected java.util.List collectionNames = new Vector();
	public java.util.List getCollectionNames() { return collectionNames; }
	//--- USER containers and names ---
	protected java.util.List userContainers = new Vector();
	public java.util.List getUserContainers() { return userContainers; }
	protected java.util.List userNames = new Vector();
	public java.util.List getUserNames() { return userNames; }
	//--- POSSIBLE VALUES containers and names ---
	protected java.util.List possibleContainers = new Vector();
	public java.util.List getPossibleContainers() { return possibleContainers; }
	protected java.util.List keys = new Vector();
	public java.util.List getKeys() { return keys; }

	/** ViewEdit frame constructor */
	public EdocViewEditFrame$Class(Object host, edoc.repository.EdocRepository rep) {
		//--- Frame settings ---
		getContentPane().setLayout(new BorderLayout());
		setTitle(host.toString());
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		//--- Repository settings ---
		this.rep = rep;
		this.host = host;

	}

	/** Refresh function */
	public void refreshAction() {
		//--- For each PRIMITIVE ---
		Iterator itField = primitiveContainers.iterator();
		Iterator itName = primitiveNames.iterator();
		while (itField.hasNext()) {
			//--- Get the textField ---
			JTextField textField = (JTextField)itField.next();
			//--- Get the name of the field ---
			String name = (String)itName.next();
			String getter = Naming.getGetter(name);
			//--- Refresh the textField ---
			String strValue = "-1";
			try {
				Method method = host.getClass().getMethod(getter, new Class[] {});
				Object value = method.invoke(host, new Object[] {});
				strValue = value == null ? "null" : value.toString();
			} catch (Exception e) {
			}
			textField.setText(strValue);
		}
		//--- For each COLLECTION ---
		Iterator itList = collectionContainers.iterator();
		itName = collectionNames.iterator();
		while (itList.hasNext()) {
			//--- Get the list ---
			JList list = (JList)itList.next();
			//--- Get the name of the field ---
			String name = (String)itName.next();
			String getter = Naming.getGetter(name);
			//--- Refresh the list ---
			Vector colValue = new Vector();
			try {
				Method method = host.getClass().getMethod(getter, new Class[] {});
				Object value = method.invoke(host, new Object[] {});
				Collection col = (java.util.Collection)value;
				Iterator it = col.iterator();
				while (it.hasNext()) {;
					colValue.add(it.next());
				};
			} catch (Exception e) {
			}
			list.setListData(colValue);
		}
		//--- For each USER ---
		itList = userContainers.iterator();
		itName = userNames.iterator();
		while (itList.hasNext()) {
			//--- Get the list ---
			JList list = (JList)itList.next();
			//--- Get the name of the field ---
			String name = (String)itName.next();
			String getter = Naming.getGetter(name);
			//--- Refresh the list ---
			Vector colValue = new Vector();
			try {
				Method method = host.getClass().getMethod(getter, new Class[] {});
				Object value = method.invoke(host, new Object[] {});
				colValue.add(value);
			} catch (Exception e) {
			}
			list.setListData(colValue);
		}
		//--- For each PossibleValue ---
		itList = possibleContainers.iterator();
		Iterator itKey = keys.iterator();
		while (itList.hasNext()) {
			//--- Get the list and the key ---
			String key = (String)itKey.next();
			JList list = (JList)itList.next();
			//--- Refresh content ---
			list.setListData((Vector)rep.getInstances(key));
		}
	}
}
