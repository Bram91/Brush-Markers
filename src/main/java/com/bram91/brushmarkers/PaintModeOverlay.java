package com.bram91.brushmarkers;

import com.google.inject.Inject;
import java.awt.Dimension;
import java.awt.Graphics2D;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import java.awt.Color;
import net.runelite.client.ui.overlay.components.TitleComponent;

public class PaintModeOverlay extends OverlayPanel
{
	private static final Color DANGER = new Color(255, 0, 0, 150);

	private final BrushMarkerConfig config;

	@Inject
	private PaintModeOverlay(BrushMarkerConfig config)
	{
		this.config = config;
		setPosition(OverlayPosition.TOP_LEFT);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (!config.paintMode())
		{
			return null;
		}
		panelComponent.getChildren().add(TitleComponent.builder()
			.text("Brush Markers")
			.build());
		panelComponent.getChildren().add(LineComponent.builder()
			.left("Brush mode enabled")
			.build());
		if(config.brushHotkey() != null)
		{
			panelComponent.getChildren().add(LineComponent.builder()
				.left("Hotkey set to: " + config.brushHotkey())
				.build());
		}

		panelComponent.setBackgroundColor(DANGER);

		return super.render(graphics);
	}
}
