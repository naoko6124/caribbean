package caribbean.mixin;

import caribbean.item.PirateCompass;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {

	@Shadow
	private ItemStack mainHand;

	@Shadow
	private ItemStack offHand;

	@Shadow
	@Final
	private MinecraftClient client;

	@Inject(method = "updateHeldItems()V", at = @At("HEAD"))
	private void cancelCompassAnimation(CallbackInfo ci) {
		ItemStack newMainStack = client.player.getMainHandStack();
		if (newMainStack.getItem() instanceof PirateCompass && mainHand.getItem() instanceof PirateCompass) {
			PirateCompass newMainCompass = (PirateCompass) newMainStack.getItem();
			PirateCompass mainCompass = (PirateCompass) mainHand.getItem();
			mainHand = newMainStack;
		}

		ItemStack newOffStack = client.player.getOffHandStack();
		if (newOffStack.getItem() instanceof PirateCompass && offHand.getItem() instanceof PirateCompass) {
			PirateCompass newOffCompass = (PirateCompass) newOffStack.getItem();
			PirateCompass offCompass = (PirateCompass) offHand.getItem();
			offHand = newOffStack;
		}
	}
}