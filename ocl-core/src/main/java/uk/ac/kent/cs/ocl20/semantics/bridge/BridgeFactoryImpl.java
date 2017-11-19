package uk.ac.kent.cs.ocl20.semantics.bridge;

import uk.ac.kent.cs.ocl20.OclProcessor;

abstract public class BridgeFactoryImpl implements BridgeFactory {
	protected OclProcessor processor=null;
	public BridgeFactoryImpl(OclProcessor proc) {
		this.processor = proc;
	}
	/** Build a namedElement */
	public NamedElement buildNamedElement(String name, ModelElement referred_element, Boolean imp) {
		return new NamedElementImpl(name, referred_element, imp);
	}
	
	/** Build an environment */
	public Environment buildEnvironment() {
		return new EnvironmentImpl(this);
	}
}
