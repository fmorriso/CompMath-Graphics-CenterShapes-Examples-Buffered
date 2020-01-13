import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class CenterShapesBufferedPanel extends JPanel
{
	private static final int MIN_WIDTH = 100, MIN_HEIGHT = 100;

	private BufferedImage bufImage;
	private Graphics bufGraphics;

	public CenterShapesBufferedPanel()
	{

		bufImage = new BufferedImage(MIN_WIDTH, MIN_HEIGHT, BufferedImage.TYPE_INT_RGB);
		bufGraphics = bufImage.getGraphics();
		//regenerate();

		this.addComponentListener(new ComponentListener()
		{
			@Override
			public void componentResized(ComponentEvent e)
			{
				//System.out.println("componentResized");
				// get current size of parent container (because it may have been resized)
				Container parent = getParent();				
				int width = parent.getWidth();
				int height = parent.getHeight();

				// set our size to be the same as our parent container
				Component comp = e.getComponent();
				if(comp instanceof JPanel) {
					JPanel pnl = (JPanel)comp;
					pnl.setSize(width, height);
					redrawBuffer();
				}				
			}

			@Override
			public void componentHidden(ComponentEvent e)
			{
				// DON'T CARE
			}

			@Override
			public void componentMoved(ComponentEvent e)
			{
				// DON'T CARE

			}

			@Override
			public void componentShown(ComponentEvent e)
			{
				// DON'T CARE

			}
		});
	}
	
	private void redrawBuffer()
	{
		Container parent = getParent();
		if(parent == null) {
			System.out.println("redrawBuffer: getParent() returned null");
			return;
		}
		int x = parent.getX();
		int y = parent.getY();
		int width = parent.getWidth();
		int height = parent.getHeight();
		
		bufImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		bufGraphics = bufImage.createGraphics();

		// fill the background of this container
		bufGraphics.setColor(Color.BLUE);
		bufGraphics.fillRect(x, y, width, height);

		// draw a circle in the center of this container
		bufGraphics.setColor(Color.RED);
		fillOvalCentered(width / 2, height / 2);

		bufGraphics.setColor(Color.YELLOW);
		fillRectCentered(width / 4, height / 4);
	}

	/**
	 * @param g - the Graphics instance we are drawing on
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//System.out.println("paintComponent");
		g.drawImage(bufImage, 0, 0, getWidth(), getHeight(), null);

	}

	/**
	 * Draw an oval in the center of the JPanel
	 * 
	 * @param width - width of the shape
	 * @param height - height of the shape
	 * @param g - Graphics object we are drawing on
	 */
	public void fillOvalCentered(int width, int height)
	{
		// left corner X = center of JPanel minus half width of shape
		int x = this.getWidth() / 2 - width / 2;

		// left corner y = center of JPanel minus half height of shape
		int y = this.getHeight() / 2 - height / 2;

		bufGraphics.fillOval(x, y, width, height);
	}

	/**
	 * Draw a rectangle in the center of the JPanel
	 * 
	 * @param width - width of the shape
	 * @param height - height of the shape
	 * @param g - Graphics object we are drawing on
	 */
	public void fillRectCentered(int width, int height)
	{
		// left corner X = center of JPanel minus half width of shape
		int x = this.getWidth() / 2 - width / 2;

		// left corner y = center of JPanel minus half height of shape
		int y = this.getHeight() / 2 - height / 2;

		bufGraphics.fillRect(x, y, width, height);
	}

}