
public class Main {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		World world = new World();
		
		Ant ant = new Ant(new Point(0, 0, 0));
		world.Ants = new Ant[]{ ant };
		
		int count = 100;
		
		while (count > 0)
		{
			for (Ant currentAnt : world.Ants)
			{
				Block[] adjacentBlocks = world.GetBlocksAdjacentToAnt(currentAnt);
				currentAnt.MoveRandomly(adjacentBlocks);
			}
			
			Thread.currentThread().sleep(100);
			
			count--;
		}
	}

	
}
