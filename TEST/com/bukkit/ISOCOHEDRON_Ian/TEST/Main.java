package com.bukkit.ISOCOHEDRON_Ian.TEST;

import java.util.ArrayList;

import org.bukkit.block.Block;

public class Main implements Runnable{

	public static Point p;
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	@Override
	public void run() {
	World world = new World();
		world.load();
		p = TEST.getSpawn();
		p.x = World.WorldDimension/2;
		p.z = World.WorldDimension/2;
		p.y = World.WorldDimension_H/2;
		System.out.println("RealPos: " + TEST.spawnx + " " + TEST.spawny + " " + TEST.spawnz);
		System.out.println("LocalPos: " + p.x + " " + p.y + " " + p.z);
		world.Nest = new Nest(world.Blocks[p.x][p.y][p.z], 10);
		
		//TEST.current_world.getBlockAt(TEST.spawnx,TEST.spawny,TEST.spawnz).setTypeId(11);
		
		TEST.setBlockAt(p.x, p.y+10, p.z, 57);
		
		ArrayList<Ant> ants = new ArrayList<Ant>(); 
		
		for (int i = 0; i < 10; i++) {
			Ant a = new Ant(world.Blocks[p.x][p.y][p.z], 64);
			a.setNest(world.Nest);
			ants.add(a);
			
		}
		
		world.Ants =  ants;
		
		int count = 1000;
		
		while (count > 0)
		{
			for (Ant currentAnt : world.Ants)
			{
				ArrayList<com.bukkit.ISOCOHEDRON_Ian.TEST.Block> adjacentBlocks = world.GetBlocksAdjacentToAnt(currentAnt);
				currentAnt.MoveRandomly(adjacentBlocks);
				try {
					Thread.currentThread().sleep(10);
				} catch (InterruptedException exception) {
					// TODO Auto-generated catch-block stub.
					exception.printStackTrace();
				}
			}
			
//			try {
//				Thread.currentThread().sleep(100);
//			} catch (InterruptedException exception) {
//				// TODO Auto-generated catch-block stub.
//				exception.printStackTrace();
//			}
			if(count%64==0)world.evaporate();
			count--;
			
		}
		
		System.out.println("Finished!")
		
	}
}