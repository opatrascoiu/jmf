package uk.ac.kent.cs.kmf.common;

import java.io.*;
import java.util.*;

public class History {
	public History() {
	}
	// Is empty
	public boolean isEmpty() {
		return objList.size() == 0;
	}
	// Load history
	public void load() {
    	File file = new File(fileName);
    	if (file.exists()) {
    		try {
            	// Print out the log
            	BufferedReader in = new BufferedReader(new FileReader(file));
            	String line;
            	while ((line=in.readLine())!=null) objList.add(line);
    		} catch (Exception e) {
    		}
    	}
	}
	// Save history
	public void save() {
    	try {
    		File file = new File(fileName);
            // Print out the log
            PrintWriter out = new PrintWriter(new FileWriter(file));
            for(int i=0; i<objList.size(); i++) out.println((String)objList.get(i));
            out.close();
   		} catch (Exception e) {
   		}
    }
	// Get the ith elemnt
	public Object elementAt(int i) {
		return objList.elementAt(i);
	}
	// Save history
	public void add(Object obj) {
		if (objList.contains(obj)) return;
		if (objList.size() == max) objList.remove(max-1);
		objList.insertElementAt(obj, 0);
	}
	// Iterate over the history
	public Iterator iterator() {
		iter = objList.iterator();
		return iter;
	} 
	public boolean hasNext() {
		return iter.hasNext();
	} 
	public Object next() {
		return iter.next();
	} 
	// List of objects
	Vector objList = new Vector();
	// File used to store history
	final String fileName = "History.txt";
	// Maximum number of object 
	final int max = 5;
	// Local iterator
	Iterator iter;
}
