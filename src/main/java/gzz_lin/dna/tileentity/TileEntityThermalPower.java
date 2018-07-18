package gzz_lin.dna.tileentity;

import gzz_lin.dna.api.tile.IElectricityStorager;
import gzz_lin.dna.block.BlockThermalPower;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergySource;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

@Optional.Interface(iface = "ic2.api.energy.tile.IEnergySource", modid = "ic2")
public class TileEntityThermalPower extends TileEntity implements IElectricityStorager, ITickable, IEnergySource {
    private int electricity = 0;
    public static final int MAX = 4000;
    public static final int BURN_TO_ELECTRICITY = 5;
    private ItemStackHandler itemhandler = new ItemStackHandler();
    private int burnTime = 0;
    private int butnInitTime = 0;
    private boolean updata = false;

    public int getMax() {
        return MAX;
    }

    public ItemStackHandler getItemStackHandler() {
        return this.itemhandler;
    }

    public int getElectricity() {
        return this.electricity;
    }

    public int getBurnTime() {
        return this.burnTime;
    }

    public int getBurnInitTime() {
        return this.butnInitTime;
    }

    @Override
    public int useElectricity(int need) {
        if (this.electricity >= need) {
            this.setElectricity(this.electricity - need);
        } else {
            need = this.electricity;
            this.setElectricity(0);
        }
        return need;

    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.electricity = compound.getInteger("tile");
        this.burnTime = compound.getInteger("burnTime");
        this.butnInitTime = compound.getInteger("butnInitTime");
        this.itemhandler.deserializeNBT(compound.getCompoundTag("item"));

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("tile", this.electricity);
        compound.setInteger("burntime", this.burnTime);
        compound.setInteger("butnInitTime", this.butnInitTime);
        compound.setTag("item", this.itemhandler.serializeNBT());
        return super.writeToNBT(compound);
    }


    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability)) {
            return true;
        }
        return false;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability)) {
            return (T) this.itemhandler;
        }
        return null;
    }

    @Override
    public void invalidate() {
        super.invalidate();
        if (Loader.isModLoaded("ic2")) {
            this.close();
        }

    }

    @Override
    public void update() {
        if (this.world.isRemote)
            return;
        if (!this.updata && Loader.isModLoaded("ic2")) {
            this.open();
            this.updata = true;
        }

        IBlockState blockState = this.world.getBlockState(pos);
        if (this.burnTime <= 0) {
            ItemStack itemStack = this.itemhandler.extractItem(0, 1, true);
            int burn = TileEntityFurnace.getItemBurnTime(itemStack);
            this.world.setBlockState(pos, blockState.withProperty(BlockThermalPower.RUNING, false));
            if (burn > 0 && this.electricity < MAX) {
                this.itemhandler.extractItem(0, 1, false);
                this.butnInitTime = burn;
                this.burnTime = burn;
                if (this.itemhandler.getStackInSlot(0).isEmpty()) {
                    ItemStack item1 = itemStack.getItem().getContainerItem(itemStack);
                    this.itemhandler.setStackInSlot(0, item1);
                }
                this.world.setBlockState(pos, blockState.withProperty(BlockThermalPower.RUNING, true));
                this.markDirty();
            }

        } else if (this.burnTime > 0) {

            this.burnTime--;
            if (this.electricity + BURN_TO_ELECTRICITY <= MAX) {
                this.setElectricity(this.electricity + BURN_TO_ELECTRICITY);
            } else {
                this.setElectricity(MAX);
            }
            this.markDirty();
        }


    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {

        return oldState.getBlock() != newSate.getBlock();
    }

    @Optional.Method(modid = "ic2")
    public void open() {
        MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
    }

    @Optional.Method(modid = "ic2")
    public void close() {
        MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
    }

    @Override
    public void setElectricity(int e) {

        this.electricity = e;

    }

    @Override
    public boolean emitsEnergyTo(IEnergyAcceptor receiver, EnumFacing side) {

        return true;
    }

    @Override
    public double getOfferedEnergy() {

        return this.electricity;
    }

    @Override
    public void drawEnergy(double amount) {
        this.electricity -= amount;
    }

    @Override
    public int getSourceTier() {

        return 1;
    }

}
