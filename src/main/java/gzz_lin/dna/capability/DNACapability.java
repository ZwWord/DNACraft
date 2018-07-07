package gzz_lin.dna.capability;

import java.util.HashMap;
import java.util.Map.Entry;

import gzz_lin.dna.model.DNA;

/**
 * DNA能力实现
 * @author Gzz_lin
 *
 */
public class DNACapability implements IDNACapability {

	private HashMap<DNA, Integer> map = new HashMap<>();

	@Override
	public void setDNA(HashMap<DNA, Integer> DNA) {
		for (Entry<DNA, Integer> di : DNA.entrySet()) {
			this.valueDetection(di.getKey(), di.getValue());
		}
		this.map = (HashMap<DNA, Integer>) DNA.clone();
	}

	@Override
	public HashMap<DNA, Integer> getDNA() {
		return (HashMap<DNA, Integer>) map.clone();
	}

	@Override
	public int getValue(DNA dna) {
		Integer value = map.get(dna);
		return value == null ? -1 : value;
	}

	@Override
	public boolean removeDNA(DNA dna) {

		return map.remove(dna) == null ? false : true;
	}

	@Override
	public boolean addDNA(DNA dna, int value) {
		this.valueDetection(dna, value);
		if (!this.hasDNA(dna)) {
			map.put(dna, value);
			return true;
		}
		return false;
	}

	@Override
	public boolean hasDNA(DNA dna) {
		if (this.getValue(dna) >= 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean setValue(DNA dna, int value) {
		this.valueDetection(dna, value);
		if (this.hasDNA(dna)) {
			map.put(dna, value);
			return true;
		}
		return false;
	}

	private void valueDetection(DNA dna, int value) {
		if (value < 0 || value > dna.getMax()) {
			throw new RuntimeException(
					"DNA　value error：" + dna + " Max Value is " + dna.getMax() + ",error value:" + value);
		}
	}
}
