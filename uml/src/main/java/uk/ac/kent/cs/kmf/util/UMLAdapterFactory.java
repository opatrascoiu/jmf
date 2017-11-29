package uk.ac.kent.cs.kmf.util;

import uk.ac.kent.cs.kmf.xmi.AdapterFactory;
import uk.ac.kent.cs.kmf.xmi.ReaderAdapter;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.*;

public class UMLAdapterFactory extends AdapterFactory {
    protected UmlRepository repository;
    protected ILog log;

    public UMLAdapterFactory(UmlRepository r, ILog l) {
        repository = r;
        log = l;
    }

    public ReaderAdapter createReaderAdapter() {
        return new UMLReaderAdapter(repository, log);
    }
}
