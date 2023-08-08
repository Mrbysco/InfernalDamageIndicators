package com.mrbysco.infernalhealth.client;

import atomicstryker.infernalmobs.common.InfernalMobsCore;
import atomicstryker.infernalmobs.common.MobModifier;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;

public class InfernalHelper {

	public static String getInfernalName(LivingEntity entity) {
		MobModifier modifier = InfernalMobsCore.getMobModifiers(entity);
		if (modifier != null) {
			return modifier.getEntityDisplayName(entity);
		}
		return "";
	}

	public static void draw(Minecraft mc, PoseStack matrix, LivingEntity entity) {
		MobModifier modifier = InfernalMobsCore.getMobModifiers(entity);
		if (modifier != null) {
			String[] display = modifier.getDisplayNames();
			int yOffset = 14;
			for (int i = 0; i < display.length && display[i] != null; ++i) {
				yOffset += 10;
				mc.font.drawShadow(matrix, display[i], 0, (float) yOffset, 16777215);
			}
		}
	}
}
