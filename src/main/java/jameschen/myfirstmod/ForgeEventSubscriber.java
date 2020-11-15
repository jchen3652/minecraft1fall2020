package jameschen.myfirstmod;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.inventory.EquipmentSlotType;

@EventBusSubscriber(modid = MyFirstMod.modid, bus = EventBusSubscriber.Bus.FORGE)
public class ForgeEventSubscriber {
	@SubscribeEvent
	public static void onPlayerInteractEntity(PlayerInteractEvent.EntityInteractSpecific event) {
		if (!event.getEntity().world.isRemote()
				&& event.getPlayer().getHeldItem(event.getHand()).getItem().equals(Items.BUCKET)
				&& event.getTarget().getType().equals(EntityType.ZOMBIE)) {

			event.getPlayer().getHeldItem(event.getHand()).shrink(1);
			event.getPlayer().addItemStackToInventory(new ItemStack(Items.ZOMBIE_SPAWN_EGG));
			event.getTarget().remove();
		}
	}

//	@SubscribeEvent
//	public static void onDeath(LivingDeathEvent event) {
//		if (!event.getEntity().world.isRemote() && event.getEntity().getType().equals(EntityType.CREEPER)) {
//
//			for (int i = 0; i < 1; ++i) {
//				CreeperEntity creeper1 = new CreeperEntity(EntityType.CREEPER, event.getEntity().getEntityWorld());
//				creeper1.setLocationAndAngles(event.getEntity().getPosX(), event.getEntity().getPosY(),
//						event.getEntity().getPosZ(), 0, 0);
//				event.getEntity().getEntityWorld().addEntity(creeper1);
//			}
//		}
//	}

	@SubscribeEvent
	public static void onEntityJoin(EntityJoinWorldEvent event) {
		if (!event.getEntity().world.isRemote() && event.getEntity().getType().equals(EntityType.ZOMBIE)) {
			ZombieEntity zombie = (ZombieEntity) event.getEntity();
			
			zombie.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.field_234754_kI_));
			zombie.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.SHIELD));
			zombie.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(Items.field_234763_ls_));
			zombie.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(Items.field_234764_lt_));
			zombie.setItemStackToSlot(EquipmentSlotType.LEGS, new ItemStack(Items.field_234765_lu_));
			zombie.setItemStackToSlot(EquipmentSlotType.FEET, new ItemStack(Items.field_234766_lv_));
			
			
			
			
			
//			zombie.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.DIAMOND_SWORD));
			
			
			
		}
	}
}
