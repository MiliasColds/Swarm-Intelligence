package com.bukkit.ISOCOHEDRON_Ian.TEST;
public class Block {
	public Point Position;
	public int ID;
	public float r_pheromone,e_pheromone = 0.00000001f;
	public Block(Point position,int ID)
	{
		this.ID = ID;
		this.Position = position;
	}
	
	
	public String toString(){
		return ""+this.ID+" @:"+this.Position;
	}
	

	public boolean equals(Object o){
		
		if(o instanceof com.bukkit.ISOCOHEDRON_Ian.TEST.Block){
			com.bukkit.ISOCOHEDRON_Ian.TEST.Block b = (com.bukkit.ISOCOHEDRON_Ian.TEST.Block)o;
			if(b.Position.equals(this.Position)){
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


	public float getHardness() {
		
		return 1;
	}
}
