package com.bukkit.ISOCOHEDRON_Ian.TEST;

public class Block implements Comparable{
	public Point Position;
	public int ID;
	public float r_pheromone = (float) 1,e_pheromone = (float) 1;
	public World World;
	
	public Block(Point position,int ID, World world)
	{
		this.ID = ID;
		this.Position = position;
		this.World = world;
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


	public double getHardness() {
		if(this.ID!=16){
			return 800 - (1/(this.World.Nest.Position.Position.DistanceTo(this.Position)+1))*400;
		}
		else {
			return (800 - (1/(this.World.Nest.Position.Position.DistanceTo(this.Position)+1))*400)-200;
		}
	}


	@Override
	public int compareTo(Object o) {
		if(o instanceof com.bukkit.ISOCOHEDRON_Ian.TEST.Block){
			com.bukkit.ISOCOHEDRON_Ian.TEST.Block b = (com.bukkit.ISOCOHEDRON_Ian.TEST.Block)o;
			return this.Position.compareTo(b.Position);
		}
		return 0;
	}
}
