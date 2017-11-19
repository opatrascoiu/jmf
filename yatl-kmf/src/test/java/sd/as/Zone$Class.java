/**
 *
 *  Class Zone$Class.java
 *
 *  Generated by KMFStudio at 18 February 2004 20:38:45
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package sd.as;

public class Zone$Class
implements
	Zone,
    sd.SdVisitable
{
	/** Default constructor */
	public Zone$Class() {
		//--- Set property 'excludingContours' from 'Zone' ---
		this.excludingContours = new java.util.Vector();
		//--- Set property 'containingContours' from 'Zone' ---
		this.containingContours = new java.util.Vector();
		//--- Set property 'spider' from 'Zone' ---
		this.spider = new java.util.Vector();
		//--- Set property 'unitaryDiagrams' from 'Zone' ---
		this.unitaryDiagrams = new java.util.Vector();
		//--- Set property 'shadedDiagrams' from 'Zone' ---
		this.shadedDiagrams = new java.util.Vector();
		//--- Set property 'allContours' from 'Zone' ---
		this.allContours = new java.util.Vector();
	}


	/** Property 'excludingContours' from 'Zone' */
	protected java.util.List excludingContours;
	/** Get property 'excludingContours' from 'Zone' */
	public java.util.List getExcludingContours() {
		return excludingContours;
	}
	/** Set property 'excludingContours' from 'Zone' */
	public void setExcludingContours(java.util.List excludingContours) { 
		this.excludingContours = excludingContours;
	}

	/** Property 'containingContours' from 'Zone' */
	protected java.util.List containingContours;
	/** Get property 'containingContours' from 'Zone' */
	public java.util.List getContainingContours() {
		return containingContours;
	}
	/** Set property 'containingContours' from 'Zone' */
	public void setContainingContours(java.util.List containingContours) { 
		this.containingContours = containingContours;
	}

	/** Property 'spider' from 'Zone' */
	protected java.util.List spider;
	/** Get property 'spider' from 'Zone' */
	public java.util.List getSpider() {
		return spider;
	}
	/** Set property 'spider' from 'Zone' */
	public void setSpider(java.util.List spider) { 
		this.spider = spider;
	}

	/** Property 'unitaryDiagrams' from 'Zone' */
	protected java.util.List unitaryDiagrams;
	/** Get property 'unitaryDiagrams' from 'Zone' */
	public java.util.List getUnitaryDiagrams() {
		return unitaryDiagrams;
	}
	/** Set property 'unitaryDiagrams' from 'Zone' */
	public void setUnitaryDiagrams(java.util.List unitaryDiagrams) { 
		this.unitaryDiagrams = unitaryDiagrams;
	}

	/** Property 'shadedDiagrams' from 'Zone' */
	protected java.util.List shadedDiagrams;
	/** Get property 'shadedDiagrams' from 'Zone' */
	public java.util.List getShadedDiagrams() {
		return shadedDiagrams;
	}
	/** Set property 'shadedDiagrams' from 'Zone' */
	public void setShadedDiagrams(java.util.List shadedDiagrams) { 
		this.shadedDiagrams = shadedDiagrams;
	}

	/** Property 'allContours' from 'Zone' */
	protected java.util.List allContours;
	/** Get property 'allContours' from 'Zone' */
	public java.util.List getAllContours() {
		return allContours;
	}
	/** Set property 'allContours' from 'Zone' */
	public void setAllContours(java.util.List allContours) { 
		this.allContours = allContours;
	}

	/** The id */
	protected String id;
	/** Get the id */
	public String getId() {
		return id;
	}
	/** Set the id */
	public void setId(String id) {
		this.id = id;
	}

	/** Override toString */
	public String toString() {
		String strId = "sd.as.Zone";
		String name = null;
		try {
			java.lang.Class cls = this.getClass();
			java.lang.reflect.Method method = cls.getMethod("getName", new java.lang.Class[] {});
			name = (String) method.invoke(this, new Object[] {});
			if (name != null && name.length()==0) name = null;
		} catch (Exception e) {
		}
		if (name == null)
			return strId+" 'id-"+getId()+"'";
		else
			return strId+" '"+name+"-"+getId()+"'";
	}

	/** Delete the object */
	public void delete() {
		java.util.Iterator excludingContoursIt = this.excludingContours.iterator();
		while (excludingContoursIt.hasNext()) {
			sd.as.Contour excludingContoursObj = (sd.as.Contour)excludingContoursIt.next();
			excludingContoursObj.getExcludingContourZones().remove(this);
			excludingContoursObj.getExcludingContourZones().remove(this);
		}
		java.util.Iterator containingContoursIt = this.containingContours.iterator();
		while (containingContoursIt.hasNext()) {
			sd.as.Contour containingContoursObj = (sd.as.Contour)containingContoursIt.next();
			containingContoursObj.getContainingContourZones().remove(this);
			containingContoursObj.getContainingContourZones().remove(this);
		}
		java.util.Iterator spiderIt = this.spider.iterator();
		while (spiderIt.hasNext()) {
			sd.as.Spider spiderObj = (sd.as.Spider)spiderIt.next();
			spiderObj.getHabitat().remove(this);
			spiderObj.getHabitat().remove(this);
		}
		java.util.Iterator unitaryDiagramsIt = this.unitaryDiagrams.iterator();
		while (unitaryDiagramsIt.hasNext()) {
			sd.as.UnitaryDiagram unitaryDiagramsObj = (sd.as.UnitaryDiagram)unitaryDiagramsIt.next();
			unitaryDiagramsObj.getZones().remove(this);
			unitaryDiagramsObj.getZones().remove(this);
		}
		java.util.Iterator shadedDiagramsIt = this.shadedDiagrams.iterator();
		while (shadedDiagramsIt.hasNext()) {
			sd.as.UnitaryDiagram shadedDiagramsObj = (sd.as.UnitaryDiagram)shadedDiagramsIt.next();
			shadedDiagramsObj.getShadedZones().remove(this);
			shadedDiagramsObj.getShadedZones().remove(this);
		}
		java.util.Iterator allContoursIt = this.allContours.iterator();
		while (allContoursIt.hasNext()) {
			sd.as.Contour allContoursObj = (sd.as.Contour)allContoursIt.next();
			allContoursObj.getAllContourZone().remove(this);
			allContoursObj.getAllContourZone().remove(this);
		}
	}

	/** Clone the object */
	public Object clone() {
		Zone$Class obj = new Zone$Class();
		obj.excludingContours = excludingContours==null ? null : (java.util.List)((java.util.Vector)this.excludingContours).clone();
		obj.containingContours = containingContours==null ? null : (java.util.List)((java.util.Vector)this.containingContours).clone();
		obj.spider = spider==null ? null : (java.util.List)((java.util.Vector)this.spider).clone();
		obj.unitaryDiagrams = unitaryDiagrams==null ? null : (java.util.List)((java.util.Vector)this.unitaryDiagrams).clone();
		obj.shadedDiagrams = shadedDiagrams==null ? null : (java.util.List)((java.util.Vector)this.shadedDiagrams).clone();
		obj.allContours = allContours==null ? null : (java.util.List)((java.util.Vector)this.allContours).clone();
		return obj;
	}

	/** Accept 'sd.as.ZoneVisitor' */
	public Object accept(sd.SdVisitor v, Object data) {
		return v.visit(this, data);
	}
}