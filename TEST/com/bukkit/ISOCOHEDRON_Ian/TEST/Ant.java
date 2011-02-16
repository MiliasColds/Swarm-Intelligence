package com.bukkit.ISOCOHEDRON_Ian.TEST;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Ant 
{
	public com.bukkit.ISOCOHEDRON_Ian.TEST.Block Position;
	public com.bukkit.ISOCOHEDRON_Ian.TEST.Block Previous;
	public com.bukkit.ISOCOHEDRON_Ian.TEST.Block heldBlock;
	public float a,b,g;
	public int Threshold;
	private int LifeTime;
	private float b_deg = 0.25f;;
	private enum ANT_STATE {
		Exploring,
		Returning,
		Resource
	}
	public ANT_STATE state;
	private Nest Nest;
	public Ant(com.bukkit.ISOCOHEDRON_Ian.TEST.Block position, int LifeTime)
	{
		a = 7;
		b = 4;
		g = 6;
		this.state = ANT_STATE.Exploring;
		this.Position = position;
		this.Previous = position;
		this.LifeTime = LifeTime;
	}
	
	public void MoveRandomly(ArrayList<com.bukkit.ISOCOHEDRON_Ian.TEST.Block> adjacentBlocks)
	{
		com.bukkit.ISOCOHEDRON_Ian.TEST.Block targetBlock = this.Position;// = adjacentBlocks.get(random.nextInt(adjacentBlocks.size()));
		this.b-=this.b_deg;
		switch(this.state){
			case Exploring:
				adjacentBlocks.remove(this.Previous);
				Random random = new Random();
				float r = random.nextFloat();
				float total = 0;
				for(int i = 0; i < adjacentBlocks.size(); i ++ ) {
					float totalx = 1;
					totalx *= (float) Math.pow(adjacentBlocks.get(i).r_pheromone,this.a) ;
					totalx *= (float) Math.pow(adjacentBlocks.get(i).e_pheromone,this.b) ;
					totalx *= (float) Math.pow(adjacentBlocks.get(i).getHardness(),this.g);
					totalx+=1e-6;
					total+=totalx;
				}
				float probabilities[] = new float[adjacentBlocks.size()];
				for(int i = 0; i < adjacentBlocks.size(); i ++ ) {
					float R = (float) Math.pow(adjacentBlocks.get(i).r_pheromone,this.a) ;
					float E = (float) Math.pow(adjacentBlocks.get(i).e_pheromone,this.b) ;
					float H = (float) Math.pow(adjacentBlocks.get(i).getHardness(),this.g);
					System.out.println("R:"+R);
					//System.out.println("E:"+E);
					//System.out.println("H:"+H);
					//System.out.println("total:"+total);
					probabilities[i] = (float) (((R*E*H)+1e-6)/(total));
				}
				for(int m=0;m<probabilities.length;m++){
					//System.out.println(probabilities[m]);
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
						this.Previous.e_pheromone += (800-this.Previous.getHardness())/20;
						this.Position.e_pheromone += (800-this.Position.getHardness())/20;
						this.state = ANT_STATE.Resource;
						TEST.setBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z,90);
						TEST.setBlockAt(this.Previous.Position.x,this.Previous.Position.y,this.Previous.Position.z,20);
						this.Previous = this.Position;
						this.Position = targetBlock;
						System.out.println(targetBlock);
					}
					else if(TEST.getBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z)==52){
						//this.heldBlock = this.Position; // new com.bukkit.ISOCOHEDRON_Ian.TEST.Block( targetBlock.Position,TEST.getBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z));
						this.Previous.e_pheromone += (800-this.Previous.getHardness())/20;
						//this.Position.e_pheromone += (800-this.Position.getHardness())/20;
						this.state = ANT_STATE.Resource;
						TEST.setBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z,90);
						TEST.setBlockAt(this.Previous.Position.x,this.Previous.Position.y,this.Previous.Position.z,52);
						this.Previous = this.Position;
						this.Position = targetBlock;
						System.out.println(targetBlock);
						this.LifeTime --;
					}
					else { 
						this.LifeTime --;
						//TEST.removeBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z);
						this.Previous.e_pheromone += (800-this.Previous.getHardness())/20;
						//System.out.println("layed :"+(800-this.Previous.getHardness())/20+" pheremone");
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
				this.b = 5;
				break;
			case Resource:
				this.Previous.r_pheromone += (800-this.Previous.getHardness())/5;
				//float e_amounts[] = new float[adjacentBlocks.size()]; // exploratory
				Collections.sort(adjacentBlocks);
				com.bukkit.ISOCOHEDRON_Ian.TEST.Block maximum = adjacentBlocks.get(0);
				for(int i = 0; i < adjacentBlocks.size(); i ++ ) {
					if(adjacentBlocks.get(i).e_pheromone > maximum.e_pheromone)
					{
						maximum = adjacentBlocks.get(i);
					}
					//System.out.println(i+"@"+adjacentBlocks.get(i).e_pheromone);
				}
				//System.console().readLine();
				targetBlock = maximum;
				
				try{
						//TEST.removeBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z);
						TEST.setBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z,90);
						TEST.setBlockAt(this.Previous.Position.x,this.Previous.Position.y,this.Previous.Position.z,57);
						this.Previous = this.Position;
						this.Position = targetBlock;
						System.out.println(targetBlock);
				}
				catch(NullPointerException e){
					System.out.println(targetBlock.Position);
				}
				if(this.Position.Position.DistanceTo(this.Nest.Position.Position)<=0){
					this.state = ANT_STATE.Exploring;
					this.b = 5;
				}
				break;
		}
		if(this.LifeTime <= 0)
		{
			this.state = ANT_STATE.Returning;
		}
		this.Position.World.P_Blocks.add(this.Previous);
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
