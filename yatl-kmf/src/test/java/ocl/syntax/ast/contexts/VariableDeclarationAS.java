/**
 *
 *  Class VariableDeclarationAS.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:43
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.contexts;

public interface VariableDeclarationAS
extends
    ocl.syntax.SyntaxElement
{

	/** Get the 'name' from 'VariableDeclarationAS' */
	public String getName();
	/** Set the 'name' from 'VariableDeclarationAS' */
	public void setName(String name);

	/** Get the 'operationAS' from 'VariableDeclarationAS' */
	public OperationAS getOperationAS();
	/** Set the 'operationAS' from 'VariableDeclarationAS' */
	public void setOperationAS(OperationAS operationAS);

	/** Get the 'constraintAS' from 'VariableDeclarationAS' */
	public ConstraintAS getConstraintAS();
	/** Set the 'constraintAS' from 'VariableDeclarationAS' */
	public void setConstraintAS(ConstraintAS constraintAS);

	/** Get the 'initExp' from 'VariableDeclarationAS' */
	public ocl.syntax.ast.expressions.OclExpressionAS getInitExp();
	/** Set the 'initExp' from 'VariableDeclarationAS' */
	public void setInitExp(ocl.syntax.ast.expressions.OclExpressionAS initExp);

	/** Get the 'type' from 'VariableDeclarationAS' */
	public ocl.syntax.ast.types.TypeAS getType();
	/** Set the 'type' from 'VariableDeclarationAS' */
	public void setType(ocl.syntax.ast.types.TypeAS type);

	/** Get the 'tupleLiteralExpAS' from 'VariableDeclarationAS' */
	public ocl.syntax.ast.expressions.TupleLiteralExpAS getTupleLiteralExpAS();
	/** Set the 'tupleLiteralExpAS' from 'VariableDeclarationAS' */
	public void setTupleLiteralExpAS(ocl.syntax.ast.expressions.TupleLiteralExpAS tupleLiteralExpAS);

	/** Get the 'letExpAS' from 'VariableDeclarationAS' */
	public ocl.syntax.ast.expressions.LetExpAS getLetExpAS();
	/** Set the 'letExpAS' from 'VariableDeclarationAS' */
	public void setLetExpAS(ocl.syntax.ast.expressions.LetExpAS letExpAS);

	/** Get the 'iteratorLoop' from 'VariableDeclarationAS' */
	public ocl.syntax.ast.expressions.LoopExpAS getIteratorLoop();
	/** Set the 'iteratorLoop' from 'VariableDeclarationAS' */
	public void setIteratorLoop(ocl.syntax.ast.expressions.LoopExpAS iteratorLoop);

	/** Get the 'resultLoop' from 'VariableDeclarationAS' */
	public ocl.syntax.ast.expressions.LoopExpAS getResultLoop();
	/** Set the 'resultLoop' from 'VariableDeclarationAS' */
	public void setResultLoop(ocl.syntax.ast.expressions.LoopExpAS resultLoop);

	/** Get the 'variableExpAS' from 'VariableDeclarationAS' */
	public ocl.syntax.ast.expressions.VariableExpAS getVariableExpAS();
	/** Set the 'variableExpAS' from 'VariableDeclarationAS' */
	public void setVariableExpAS(ocl.syntax.ast.expressions.VariableExpAS variableExpAS);

	/** Get the 'tupleTypeAS' from 'VariableDeclarationAS' */
	public ocl.syntax.ast.types.TupleTypeAS getTupleTypeAS();
	/** Set the 'tupleTypeAS' from 'VariableDeclarationAS' */
	public void setTupleTypeAS(ocl.syntax.ast.types.TupleTypeAS tupleTypeAS);

	/** Get the id */
	public String getId();
	/** Set the id */
	public void setId(String id);

	/** Override the toString */
	public String toString();

	/** Delete the object */
	public void delete();

	/** Clone the object */
	public Object clone();
}