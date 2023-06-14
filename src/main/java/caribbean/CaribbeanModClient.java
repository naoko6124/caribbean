package caribbean;

import caribbean.datastorage.DataStorage;
import caribbean.datastorage.Island;
import caribbean.item.PirateCompass;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.item.ModelPredicateProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class CaribbeanModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FabricModelPredicateProviderRegistry.register(CaribbeanMod.piratecompass, new Identifier("angle"), new ModelPredicateProvider() {
            private double rotation;
            private double rota;
            private long lastUpdateTick;

            @Override
            public float call(ItemStack stack, ClientWorld world, LivingEntity entityLiving) {
                if (entityLiving == null && !stack.isInFrame()) {
                    return 0.0F;
                } else {
                    final boolean entityExists = entityLiving != null;
                    final Entity entity = (Entity) (entityExists ? entityLiving : stack.getFrame());
                    if (world == null && entity.world instanceof ClientWorld) {
                        world = (ClientWorld) entity.world;
                    }

                    double rotation = entityExists ? (double) entity.yaw : getFrameRotation((ItemFrameEntity) entity);
                    rotation = rotation % 360.0D;
                    double adjusted = Math.PI - ((rotation - 90.0D) * 0.01745329238474369D - getAngle(world, entity, stack));

                    if (entityExists) {
                        adjusted = wobble(world, adjusted);
                    }

                    final float f = (float) (adjusted / (Math.PI * 2D));
                    return MathHelper.floorMod(f, 1.0F);
                }
            }

            private double wobble(ClientWorld world, double amount) {
                if (world.getTime() != lastUpdateTick) {
                    lastUpdateTick = world.getTime();
                    double d0 = amount - rotation;
                    d0 = d0 % (Math.PI * 2D);
                    d0 = MathHelper.clamp(d0, -1.0D, 1.0D);
                    rota += d0 * 0.1D;
                    rota *= 0.8D;
                    rotation += rota;
                }

                return rotation;
            }

            private double getFrameRotation(ItemFrameEntity itemFrame) {
                return (double) MathHelper.wrapDegrees(180 + itemFrame.getHorizontalFacing().getHorizontal() * 90);
            }

            private double getAngle(ClientWorld world, Entity entity, ItemStack stack) {
                if (stack.getItem() == CaribbeanMod.piratecompass) {
                    PirateCompass compassItem = (PirateCompass) stack.getItem();
                    BlockPos pos;
                    if (DataStorage.getInstance().currentIsland != null) {
                        pos = new BlockPos(DataStorage.getInstance().currentIsland.x, 0, DataStorage.getInstance().currentIsland.z);
                    } else {
                        pos = world.getSpawnPos();
                    }
                    double xdif = pos.getX() - entity.getPos().x;
                    double zdif = pos.getZ() - entity.getPos().z;
                    double distance = Math.sqrt(Math.pow(xdif, 2) + Math.pow(zdif, 2));
                    if (distance < 3 && DataStorage.getInstance().currentIsland != null) {
                        if (DataStorage.getInstance().rota.vazia()) {
                            DataStorage.getInstance().currentIsland = null;
                            entity.sendSystemMessage(Text.of("Chegou ao Destino!"), entity.getUuid());
                        }
                        else {
                            DataStorage.getInstance().currentIsland = (Island)DataStorage.getInstance().rota.desenfileirar();
                            entity.sendSystemMessage(Text.of("Próxima parada, " + DataStorage.getInstance().currentIsland.name + "!"), entity.getUuid());
                        }
                    }
                    return Math.atan2((double) pos.getZ() - entity.getPos().z, (double) pos.getX() - entity.getPos().x);
                }
                return 0.0D;
            }
        });
    }
}
