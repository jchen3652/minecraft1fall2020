package jameschen.myfirstmod.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import jameschen.myfirstmod.MyFirstMod;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			MyFirstMod.modid);

	public static final RegistryObject<Block> RUBY_ORE = BLOCKS.register("ruby_ore",
			() -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)));
}
