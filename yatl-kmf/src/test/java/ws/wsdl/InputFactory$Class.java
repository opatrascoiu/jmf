/**
 *
 *  Class InputFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.wsdl;

public class InputFactory$Class
	extends ws.WsFactory$Class
	implements InputFactory
{
	/** Default factory constructor */
	public InputFactory$Class() {
	}
	public InputFactory$Class(ws.repository.WsRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Input obj = new Input$Class();
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.wsdl.Input", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name, ws.wsdl.Message message) {
		Input obj = new Input$Class(name, message);
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.wsdl.Input", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "InputFactory";
	}

	/** Accept 'ws.wsdl.InputVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}