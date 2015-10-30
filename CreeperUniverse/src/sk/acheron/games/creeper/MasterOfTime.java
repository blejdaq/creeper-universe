package sk.acheron.games.creeper;

import java.util.Timer;
import java.util.TimerTask;


public class MasterOfTime 
{
	private CreeperChasis _creeperChasis;
	private Timer _timer;
	private int _seconds;
	
	public MasterOfTime(CreeperChasis aCreeperChasis)
	{
		_creeperChasis = aCreeperChasis;
		
		_seconds = 0;
		_timer = new Timer();
		_timer.schedule(new TimerTask() { 
							public void run() { doJob(); }
						}, 
						0, 
						100);
	}
	
	
	private void doJob()
	{
		System.out.println("Time");
		_creeperChasis.updateWorld();
		_seconds++;
		if ((_seconds % 20 == 0) && (_seconds > 100)) {
			System.out.println("Bomb!");
			_creeperChasis.bomb();
		}
	}
	
}
