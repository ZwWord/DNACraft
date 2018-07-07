package gzz_lin.dna.model;

public class DNAValue {
	private DNA dna;
	private int min;
	private int max;

	public DNAValue(DNA dna, int min, int max) {
		this.dna = dna;
		if(min<0||max<0||min>=max||max>dna.getMax()) {
			throw new RuntimeException("erro value");
		}
		this.min = min;
		this.max = max;
		
	}

	public DNA getDna() {
		return dna;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	@Override
	public int hashCode() {
		
		return this.dna.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof DNAValue) {
			return this.dna.equals(((DNAValue)obj).getDna());
		}
		return false;
	}

}
