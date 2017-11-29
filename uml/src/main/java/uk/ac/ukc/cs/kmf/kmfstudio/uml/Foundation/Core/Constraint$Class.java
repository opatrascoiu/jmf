/**
 *
 *  Class Constraint$Class.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:46
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core;

public class Constraint$Class
extends
	ModelElement$Class
implements
	Constraint,
    uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitable
{
	/** Default constructor */
	public Constraint$Class() {
		//--- Set property 'name' from 'ModelElement' ---
		this.name = null;
		//--- Set property 'visibility' from 'ModelElement' ---
		this.visibility = null;
		//--- Set property 'isSpecification' from 'ModelElement' ---
		this.isSpecification = new Boolean(false);
		//--- Set property 'clientDependency' from 'ModelElement' ---
		this.clientDependency = new java.util.LinkedHashSet();
		//--- Set property 'supplierDependency' from 'ModelElement' ---
		this.supplierDependency = new java.util.LinkedHashSet();
		//--- Set property 'implementationLocation' from 'ModelElement' ---
		this.implementationLocation = new java.util.LinkedHashSet();
		//--- Set property 'binding' from 'ModelElement' ---
		this.binding = null;
		//--- Set property 'package_' from 'ModelElement' ---
		this.package_ = null;
		//--- Set property 'behavior' from 'ModelElement' ---
		this.behavior = new java.util.LinkedHashSet();
		//--- Set property 'taggedValue' from 'ModelElement' ---
		this.taggedValue = new java.util.LinkedHashSet();
		//--- Set property 'referenceTag' from 'ModelElement' ---
		this.referenceTag = new java.util.LinkedHashSet();
		//--- Set property 'stereotype' from 'ModelElement' ---
		this.stereotype = new java.util.LinkedHashSet();
		//--- Set property 'constraint' from 'ModelElement' ---
		this.constraint = new java.util.LinkedHashSet();
		//--- Set property 'namespace_' from 'ModelElement' ---
		this.namespace_ = null;
		//--- Set property 'targetFlow' from 'ModelElement' ---
		this.targetFlow = new java.util.LinkedHashSet();
		//--- Set property 'sourceFlow' from 'ModelElement' ---
		this.sourceFlow = new java.util.LinkedHashSet();
		//--- Set property 'comment' from 'ModelElement' ---
		this.comment = new java.util.LinkedHashSet();
		//--- Set property 'presentation' from 'ModelElement' ---
		this.presentation = new java.util.LinkedHashSet();
		//--- Set property 'tie' from 'ModelElement' ---
		this.tie = new java.util.LinkedHashSet();
		//--- Set property 'templateParameter' from 'ModelElement' ---
		this.templateParameter = null;
		//--- Set property 'templateParameters' from 'ModelElement' ---
		this.templateParameters = new java.util.LinkedHashSet();
		//--- Set property 'body' from 'Constraint' ---
		this.body = null;
		//--- Set property 'constrainedStereotye' from 'Constraint' ---
		this.constrainedStereotye = null;
		//--- Set property 'constrainedElement' from 'Constraint' ---
		this.constrainedElement = new java.util.Vector();
	}
	/** Specialized constructor */
	public Constraint$Class(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name name, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.VisibilityKind visibility, Boolean isSpecification, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.BooleanExpression body) {
		//--- Set property 'name' from 'ModelElement' ---
		this.name = name;
		//--- Set property 'visibility' from 'ModelElement' ---
		this.visibility = visibility;
		//--- Set property 'isSpecification' from 'ModelElement' ---
		this.isSpecification = isSpecification;
		//--- Set property 'clientDependency' from 'ModelElement' ---
		this.clientDependency = new java.util.LinkedHashSet();
		//--- Set property 'supplierDependency' from 'ModelElement' ---
		this.supplierDependency = new java.util.LinkedHashSet();
		//--- Set property 'implementationLocation' from 'ModelElement' ---
		this.implementationLocation = new java.util.LinkedHashSet();
		//--- Set property 'binding' from 'ModelElement' ---
		this.binding = null;
		//--- Set property 'package_' from 'ModelElement' ---
		this.package_ = null;
		//--- Set property 'behavior' from 'ModelElement' ---
		this.behavior = new java.util.LinkedHashSet();
		//--- Set property 'taggedValue' from 'ModelElement' ---
		this.taggedValue = new java.util.LinkedHashSet();
		//--- Set property 'referenceTag' from 'ModelElement' ---
		this.referenceTag = new java.util.LinkedHashSet();
		//--- Set property 'stereotype' from 'ModelElement' ---
		this.stereotype = new java.util.LinkedHashSet();
		//--- Set property 'constraint' from 'ModelElement' ---
		this.constraint = new java.util.LinkedHashSet();
		//--- Set property 'namespace_' from 'ModelElement' ---
		this.namespace_ = null;
		//--- Set property 'targetFlow' from 'ModelElement' ---
		this.targetFlow = new java.util.LinkedHashSet();
		//--- Set property 'sourceFlow' from 'ModelElement' ---
		this.sourceFlow = new java.util.LinkedHashSet();
		//--- Set property 'comment' from 'ModelElement' ---
		this.comment = new java.util.LinkedHashSet();
		//--- Set property 'presentation' from 'ModelElement' ---
		this.presentation = new java.util.LinkedHashSet();
		//--- Set property 'tie' from 'ModelElement' ---
		this.tie = new java.util.LinkedHashSet();
		//--- Set property 'templateParameter' from 'ModelElement' ---
		this.templateParameter = null;
		//--- Set property 'templateParameters' from 'ModelElement' ---
		this.templateParameters = new java.util.LinkedHashSet();
		//--- Set property 'body' from 'Constraint' ---
		this.body = body;
		//--- Set property 'constrainedStereotye' from 'Constraint' ---
		this.constrainedStereotye = null;
		//--- Set property 'constrainedElement' from 'Constraint' ---
		this.constrainedElement = new java.util.Vector();
	}


	/** Property 'body' from 'Constraint' */
	protected uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.BooleanExpression body;
	/** Get property 'body' from 'Constraint' */
		public uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.BooleanExpression getBody() {
		return body;
	}
	/** Set property 'body' from 'Constraint' */
		public void setBody(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.BooleanExpression body) {
		this.body = body;
	}

	/** Property 'constrainedStereotye' from 'Constraint' */
	protected uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Extension_Mechanisms.Stereotype constrainedStereotye;
	/** Get property 'constrainedStereotye' from 'Constraint' */
	public uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Extension_Mechanisms.Stereotype getConstrainedStereotye() {
		return constrainedStereotye;
	}
	/** Set property 'constrainedStereotye' from 'Constraint' */
	public void setConstrainedStereotye(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Extension_Mechanisms.Stereotype constrainedStereotye) { 
		this.constrainedStereotye = constrainedStereotye;
	}

	/** Property 'constrainedElement' from 'Constraint' */
	protected java.util.List constrainedElement;
	/** Get property 'constrainedElement' from 'Constraint' */
	public java.util.List getConstrainedElement() {
		return constrainedElement;
	}
	/** Set property 'constrainedElement' from 'Constraint' */
	public void setConstrainedElement(java.util.List constrainedElement) { 
		this.constrainedElement = constrainedElement;
	}

	/** The id */
	protected String id;
	/** Get the id */
	public String getId() {
		return id;
	}
	/** Set the id */
	public void setId(String id) {
		this.id = id;
	}

	/** Override toString */
	public String toString() {
		String strId = "uml.Foundation.Core.Constraint";
		String name = null;
		try {
			java.lang.Class cls = this.getClass();
			java.lang.reflect.Method method = cls.getMethod("getName", new java.lang.Class[] {});
			name = (String) method.invoke(this, new Object[] {});
			if (name != null && name.length()==0) name = null;
		} catch (Exception e) {
		}
		if (name == null)
			return strId+" 'id-"+getId()+"'";
		else
			return strId+" '"+name+"-"+getId()+"'";
	}

	/** Delete the object */
	public void delete() {
		java.util.Iterator clientDependencyIt = this.clientDependency.iterator();
		while (clientDependencyIt.hasNext()) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Dependency clientDependencyObj = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Dependency)clientDependencyIt.next();
			clientDependencyObj.getClient().remove(this);
			clientDependencyObj.getClient().remove(this);
		}
		java.util.Iterator supplierDependencyIt = this.supplierDependency.iterator();
		while (supplierDependencyIt.hasNext()) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Dependency supplierDependencyObj = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Dependency)supplierDependencyIt.next();
			supplierDependencyObj.getSupplier().remove(this);
			supplierDependencyObj.getSupplier().remove(this);
		}
		java.util.Iterator implementationLocationIt = this.implementationLocation.iterator();
		while (implementationLocationIt.hasNext()) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Component implementationLocationObj = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Component)implementationLocationIt.next();
			implementationLocationObj.getResident().remove(this);
			implementationLocationObj.getResident().remove(this);
		}
		if (binding != null)
			this.binding.getArgument().remove(this);
		if (package_ != null)
			this.package_.getImportedElement().remove(this);
		java.util.Iterator behaviorIt = this.behavior.iterator();
		while (behaviorIt.hasNext()) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Behavioral_Elements.State_Machines.StateMachine behaviorObj = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Behavioral_Elements.State_Machines.StateMachine)behaviorIt.next();
			if (behaviorObj != null)
				behaviorObj.setContext(null);
		}
		java.util.Iterator taggedValueIt = this.taggedValue.iterator();
		while (taggedValueIt.hasNext()) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Extension_Mechanisms.TaggedValue taggedValueObj = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Extension_Mechanisms.TaggedValue)taggedValueIt.next();
			if (taggedValueObj != null)
				taggedValueObj.setModelElement(null);
		}
		java.util.Iterator referenceTagIt = this.referenceTag.iterator();
		while (referenceTagIt.hasNext()) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Extension_Mechanisms.TaggedValue referenceTagObj = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Extension_Mechanisms.TaggedValue)referenceTagIt.next();
			referenceTagObj.getReferenceValue().remove(this);
			referenceTagObj.getReferenceValue().remove(this);
		}
		java.util.Iterator stereotypeIt = this.stereotype.iterator();
		while (stereotypeIt.hasNext()) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Extension_Mechanisms.Stereotype stereotypeObj = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Extension_Mechanisms.Stereotype)stereotypeIt.next();
			stereotypeObj.getExtendedElement().remove(this);
			stereotypeObj.getExtendedElement().remove(this);
		}
		java.util.Iterator constraintIt = this.constraint.iterator();
		while (constraintIt.hasNext()) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Constraint constraintObj = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Constraint)constraintIt.next();
			constraintObj.getConstrainedElement().remove(this);
			constraintObj.getConstrainedElement().remove(this);
		}
		if (namespace_ != null)
			this.namespace_.getOwnedElement().remove(this);
		java.util.Iterator targetFlowIt = this.targetFlow.iterator();
		while (targetFlowIt.hasNext()) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Flow targetFlowObj = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Flow)targetFlowIt.next();
			targetFlowObj.getTarget().remove(this);
			targetFlowObj.getTarget().remove(this);
		}
		java.util.Iterator sourceFlowIt = this.sourceFlow.iterator();
		while (sourceFlowIt.hasNext()) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Flow sourceFlowObj = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Flow)sourceFlowIt.next();
			sourceFlowObj.getSource().remove(this);
			sourceFlowObj.getSource().remove(this);
		}
		java.util.Iterator commentIt = this.comment.iterator();
		while (commentIt.hasNext()) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Comment commentObj = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Comment)commentIt.next();
			commentObj.getAnnotatedElement().remove(this);
			commentObj.getAnnotatedElement().remove(this);
		}
		java.util.Iterator presentationIt = this.presentation.iterator();
		while (presentationIt.hasNext()) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.PresentationElement presentationObj = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.PresentationElement)presentationIt.next();
			presentationObj.getSubject().remove(this);
			presentationObj.getSubject().remove(this);
		}
		java.util.Iterator tieIt = this.tie.iterator();
		while (tieIt.hasNext()) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Tie tieObj = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Tie)tieIt.next();
			tieObj.getTiedElement().remove(this);
			tieObj.getTiedElement().remove(this);
		}
		if (templateParameter != null)
			this.templateParameter.getDefaultElement().remove(this);
		java.util.Iterator templateParametersIt = this.templateParameters.iterator();
		while (templateParametersIt.hasNext()) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.TemplateParameter templateParametersObj = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.TemplateParameter)templateParametersIt.next();
			if (templateParametersObj != null)
				templateParametersObj.setModelElement(null);
		}
		if (constrainedStereotye != null)
			this.constrainedStereotye.getStereotypeConstraint().remove(this);
		java.util.Iterator constrainedElementIt = this.constrainedElement.iterator();
		while (constrainedElementIt.hasNext()) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement constrainedElementObj = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement)constrainedElementIt.next();
			constrainedElementObj.getConstraint().remove(this);
			constrainedElementObj.getConstraint().remove(this);
		}
	}

	/** Clone the object */
	public Object clone() {
		Constraint$Class obj = new Constraint$Class();
		obj.name = name==null ? null : (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name)this.name.clone();
		obj.visibility = visibility==null ? null : (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.VisibilityKind)this.visibility.clone();
		obj.isSpecification = isSpecification==null ? null : this.isSpecification;
		obj.clientDependency = clientDependency==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.clientDependency).clone();
		obj.supplierDependency = supplierDependency==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.supplierDependency).clone();
		obj.implementationLocation = implementationLocation==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.implementationLocation).clone();
		obj.binding = binding==null ? null : this.binding;
		obj.package_ = package_==null ? null : this.package_;
		obj.behavior = behavior==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.behavior).clone();
		obj.taggedValue = taggedValue==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.taggedValue).clone();
		obj.referenceTag = referenceTag==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.referenceTag).clone();
		obj.stereotype = stereotype==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.stereotype).clone();
		obj.constraint = constraint==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.constraint).clone();
		obj.namespace_ = namespace_==null ? null : this.namespace_;
		obj.targetFlow = targetFlow==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.targetFlow).clone();
		obj.sourceFlow = sourceFlow==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.sourceFlow).clone();
		obj.comment = comment==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.comment).clone();
		obj.presentation = presentation==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.presentation).clone();
		obj.tie = tie==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.tie).clone();
		obj.templateParameter = templateParameter==null ? null : this.templateParameter;
		obj.templateParameters = templateParameters==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.templateParameters).clone();
		obj.body = body==null ? null : (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.BooleanExpression)this.body.clone();
		obj.constrainedStereotye = constrainedStereotye==null ? null : this.constrainedStereotye;
		obj.constrainedElement = constrainedElement==null ? null : (java.util.List)((java.util.Vector)this.constrainedElement).clone();
		return obj;
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ConstraintVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}