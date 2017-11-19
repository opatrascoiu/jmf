/**
 * 
 * @author Octavian Patrascoiu
 *
 */

package uk.ac.kent.cs.ocl20.synthesis;

import java.util.Set;

public interface RuntimeEnvironment {
	public RuntimeEnvironment getParent();
	public void setParent(RuntimeEnvironment p);

	public Object getValue(String name);
	public void setValue(String name, Object value);
	public Set getKeys();
	
	public RuntimeEnvironment newEnvironment();
}
