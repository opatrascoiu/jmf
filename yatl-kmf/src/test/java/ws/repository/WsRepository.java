/**
 *
 *  Class WsRepository.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.repository;

public interface WsRepository
	extends uk.ac.kent.cs.kmf.common.Repository
{
	///--- FACTORIES ---
	/** Get a specific factory */
	public ws.WsFactory getFactory(String fullClassName);
	/** Register a factory */
	public void registerFactory(String fullClassName, ws.WsFactory factory);

	//--- BROWSER ---
	/** Get the content of the repository */
	public javax.swing.tree.DefaultMutableTreeNode toJTree();
	/** Save the repository into an XMI file */
	public void saveXMI(String fileName);
	/** Create a new repository from an XMI file */
	public uk.ac.kent.cs.kmf.common.Repository loadXMI(String fileName);
	/** Get the content of the repository */
	public String toHUTN();
	/** Save the repository into an HUTN file */
	public void saveHUTN(String fileName);
	/** Create a new repository from an HUTN file */
	public WsRepository loadHUTN(String fileName);

}