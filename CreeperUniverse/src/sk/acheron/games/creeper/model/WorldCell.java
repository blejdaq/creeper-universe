package sk.acheron.games.creeper.model;

public class WorldCell {
	
	private boolean _free;
	private int _creepLevel;
	
	private boolean _scanned;
	private boolean _bomb;
	
	public WorldCell() {
		_free = true;
		_creepLevel = 0;
	}

	public void incCreepLevel(int aAmount) {
		_creepLevel += aAmount;
		if (_creepLevel > 200) {
			_creepLevel = 200;
		} else if (_creepLevel < 0) {
			_creepLevel = 0;
		}
	}
	public boolean isFree() {
		return _free;
	}
	public void setFree(boolean aFree) {
		_free = aFree;
	}
	public int getCreepLevel() {
		return _creepLevel;
	}
	public void setCreepLevel(int aCreepLevel) {
		_creepLevel = aCreepLevel;
	}
	public boolean isScanned() {
		return _scanned;
	}
	public void setScanned(boolean aScanned) {
		_scanned = aScanned;
	}

	public boolean isBomb() {
		return _bomb;
	}

	public void setBomb(boolean aBomb) {
		_bomb = aBomb;
	}
}
