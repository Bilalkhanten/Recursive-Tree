

import javax.swing.JFrame;


public class PlantTest
{

	public static void main(String[] args)
	{
		JFrame appFrame = new JFrame();
		graphic rectPanel = new graphic();
		appFrame.setContentPane(rectPanel);
		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appFrame.pack();
		appFrame.show();
		
		
		Plant tee = new Plant(400,600,400,350);
			rectPanel.DrawDrawableObject(tee);	
			
			
	}

}
