package uk.ac.kent.cs.kmf.xmi;

import uk.ac.kent.cs.kmf.util.*;

public interface IXMIReader {
    public XMIFile read(String fileName, ILog log) throws Exception;
}
