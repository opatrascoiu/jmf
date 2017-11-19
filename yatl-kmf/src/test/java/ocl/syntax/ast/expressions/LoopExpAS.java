/**
 *
 *  Class LoopExpAS.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:43
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public interface LoopExpAS
extends
    ocl.syntax.SyntaxElement,
    OclExpressionAS
{

	/** Get the 'name' from 'LoopExpAS' */
	public String getName();
	/** Set the 'name' from 'LoopExpAS' */
	public void setName(String name);

	/** Get the 'loopBody' from 'LoopExpAS' */
	public OclExpressionAS getLoopBody();
	/** Set the 'loopBody' from 'LoopExpAS' */
	public void setLoopBody(OclExpressionAS loopBody);

	/** Get the 'iterator' from 'LoopExpAS' */
	public ocl.syntax.ast.contexts.VariableDeclarationAS getIterator();
	/** Set the 'iterator' from 'LoopExpAS' */
	public void setIterator(ocl.syntax.ast.contexts.VariableDeclarationAS iterator);

	/** Get the 'result' from 'LoopExpAS' */
	public ocl.syntax.ast.contexts.VariableDeclarationAS getResult();
	/** Set the 'result' from 'LoopExpAS' */
	public void setResult(ocl.syntax.ast.contexts.VariableDeclarationAS result);

	/** Get the 'source' from 'LoopExpAS' */
	public OclExpressionAS getSource();
	/** Set the 'source' from 'LoopExpAS' */
	public void setSource(OclExpressionAS source);

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