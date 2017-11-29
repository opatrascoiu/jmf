package uk.ac.kent.cs.kmf.util;

public interface Loader {
	public Pair loadXMI(String inputFile, ILog log);
	public Pair loadModel(String inputFile, ILog log);
}
