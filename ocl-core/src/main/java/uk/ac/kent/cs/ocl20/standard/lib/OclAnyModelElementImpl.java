package uk.ac.kent.cs.ocl20.standard.lib;

import java.lang.reflect.Method;

/**
 * @author dha
 *
 */
public class OclAnyModelElementImpl 
	extends OclAnyImpl 
	implements OclAnyModelElement 
{
	protected java.lang.Object modelElement;
	public OclAnyModelElementImpl(java.lang.Object modelElem, StdLibAdapter adapter) {
		super(adapter);
		this.modelElement = modelElem;
	}

	public Object clone() {
		try {
			Method m = modelElement.getClass().getMethod("clone",new Class[]{});
			return m.invoke(modelElement, new Object[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean equals(Object o) {
		if (o instanceof OclAnyModelElement)
			return modelElement.equals( ((OclAnyModelElement)o).getImpl() );
		return false;
	}

	public Object getImpl() {
		return modelElement;
	}

	public void setImpl(Object impl) {
		modelElement = impl;
	}

	public String toString() {
		return modelElement.toString();
	}

}
