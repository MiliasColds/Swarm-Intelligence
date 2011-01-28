package com.bukkit.ISOCOHEDRON_Ian.TEST;
import java.util.ArrayList;
import java.util.Random;

public class Ant 
{
	public Point Position;
	
	public com.bukkit.ISOCOHEDRON_Ian.TEST.Block heldBlock;
	
	public int Threshold;
	
	public Ant(Point position)
	{
		this.Position = position;
	}
	
	public void MoveRandomly(ArrayList<com.bukkit.ISOCOHEDRON_Ian.TEST.Block> adjacentBlocks)
	{
		Random random = new Random();
		com.bukkit.ISOCOHEDRON_Ian.TEST.Block targetBlock = adjacentBlocks.get(random.nextInt(adjacentBlocks.size()));
		TEST.removeBlockAt(targetBlock.Position.x,targetBlock.Position.y,targetBlock.Position.z);
		this.Position = targetBlock.Position;
	}
}
