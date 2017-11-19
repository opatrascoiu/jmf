/**
 *
 *  Class OutputFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.wsdl;

public class OutputFactory$Class
	extends ws.WsFactory$Class
	implements OutputFactory
{
	/** Default factory constructor */
	public OutputFactory$Class() {
	}
	public OutputFactory$Class(ws.repository.WsRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Output obj = new Output$Class();
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.wsdl.Output", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name, ws.wsdl.Message message) {
		Output obj = new Output$Class(name, message);
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.wsdl.Output", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "OutputFactory";
	}

	/** Accept 'ws.wsdl.OutputVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}