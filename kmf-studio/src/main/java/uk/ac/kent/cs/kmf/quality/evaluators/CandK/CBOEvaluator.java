package uk.ac.kent.cs.kmf.quality.evaluators.CandK;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Association;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Attribute;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Feature;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Operation;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Parameter;

/**
 * @author Octavian Patrascoiu
 *
 */
public class CBOEvaluator 
	extends Evaluator 
{
	public CBOEvaluator(QualityModel qModel) {
		super(qModel);
	}
	
	public Metric evaluate(Object elem, Metric m) {
		if (elem instanceof Class_) {
			return this.couplingBetweenObjectClasses((Class_) elem, m);
		}
		return null;
	}

	/** Coupling between object classes */
	public Metric couplingBetweenObjectClasses(Class_ cls, Metric m) {
		Set set = new HashSet();
		set.addAll(this.couplingThroughAttributes(cls));
		set.addAll(this.couplingThroughAssociations(cls));
		set.addAll(this.couplingThroughMethods(cls));
		double value = set.size();
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
	protected Set couplingThroughAttributes(Class_ cls) {
		Set res = new HashSet();
		Iterator i = cls.getFeature().iterator();
		while (i.hasNext()) {
			Feature f = (Feature)i.next();
			if (f instanceof Attribute) {
				Attribute attrib = (Attribute)f;
				if (attrib.getType() != null)
					res.add(context.getNaming().getFullClassifierName(attrib.getType()));
			}
		}
		return res;
	}
	protected Set couplingThroughAssociations(Class_ cls) {
		Set res = new HashSet();
		Iterator j = context.getAssociations().iterator();
		while (j.hasNext()) {
			Association assoc = (Association)j.next();
			AssociationEnd aend1 = (AssociationEnd)assoc.getConnection().get(0);
			AssociationEnd aend2 = (AssociationEnd)assoc.getConnection().get(1);
			if (aend1.getType() == cls) {
				if (aend1.getType() != null)
					res.add(context.getNaming().getFullClassifierName(aend1.getType()));
			} else {
				if (aend2.getType() != null)
					res.add(context.getNaming().getFullClassifierName(aend2.getType()));
			}
		}
		return res;
	}
	protected Set couplingThroughMethods(Class_ cls) {
		Set res = new HashSet();
		Iterator i = cls.getFeature().iterator();
		while (i.hasNext()) {
			Feature f = (Feature)i.next();
			if (f instanceof Operation) {
				Operation op = (Operation)f;
				Iterator j = op.getParameter().iterator();
				while (j.hasNext()) {
					Parameter param = (Parameter)j.next(); 
					if (param.getType() != null)
						res.add(context.getNaming().getFullClassifierName(param.getType()));
				}
			}
		}
		return res;
	}
}
