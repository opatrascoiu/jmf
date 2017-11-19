/**
 *
 *  Class Documentation$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.wsdl;

public class Documentation$Class
implements
	Documentation,
    ws.WsVisitable
{
	/** Default constructor */
	public Documentation$Class() {
		//--- Set property 'text' from 'Documentation' ---
		this.text = null;
		//--- Set property 'wSDLElement' from 'Documentation' ---
		this.wSDLElement = null;
	}
	/** Specialized constructor */
	public Documentation$Class(String text) {
		//--- Set property 'text' from 'Documentation' ---
		this.text = text;
		//--- Set property 'wSDLElement' from 'Documentation' ---
		this.wSDLElement = null;
	}


	/** Property 'text' from 'Documentation' */
	protected String text;
	/** Get property 'text' from 'Documentation' */
		public String getText() {
		return text;
	}
	/** Set property 'text' from 'Documentation' */
		public void setText(String text) {
		this.text = text;
	}

	/** Property 'wSDLElement' from 'Documentation' */
	protected WSDLElement wSDLElement;
	/** Get property 'wSDLElement' from 'Documentation' */
	public WSDLElement getWSDLElement() {
		return wSDLElement;
	}
	/** Set property 'wSDLElement' from 'Documentation' */
	public void setWSDLElement(WSDLElement wSDLElement) { 
		this.wSDLElement = wSDLElement;
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
		String strId = "ws.wsdl.Documentation";
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
		if (wSDLElement != null)
			this.wSDLElement.setDocumentation(null);
	}

	/** Clone the object */
	public Object clone() {
		Documentation$Class obj = new Documentation$Class();
		obj.text = text==null ? null : this.text;
		obj.wSDLElement = wSDLElement==null ? null : this.wSDLElement;
		return obj;
	}

	/** Accept 'ws.wsdl.DocumentationVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}