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
		this.state = ANT_STATE.Exploring;
		this.Position = position;
		this.Previous = position;
		this.LifeTime = LifeTime;
	}
	
	public void MoveRandomly(ArrayList<com.bukkit.ISOCOHEDRON_Ian.TEST.Block> adjacentBlocks)
	{
		com.bukkit.ISOCOHEDRON_Ian.TEST.Block targetBlock = this.Position;// = adjacentBlocks.get(random.nextInt(adjacentBlocks.size()));
		adjacentBlocks.remove(this.Previous);
		switch(this.state){
			case Exploring:
				Random random = new Random();
				float r = random.nextFloat();
				float total = 0;
				for(int i = 0; i < adjacentBlocks.size(); i ++ ) {
					float totalx = 1;
					totalx *= (float) Math.pow(adjacentBlocks.get(i).r_pheromone,this.a) ;
					totalx *= (float) Math.pow(adjacentBlocks.get(i).e_pheromone,this.b) ;
					totalx *= (float) Math.pow(adjacentBlocks.get(i).getHardness(),this.g);
					total+=totalx;
				}
				float probabilities[] = new float[adjacentBlocks.size()];
				for(int i = 0; i < adjacentBlocks.size(); i ++ ) {
					float R = (float) Math.pow(adjacentBlocks.get(i).r_pheromone,this.a) ;
					float E = (float) Math.pow(adjacentBlocks.get(i).e_pheromone,this.b) ;
					float H = (float) Math.pow(adjacentBlocks.get(i).getHardness(),this.g);
					//System.out.println(R);
					//System.out.println(E);
					//sSystem.out.println(H);
					probabilities[i] = (R*E*H)/(total);
				}
				for(int m=0;m<probabilities.length;m++){
					System.out.println(probabilities[m]);
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
						this.heldBlock = this.Position; // new com.bukkit.ISOCOHEDRON_Ian.TEST.Block( targetBlock.Position,TEST.getBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z));
						if(!this.Position.equals(this.Previous)){
							this.Previous.e_pheromone += 1/this.Previous.getHardness();
						}
						this.state = ANT_STATE.Resource;
						TEST.setBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z,90);
						TEST.setBlockAt(this.Previous.Position.x,this.Previous.Position.y,this.Previous.Position.z,20);
						this.Previous = this.Position;
						this.Position = targetBlock;
						System.out.println(targetBlock);
					}
					else { 
						this.LifeTime --;
						//TEST.removeBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z);
						if(!this.Position.equals(this.Previous)){
							this.Previous.e_pheromone += 1/this.Previous.getHardness();
						}
						this.Previous = this.Position;
						TEST.setBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z,90);
						TEST.setBlockAt(this.Previous.Position.x,this.Previous.Position.y,this.Previous.Position.z,20);
						this.Position = targetBlock;
						System.out.println(targetBlock);
					}
				}
				catch(NullPointerException e){
					System.out.println(targetBlock.Position);
					e.printStackTrace();
				}
				break;
			case Returning:
				this.Position = this.Nest.Position;
				this.state = ANT_STATE.Exploring;
				this.LifeTime = 64;
				break;
			case Resource:
				this.Previous.r_pheromone += 0.01;
				float e_amounts[] = new float[adjacentBlocks.size()]; // exploratory
				
				com.bukkit.ISOCOHEDRON_Ian.TEST.Block maximum = adjacentBlocks.get(0);
				for(int i = 0; i < adjacentBlocks.size(); i ++ ) {
					if(adjacentBlocks.get(i).e_pheromone > maximum.e_pheromone)
					{
						maximum = adjacentBlocks.get(i);
					}
				}
				targetBlock = maximum;
				
				try{
					if(TEST.getBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z)==16){
						this.heldBlock = this.Position; //new com.bukkit.ISOCOHEDRON_Ian.TEST.Block( targetBlock.Position,TEST.removeBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z));
						TEST.setBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z,90);
						TEST.setBlockAt(this.Previous.Position.x,this.Previous.Position.y,this.Previous.Position.z,0);
						this.Previous = this.Position;
						this.Position = targetBlock;
						System.out.println(targetBlock);
					}
					else { 
						TEST.removeBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z);
						TEST.setBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z,90);
						TEST.setBlockAt(this.Previous.Position.x,this.Previous.Position.y,this.Previous.Position.z,0);
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

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param nest2
	 */
	public void setNest(Nest nest2) {
		this.Nest = nest2;
		
	}
}
