/**
 *
 *  Class Relationship$Factory.java
 *
 *  Generated by KMFStudio at 29 July 2003 10:22:55
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitable;

public interface Relationship$Factory
extends
    UmlFactory,
    UmlVisitable
{
	/** Default builder */
	public Object build();
	/** Specialized builder */
	public Object build(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name name, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.VisibilityKind visibility, Boolean isSpecification);
}
