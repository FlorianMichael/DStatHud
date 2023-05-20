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

// Copied from FlorianMichael/RClasses
public class StringUtils {
    public static String formatBytes(final long value) {
        if (value < 1024L)
            return value + "B";
        else if (value < 1024L * 1024L)
            return String.format("%.2f", (((double) value / 1024.0))) + "Kb";
        else if (value < 1024L * 1024L * 1024L)
            return String.format("%.2f", (((double) value / 1024.0 / 1024.0))) + "Mb";
        else if (value < 1024L * 1024L * 1024L * 1024L)
            return String.format("%.2f", (((double) value / 1024.0 / 1024.0 / 1024.0))) + "Gb";
        else
            return String.format("%.2f", (((double) value / 1024.0 / 1024.0 / 1024.0 / 1024.0))) + "Tb";
    }
}
