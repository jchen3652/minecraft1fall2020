package jameschen.myfirstmod;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

public class MagicItemTier implements IItemTier {

	@Override
	public int getMaxUses() {
		return 10000;
	}

	@Override
	public float getEfficiency() {
		return 10000;
	}

	@Override
	public float getAttackDamage() {
		return 10000;
	}

	@Override
	public int getHarvestLevel() {
		return 3;
	}

	@Override
	public int getEnchantability() {
		return 10000;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.fromItems(Items.DIAMOND_BLOCK);
	}

}
