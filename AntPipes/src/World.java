import java.util.ArrayList;

public class World {
	public Ant[] Ants;
	public Block[][][] Blocks;
	public Nest Nest;
	
	public int WorldDimension = 256;
	
	public Block[] GetBlocksAdjacentToAnt(Ant currentAnt) 
	{
		ArrayList<Block> blocks = new ArrayList<Block>();
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
		
		return (Block[])blocks.toArray();
	}
}
