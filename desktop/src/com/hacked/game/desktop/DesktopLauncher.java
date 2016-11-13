package com.hacked.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hacked.game.DeathRun;
import com.hacked.game.DeathRun;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = DeathRun.WIDTH;
		config.height = DeathRun.HEIGHT;
		config.title = DeathRun.TITLE;
		new LwjglApplication(new DeathRun(), config);
	}
}
