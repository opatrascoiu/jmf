package uk.ac.kent.cs.kmf.util;

/**
 * 
 * @author Octavian Patrascoiu
 *
 */
public class PairImpl implements Pair {

	/**
	 * Constuctors 
	 */
	public PairImpl() {
	}
	public PairImpl(Object first, Object second) {
		this.first = first;
		this.second = second;
	}

	/* 
	 * First element
	 */
	protected Object first;
	public void setFirst(Object first) {
		this.first = first;
	}
	public Object getFirst() {
		return first;
	}

	/* 
	 * Second element
	 */
	protected Object second;
	public void setSecond(Object second) {
		this.second = second;
	}

	public Object getSecond() {
		return second;
	}
	
	/** toString */
	public String toString() {
		return "Pair("+first+", "+second+")";
	}

}
