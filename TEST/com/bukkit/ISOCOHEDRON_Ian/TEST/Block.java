package com.bukkit.ISOCOHEDRON_Ian.TEST;
public class Block {
	public Point Position;
	public int ID;
	public Block(Point position,int ID)
	{
		this.ID = ID;
		this.Position = position;
	}
	
	
	public String toString(){
		return ""+this.ID+" at:"+this.Position;
		
	}
}
