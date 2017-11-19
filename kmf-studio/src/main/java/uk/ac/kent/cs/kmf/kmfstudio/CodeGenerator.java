package uk.ac.kent.cs.kmf.kmfstudio;

import uk.ac.kent.cs.kmf.util.ILog;

/**
 * @author Octavian Patrascoiu
 *
 */
public interface CodeGenerator {
	public void generate(Project project, Naming naming, ILog log);
}
