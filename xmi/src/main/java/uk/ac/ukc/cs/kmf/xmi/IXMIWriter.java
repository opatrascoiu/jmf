package uk.ac.ukc.cs.kmf.xmi;

import uk.ac.kent.cs.kmf.util.*;

public interface IXMIWriter {
    public void write(IXMIFile xmiFile, String fileName, ILog log) throws Exception;
}
