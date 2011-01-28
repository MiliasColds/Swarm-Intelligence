package com.bukkit.ISOCOHEDRON_Ian.TEST;
import java.util.ArrayList;

import org.bukkit.block.Block;

public class World {
	public Ant[] Ants;
	public com.bukkit.ISOCOHEDRON_Ian.TEST.Block[][][] Blocks ;
	public Nest Nest;
	
	public int WorldDimension = 15;
	
	
	public World(){
		this.Blocks = new com.bukkit.ISOCOHEDRON_Ian.TEST.Block[WorldDimension][WorldDimension][128];
	}
	
	
	public ArrayList<com.bukkit.ISOCOHEDRON_Ian.TEST.Block> GetBlocksAdjacentToAnt(Ant currentAnt) 
	{
		ArrayList<com.bukkit.ISOCOHEDRON_Ian.TEST.Block> blocks = new ArrayList<com.bukkit.ISOCOHEDRON_Ian.TEST.Block>();
		Point antPos = currentAnt.Position;
		
		if (antPos.x - 1 > 0) // Left
			blocks.add(Blocks[antPos.x-1][antPos.y][antPos.z]);
		if (antPos.y - 1 > 0) // Back
			blocks.add(Blocks[antPos.x][antPos.y-1][antPos.z]);
		if (antPos.z - 1 > 0) // Down
			blocks.add(Blocks[antPos.x][antPos.y][antPos.z-1]);
		if (antPos.x + 1 < WorldDimension-1) // Right
			blocks.add(Blocks[antPos.x+1][antPos.y][antPos.z]);
		if (antPos.y + 1 < WorldDimension-1) // Forward
			blocks.add(Blocks[antPos.x][antPos.y+1][antPos.z]);
		if (antPos.z + 1 < WorldDimension-1) // Up
			blocks.add(Blocks[antPos.x][antPos.y][antPos.z+1]);
		
		return blocks;
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 */
	public void load() {
		while(TEST.current_world==null){}
		for(int i=1;i<15;i++){
			 for(int j=1;j<15;j++){
				 for(int k=1;k<128;k++){
					 Block b = TEST.current_world.getBlockAt(i,j,k);
					 this.Blocks[i][j][k] = new com.bukkit.ISOCOHEDRON_Ian.TEST.Block(new Point(i,j,k),b.getTypeId());
				 }
			 }
		 }
		
	}
}
