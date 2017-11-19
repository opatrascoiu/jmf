package uk.ac.kent.cs.kmf.kmfstudio.gui;

import java.io.File;

class BasicFileFilter extends javax.swing.filechooser.FileFilter {
    protected String ext[];
    public BasicFileFilter() {
    }
    public BasicFileFilter(String ext1[]) {
        this.ext = new String[ext1.length];
        for(int i=0; i<ext.length; i++) ext[i]=ext1[i];
    }
    public boolean accept(File f) {
        if (f.isDirectory()) return true;
        if (ext.length == 0) return true;
        String path = f.getPath();
        int pos = path.lastIndexOf(".");
        if (pos == -1) return true;
        String ext1 = path.substring(pos+1);
        for(int i=0; i<ext.length; i++)
            if (ext1.equalsIgnoreCase(ext[i])) return true;
        return false;
    }
    public String getDescription() {
    	if (ext.length == 0) return "*.*";
    	String res = "*."+ext[0];
        for(int i=1; i<ext.length; i++) {
        	res += ", *."+ext[i];
        }
        return res;
    }
}
       
        
        
        
    