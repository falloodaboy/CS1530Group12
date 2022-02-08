package com.testgame.demo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.testgame.demo.MyDemoGame;
import com.testgame.demo.TerraRogueDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1080;
		config.height = 1080;
		new LwjglApplication(new TerraRogueDemo(), config);
	}
}
