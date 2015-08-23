package cluedo;

import cluedo.gui.Frame;

public class Output {
	
	private static Frame frame;
	
	public static void appendText(String text){
		frame.getOptions().getTextArea().append(text+"\n");
	}
	
	public static void setText(String text){
		frame.getOptions().getTextArea().setText(text+"\n");
	}

	public static void setFrame(Frame f) {
		frame = f;
	}

}
