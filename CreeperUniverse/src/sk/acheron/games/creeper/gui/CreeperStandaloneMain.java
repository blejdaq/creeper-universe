package sk.acheron.games.creeper.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;

import sk.acheron.games.creeper.CreeperChasis;
import sk.acheron.games.creeper.model.WorldModel;

public class CreeperStandaloneMain 
{
	private static void createAndShowGUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Couldn't use system look and feel.");
		}

		//Create and set up the window.
		JFrame frame = new JFrame("CreeperFrame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1024, 1100));

		CreeperChasis creeperChasis = CreeperChasis.getInstance();
		
		CreeperPanel creeperPanel = new CreeperPanel(1024, 768, creeperChasis.getWorldModel()); 
		creeperChasis.setCreepPanel(creeperPanel);
		frame.add(creeperPanel);
/*		
		TaoChasis chasis = TaoChasis.getInstance();
		chasis.setTaoMainPanel(taoMainPanel);
		chasis.setServerHost("localhost");
		chasis.setServerPort(1111);
		try {
			chasis.connect();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			chasis.getSoundPlayer().setClickAudio(Applet.newAudioClip(new URL("file:resources/click.wav")));
			chasis.getSoundPlayer().setSelectAudio(Applet.newAudioClip(new URL("file:resources/select.wav")));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
*/		
		//Display the window.
		frame.pack();
		frame.setVisible(true);
    }
	
	public static void main(String[] aArgs) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

}
