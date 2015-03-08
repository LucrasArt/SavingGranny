package lucrasart;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SavingGrannyMain extends Game {
	private static final int WIDTH = 1024; 
	private static final int HEIGHT = 700;
	
	//public PantallaMenu pantallaMenu;
	public Cover pantallaMenu;
	
	public SpriteBatch batch;
	public OrthographicCamera camera;
	public Viewport viewport; 
	public boolean win;
	public boolean win2; 
	public boolean gameOver;
	
	public static Music GameMusic; 
	
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		viewport = new FitViewport(WIDTH, HEIGHT, camera); 
		pantallaMenu = new Cover(this);
		win = false;
		win2=false;
		gameOver = false;
		setScreen(pantallaMenu);
		
		GameMusic = Gdx.audio.newMusic(Gdx.files.internal("Green_Hills.mp3"));
		GameMusic.play();
		GameMusic.setLooping(true);

	}

	
	
	
	@Override
	public void dispose() { 
		pantallaMenu.dispose();
		batch.dispose();
	}
}
