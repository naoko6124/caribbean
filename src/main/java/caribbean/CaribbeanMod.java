package caribbean;

import caribbean.item.IslandsBook;
import caribbean.item.PathBook;
import caribbean.item.PirateCompass;
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

public class CaribbeanMod implements ModInitializer {
    public static IslandsBook islands_book = new IslandsBook(new FabricItemSettings().maxCount(1));
    public static PathBook path_book = new PathBook(new FabricItemSettings().maxCount(1));
    public static PirateCompass piratecompass = new PirateCompass(new FabricItemSettings().maxCount(1));
    public static final ArmorMaterial pirate_armor = new FabricArmorMaterial();
    public static BaseArmor pirate_helmet = new BaseArmor(pirate_armor, EquipmentSlot.HEAD);
    public static BaseArmor pirate_chestplate = new BaseArmor(pirate_armor, EquipmentSlot.CHEST);
    public static BaseArmor pirate_leggings = new BaseArmor(pirate_armor, EquipmentSlot.LEGS);
    public static BaseArmor pirate_boots = new BaseArmor(pirate_armor, EquipmentSlot.FEET);
    private static final ItemGroup caribbean_group = FabricItemGroupBuilder.create(
            new Identifier("caribbean", "caribbean_group"))
            .icon(() -> new ItemStack(islands_book))
            .appendItems(stacks -> {
                stacks.add(new ItemStack(islands_book));
                stacks.add(new ItemStack(path_book));
                stacks.add(new ItemStack(piratecompass));
                stacks.add(new ItemStack(pirate_helmet));
                stacks.add(new ItemStack(pirate_chestplate));
                stacks.add(new ItemStack(pirate_leggings));
                stacks.add(new ItemStack(pirate_boots));
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
        Registry.register(Registry.ITEM, new Identifier("caribbean", "piratecompass"), piratecompass);
        Registry.register(Registry.ITEM, new Identifier("caribbean","pirate_helmet"), pirate_helmet);
        Registry.register(Registry.ITEM, new Identifier("caribbean","pirate_chestplate"), pirate_chestplate);
        Registry.register(Registry.ITEM, new Identifier("caribbean","pirate_leggings"), pirate_leggings);
        Registry.register(Registry.ITEM, new Identifier("caribbean","pirate_boots"), pirate_boots);
    }
}