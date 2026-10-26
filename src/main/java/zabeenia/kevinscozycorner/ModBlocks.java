
package zabeenia.kevinscozycorner;

import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.item.CreativeModeTabs;

public class ModBlocks {
    public static final ResourceKey<net.minecraft.world.level.block.Block> KEVIN_PLUSH_KEY =
            ResourceKey.create(
                    Registries.BLOCK,
                    KevinSCozyCornerAnEndermanPlushie.id("kevin_plush")
            );

    public static KevinPlushBlock KEVIN_PLUSH;

    public static void registerModBlocks() {
        KEVIN_PLUSH = new KevinPlushBlock(
                BlockBehaviour.Properties.of()
                        .setId(KEVIN_PLUSH_KEY)
                        .strength(0.5f)
                        .noOcclusion()
        );

        Registry.register(
                BuiltInRegistries.BLOCK,
                KEVIN_PLUSH_KEY,
                KEVIN_PLUSH
        );

        ResourceKey<Item> itemKey = ResourceKey.create(
                Registries.ITEM,
                KevinSCozyCornerAnEndermanPlushie.id("kevin_plush")
        );

        Registry.register(
                BuiltInRegistries.ITEM,
                itemKey,
                new BlockItem(
                        KEVIN_PLUSH,
                        new Item.Properties().setId(itemKey)
                )
        );

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .register(entries -> entries.accept(
                        BuiltInRegistries.ITEM.getValue(
                                KevinSCozyCornerAnEndermanPlushie.id("kevin_plush")
                        )
                ));
    }
}
