package com.bukkit.ISOCOHEDRON_Ian.TEST;
import java.util.ArrayList;
import java.util.Random;

public class Ant 
{
	public com.bukkit.ISOCOHEDRON_Ian.TEST.Block Position;
	public com.bukkit.ISOCOHEDRON_Ian.TEST.Block Previous;
	public com.bukkit.ISOCOHEDRON_Ian.TEST.Block heldBlock;
	public float a,b,g;
	public int Threshold;
	private int LifeTime;
	private enum ANT_STATE {
		Exploring,
		Returning,
		Resource
	}
	public ANT_STATE state;
	private Nest Nest;
	public Ant(com.bukkit.ISOCOHEDRON_Ian.TEST.Block position, int LifeTime)
	{
		a = 5;
		b = 3;
		g = 7;
		this.Position = position;
		this.LifeTime = LifeTime;
	}
	
	public void MoveRandomly(ArrayList<com.bukkit.ISOCOHEDRON_Ian.TEST.Block> adjacentBlocks)
	{
		com.bukkit.ISOCOHEDRON_Ian.TEST.Block targetBlock = null;// = adjacentBlocks.get(random.nextInt(adjacentBlocks.size()));
		adjacentBlocks.remove(this.Previous);
		switch(state){
			case Exploring:
				Random random = new Random();
				
				float r = random.nextFloat();
				float r_total = 0; // resource
				float e_total = 0; // exploratory
				float hardness_total = 0;
				for(int i = 0; i < adjacentBlocks.size(); i ++ ) {
					r_total += adjacentBlocks.get(i).r_pheromone;
					e_total += adjacentBlocks.get(i).e_pheromone;
					hardness_total += adjacentBlocks.get(i).getHardness();
				}
				float probabilities[] = new float[adjacentBlocks.size()];
				for(int i = 0; i < adjacentBlocks.size(); i ++ ) {
					float R = (float)Math.pow(adjacentBlocks.get(i).r_pheromone,a) ;
					float E = (float) Math.pow(adjacentBlocks.get(i).e_pheromone,b) ;
					float H = (float) Math.pow(adjacentBlocks.get(i).getHardness(),g);
					probabilities[i] = (R*E*H)/(r_total*e_total*hardness_total);
				}
				float probability = 0;
				for(int i = 0; i < adjacentBlocks.size(); i ++ ) {
					probability += probabilities[i];
					if(r<=probability){
						targetBlock = adjacentBlocks.get(i);
						break;
					}
				}
				try{
					if(TEST.getBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z)==16){
						this.heldBlock =new com.bukkit.ISOCOHEDRON_Ian.TEST.Block( targetBlock.Position,TEST.removeBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z));
						this.Previous.e_pheromone += 1/Previous.getHardness();
						this.state = ANT_STATE.Resource;
						this.Previous = this.Position;
						this.Position = targetBlock;
						System.out.println(targetBlock);
					}
					else { 
						this.LifeTime --;
						TEST.removeBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z);
						this.Previous.e_pheromone += 1/Previous.getHardness();
						this.Previous = this.Position;
						this.Position = targetBlock;
						System.out.println(targetBlock);
					}
				}
				catch(NullPointerException e){
					System.out.println(targetBlock.Position);
				}
				break;
			case Returning:
				this.Position = this.Nest.Position;
				this.state = ANT_STATE.Exploring;
				break;
			case Resource:
				Previous.r_pheromone += 0.01;
				float e_amounts[] = new float[adjacentBlocks.size()]; // exploratory
				
				com.bukkit.ISOCOHEDRON_Ian.TEST.Block maximum = adjacentBlocks.get(0);
				for(int i = 0; i < adjacentBlocks.size(); i ++ ) {
					//e_amounts[i] = adjacentBlocks.get(i).e_pheromone;
					if(adjacentBlocks.get(i).e_pheromone > maximum.e_pheromone)
					{
						maximum = adjacentBlocks.get(i);
					}
				}
				targetBlock = maximum;
				
				try{
					if(TEST.getBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z)==16){
						this.heldBlock =new com.bukkit.ISOCOHEDRON_Ian.TEST.Block( targetBlock.Position,TEST.removeBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z));
						this.Previous = this.Position;
						this.Position = targetBlock;
						System.out.println(targetBlock);
					}
					else { 
						TEST.removeBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z);
						this.Previous = this.Position;
						this.Position = targetBlock;
						System.out.println(targetBlock);
					}
				}
				catch(NullPointerException e){
					System.out.println(targetBlock.Position);
				}
				break;
		}
		if(this.LifeTime <= 0)
		{
			this.state = ANT_STATE.Returning;
		}
	}
}
