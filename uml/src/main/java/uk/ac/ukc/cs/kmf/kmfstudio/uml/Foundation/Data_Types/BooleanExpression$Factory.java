/**
 *
 *  Class BooleanExpression$Factory.java
 *
 *  Generated by KMFStudio at 29 July 2003 10:22:57
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitable;

public interface BooleanExpression$Factory
extends
    UmlFactory,
    UmlVisitable
{
	/** Default builder */
	public Object build();
	/** Specialized builder */
	public Object build(Name language, String body);
}
