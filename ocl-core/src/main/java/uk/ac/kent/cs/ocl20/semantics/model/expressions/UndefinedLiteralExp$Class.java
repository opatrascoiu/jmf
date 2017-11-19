package uk.ac.kent.cs.ocl20.semantics.model.expressions;

import uk.ac.kent.cs.ocl20.semantics.SemanticsVisitor;

/**
 * @author dha
 *
 */
public class UndefinedLiteralExp$Class extends LiteralExp$Class implements UndefinedLiteralExp {
	public Object accept(SemanticsVisitor v, Object data) {
		return v.visit(this, data);
	}

}
