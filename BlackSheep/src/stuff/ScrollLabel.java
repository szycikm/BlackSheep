package stuff;

import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.Scrollable;

public class ScrollLabel extends JLabel implements Scrollable
{
	public ScrollLabel(String text)
	{
		super(text);
	}

	public Dimension getPreferredScrollableViewportSize()
	{
		return getPreferredSize();
	}

	public int getScrollableBlockIncrement(Rectangle r, int orietation, int direction)
	{
		return 10;
	}

	public boolean getScrollableTracksViewportHeight()
	{
		return false;
	}

	public boolean getScrollableTracksViewportWidth()
	{
		return false;
	}

	public int getScrollableUnitIncrement(Rectangle r, int orientation, int direction)
	{
		return 10;
	}
}
