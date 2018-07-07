package gzz_lin.dna.model;

import java.util.HashSet;

public class DNAHolder {
private Class enttiyClass;
private HashSet<DNAValue> dnaValues=new HashSet<>();
public DNAHolder(Class enttiyClass, DNAValue... dnaValues) {
	this.enttiyClass = enttiyClass;
	for(DNAValue dv:dnaValues) {
		this.dnaValues.add(dv);
	}
}
public Class getEnttiyClass() {
	return enttiyClass;
}
public HashSet<DNAValue> getDnaValues() {
	return dnaValues;
}
@Override
public int hashCode() {
	return this.enttiyClass.hashCode()+19940613;
}
@Override
public boolean equals(Object obj) {
	return this.enttiyClass.equals(obj);
}


}
