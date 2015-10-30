package sk.acheron.games.creeper.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import sk.acheron.games.creeper.CreeperChasis;
import sk.acheron.games.creeper.model.WorldCell;
import sk.acheron.games.creeper.model.WorldModel;

public class CreeperPanel extends JPanel 
{
	private static final long serialVersionUID = 6544598565775603803L;

	private WorldModel _worldModel;
	
	public CreeperPanel(int aWidth, int aHeight, WorldModel aWorldModel) 
	{
		_worldModel = aWorldModel;
		setSize(aWidth, aHeight);
		setLayout(null);
	}

	
	@Override
	public void paint(Graphics aGrp) 
	{
		super.paint(aGrp);
		for (int y = 0; y < CreeperChasis.SIZE_X; y++)
		{
			for (int x = 0; x < CreeperChasis.SIZE_X; x++)
			{
				WorldCell cell = _worldModel.getCell(x, y);
				
				if (!cell.isFree()) {
					// barrier
					aGrp.setColor(new Color(0, 255, 0));
				}
				else if (cell.isBomb()) {
					// bomb
					aGrp.setColor(new Color(255, 0, 0));
					cell.setBomb(false);
				}
				else
				{
					int creepLvl = _worldModel.getCell(x, y).getCreepLevel();
					if (creepLvl == 0)
					{
						aGrp.setColor(new Color(255, 255, 255));
					}
					else
					{
						
						int creepColor = 255 - creepLvl / 2;
						if (creepColor > 255) {
							creepColor = 255;
						}
						aGrp.setColor(new Color(0, 0, creepColor));
						
						/*
						int ind = (int) ((11.0d / 200.0d) * creepLvl);
						aGrp.setColor(CreeperChasis.getInstance().getPalette()[ind]);
						*/
					}
				}
				
				aGrp.fillRect(x * 10, y * 10, 10, 10);
			}
			
		}
	}
	
	
	
}
