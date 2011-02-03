package com.bukkit.ISOCOHEDRON_Ian.TEST;


/*
 * Generic Point class, contains an two-dimensional coordinate point. I figured we could throw in some
 * generic distance/etc. things in here if we needed to.
 */
public class Point {
	public int x;
	public int y;
	public int z;
	
	public Point(int xPos, int yPos, int zPos)
	{
		this.x = xPos;
		this.y = yPos;
		this.z = zPos;
	}
	
	
	public String toString(){
		return "("+this.x+", "+this.y+", "+this.z+")";
	}
}
