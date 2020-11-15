package jameschen.myfirstmod.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.function.Supplier;

import jameschen.myfirstmod.MyFirstMod;

public class ModItemGroups {
	public static final ItemGroup MOD_ITEM_GROUP = new ModItemGroup(MyFirstMod.modid, () -> new ItemStack(Items.BLUE_BANNER));
	
	public static class ModItemGroup extends ItemGroup {
		private final Supplier<ItemStack> iconSupplier;

		public ModItemGroup(String label, Supplier<ItemStack> iconSupplierIn) {
			super(label);
			iconSupplier = iconSupplierIn;
		}

		@Override
		public ItemStack createIcon() {
			return iconSupplier.get();
		}
	}
}
