package uk.ac.kent.cs.kmf.quality;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.evaluators.OclEvaluator;
import uk.ac.kent.cs.kmf.quality.evaluators.CandK.CBOEvaluator;
import uk.ac.kent.cs.kmf.quality.evaluators.CandK.DITEvaluator;
import uk.ac.kent.cs.kmf.quality.evaluators.CandK.LCOMEvaluator;
import uk.ac.kent.cs.kmf.quality.evaluators.CandK.NOCEvaluator;
import uk.ac.kent.cs.kmf.quality.evaluators.CandK.RFCEvaluator;
import uk.ac.kent.cs.kmf.quality.evaluators.CandK.WMCEvaluator;
import uk.ac.kent.cs.kmf.quality.evaluators.Li.NACEvaluator;
import uk.ac.kent.cs.kmf.quality.evaluators.Li.NDCEvaluator;
import uk.ac.kent.cs.kmf.quality.evaluators.kmf.model.*;
import uk.ac.kent.cs.kmf.quality.evaluators.kmf.namespace.*;
import uk.ac.kent.cs.kmf.quality.evaluators.kmf.classifier.*;
import uk.ac.kent.cs.kmf.quality.evaluators.kmf.operation.*;
import uk.ac.kent.cs.kmf.quality.evaluators.kmf.ocl.*;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.kent.cs.kmf.quality.metrics.MetricFactory;
import uk.ac.kent.cs.kmf.util.ILog;

/**
 * @author Octavian Patrascoiu
 *
 */
public class QualityModel {
	//
	// Constructors
	//
	public QualityModel() {
	}
	public QualityModel(Context context, ILog log) {
		this.context = context;
		this.log = log;
		// C&K
		this.evaluators.put("noc", new NOCEvaluator(this));
		this.evaluators.put("dit", new DITEvaluator(this));
		this.evaluators.put("wmc", new WMCEvaluator(this));
		this.evaluators.put("rfc", new RFCEvaluator(this));
		this.evaluators.put("cbo", new CBOEvaluator(this));
		this.evaluators.put("lcom", new LCOMEvaluator(this));
		// LI
		this.evaluators.put("nac", new NACEvaluator(this));
		this.evaluators.put("ndc", new NDCEvaluator(this));
		// OCL
		this.evaluators.put("ocl", new OclEvaluator(this));
		// KMF-Second level
		this.evaluators.put("model-main", new ModelMAINEvaluator(this));
		this.evaluators.put("model-chan", new ModelCHANEvaluator(this));
		this.evaluators.put("model-test", new ModelTESTEvaluator(this));
		this.evaluators.put("cls-main", new ClsMAINEvaluator(this));
		this.evaluators.put("cls-anal", new ClsANALEvaluator(this));
		this.evaluators.put("cls-chan", new ClsCHANEvaluator(this));
		this.evaluators.put("cls-stab", new ClsSTABEvaluator(this));
		this.evaluators.put("cls-test", new ClsTESTEvaluator(this));
		this.evaluators.put("cls-usab", new ClsUSABEvaluator(this));
		this.evaluators.put("cls-spec", new ClsSPECEvaluator(this));
		// KMF-First level
		// KMF-Model
		this.evaluators.put("model-hnt", new ModelHNTEvaluator(this));
		this.evaluators.put("model-hig", new ModelHIGEvaluator(this));
		this.evaluators.put("model-ncn", new ModelNCNEvaluator(this));
		this.evaluators.put("model-ancpn", new ModelANCPNEvaluator(this));
		this.evaluators.put("model-acc", new ModelACCEvaluator(this));
		this.evaluators.put("model-adig", new ModelADIGEvaluator(this));
		this.evaluators.put("model-amc", new ModelAMCEvaluator(this));
		this.evaluators.put("model-aocc", new ModelAOCCEvaluator(this));
		// KMF-Namespace
		this.evaluators.put("ns-ndcn", new NsNDCNEvaluator(this));
		this.evaluators.put("ns-ncn", new NsNCNEvaluator(this));
		this.evaluators.put("ns-ndcc", new NsNDCCEvaluator(this));
		this.evaluators.put("ns-ncc", new NsNCCEvaluator(this));
		this.evaluators.put("ns-dnt", new NsDNTEvaluator(this));
		// KMF-Classifier
		this.evaluators.put("cls-nlp", new ClsNLPEvaluator(this));
		this.evaluators.put("cls-np", new ClsNPEvaluator(this));
		this.evaluators.put("cls-nlo", new ClsNLOEvaluator(this));
		this.evaluators.put("cls-no", new ClsNOEvaluator(this));
		this.evaluators.put("cls-aclo", new ClsACLOEvaluator(this));
		this.evaluators.put("cls-aco", new ClsACOEvaluator(this));
		this.evaluators.put("cls-nda", new ClsNDAEvaluator(this));
		this.evaluators.put("cls-na", new ClsNAEvaluator(this));
		this.evaluators.put("cls-ndd", new ClsNDDEvaluator(this));
		this.evaluators.put("cls-nd", new ClsNDEvaluator(this));
		this.evaluators.put("cls-nmi", new ClsNMIEvaluator(this));
		this.evaluators.put("cls-nrdc", new ClsNRDCEvaluator(this));
		this.evaluators.put("cls-nre", new ClsNREEvaluator(this));
		this.evaluators.put("cls-lc", new ClsLCEvaluator(this));
		this.evaluators.put("cls-c", new ClsCEvaluator(this));
		this.evaluators.put("cls-dig", new ClsDIGEvaluator(this));
		// KMF-Operation
		this.evaluators.put("oper-mcc", new OperMCCEvaluator(this));
		this.evaluators.put("oper-np", new OperNPEvaluator(this));
		// KMF-OCL
		this.evaluators.put("ocl-ndp", new OclNDPEvaluator(this));
		this.evaluators.put("ocl-hnt", new OclHNTEvaluator(this));
		this.evaluators.put("ocl-mcc", new OclMCCEvaluator(this));
		this.evaluators.put("ocl-halc", new OclHALCEvaluator(this));
		this.evaluators.put("ocl-nv", new OclNVEvaluator(this));
	}

