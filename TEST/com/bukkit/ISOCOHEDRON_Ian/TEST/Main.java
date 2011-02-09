package com.bukkit.ISOCOHEDRON_Ian.TEST;

import java.util.ArrayList;

import org.bukkit.block.Block;

public class Main implements Runnable{

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	@Override
	public void run() {
	World world = new World();
		world.load();
		Point p = TEST.getSpawn();
		p.x = World.WorldDimension/2;
		p.z = World.WorldDimension/2;
		
		world.Nest = new Nest(world.Blocks[p.x][p.y][p.z], 10);
		
		ArrayList<Ant> ants = new ArrayList<Ant>(); 
		
		for (int i = 0; i < 10; i++) {
			ants.add(new Ant(world.Blocks[p.x][p.y][p.z], 64));
		}
		
		world.Ants = (Ant[]) ants.toArray();
		
		int count = 10000;
		
		while (count > 0)
		{
			for (Ant currentAnt : world.Ants)
			{
				ArrayList<com.bukkit.ISOCOHEDRON_Ian.TEST.Block> adjacentBlocks = world.GetBlocksAdjacentToAnt(currentAnt);
				currentAnt.MoveRandomly(adjacentBlocks);
			}
			
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException exception) {
				// TODO Auto-generated catch-block stub.
				exception.printStackTrace();
			}
			
			count--;
		}
		
	}

	
}