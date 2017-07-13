package cam72cam.immersiverailroading;

import org.apache.logging.log4j.Logger;

import cam72cam.immersiverailroading.blocks.BlockRail;
import cam72cam.immersiverailroading.blocks.BlockRailGag;
import cam72cam.immersiverailroading.library.TrackItems;
import cam72cam.immersiverailroading.tile.TileRail;
import cam72cam.immersiverailroading.tile.TileRailGag;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@Mod(modid = ImmersiveRailroading.MODID, name="ImmersiveRailroading", version = ImmersiveRailroading.VERSION)
public class ImmersiveRailroading
{
    public static final String MODID = "immersiverailroading";
    public static final String VERSION = "0.1";
    
	@ObjectHolder(BlockRailGag.name)
	public static final BlockRailGag BLOCK_RAIL_GAG = new BlockRailGag();
	@ObjectHolder(BlockRail.NAME)
	public static BlockRail BLOCK_RAIL = new BlockRail();
	
	public static Logger logger;
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
    
    @Mod.EventBusSubscriber(modid = MODID)
    public static class Registration
    {
    	
        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event)
        {
    		event.getRegistry().register(BLOCK_RAIL_GAG);
    		event.getRegistry().register(BLOCK_RAIL);
        	GameRegistry.registerTileEntity(TileRailGag.class, BlockRailGag.name);
        	GameRegistry.registerTileEntity(TileRail.class, BlockRail.NAME);
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event)
        {
        	event.getRegistry().register(new ItemBlock(BLOCK_RAIL_GAG).setRegistryName(BLOCK_RAIL_GAG.getRegistryName()));
        	event.getRegistry().register(new ItemBlockMeta(BLOCK_RAIL).setRegistryName(BLOCK_RAIL.getRegistryName()));
        }

        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event)
        {
            OBJLoader.INSTANCE.addDomain(MODID.toLowerCase());
            
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BLOCK_RAIL_GAG), 0, new ModelResourceLocation(BLOCK_RAIL_GAG.getRegistryName(), "inventory"));
            
            for (TrackItems item : TrackItems.values()) {
	            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BLOCK_RAIL), item.getMeta(), new ModelResourceLocation(BLOCK_RAIL.getRegistryName(), "inventory"));
            }
        }
    }
}
