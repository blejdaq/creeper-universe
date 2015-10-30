package sk.acheron.games.creeper;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import sk.acheron.games.creeper.gui.CreeperPanel;
import sk.acheron.games.creeper.model.WorldModel;

public class CreeperChasis 
{
	public static int SIZE_X = 50;
	public static int SIZE_Y = 50;
	
	private static CreeperChasis _creeperChasis;
	
	private WorldModel _worldModel;
	private MasterOfTime _timeMaster;
	private CreeperPanel _creepPanel;
	private Color[] _palette = new Color[12]; // undersea -2, unsersea -1, sea level, ...12 gradient levels..., over the hills 
	
	public CreeperChasis()
	{
		_worldModel = new WorldModel();
		_timeMaster = new MasterOfTime(this);
		
		initPalette();
		
		for (int y = 2; y <= 8; y++)
		{
			_worldModel.setBarrier(5, y);
			_worldModel.setBarrier(15, y + 5);
			_worldModel.setBarrier(15, y + 5 + 7);
			_worldModel.setBarrier(15, y + 5 + 15);
		}

		
		for (int y = 32; y < 48; y++) {
			for (int x = 22; x < 48; x++) {
				_worldModel.setBarrier(x, y);
			}
		}
		
		for (int y = 4; y <= 6; y++) {
			for (int x = 15; x <= 17; x++) {
				_worldModel.setBarrier(x, y);
			}
		}
		
		_worldModel.getCell(1, 4).incCreepLevel(50);
		
	}

	
	/**
	 * Initialize palette from image file
	 */
	private void initPalette() {
		try {
			BufferedImage paletteImg = ImageIO.read(new File("palette.png"));
			
			for (int i = 0; i < 12; i++) {
				int pos = (int)(paletteImg.getHeight() / 12 * i);
				
				int r = paletteImg.getRaster().getSample(0, pos, 0);
				int g = paletteImg.getRaster().getSample(0, pos, 1);
				int b = paletteImg.getRaster().getSample(0, pos, 2);
				
				_palette[i] = new Color(r, g, b);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void updateWorld()
	{
		for (int y = 0; y < SIZE_X; y++) {
			for (int x = 0; x < SIZE_X; x++) {
				_worldModel.getCell(x, y).setScanned(false);
			}
		}
		
		creep(1, 4);
		if (_creepPanel != null) {
			_creepPanel.repaint();
		}
		
	}
	
	public void bomb() {
		int centerX = 12;
		int centerY = 9;
		_worldModel.getCell(centerX, centerY).setBomb(true);
		
		for (int dy = -10; dy <= 10; dy++) {
			for (int dx = -10; dx <= 10; dx++) {
				int effX = centerX + dx;
				int effY = centerY + dy;
				if ((effX >= 0) && (effX < SIZE_X) && (effY >= 0) && (effY < SIZE_X)) {
					if (_worldModel.getCell(effX, effY).getCreepLevel() > 0) {
						int dist = (int) Math.sqrt((dx*dx) + (dy*dy));
						_worldModel.getCell(effX, effY).incCreepLevel(-30 * (14 - dist));
					}
				}
			}
		}
		
	}

	
	private void creep(int aX, int aY) {
		_worldModel.getCell(aX, aY).setScanned(true);
		
		for (int dy = -1; dy <= 1; dy++) {
			for (int dx = -1; dx <= 1; dx++) {
				
				if ((dx == 0) && (dy == 0)) {
					continue;
				}
				
				// neighbour cell
				int effX = aX + dx;
				int effY = aY + dy;
				
				if ((effX >= 0) && (effX < SIZE_X) && (effY >= 0) && (effY < SIZE_X)) {
					// in this world
					
					if (!_worldModel.getCell(effX, effY).isScanned()) {

						if (_worldModel.getCell(effX, effY).isFree()) {
							// not barrier
							
							if (_worldModel.getCell(effX, effY).getCreepLevel() > 0) {
								// has creep
								
								creep(effX, effY);
							}
						}
					}
					
					_worldModel.getCell(effX, effY).incCreepLevel(_worldModel.getCell(aX, aY).getCreepLevel() / 20);
				}
			}
		}
	}
	
	
	public WorldModel getWorldModel() {
		return _worldModel;
	}
	
	
	public static CreeperChasis getInstance()
	{
		if (_creeperChasis == null)
		{
			_creeperChasis = new CreeperChasis();
		}
		return _creeperChasis;
	}


	public CreeperPanel getCreepPanel() {
		return _creepPanel;
	}


	public void setCreepPanel(CreeperPanel aCreepPanel) {
		_creepPanel = aCreepPanel;
	}


	public Color[] getPalette() {
		return _palette;
	}
}
