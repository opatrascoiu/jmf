/**
 *
 *  Class OclExpression.java
 *
 *  Generated by KMFStudio at 09 May 2003 17:47:39
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.semantics.model.expressions;

import uk.ac.kent.cs.ocl20.semantics.SemanticsElement;

public interface OclExpression
extends
    SemanticsElement
{

	/** Get the 'isMarkedPre' from 'OclExpression' */
	public Boolean getIsMarkedPre();
	/** Set the 'isMarkedPre' from 'OclExpression' */
	public void setIsMarkedPre(Boolean isMarkedPre);

	/** Get the 'appliedProperty' from 'OclExpression' */
	public CallExp getAppliedProperty();
	/** Set the 'appliedProperty' from 'OclExpression' */
	public void setAppliedProperty(CallExp appliedProperty);

	/** Get the 'operationCallExp' from 'OclExpression' */
	public OperationCallExp getOperationCallExp();
	/** Set the 'operationCallExp' from 'OclExpression' */
	public void setOperationCallExp(OperationCallExp operationCallExp);

	/** Get the 'initialisedVariable' from 'OclExpression' */
	public VariableDeclaration getInitialisedVariable();
	/** Set the 'initialisedVariable' from 'OclExpression' */
	public void setInitialisedVariable(VariableDeclaration initialisedVariable);

	/** Get the 'type' from 'OclExpression' */
	public uk.ac.kent.cs.ocl20.semantics.bridge.Classifier getType();
	/** Set the 'type' from 'OclExpression' */
	public void setType(uk.ac.kent.cs.ocl20.semantics.bridge.Classifier type);

	/** Get the 'propertyCallExp' from 'OclExpression' */
	public PropertyCallExp getPropertyCallExp();
	/** Set the 'propertyCallExp' from 'OclExpression' */
	public void setPropertyCallExp(PropertyCallExp propertyCallExp);

	/** Get the 'loopExp' from 'OclExpression' */
	public LoopExp getLoopExp();
	/** Set the 'loopExp' from 'OclExpression' */
	public void setLoopExp(LoopExp loopExp);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