	/** Set the list of metrics attached to a model element */
	public void setMetrics(Object obj, List list) {
		metrics.put(obj, list);
	}

	/** Get the list of metrics attached to a model element */
	public List getMetrics(Object obj) {
		return (List)metrics.get(obj);
	}
	
	/** Evaluate the quality of a list of model elements */
	public List evaluate(List elems, List metrics) {
		List res = new Vector();
		if (elems == null) return res;
		for(int i=0; i<elems.size(); i++) {
			Object elem = elems.get(i);
			res.add(evaluate(elem, metrics));
		}
		return res;
	}

	/** Evaluate the quality of a model element */
	public List evaluate(Object elem, List metrics) {
		List res = new Vector();
		if (metrics != null) {
			for(int i=0; i<metrics.size(); i++) {
				Metric m = (Metric)metrics.get(i);
				Metric m1 = evaluate(elem, m);
				if (m1 != null)
					res.add(m1);
			}
		}
		setMetrics(elem, res);
		return res;
	}

	/** Evaluate the quality of a model element */
	public Metric evaluate(Object elem, Metric m) {
		Metric res = null;
		String type = m.getType();
		if (type.toLowerCase().trim().equals("system")) {
			String key = m.getKey().toLowerCase().trim();
			Evaluator eval = (Evaluator)evaluators.get(key);
			if (eval != null) {
				res = eval.evaluate(elem, m);
			} else {
				if (log != null)
					log.reportMessage("Cannot found evaluator for '"+key+"'");
			}
		} else if (type.toLowerCase().trim().equals("ocl")) {
			String key = "ocl";
			Evaluator eval = (Evaluator)evaluators.get(key);
			if (eval != null) {
				res = eval.evaluate(elem, m);
			} else {
				if (log != null)
					log.reportMessage("Cannot found evaluator for '"+key+"'");
			}
		}
		return res;
	}

	//
	// Get/set methods
	//
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}

		
	//
	// Local properties
	//
	/** Context of evaluation */
	protected Context context;
	/** Table of metrics */
	protected Map metrics = new HashMap();
	/** Metric builder */
	public static MetricFactory factory = new MetricFactory();
	/** Table to choose the appropiate evaluator */
	protected Map evaluators = new HashMap();
	/** Log file to report errors */
	protected ILog log;
}
