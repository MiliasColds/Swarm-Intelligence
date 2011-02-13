package com.bukkit.ISOCOHEDRON_Ian.TEST;
import java.util.ArrayList;

import org.bukkit.block.Block;

public class World {
	public ArrayList<Ant> Ants;
	public com.bukkit.ISOCOHEDRON_Ian.TEST.Block[][][] Blocks ;
	public Nest Nest;
	public static int WorldDimension_H = 128;
	public static int WorldDimension = 40;
	
	
	public World(){
		this.Blocks = new com.bukkit.ISOCOHEDRON_Ian.TEST.Block[WorldDimension][WorldDimension_H][WorldDimension];
	}
	
	
	public ArrayList<com.bukkit.ISOCOHEDRON_Ian.TEST.Block> GetBlocksAdjacentToAnt(Ant currentAnt) 
	{
		ArrayList<com.bukkit.ISOCOHEDRON_Ian.TEST.Block> blocks = new ArrayList<com.bukkit.ISOCOHEDRON_Ian.TEST.Block>();
		Point antPos = currentAnt.Position.Position;
		
		if (antPos.x - 1 > 0) // Left
			blocks.add(Blocks[antPos.x-1][antPos.y][antPos.z]);
		if (antPos.y - 1 > 0) // Back
			//blocks.add(Blocks[antPos.x][antPos.y-1][antPos.z]);
		if (antPos.z - 1 > 0) // Down
			blocks.add(Blocks[antPos.x][antPos.y][antPos.z-1]);
		if (antPos.x + 1 < WorldDimension-1) // Right
			blocks.add(Blocks[antPos.x+1][antPos.y][antPos.z]);
		if (antPos.y + 1 < WorldDimension_H-1) // Forward
			//blocks.add(Blocks[antPos.x][antPos.y+1][antPos.z]);
		if (antPos.z + 1 < WorldDimension-1) // Up
			blocks.add(Blocks[antPos.x][antPos.y][antPos.z+1]);
		
		return blocks;
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 */
	public void load() {
		for(int i=0;i<WorldDimension;i++){
			 //for(int j=0;j<WorldDimension_H;j++){
				 for(int k=0;k<WorldDimension;k++){
					 TEST.setBlockAt(i,this.WorldDimension_H/2+5,k,1);
				 }
			 //}
		 }
		for(int i=10;i<15;i++){
			 //for(int j=0;j<WorldDimension_H;j++){
				 for(int k=10;k<15;k++){
					 TEST.setBlockAt(i,this.WorldDimension_H/2+5,k,16);
				 }
			 //}
		 }
		
		for(int i=0;i<WorldDimension;i++){
			 for(int j=0;j<WorldDimension_H;j++){
				 for(int k=0;k<WorldDimension;k++){
					 int b = TEST.getBlockAt(i,j,k);
					 this.Blocks[i][j][k] = new com.bukkit.ISOCOHEDRON_Ian.TEST.Block(new Point(i,j,k),b, this);
					 System.out.println(this.Blocks[i][j][k]);
				 }
			 }
		 }
		
	}
}
