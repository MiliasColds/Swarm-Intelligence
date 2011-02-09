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
	
	
	public boolean equals(Object o){
		
		if(o instanceof com.bukkit.ISOCOHEDRON_Ian.TEST.Point){
			com.bukkit.ISOCOHEDRON_Ian.TEST.Point p = (com.bukkit.ISOCOHEDRON_Ian.TEST.Point)o;
			if(p.x==this.x && p.y==this.y && p.z==this.z){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
		
	}
}