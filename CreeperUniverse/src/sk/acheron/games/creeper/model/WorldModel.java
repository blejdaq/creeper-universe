package sk.acheron.games.creeper.model;

import sk.acheron.games.creeper.CreeperChasis;

public class WorldModel 
{
	private WorldCell[][] _world;

	
	public WorldModel()
	{
		_world = new WorldCell[CreeperChasis.SIZE_X][CreeperChasis.SIZE_X];
		
		for (int y = 0; y < CreeperChasis.SIZE_X; y++) {
			for (int x = 0; x < CreeperChasis.SIZE_X; x++) {
				_world[y][x] = new WorldCell();
			}
		}
	}
	
	public WorldCell getCell(int aX, int aY) {
		return _world[aY][aX];
	}
	
	public void setBarrier(int aX, int aY) {
		_world[aY][aX].setFree(false);
	}
}