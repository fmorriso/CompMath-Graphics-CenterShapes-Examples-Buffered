import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class CenterShapesBufferedDriver
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Center Shapes Buffered");
		
		// use a portion of the available device screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final double pct = 2.0/3;
		final int squareSize = (int) (Math.min(screenSize.height, screenSize.width) * pct);
		frame.setSize(squareSize, squareSize);
		
		// center the JFrame on the current device
		frame.setLocationRelativeTo(null); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new CenterShapesBufferedPanel());
		frame.setVisible(true);
	}

}
