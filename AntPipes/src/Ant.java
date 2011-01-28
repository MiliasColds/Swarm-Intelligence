import java.util.Random;

public class Ant 
{
	public Point Position;
	
	public Block heldBlock;
	
	public int Threshold;
	
	public Ant(Point position)
	{
		this.Position = position;
	}
	
	public void MoveRandomly(Block[] adjacentBlocks)
	{
		Random random = new Random();
		Block targetBlock = adjacentBlocks[random.nextInt(adjacentBlocks.length)];
		
		this.Position = targetBlock.Position;
	}
}
