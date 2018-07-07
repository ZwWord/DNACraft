package gzz_lin.dna;

import org.apache.logging.log4j.Logger;

import gzz_lin.dna.capability.CapablilityLoader;
import gzz_lin.dna.command.CommandDNA;
import gzz_lin.dna.entity.EntityLoader;
import gzz_lin.dna.entity.RenderLoader;
import gzz_lin.dna.item.DNAItems;
import gzz_lin.dna.net.NetWorkLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = DNACraft.MODID, name = DNACraft.NAME, version = DNACraft.VERSION)
public class DNACraft
{
    public static final String MODID = "dc";
    public static final String NAME = "DNA Craft";
    public static final String VERSION = "1.0";

    @Instance(DNACraft.MODID)
    public static DNACraft instance;
    
    public static final CreativeTabs TAB=new CreativeTabs("dc") {
		
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(DNAItems.BIO_MICROCAPSULE_NUTRIENT,1,3);
		}
	};
	
    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        MinecraftForge.EVENT_BUS.register(new EventLoader());
        new CapablilityLoader();
        new NetWorkLoader();
        new EntityLoader();
        new RenderLoader();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
    @EventHandler
    public void serverStart(FMLServerStartingEvent event) {
    	//注册指令
    	event.registerServerCommand(new CommandDNA());
    }
}
