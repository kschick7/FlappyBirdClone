package com.kyleschick.hoverboy.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kyleschick.hoverboy.HoverBoy;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Hover Boy";
        config.width = 480;
        config.height = 800;
		new LwjglApplication(new HoverBoy(), config);
	}
}
