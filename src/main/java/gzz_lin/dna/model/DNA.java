package gzz_lin.dna.model;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class DNA {
	private String name;
	private int max=0;
	private ResourceLocation icon;
	private int iconOffstX;
	private int iconOffstY;
	
	public DNA(String name, int max, ResourceLocation icon, int iconOffstX, int iconOffstY) {
		super();
		this.name = name;
		this.max = max;
		this.icon = icon;
		this.iconOffstX = iconOffstX;
		this.iconOffstY = iconOffstY;
	}
	public String getName() {
		return name;
	}
	public int getMax() {
		return max;
	}
	
	public ResourceLocation getIcon() {
		return icon;
	}
	public int getIconOffstX() {
		return iconOffstX;
	}
	public int getIconOffstY() {
		return iconOffstY;
	}
	public String getDisplayName() {
		return I18n.format("dna."+this.getName()+".name");
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof DNA) {
			return ((DNA)obj).getName().equals(this.getName());
		}else {
			return false;
		}
	}
	@Override
	public int hashCode() {
		
		return this.name.hashCode()+19940613;
	}
	@Override
	public String toString() {
		return "dna."+this.getName();
	}
	
	
}
