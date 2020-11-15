package jameschen.myfirstmod;

import jameschen.myfirstmod.init.ModBlocks;
import jameschen.myfirstmod.init.ModItemGroups;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid=MyFirstMod.modid, bus=EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {
		// This creates our custom ingot
		Item myItem = new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
		setup(myItem, "my_ingot");
		event.getRegistry().register(myItem);
		
		
		// This creates our magic sword
		Item magicSword = new SwordItem(new MagicItemTier(), 10000, 10000, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
		setup(magicSword, "magic_sword");
		event.getRegistry().register(magicSword);
		
		
		ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
		    final Item.Properties properties = new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP);
		    final BlockItem blockItem = new BlockItem(block, properties);
		    blockItem.setRegistryName(block.getRegistryName());
		    event.getRegistry().register(blockItem);
		});
	
	}
	
	// All the code below here is just copied and pasted!
	
	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
		return setup(entry, new ResourceLocation(MyFirstMod.modid, name));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}
}
