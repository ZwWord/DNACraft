package gzz_lin.dna.capability;

import java.util.HashMap;

import gzz_lin.dna.model.DNA;

/**
 * DNA能力接口
 * 
 * @author Gzz_lin
 *
 */
public interface IDNACapability {
	void setDNA(HashMap<DNA, Integer> DNA);

	HashMap<DNA, Integer> getDNA();

	int getValue(DNA dna);

	boolean setValue(DNA dna,int value);

	boolean removeDNA(DNA dna);

	boolean addDNA(DNA dna, int value);

	boolean hasDNA(DNA dna);

}
