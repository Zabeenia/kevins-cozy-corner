package zabeenia.kevinscozycorner;

import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.resources.Identifier;
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

        Registry.register(
                BuiltInRegistries.ITEM,
                ResourceKey.create(
                        Registries.ITEM,
                        Identifier.fromNamespaceAndPath("kevins-cozy-corner", "kevin_plush")
                ),
                new BlockItem(
                        KEVIN_PLUSH,
                        new Item.Properties().setId(
                                ResourceKey.create(
                                        Registries.ITEM,
                                        Identifier.fromNamespaceAndPath("kevins-cozy-corner", "kevin_plush")
                                )
                        )
                )
        );
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .register(entries -> entries.accept(
                        BuiltInRegistries.ITEM.getValue(
                                Identifier.fromNamespaceAndPath("kevins-cozy-corner", "kevin_plush")
                        )
                ));
    }
}