/**
 *
 *  Class OnAlarmFactory.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:38
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public interface OnAlarmFactory
extends
    ws.WsFactory
{
	/** Default builder */
	public Object build();
	/** Specialized builder */
	public Object build(DurationKind durationKind);
}
