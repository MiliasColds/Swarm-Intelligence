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
		Ant ant = new Ant(new Point(0, 0, 0));
		world.Ants = new Ant[]{ ant };
		
		int count = 100;
		
		while (count > 0)
		{
			for (Ant currentAnt : world.Ants)
			{
				ArrayList<com.bukkit.ISOCOHEDRON_Ian.TEST.Block> adjacentBlocks = world.GetBlocksAdjacentToAnt(currentAnt);
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
