package lucrasart;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SavingGrannyMain extends Game 
{
	private static final int WIDTH = 1024; 
	private static final int HEIGHT = 700;
	
	public Cover cover;
	
	public SpriteBatch batch;
	public OrthographicCamera camera;
	public Viewport viewport; 
	public static boolean win;
	public static boolean win2; 
	public static boolean win3;
	public static boolean win4;
	public static boolean win5;
	
	public boolean flagLevel1,flagLevel2,flagLevel3,flagLevel4,flagLevel5;
	
	public boolean gameOver;
	
	public static Music GameMusic; 
	
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		viewport = new FitViewport(WIDTH, HEIGHT, camera); 
		cover = new Cover(this);
		win = false;
		win2=false;
		win3 = false;
		win4= false;
		win5 = false;
		flagLevel1=flagLevel2=flagLevel3=flagLevel4=flagLevel5=false;
		gameOver = false;
		setScreen(cover);
		
		GameMusic = Gdx.audio.newMusic(Gdx.files.internal("Audio/Path_to_Follow1.mp3"));
		GameMusic.play();
		GameMusic.setLooping(true);

	}

	@Override
	public void dispose() 
	{ 
		cover.dispose();
		batch.dispose();
	}
}
