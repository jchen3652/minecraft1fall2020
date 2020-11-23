package jameschen.myfirstmod.init;

import jameschen.myfirstmod.MyFirstMod;
import jameschen.myfirstmod.world.biome.ExampleBiome;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
 
public class ModBiomes {
    // Creates a deferred register to hold all of our biomes
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES,
            MyFirstMod.modid);
 
    // This Creates a registry object for our example biome
    public static final RegistryObject<Biome> EXAMPLE_BIOME = BIOMES.register(ExampleBiome.getName(),
            ExampleBiome.getBiomeSupplier());
 
    /**
     * This method is called to add our biome(s) to the list of possible spawns
     */
    public static void registerBiomes() {
        // ----------------------Begin code to register example biome------------------
        // THIS CODE WILL NEED TO BE REPEATED FOR EACH NEW BIOME
 
        // How often biome will spawn, 50 for normal biomes. Higher here b/c favoritism
        int example_biome_weight = 500;
 
        // Do not change this code
        BiomeManager
                .addBiome(ExampleBiome.getType(),
                        new BiomeManager.BiomeEntry(
                                RegistryKey.getOrCreateKey(Registry.BIOME_KEY,
                                        new ResourceLocation(MyFirstMod.modid, ExampleBiome.getName())),
                                example_biome_weight));
        // -----------------------End code to register example biome------------------
 
    }
}