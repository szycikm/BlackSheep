package stuff;

import javax.swing.JScrollPane;

public final class Logger
{
	private static boolean initialized;
	private static ScrollLabel logLabel;
	private static JScrollPane logScrollPane;
	
	static
	{
		initialized = false;
	}
	
	public static void initialize(ScrollLabel logLabel, JScrollPane logScrollPane)
	{
		Logger.logLabel = logLabel;
		Logger.logScrollPane = logScrollPane;
		initialized = true;
	}
	
	public static void writeMessage(String msg)
	{
		if(!initialized) return;
		logLabel.setText(logLabel.getText() + msg + "<br />");
		logScrollPane.getVerticalScrollBar().setValue(logScrollPane.getVerticalScrollBar().getMaximum());
	}
}