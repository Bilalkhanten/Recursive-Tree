

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;
public class graphic extends JPanel
{
	public graphic()
	{
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		circles = new ArrayList<Drawable>();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
	
		for (int k = 0; k < circles.size(); k++)
		{
				circles.get(k).Draw(g2);
				repaint();
		}
	}

	public void DrawDrawableObject(Drawable objectToDraw)
	{
	
		drawableObject =  objectToDraw;
		circles.add(drawableObject);
		repaint();
	}
	
	private Drawable drawableObject;

	private static final int PANEL_WIDTH = 800;
	private static final int PANEL_HEIGHT =800;
	private ArrayList<Drawable> circles;
}




