package jameschen.myfirstmod.world.biome;

import java.util.function.Supplier;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.audio.BackgroundMusicSelector;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeAmbience.GrassColorModifier;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.biome.ParticleEffectAmbience;
import net.minecraft.world.biome.SoundAdditionsAmbience;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeManager;

public class ExampleBiome {
	// -------------------------------------------------------------------------------------------------
	// Required Biome Settings
	// -------------------------------------------------------------------------------------------------

	// The type of precipitation
	private static final RainType precipitation = RainType.SNOW;

	// Biome Category
	private static final Category category = Category.PLAINS;

	// How "deep" the top layer goes
	private static final float depth = 0.125f;

	// How extreme terrain height differences are
	private static final float scale = 0.5f;

	// Lower means spawns near colder biomes, higher means spawning near warmer
	private static final float temperature = 0.5f;

	// How often it rains?
	private static final float downfall = 1f;

	// -------------------------------------------------------------------------------------------------
	// Do not modify - this stuff just works
	// -------------------------------------------------------------------------------------------------

	// Builders
	private static final BiomeAmbience.Builder ambienceBuilder = new BiomeAmbience.Builder();
	private static final MobSpawnInfo.Builder mobSpawnInfoBuilder = new MobSpawnInfo.Builder();
	private static final BiomeGenerationSettings.Builder biomeGenBuilder = new BiomeGenerationSettings.Builder();

	public static Supplier<Biome> getBiomeSupplier() {
		// -------------------------------------------------------------------------------------------------
		// Required Biome Ambience Settings
		// -------------------------------------------------------------------------------------------------
		int fogColor = 16720384;
		ambienceBuilder.setFogColor(fogColor);

		int waterColor = 16720384;
		ambienceBuilder.setWaterColor(waterColor);

		int waterFogColor = 16720384;
		ambienceBuilder.setWaterFogColor(waterFogColor);

		int skyColor = 16720384;
		ambienceBuilder.withSkyColor(skyColor);

		// -------------------------------------------------------------------------------------------------
		// Optional Biome Ambience Settings. You can comment out any pair of these lines
		// -------------------------------------------------------------------------------------------------

		int foliageColor = 16720384;
		ambienceBuilder.withFoliageColor(foliageColor);

		int grassColor = 16720384;
		ambienceBuilder.withGrassColor(grassColor);

		GrassColorModifier modifier = GrassColorModifier.NONE;
		ambienceBuilder.withGrassColorModifier(modifier);

		// Particle effect that happens randomly. Particle Type and weight
		ParticleEffectAmbience particleEffect = new ParticleEffectAmbience(ParticleTypes.HEART, 0.05f);
		ambienceBuilder.setParticle(particleEffect);

		// Ambient Sound - plays when nothing is happening
		SoundEvent soundAmbience = SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP;
		ambienceBuilder.setAmbientSound(soundAmbience);

		// Mood sound - this randomly comes in once in a while
		MoodSoundAmbience moodSoundAmbience = MoodSoundAmbience.DEFAULT_CAVE;
		ambienceBuilder.setMoodSound(moodSoundAmbience);

		// Random sounds - Sound and probability
		SoundAdditionsAmbience additions = new SoundAdditionsAmbience(SoundEvents.ENTITY_VILLAGER_DEATH, 0.001f);

		// Music sound, min delay, max delay, overwrite (yes/no)
		// If you want background music, you must comment out ambient sounds
		BackgroundMusicSelector backgroundMusic = new BackgroundMusicSelector(SoundEvents.MUSIC_DISC_CAT, 12000, 24000,
				true);
		ambienceBuilder.setMusic(backgroundMusic);

		// -------------------------------------------------------------------------------------------------
		// Optional mob spawn settings. You can comment out any pair of these lines
		// -------------------------------------------------------------------------------------------------

		// What spawns, how often these packs spawn, smallest pack size, largest pack
		// size
		MobSpawnInfo.Spawners beeSpawner = new MobSpawnInfo.Spawners(EntityType.BEE, 20, 3, 6);
		mobSpawnInfoBuilder.withSpawner(EntityClassification.CREATURE, beeSpawner);

		// -------------------------------------------------------------------------------------------------
		// Required World Generation Settings
		// -------------------------------------------------------------------------------------------------
		SurfaceBuilder<SurfaceBuilderConfig> surfaceType = SurfaceBuilder.DEFAULT;
		BlockState topMaterial = Blocks.GRASS_BLOCK.getDefaultState();
		BlockState underMaterial = Blocks.ANCIENT_DEBRIS.getDefaultState();
		BlockState underWaterMaterial = Blocks.SEA_LANTERN.getDefaultState();

		// DO NOT CHANGE - will create our surface!
		biomeGenBuilder.withSurfaceBuilder(new ConfiguredSurfaceBuilder<SurfaceBuilderConfig>(surfaceType,
				new SurfaceBuilderConfig(topMaterial, underMaterial, underWaterMaterial)));

		// -------------------------------------------------------------------------------------------------
		// Optional World Generation Settings
		// -------------------------------------------------------------------------------------------------

		// OPTIONAL: YOU CAN COMMENT THIS ALL OUT. Creates caves.
		float caveProbability = 0.5f;
		ProbabilityConfig caveCarverProbability = new ProbabilityConfig(caveProbability);
		ConfiguredCarver<ProbabilityConfig> caveCarver = WorldCarver.CAVE.func_242761_a(caveCarverProbability);
		biomeGenBuilder.withCarver(GenerationStage.Carving.LIQUID, caveCarver);

		// OPTIONAL: You can customize the generation of canyons.
		float canyonProbability = 0.5f;
		ProbabilityConfig canyonCarverProbability = new ProbabilityConfig(canyonProbability);
		ConfiguredCarver<ProbabilityConfig> canyonCarver = WorldCarver.CANYON.func_242761_a(canyonCarverProbability);
		biomeGenBuilder.withCarver(GenerationStage.Carving.LIQUID, canyonCarver);

		// OPTIONAL: You can customize the generation of structures
		biomeGenBuilder.withStructure(StructureFeatures.field_244157_w); // Snowy Village
		biomeGenBuilder.withStructure(StructureFeatures.field_244136_b); // Mineshaft

		// -------------------------------------------------------------------------------------------------
		// MUST BE COPIED - BEGIN
		// -------------------------------------------------------------------------------------------------
		return () -> {
			return new Biome.Builder().precipitation(precipitation).temperature(temperature)
					.setEffects(ambienceBuilder.build()).category(category).downfall(downfall).scale(scale).depth(depth)
					.withMobSpawnSettings(mobSpawnInfoBuilder.copy()).withGenerationSettings(biomeGenBuilder.build())
					.build();

		};
		// -------------------------------------------------------------------------------------------------
		// MUST BE COPIED - END
		// -------------------------------------------------------------------------------------------------

	}

	public static String getName() {
		return "example_biome";
	}

	public static BiomeManager.BiomeType getType() {
		return BiomeManager.BiomeType.COOL;
	}

}
