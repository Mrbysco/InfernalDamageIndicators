package com.mrbysco.infernalhealth.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrbysco.infernalhealth.client.InfernalHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.torocraft.torohealth.display.BarDisplay;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BarDisplay.class, remap = false)
public class BarDisplayMixin {
	@Shadow
	@Final
	private Minecraft mc;

	@Inject(at = @At("HEAD"), method = "getEntityName(Lnet/minecraft/world/entity/LivingEntity;)Ljava/lang/String;", cancellable = true)
	private void infernalhealth_getEntityName(LivingEntity entity, CallbackInfoReturnable<String> cir) {
		String infernalName = InfernalHelper.getInfernalName(entity);
		if (!infernalName.isEmpty()) {
			cir.setReturnValue(infernalName);
		}
	}

	@Inject(at = @At("TAIL"), method = "draw(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/entity/LivingEntity;)V")
	public void infernalhealth_draw(PoseStack matrix, LivingEntity entity, CallbackInfo ci) {
		InfernalHelper.draw(mc, matrix, entity);
	}
}
