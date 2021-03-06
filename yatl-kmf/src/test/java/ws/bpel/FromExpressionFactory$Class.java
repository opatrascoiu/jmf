/**
 *
 *  Class FromExpressionFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:38
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public class FromExpressionFactory$Class
	extends ws.WsFactory$Class
	implements FromExpressionFactory
{
	/** Default factory constructor */
	public FromExpressionFactory$Class() {
	}
	public FromExpressionFactory$Class(ws.repository.WsRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		FromExpression obj = new FromExpression$Class();
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.FromExpression", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String expression) {
		FromExpression obj = new FromExpression$Class(expression);
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.FromExpression", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "FromExpressionFactory";
	}

	/** Accept 'ws.bpel.FromExpressionVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
