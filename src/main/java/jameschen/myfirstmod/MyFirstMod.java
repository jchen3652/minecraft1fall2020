package jameschen.myfirstmod;

import jameschen.myfirstmod.init.ModBiomes;
import jameschen.myfirstmod.init.ModBlocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MyFirstMod.modid)
public class MyFirstMod {
	public static final String modid = "myfirstmod";
	
	public MyFirstMod() {
		ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModBiomes.BIOMES.register(FMLJavaModLoadingContext.get().getModEventBus());
		
	}
}
