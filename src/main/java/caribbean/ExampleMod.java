package caribbean;

import caribbean.item.PirateBook;
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
    public static PirateBook pirate_book = new PirateBook(new FabricItemSettings().maxCount(1));
    public static final ArmorMaterial FABRIC_ARMOR = new FabricArmorMaterial();
    private static final ItemGroup caribbean_group = FabricItemGroupBuilder.create(
            new Identifier("caribbean", "caribbean_group"))
            .icon(() -> new ItemStack(pirate_book))
            .appendItems(stacks -> {
                stacks.add(new ItemStack(pirate_book));
            })
            .build();
    @Override
    public void onInitialize() {
        Registry.register(
                Registry.ITEM,
                new Identifier("caribbean", "pirate_book"),
                pirate_book
        );
        Registry.register(Registry.ITEM,new Identifier("caribean","pirate_helmet"),new BaseArmor(FABRIC_ARMOR, EquipmentSlot.HEAD));
        Registry.register(Registry.ITEM,new Identifier("caribean","pirate_chestplate"),new BaseArmor(FABRIC_ARMOR, EquipmentSlot.CHEST));
        Registry.register(Registry.ITEM,new Identifier("caribean","pirate_leggings"),new BaseArmor(FABRIC_ARMOR, EquipmentSlot.LEGS));
        Registry.register(Registry.ITEM,new Identifier("caribean","pirate_boots"),new BaseArmor(FABRIC_ARMOR, EquipmentSlot.FEET));
    }
}