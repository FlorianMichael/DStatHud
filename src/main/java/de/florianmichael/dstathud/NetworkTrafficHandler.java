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

import io.netty.handler.traffic.ChannelTrafficShapingHandler;
import io.netty.handler.traffic.TrafficCounter;

public class NetworkTrafficHandler extends ChannelTrafficShapingHandler {
    public final static String NETWORK_TRAFFIC_HANDLER_NAME = "network-traffic";

    public static long incoming = 0;
    public static long outgoing = 0;

    public NetworkTrafficHandler() {
        super(1000);
    }

    @Override
    protected void doAccounting(TrafficCounter counter) {
        incoming = counter.lastReadThroughput();
        outgoing = counter.lastWriteThroughput();
    }
}
