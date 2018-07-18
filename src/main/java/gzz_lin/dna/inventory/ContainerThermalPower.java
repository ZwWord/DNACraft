package gzz_lin.dna.inventory;

import java.util.List;

import gzz_lin.dna.tileentity.TileEntityThermalPower;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerThermalPower extends Container {
	private ItemStackHandler burnItem = null;
	private TileEntityThermalPower tileEntity;
	private int burnTime = 0;
	private int burnInitTime = 0;
	private int electricity = 0;

	public ContainerThermalPower(EntityPlayer player, TileEntity tileEntity) {
		this.tileEntity = (TileEntityThermalPower) tileEntity;
		this.burnItem = this.tileEntity.getItemStackHandler();
		this.addSlot(player);
	}

	private void addSlot(EntityPlayer player) {
		this.addSlotToContainer(new SlotItemHandler(burnItem, 0, 56, 35));
		NonNullList<ItemStack> mainInventory = player.inventory.mainInventory;
		for (int h = 0; h < 3; h++) {
			for (int w = 0; w < 9; w++) {
				this.addSlotToContainer(new Slot(player.inventory, 9 + h * 9 + w, 8 + w * 18, 84 + h * 18));
			}
		}
		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return playerIn.getDistanceSq(tileEntity.getPos()) <= 64;
	}

	public TileEntityThermalPower getTileEntity() {
		return this.tileEntity;
	}
	public int getBurnTime() {
		return this.burnTime;
	}
	public int getBurnInitTime() {
		return this.burnInitTime;
	}
	
	public int getElectricity() {
		return electricity;
	}

	@Override
	public void detectAndSendChanges() {

super.detectAndSendChanges();
		for (IContainerListener listener : this.listeners) {
			if (this.burnInitTime != this.tileEntity.getBurnInitTime())
				listener.sendWindowProperty(this, 0, this.tileEntity.getBurnInitTime());
			if (this.burnTime != this.tileEntity.getBurnTime())
				listener.sendWindowProperty(this, 1, this.tileEntity.getBurnTime());
			if (this.electricity != this.tileEntity.getElectricity())
				listener.sendWindowProperty(this, 2, this.tileEntity.getElectricity());
		}
		
		this.burnInitTime = this.tileEntity.getBurnInitTime();
		this.burnTime = this.tileEntity.getBurnTime();
		this.electricity = this.tileEntity.getElectricity();
		
	}

	@Override
	public void updateProgressBar(int id, int data) {
//System.out.println(id+":"+data);
		super.updateProgressBar(id, data);
		switch (id) {
		case 0:
			this.burnInitTime = data;
			break;
		case 1:
			this.burnTime = data;
			break;
		case 2:
			this.electricity = data;
			break;
		default:
			break;
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {

		return null;
	}
	
	
	

}
