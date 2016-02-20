package lucrasart.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import lucrasart.SavingGrannyMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.addIcon("logo-256.png", FileType.Internal);
		config.addIcon("logo-64.png", FileType.Internal);
	    config.addIcon("logo-32.png", FileType.Internal);
		config.width=1024;
		config.height=700;
		new LwjglApplication(new SavingGrannyMain(), config);
	}
}
