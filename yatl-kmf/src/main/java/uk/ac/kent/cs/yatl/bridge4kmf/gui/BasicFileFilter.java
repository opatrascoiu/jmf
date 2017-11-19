package uk.ac.kent.cs.yatl.bridge4kmf.gui;

import java.io.File;

/**
 * @author Octavian Patrascoiu
 *
 */
class BasicFileFilter 
	extends javax.swing.filechooser.FileFilter 
{
	String ext[];
	public BasicFileFilter() {
		this.ext = new String[] {""};
	}
	public BasicFileFilter(String ext[]) {
		this.ext = new String[ext.length];
		for(int i=0; i<ext.length; i++) this.ext[i] = ext[i];
	}
	public boolean accept(File f) {
		if (f.isDirectory()) return true;
		String path = f.getPath();
		int pos = path.lastIndexOf(".");
		if (pos == -1) return true;
		if (ext.length == 0) return true;
		String ext1 = path.substring(pos+1);
		for(int i=0; i<ext.length; i++) {
			if (ext[i].equals("")) return true;
			if (ext1.equalsIgnoreCase(ext[i])) return true;
		}
		return false;
	}
	public String getDescription() {
		if (ext.length == 0) return "*.*";
		String str = new String();
		str += "*."+ext[0];
		for(int i=1; i<ext.length; i++) {
			str += ", *."+ext[i];
		}
		return str;
	}
}