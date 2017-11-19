/**
 * 
 * @author Octavian Patrascoiu
 *
 */

package uk.ac.kent.cs.ocl20.synthesis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RuntimeEnvironmentImpl
implements RuntimeEnvironment
{
	public RuntimeEnvironmentImpl() {
		bindings = new HashMap();
	}

	protected RuntimeEnvironment parent;
	public RuntimeEnvironment getParent() {
		return parent;
	}
	public void setParent(RuntimeEnvironment p) {
		parent = p;
	}

	protected Map bindings;

	public Object getValue(String name) {
		Object value = bindings.get(name);
		if (value == null) {
			if (parent != null) value = parent.getValue(name);
		}
		return value;
	}
	public void setValue(String name, Object value) {
		bindings.put(name, value);
	}
	
	public Set getKeys() {
		return bindings.keySet();
	}

	public RuntimeEnvironment newEnvironment() {
		RuntimeEnvironment newEnv = new RuntimeEnvironmentImpl();
		newEnv.setParent(this);
		return newEnv;
	}
}
