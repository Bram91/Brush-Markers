package com.bram91.brushmarkers;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class BrushMarkersPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(BrushMarkerPlugin.class);
		RuneLite.main(args);
	}
}