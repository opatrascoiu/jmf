/**
 *
 *  Class SdJTreeVisitor$Class.java
 *
 *  Generated by KMFStudio at 18 February 2004 20:38:46
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package sd.repository;

import java.util.*;
import java.lang.reflect.*;
import javax.swing.tree.*;

import uk.ac.kent.cs.kmf.util.*;

public class SdJTreeVisitor$Class
	implements SdJTreeVisitor
{
	/** Visit factory for 'SdFactory' */
	public Object visit(sd.SdFactory host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		return root;
	}
	/** Visit factory for 'sd.as.Diagram' */
	public Object visit(sd.as.DiagramFactory host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		//--- Add all methods ---
		Class factoryClass = host.getClass();
		Method methods[] = factoryClass.getMethods();
		for(int i=0; i<methods.length; i++)
			if (methods[i].getName().indexOf("build")==0)
				root.add(new DefaultMutableTreeNode(methods[i], false));
		return root;
	}
	/** Visit class for 'sd.as.Diagram' */
	public Object visit(sd.as.Diagram host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		//--- Properties for Diagram ---
		//--- Property name ---
		DefaultMutableTreeNode nameNode = getNode(this, "name", host.getName(), data);
		root.add(nameNode);
		//--- Association compoundDiagram ---
		DefaultMutableTreeNode compoundDiagramNode = getNode(this, "compoundDiagram", host.getCompoundDiagram(), data);
		root.add(compoundDiagramNode);
		return root;
	}
	/** Visit factory for 'sd.as.CompoundDiagram' */
	public Object visit(sd.as.CompoundDiagramFactory host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		//--- Add all methods ---
		Class factoryClass = host.getClass();
		Method methods[] = factoryClass.getMethods();
		for(int i=0; i<methods.length; i++)
			if (methods[i].getName().indexOf("build")==0)
				root.add(new DefaultMutableTreeNode(methods[i], false));
		return root;
	}
	/** Visit class for 'sd.as.CompoundDiagram' */
	public Object visit(sd.as.CompoundDiagram host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		//--- Properties for CompoundDiagram ---
		//--- Association children ---
		DefaultMutableTreeNode childrenNode = getNode(this, "children", host.getChildren(), data);
		root.add(childrenNode);
		//--- Association unitaryParts ---
		DefaultMutableTreeNode unitaryPartsNode = getNode(this, "unitaryParts", host.getUnitaryParts(), data);
		root.add(unitaryPartsNode);
		//--- Properties for Diagram ---
		//--- Property name ---
		DefaultMutableTreeNode nameNode = getNode(this, "name", host.getName(), data);
		root.add(nameNode);
		//--- Association compoundDiagram ---
		DefaultMutableTreeNode compoundDiagramNode = getNode(this, "compoundDiagram", host.getCompoundDiagram(), data);
		root.add(compoundDiagramNode);
		return root;
	}
	/** Visit factory for 'sd.as.AndCompound' */
	public Object visit(sd.as.AndCompoundFactory host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		//--- Add all methods ---
		Class factoryClass = host.getClass();
		Method methods[] = factoryClass.getMethods();
		for(int i=0; i<methods.length; i++)
			if (methods[i].getName().indexOf("build")==0)
				root.add(new DefaultMutableTreeNode(methods[i], false));
		return root;
	}
	/** Visit class for 'sd.as.AndCompound' */
	public Object visit(sd.as.AndCompound host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		//--- Properties for AndCompound ---
		//--- Properties for CompoundDiagram ---
		//--- Association children ---
		DefaultMutableTreeNode childrenNode = getNode(this, "children", host.getChildren(), data);
		root.add(childrenNode);
		//--- Association unitaryParts ---
		DefaultMutableTreeNode unitaryPartsNode = getNode(this, "unitaryParts", host.getUnitaryParts(), data);
		root.add(unitaryPartsNode);
		//--- Properties for Diagram ---
		//--- Property name ---
		DefaultMutableTreeNode nameNode = getNode(this, "name", host.getName(), data);
		root.add(nameNode);
		//--- Association compoundDiagram ---
		DefaultMutableTreeNode compoundDiagramNode = getNode(this, "compoundDiagram", host.getCompoundDiagram(), data);
		root.add(compoundDiagramNode);
		return root;
	}
	/** Visit factory for 'sd.as.Contour' */
	public Object visit(sd.as.ContourFactory host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		//--- Add all methods ---
		Class factoryClass = host.getClass();
		Method methods[] = factoryClass.getMethods();
		for(int i=0; i<methods.length; i++)
			if (methods[i].getName().indexOf("build")==0)
				root.add(new DefaultMutableTreeNode(methods[i], false));
		return root;
	}
	/** Visit class for 'sd.as.Contour' */
	public Object visit(sd.as.Contour host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		//--- Properties for Contour ---
		//--- Property name ---
		DefaultMutableTreeNode nameNode = getNode(this, "name", host.getName(), data);
		root.add(nameNode);
		//--- Association excludingContourZones ---
		DefaultMutableTreeNode excludingContourZonesNode = getNode(this, "excludingContourZones", host.getExcludingContourZones(), data);
		root.add(excludingContourZonesNode);
		//--- Association containingContourZones ---
		DefaultMutableTreeNode containingContourZonesNode = getNode(this, "containingContourZones", host.getContainingContourZones(), data);
		root.add(containingContourZonesNode);
		//--- Association allContourZone ---
		DefaultMutableTreeNode allContourZoneNode = getNode(this, "allContourZone", host.getAllContourZone(), data);
		root.add(allContourZoneNode);
		//--- Association unitaryDiagrams ---
		DefaultMutableTreeNode unitaryDiagramsNode = getNode(this, "unitaryDiagrams", host.getUnitaryDiagrams(), data);
		root.add(unitaryDiagramsNode);
		return root;
	}
	/** Visit factory for 'sd.as.Zone' */
	public Object visit(sd.as.ZoneFactory host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		//--- Add all methods ---
		Class factoryClass = host.getClass();
		Method methods[] = factoryClass.getMethods();
		for(int i=0; i<methods.length; i++)
			if (methods[i].getName().indexOf("build")==0)
				root.add(new DefaultMutableTreeNode(methods[i], false));
		return root;
	}
	/** Visit class for 'sd.as.Zone' */
	public Object visit(sd.as.Zone host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		//--- Properties for Zone ---
		//--- Association excludingContours ---
		DefaultMutableTreeNode excludingContoursNode = getNode(this, "excludingContours", host.getExcludingContours(), data);
		root.add(excludingContoursNode);
		//--- Association containingContours ---
		DefaultMutableTreeNode containingContoursNode = getNode(this, "containingContours", host.getContainingContours(), data);
		root.add(containingContoursNode);
		//--- Association spider ---
		DefaultMutableTreeNode spiderNode = getNode(this, "spider", host.getSpider(), data);
		root.add(spiderNode);
		//--- Association unitaryDiagrams ---
		DefaultMutableTreeNode unitaryDiagramsNode = getNode(this, "unitaryDiagrams", host.getUnitaryDiagrams(), data);
		root.add(unitaryDiagramsNode);
		//--- Association shadedDiagrams ---
		DefaultMutableTreeNode shadedDiagramsNode = getNode(this, "shadedDiagrams", host.getShadedDiagrams(), data);
		root.add(shadedDiagramsNode);
		//--- Association allContours ---
		DefaultMutableTreeNode allContoursNode = getNode(this, "allContours", host.getAllContours(), data);
		root.add(allContoursNode);
		return root;
	}
	/** Visit factory for 'sd.as.UnitaryDiagram' */
	public Object visit(sd.as.UnitaryDiagramFactory host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		//--- Add all methods ---
		Class factoryClass = host.getClass();
		Method methods[] = factoryClass.getMethods();
		for(int i=0; i<methods.length; i++)
			if (methods[i].getName().indexOf("build")==0)
				root.add(new DefaultMutableTreeNode(methods[i], false));
		return root;
	}
	/** Visit class for 'sd.as.UnitaryDiagram' */
	public Object visit(sd.as.UnitaryDiagram host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		//--- Properties for UnitaryDiagram ---
		//--- Association zones ---
		DefaultMutableTreeNode zonesNode = getNode(this, "zones", host.getZones(), data);
		root.add(zonesNode);
		//--- Association shadedZones ---
		DefaultMutableTreeNode shadedZonesNode = getNode(this, "shadedZones", host.getShadedZones(), data);
		root.add(shadedZonesNode);
		//--- Association spiders ---
		DefaultMutableTreeNode spidersNode = getNode(this, "spiders", host.getSpiders(), data);
		root.add(spidersNode);
		//--- Association compoundDiagram ---
		DefaultMutableTreeNode compoundDiagramNode = getNode(this, "compoundDiagram", host.getCompoundDiagram(), data);
		root.add(compoundDiagramNode);
		//--- Association contours ---
		DefaultMutableTreeNode contoursNode = getNode(this, "contours", host.getContours(), data);
		root.add(contoursNode);
		//--- Properties for Diagram ---
		//--- Property name ---
		DefaultMutableTreeNode nameNode = getNode(this, "name", host.getName(), data);
		root.add(nameNode);
		return root;
	}
	/** Visit factory for 'sd.as.FalseDiagram' */
	public Object visit(sd.as.FalseDiagramFactory host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		//--- Add all methods ---
		Class factoryClass = host.getClass();
		Method methods[] = factoryClass.getMethods();
		for(int i=0; i<methods.length; i++)
			if (methods[i].getName().indexOf("build")==0)
				root.add(new DefaultMutableTreeNode(methods[i], false));
		return root;
	}
	/** Visit class for 'sd.as.FalseDiagram' */
	public Object visit(sd.as.FalseDiagram host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		//--- Properties for FalseDiagram ---
		//--- Properties for UnitaryDiagram ---
		//--- Association zones ---
		DefaultMutableTreeNode zonesNode = getNode(this, "zones", host.getZones(), data);
		root.add(zonesNode);
		//--- Association shadedZones ---
		DefaultMutableTreeNode shadedZonesNode = getNode(this, "shadedZones", host.getShadedZones(), data);
		root.add(shadedZonesNode);
		//--- Association spiders ---
		DefaultMutableTreeNode spidersNode = getNode(this, "spiders", host.getSpiders(), data);
		root.add(spidersNode);
		//--- Association compoundDiagram ---
		DefaultMutableTreeNode compoundDiagramNode = getNode(this, "compoundDiagram", host.getCompoundDiagram(), data);
		root.add(compoundDiagramNode);
		//--- Association contours ---
		DefaultMutableTreeNode contoursNode = getNode(this, "contours", host.getContours(), data);
		root.add(contoursNode);
		//--- Properties for Diagram ---
		//--- Property name ---
		DefaultMutableTreeNode nameNode = getNode(this, "name", host.getName(), data);
		root.add(nameNode);
		return root;
	}
	/** Visit factory for 'sd.as.OrCompound' */
	public Object visit(sd.as.OrCompoundFactory host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		//--- Add all methods ---
		Class factoryClass = host.getClass();
		Method methods[] = factoryClass.getMethods();
		for(int i=0; i<methods.length; i++)
			if (methods[i].getName().indexOf("build")==0)
				root.add(new DefaultMutableTreeNode(methods[i], false));
		return root;
	}
	/** Visit class for 'sd.as.OrCompound' */
	public Object visit(sd.as.OrCompound host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		//--- Properties for OrCompound ---
		//--- Properties for CompoundDiagram ---
		//--- Association children ---
		DefaultMutableTreeNode childrenNode = getNode(this, "children", host.getChildren(), data);
		root.add(childrenNode);
		//--- Association unitaryParts ---
		DefaultMutableTreeNode unitaryPartsNode = getNode(this, "unitaryParts", host.getUnitaryParts(), data);
		root.add(unitaryPartsNode);
		//--- Properties for Diagram ---
		//--- Property name ---
		DefaultMutableTreeNode nameNode = getNode(this, "name", host.getName(), data);
		root.add(nameNode);
		//--- Association compoundDiagram ---
		DefaultMutableTreeNode compoundDiagramNode = getNode(this, "compoundDiagram", host.getCompoundDiagram(), data);
		root.add(compoundDiagramNode);
		return root;
	}
	/** Visit factory for 'sd.as.Spider' */
	public Object visit(sd.as.SpiderFactory host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		//--- Add all methods ---
		Class factoryClass = host.getClass();
		Method methods[] = factoryClass.getMethods();
		for(int i=0; i<methods.length; i++)
			if (methods[i].getName().indexOf("build")==0)
				root.add(new DefaultMutableTreeNode(methods[i], false));
		return root;
	}
	/** Visit class for 'sd.as.Spider' */
	public Object visit(sd.as.Spider host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		//--- Properties for Spider ---
		//--- Association habitat ---
		DefaultMutableTreeNode habitatNode = getNode(this, "habitat", host.getHabitat(), data);
		root.add(habitatNode);
		//--- Association unitaryDiagram ---
		DefaultMutableTreeNode unitaryDiagramNode = getNode(this, "unitaryDiagram", host.getUnitaryDiagram(), data);
		root.add(unitaryDiagramNode);
		return root;
	}
	/** Visit factory for 'sd.as.NotCompound' */
	public Object visit(sd.as.NotCompoundFactory host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		//--- Add all methods ---
		Class factoryClass = host.getClass();
		Method methods[] = factoryClass.getMethods();
		for(int i=0; i<methods.length; i++)
			if (methods[i].getName().indexOf("build")==0)
				root.add(new DefaultMutableTreeNode(methods[i], false));
		return root;
	}
	/** Visit class for 'sd.as.NotCompound' */
	public Object visit(sd.as.NotCompound host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		//--- Properties for NotCompound ---
		//--- Properties for CompoundDiagram ---
		//--- Association children ---
		DefaultMutableTreeNode childrenNode = getNode(this, "children", host.getChildren(), data);
		root.add(childrenNode);
		//--- Association unitaryParts ---
		DefaultMutableTreeNode unitaryPartsNode = getNode(this, "unitaryParts", host.getUnitaryParts(), data);
		root.add(unitaryPartsNode);
		//--- Properties for Diagram ---
		//--- Property name ---
		DefaultMutableTreeNode nameNode = getNode(this, "name", host.getName(), data);
		root.add(nameNode);
		//--- Association compoundDiagram ---
		DefaultMutableTreeNode compoundDiagramNode = getNode(this, "compoundDiagram", host.getCompoundDiagram(), data);
		root.add(compoundDiagramNode);
		return root;
	}
	/** Visit factory for 'sd.as.TrueDiagram' */
	public Object visit(sd.as.TrueDiagramFactory host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		//--- Add all methods ---
		Class factoryClass = host.getClass();
		Method methods[] = factoryClass.getMethods();
		for(int i=0; i<methods.length; i++)
			if (methods[i].getName().indexOf("build")==0)
				root.add(new DefaultMutableTreeNode(methods[i], false));
		return root;
	}
	/** Visit class for 'sd.as.TrueDiagram' */
	public Object visit(sd.as.TrueDiagram host, Object data) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);
		//--- Properties for TrueDiagram ---
		//--- Properties for UnitaryDiagram ---
		//--- Association zones ---
		DefaultMutableTreeNode zonesNode = getNode(this, "zones", host.getZones(), data);
		root.add(zonesNode);
		//--- Association shadedZones ---
		DefaultMutableTreeNode shadedZonesNode = getNode(this, "shadedZones", host.getShadedZones(), data);
		root.add(shadedZonesNode);
		//--- Association spiders ---
		DefaultMutableTreeNode spidersNode = getNode(this, "spiders", host.getSpiders(), data);
		root.add(spidersNode);
		//--- Association compoundDiagram ---
		DefaultMutableTreeNode compoundDiagramNode = getNode(this, "compoundDiagram", host.getCompoundDiagram(), data);
		root.add(compoundDiagramNode);
		//--- Association contours ---
		DefaultMutableTreeNode contoursNode = getNode(this, "contours", host.getContours(), data);
		root.add(contoursNode);
		//--- Properties for Diagram ---
		//--- Property name ---
		DefaultMutableTreeNode nameNode = getNode(this, "name", host.getName(), data);
		root.add(nameNode);
		return root;
	}
	/** Auxiliary function used by the vistors */
	DefaultMutableTreeNode getNode(SdJTreeVisitor visitor, String name, Object obj, Object data) {
		//--- Elements visited on this branch ---
		Set visitedElements = (Set)data;
		//--- Create node ---
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(name);
		//--- Construct the value ---
		//--- Primitive types ---
		if (obj == null) {
			DefaultMutableTreeNode objNode = new DefaultMutableTreeNode("null");
			root.add(objNode);
			return root;
		}
		if (uk.ac.kent.cs.kmf.util.Type.isInstanceofPrimitiveType(obj)) {
			DefaultMutableTreeNode objNode = new DefaultMutableTreeNode(obj.toString());
			root.add(objNode);
			return root;
		}
		//--- Collection types ---
		if (uk.ac.kent.cs.kmf.util.Type.isInstanceofCollectionType(obj)) {
			Collection col = (Collection)obj;
			Iterator i = col.iterator();
			while (i.hasNext()) {
				DefaultMutableTreeNode objNode = getNode(this, "Element", i.next(), data);
				root.add((DefaultMutableTreeNode)objNode.getChildAt(0));
			}
			return root;
		}
		//--- Enumerations types ---
		String fullClassName = obj.getClass().getName();
		if (fullClassName.endsWith("Enum") || fullClassName.endsWith("Kind")) {
			//--- Get class ---
			DefaultMutableTreeNode objNode = new DefaultMutableTreeNode("Error");
			try {
				Class objClass = obj.getClass();
				Class visitorClass = Class.forName("sd.SdVisitor");
				Class dataClass = data.getClass();
				Method accept = objClass.getMethod("accept", new Class[] {visitorClass, dataClass});
				objNode = (DefaultMutableTreeNode)accept.invoke(obj, new Object[] {visitor, data});
			} catch (Exception e) {
			}
			root.add(objNode);
			return root;
		}
		//--- User types ---
		//--- Already added on the current branch: make a toSring node ---
		if (visitedElements.contains(obj)) {
			DefaultMutableTreeNode objNode = new DefaultMutableTreeNode(obj, false);
			root.add(objNode);
		//--- Was not visited previously on the current branch ---
		} else {
			// Add it
			visitedElements.add(obj);
			// Visit it
			DefaultMutableTreeNode objNode = (DefaultMutableTreeNode)((sd.SdElement)obj).accept(visitor, visitedElements);
			root.add(objNode);
		}
		return root;
	}
}
