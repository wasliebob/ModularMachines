/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.helpers;

import net.minecraft.client.renderer.Tessellator;

public class IconHelper {
	public static void drawIcon(int x, int y, int width, int height, float zLevel)
	{
	    Tessellator t = Tessellator.instance;
	    t.startDrawingQuads();
	    t.setColorOpaque(255, 255, 51);
	    t.addVertexWithUV(x + 0, y + height, zLevel, 0D, 1D);
	    t.addVertexWithUV(x + width, y + height, zLevel, 1D, 1D);
	    t.addVertexWithUV(x + width, y + 0, zLevel, 1D, 0D);
	    t.addVertexWithUV(x + 0, y + 0, zLevel, 0D, 0D);
	    t.draw();
	}
	
	public static void drawIconWithoutColor(int x, int y, int width, int height, float zLevel)
	{
	    Tessellator t = Tessellator.instance;
	    t.startDrawingQuads();
	    t.addVertexWithUV(x + 0, y + height, zLevel, 0D, 1D);
	    t.addVertexWithUV(x + width, y + height, zLevel, 1D, 1D);
	    t.addVertexWithUV(x + width, y + 0, zLevel, 1D, 0D);
	    t.addVertexWithUV(x + 0, y + 0, zLevel, 0D, 0D);
	    t.draw();
	}
	
	public static void drawVertexWithColor(Tessellator t, int red, int green, int blue)
	{
		t.setColorOpaque(red, green, blue);
		t.addVertexWithUV(-0.5D, 0.5D, 0, 1, 0);
		t.addVertexWithUV(0.5D, 0.5D, 0, 0, 0);
		t.addVertexWithUV(0.5D, -0.5D, 0, 0, 1);
		t.addVertexWithUV(-0.5D, -0.5D, 0, 1, 1);
	}
	
	public static void drawVertex(Tessellator t)
	{
		t.addVertexWithUV(-0.5D, 0.5D, 0, 1, 0);
		t.addVertexWithUV(0.5D, 0.5D, 0, 0, 0);
		t.addVertexWithUV(0.5D, -0.5D, 0, 0, 1);
		t.addVertexWithUV(-0.5D, -0.5D, 0, 1, 1);
	}

	public static void drawIcon(int x, int y, int width, int height, float zLevel, int red, int green, int blue)
	{
	    Tessellator t = Tessellator.instance;
	    t.startDrawingQuads();
	    t.setColorOpaque(255, 255, 51);
	    t.setColorOpaque(red, green, blue);
	    t.addVertexWithUV(x + 0, y + height, zLevel, 0D, 1D);
	    t.addVertexWithUV(x + width, y + height, zLevel, 1D, 1D);
	    t.addVertexWithUV(x + width, y + 0, zLevel, 1D, 0D);
	    t.addVertexWithUV(x + 0, y + 0, zLevel, 0D, 0D);
	    t.draw();
	}
}