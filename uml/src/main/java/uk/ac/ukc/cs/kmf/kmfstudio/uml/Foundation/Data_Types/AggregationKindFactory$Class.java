/**
 *
 *  Class AggregationKindFactory$Class.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:44
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types;

public class AggregationKindFactory$Class
	extends uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class
	implements AggregationKindFactory
{
	/** Default factory constructor */
	public AggregationKindFactory$Class() {
	}
	public AggregationKindFactory$Class(uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		AggregationKind obj = new AggregationKind$Class();
		obj.setId(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class.newId());
		repository.addElement("uml.Foundation.Data_Types.AggregationKind", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "AggregationKindFactory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.AggregationKindVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
