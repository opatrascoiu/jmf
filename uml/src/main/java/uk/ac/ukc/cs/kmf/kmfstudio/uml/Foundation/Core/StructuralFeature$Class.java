/**
 *
 *  Class StructuralFeature$Class.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:45
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core;

public class StructuralFeature$Class
extends
	Feature$Class
implements
	StructuralFeature,
    uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitable
{
	/** Default constructor */
	public StructuralFeature$Class() {
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
		//--- Set property 'ownerScope' from 'Feature' ---
		this.ownerScope = null;
		//--- Set property 'owner' from 'Feature' ---
		this.owner = null;
		//--- Set property 'multiplicity' from 'StructuralFeature' ---
		this.multiplicity = null;
		//--- Set property 'changeability' from 'StructuralFeature' ---
		this.changeability = null;
		//--- Set property 'targetScope' from 'StructuralFeature' ---
		this.targetScope = null;
		//--- Set property 'ordering' from 'StructuralFeature' ---
		this.ordering = null;
		//--- Set property 'type' from 'StructuralFeature' ---
		this.type = null;
	}
	/** Specialized constructor */
	public StructuralFeature$Class(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name name, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.VisibilityKind visibility, Boolean isSpecification, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ScopeKind ownerScope, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Multiplicity multiplicity, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ChangeabilityKind changeability, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ScopeKind targetScope, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.OrderingKind ordering) {
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
		//--- Set property 'ownerScope' from 'Feature' ---
		this.ownerScope = ownerScope;
		//--- Set property 'owner' from 'Feature' ---
		this.owner = null;
		//--- Set property 'multiplicity' from 'StructuralFeature' ---
		this.multiplicity = multiplicity;
		//--- Set property 'changeability' from 'StructuralFeature' ---
		this.changeability = changeability;
		//--- Set property 'targetScope' from 'StructuralFeature' ---
		this.targetScope = targetScope;
		//--- Set property 'ordering' from 'StructuralFeature' ---
		this.ordering = ordering;
		//--- Set property 'type' from 'StructuralFeature' ---
		this.type = null;
	}


	/** Property 'multiplicity' from 'StructuralFeature' */
	protected uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Multiplicity multiplicity;
	/** Get property 'multiplicity' from 'StructuralFeature' */
		public uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Multiplicity getMultiplicity() {
		return multiplicity;
	}
	/** Set property 'multiplicity' from 'StructuralFeature' */
		public void setMultiplicity(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Multiplicity multiplicity) {
		this.multiplicity = multiplicity;
	}

	/** Property 'changeability' from 'StructuralFeature' */
	protected uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ChangeabilityKind changeability;
	/** Get property 'changeability' from 'StructuralFeature' */
		public uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ChangeabilityKind getChangeability() {
		return changeability;
	}
	/** Set property 'changeability' from 'StructuralFeature' */
		public void setChangeability(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ChangeabilityKind changeability) {
		this.changeability = changeability;
	}

	/** Property 'targetScope' from 'StructuralFeature' */
	protected uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ScopeKind targetScope;
	/** Get property 'targetScope' from 'StructuralFeature' */
		public uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ScopeKind getTargetScope() {
		return targetScope;
	}
	/** Set property 'targetScope' from 'StructuralFeature' */
		public void setTargetScope(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ScopeKind targetScope) {
		this.targetScope = targetScope;
	}

	/** Property 'ordering' from 'StructuralFeature' */
	protected uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.OrderingKind ordering;
	/** Get property 'ordering' from 'StructuralFeature' */
		public uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.OrderingKind getOrdering() {
		return ordering;
	}
	/** Set property 'ordering' from 'StructuralFeature' */
		public void setOrdering(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.OrderingKind ordering) {
		this.ordering = ordering;
	}

	/** Property 'type' from 'StructuralFeature' */
	protected Classifier type;
	/** Get property 'type' from 'StructuralFeature' */
	public Classifier getType() {
		return type;
	}
	/** Set property 'type' from 'StructuralFeature' */
	public void setType(Classifier type) { 
		this.type = type;
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
		String strId = "uml.Foundation.Core.StructuralFeature";
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
		if (owner != null)
			this.owner.getFeature().remove(this);
		if (type != null)
			this.type.getTypedFeature().remove(this);
	}

	/** Clone the object */
	public Object clone() {
		StructuralFeature$Class obj = new StructuralFeature$Class();
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
		obj.ownerScope = ownerScope==null ? null : (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ScopeKind)this.ownerScope.clone();
		obj.owner = owner==null ? null : this.owner;
		obj.multiplicity = multiplicity==null ? null : (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Multiplicity)this.multiplicity.clone();
		obj.changeability = changeability==null ? null : (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ChangeabilityKind)this.changeability.clone();
		obj.targetScope = targetScope==null ? null : (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ScopeKind)this.targetScope.clone();
		obj.ordering = ordering==null ? null : (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.OrderingKind)this.ordering.clone();
		obj.type = type==null ? null : this.type;
		return obj;
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.StructuralFeatureVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
