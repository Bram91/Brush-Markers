/*
 * Copyright (c) 2018, TheLonelyDev <https://github.com/TheLonelyDev>
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
 * Copyright (c) 2020, Bram91 <https://github.com/Bram91>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.bram91.brushmarkers;

import java.awt.Color;
import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("brushMarkers")
public interface BrushMarkerConfig extends Config
{
	@Alpha
	@ConfigItem(
		keyName = "brushColor1",
		name = "Color1",
		description = "Configures the color of marked tile",
		position = 0
	)
	default Color markerColor1()
	{
		return Color.RED;
	}

	@Alpha
	@ConfigItem(
		keyName = "brushColor2",
		name = "Color 2",
		description = "Configures the color of marked tile",
		position = 1
	)
	default Color markerColor2()
	{
		return Color.GREEN;
	}

	@Alpha
	@ConfigItem(
		keyName = "brushColor3",
		name = "Color 3",
		description = "Configures the color of marked tile",
		position = 2
	)
	default Color markerColor3()
	{
		return Color.BLUE;
	}

	@Alpha
	@ConfigItem(
		keyName = "brushColor4",
		name = "Color 4",
		description = "Configures the color of marked tile",
		position = 3
	)
	default Color markerColor4()
	{
		return Color.YELLOW;
	}

	@Alpha
	@ConfigItem(
		keyName = "brushColor5",
		name = "Color 5",
		description = "Configures the color of marked tile",
		position = 4
	)
	default Color markerColor5()
	{
		return Color.CYAN;
	}

	@Alpha
	@ConfigItem(
		keyName = "brushColor6",
		name = "Color 6",
		description = "Configures the color of marked tile",
		position = 5
	)
	default Color markerColor6()
	{
		return Color.MAGENTA;
	}

	@ConfigItem(
		keyName = "brushdrawOnMinimap",
		name = "Draw tiles on minimap",
		description = "Configures whether marked tiles should be drawn on minimap",
		position = 6
	)
	default boolean drawTileOnMinimmap()
	{
		return false;
	}

	@ConfigItem(
		keyName = "brushpaintMode",
		name = "Paint Mode",
		description = "Enables paint mode",
		position = 7
	)
	default boolean paintMode()
	{
		return false;
	}
}
