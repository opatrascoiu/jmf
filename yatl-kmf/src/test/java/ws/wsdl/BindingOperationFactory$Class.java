/**
 *
 *  Class BindingOperationFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.wsdl;

public class BindingOperationFactory$Class
	extends ws.WsFactory$Class
	implements BindingOperationFactory
{
	/** Default factory constructor */
	public BindingOperationFactory$Class() {
	}
	public BindingOperationFactory$Class(ws.repository.WsRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		BindingOperation obj = new BindingOperation$Class();
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.wsdl.BindingOperation", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name) {
		BindingOperation obj = new BindingOperation$Class(name);
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.wsdl.BindingOperation", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "BindingOperationFactory";
	}

	/** Accept 'ws.wsdl.BindingOperationVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}