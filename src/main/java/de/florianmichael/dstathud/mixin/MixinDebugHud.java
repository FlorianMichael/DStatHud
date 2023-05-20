/*
 * This file is part of DStatHud - https://github.com/FlorianMichael/DStatHud
 * Copyright (C) 2023 FlorianMichael/EnZaXD and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.florianmichael.dstathud.mixin;

import de.florianmichael.dstathud.NetworkTrafficHandler;
import de.florianmichael.dstathud.StringUtils;
import it.unimi.dsi.fastutil.longs.LongSet;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.entity.Entity;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(DebugHud.class)
public class MixinDebugHud {

    @Unique
    private long dstathud_lastUpdatedTime = System.currentTimeMillis();

    @Unique
    private long dstathud_outgoingTraffic = 0L;

    @Unique
    private long dstathud_incomingTraffic = 0L;

    @Inject(method = "getLeftText", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/DebugHud;getServerWorldDebugString()Ljava/lang/String;"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void showDStat(CallbackInfoReturnable<List<String>> cir, String string, IntegratedServer integratedServer, ClientConnection clientConnection, float f, float g, BlockPos blockPos, Entity entity, Direction direction, String string2, ChunkPos chunkPos, World world, LongSet longSet, List<String> list) {
        if (MinecraftClient.getInstance().isInSingleplayer()) return;

        if ((System.currentTimeMillis() - dstathud_lastUpdatedTime) > 1000) {
            dstathud_outgoingTraffic = (long) MathHelper.lerp(0.75, NetworkTrafficHandler.outgoingBytes, dstathud_outgoingTraffic);
            dstathud_incomingTraffic = (long) MathHelper.lerp(0.75, NetworkTrafficHandler.incomingBytes, dstathud_incomingTraffic);
            NetworkTrafficHandler.outgoingBytes = 0L;
            NetworkTrafficHandler.incomingBytes = 0L;
            dstathud_lastUpdatedTime = System.currentTimeMillis();
        }

        list.add(Formatting.GOLD + StringUtils.formatBytes(dstathud_outgoingTraffic) + " Up/s, " + StringUtils.formatBytes(dstathud_incomingTraffic) + " Down/s");
    }
}
