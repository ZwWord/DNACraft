package gzz_lin.dna.item;

import net.minecraft.util.IStringSerializable;

public enum BMMaterial implements IStringSerializable {
	IRON("iron"), GOLD("gold"), DIAMOND("diamond");
	private String name;

	BMMaterial(String name) {
		this.name = name;
	}

	@Override
	public String getName() {

		return this.name;
	}

	@Override
	public String toString() {

		return this.name;
	}

}
