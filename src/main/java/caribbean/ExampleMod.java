package caribbean;

import caribbean.item.IslandsBook;
import caribbean.item.PathBook;
import caribbean.item.armor.BaseArmor;
import caribbean.item.armor.FabricArmorMaterial;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExampleMod implements ModInitializer {
    public static IslandsBook islands_book = new IslandsBook(new FabricItemSettings().maxCount(1));
    public static PathBook path_book = new PathBook(new FabricItemSettings().maxCount(1));
    public static final ArmorMaterial FABRIC_ARMOR = new FabricArmorMaterial();
    private static final ItemGroup caribbean_group = FabricItemGroupBuilder.create(
            new Identifier("caribbean", "caribbean_group"))
            .icon(() -> new ItemStack(islands_book))
            .appendItems(stacks -> {
                stacks.add(new ItemStack(islands_book));
                stacks.add(new ItemStack(path_book));
            })
            .build();

    @Override
    public void onInitialize() {
        Registry.register(
                Registry.ITEM,
                new Identifier("caribbean", "islands_book"),
                islands_book
        );
        Registry.register(
                Registry.ITEM,
                new Identifier("caribbean", "path_book"),
                path_book
        );
        Registry.register(Registry.ITEM,new Identifier("caribean","pirate_helmet"),new BaseArmor(FABRIC_ARMOR, EquipmentSlot.HEAD));
        Registry.register(Registry.ITEM,new Identifier("caribean","pirate_chestplate"),new BaseArmor(FABRIC_ARMOR, EquipmentSlot.CHEST));
        Registry.register(Registry.ITEM,new Identifier("caribean","pirate_leggings"),new BaseArmor(FABRIC_ARMOR, EquipmentSlot.LEGS));
        Registry.register(Registry.ITEM,new Identifier("caribean","pirate_boots"),new BaseArmor(FABRIC_ARMOR, EquipmentSlot.FEET));
    }
}