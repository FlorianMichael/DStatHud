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

package de.florianmichael.dstathud;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

public class NetworkTrafficHandler extends ByteToMessageCodec<ByteBuf> {
    public final static String NETWORK_TRAFFIC_HANDLER_NAME = "network-traffic";

    public static long outgoingBytes = 0L;
    public static long incomingBytes = 0L;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        outgoingBytes = in.readableBytes();
        out.add(ctx.alloc().buffer().writeBytes(in).retain());
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) {
        incomingBytes = msg.readableBytes();
        out.writeBytes(msg);
    }
}
