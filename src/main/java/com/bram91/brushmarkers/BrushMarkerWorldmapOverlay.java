/*
 * Copyright (c) 2019, Benjamin <https://github.com/genetic-soybean>
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

import net.runelite.api.Client;
import net.runelite.api.Point;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.api.worldmap.WorldMap;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

class BrushMarkerWorldmapOverlay extends Overlay
{
	private static final int REGION_SIZE = 1 << 6;
	// Bitmask to return first coordinate in region
	private static final int REGION_TRUNCATE = ~((1 << 6) - 1);

	private final Client client;
	private final BrushMarkerConfig config;
	private final BrushMarkerPlugin plugin;

	@Inject
	private BrushMarkerWorldmapOverlay(Client client, BrushMarkerConfig config, BrushMarkerPlugin plugin)
	{
		this.client = client;
		this.config = config;
		this.plugin = plugin;
		setPosition(OverlayPosition.DYNAMIC);
		setPriority(0.0f);
		setLayer(OverlayLayer.MANUAL);
		drawAfterInterface(InterfaceID.WORLD_MAP);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (!config.drawTilesOnWorldMap())
		{
			return null;
		}

		drawOnWorldMap(graphics);

		return null;
	}

	private void drawOnWorldMap(Graphics2D graphics)
	{
		WorldMap worldMap = client.getWorldMap();
		Widget map = client.getWidget(ComponentID.WORLD_MAP_MAPVIEW);
		float pixelsPerTile = worldMap.getWorldMapZoom();
		if (map == null)
		{
			return;
		}
		Rectangle worldMapRect = map.getBounds();
		graphics.setClip(worldMapRect);


		int widthInTiles = (int) Math.ceil(worldMapRect.getWidth() / pixelsPerTile);
		int heightInTiles = (int) Math.ceil(worldMapRect.getHeight() / pixelsPerTile);

		Point worldMapPosition = worldMap.getWorldMapPosition();

		// Offset in tiles from anchor sides
		int yTileMin = worldMapPosition.getY() - heightInTiles / 2;
		int xRegionMin = (worldMapPosition.getX() - widthInTiles / 2) & REGION_TRUNCATE;
		int xRegionMax = ((worldMapPosition.getX() + widthInTiles / 2) & REGION_TRUNCATE) + REGION_SIZE;
		int yRegionMin = (yTileMin & REGION_TRUNCATE);
		int yRegionMax = ((worldMapPosition.getY() + heightInTiles / 2) & REGION_TRUNCATE) + REGION_SIZE;
		int regionPixelSize = (int) Math.ceil(REGION_SIZE * pixelsPerTile);

		for (int x = xRegionMin; x < xRegionMax; x += REGION_SIZE)
		{
			for (int y = yRegionMin; y < yRegionMax; y += REGION_SIZE)
			{
				int regionId = ((x >> 6) << 8) | (y >> 6);
				for (final BrushMarkerPoint point : plugin.getWorldPoints(regionId))
				{
					int yTileOffset = -(yTileMin - y);
					int xTileOffset = x + widthInTiles / 2 - worldMapPosition.getX();
					int xPos = ((int) (xTileOffset * pixelsPerTile)) + (int) worldMapRect.getX();
					int yPos = (worldMapRect.height - (int) (yTileOffset * pixelsPerTile)) + (int) worldMapRect.getY();
					int size = regionPixelSize / 64;

					graphics.setColor(point.getColor());
					graphics.drawRect(xPos + (point.getRegionX() * size), yPos - (point.getRegionY() * size) + size, size - 1, size - 1);

				}
			}
		}

	}
}
