package lucrasart;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GameScreenWolf extends AbstractScreen { 
	enum GameStateWolf {Start, Running, GameOver, Finish}//, Pause}
	
	public static GameStateWolf gameState;
	
	public static Music GameMusic2;
	
	private MapWolf map;
	private Texture continueEsp, continueEng,finalFrame;
	private Texture lose;
	
	private Stage stage;
	private Skin skin;
	private Table table;
	private Texture apple;
	private Texture life;
	private Texture watch;
	private Texture star;
	public static Label appleLabel, lifeLabel, score, time, wolfLabel;
	public static int appleCount=0, lifeCount=3, scoreCount=0, wolfCount=100; 
	public static int timeCount= 0;
	
	private float count,count2 = 0;

	public static boolean checkpoint = false;
	public static boolean checkpoint2 = false;

	static float posX,posY=0;
	static boolean pause=false;
	
	Texture background;
	
	public GameScreenWolf(SavingGrannyMain main) {
		super(main);
		
		Media.create();
		
		gameState = GameStateWolf.Start;
		
		map = new MapWolf(main, batch);
		
		continueEsp = new Texture("Scenes/continuara.png");
		continueEng = new Texture("Scenes/continued.png");
		finalFrame = new Texture("Scenes/escenaFinal.png");

		lose = new Texture (Gdx.files.internal("Scenes/gameOver.png"));
	
		apple = new Texture("Items/manzana.png");
		life = new Texture("Items/heart.png");
		watch = new Texture("Items/reloj.png");
		star= new Texture("Items/star.png");
		Image manzana = new Image(apple); 
		Image vida = new Image(life); 
		Image reloj = new Image(watch);
		Image estrella = new Image(star);

		skin = new Skin (Gdx.files.internal("skin/uiskin.json"));
		
		appleLabel = new Label ("x "+appleCount,skin);
		lifeLabel = new Label ("x "+lifeCount,skin);
		score = new Label ("x "+scoreCount,skin);
		time = new Label (" "+timeCount,skin);
		wolfLabel = new Label ("Enemy Life "+wolfCount,skin);
		
		stage = new Stage();
		
		table = new Table();
		table.setPosition(500, Gdx.graphics.getHeight()*0.9f);
		
		stage.addActor(table);
		
		Gdx.input.setInputProcessor(stage);
		
		table.add(vida).top().left().width(30).height(30);
		table.add(lifeLabel).top().left().padRight(750);
		table.add(estrella).top().left().width(30).height(30);
		table.add(score).top().right();
		table.row();
		table.add(manzana).top().left().width(30).height(30);
		table.add(appleLabel).top().left();
		table.add(reloj).top().right().width(30).height(30);
		table.add(time).top().right();
		table.row();
		table.add(wolfLabel).top().left();
		
		background = new Texture("Map/fondoWolf4.png");
		
	}
	
	@Override
	public void show()
	{
		SavingGrannyMain.GameMusic.stop();
		
		GameScreenWolf.GameMusic2 = Gdx.audio.newMusic(Gdx.files.internal("Audio/The_Drive.mp3"));
		GameMusic2.play();
		GameMusic2.setLooping(true);
		
		
	}
	
	
	
	@Override
	public void render(float delta) 
	{ 
		Gdx.gl.glClearColor(0, 0, 0, 1);  
		                                    
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		switch(gameState)
		{
		case Start:
			start();
			break;
		case Running:
			running();
			break;
		case GameOver:
			GameOver();
			break;
		case Finish:	
			finish();
			break;
/*		case Pause:
			Pause();
			break;*/
		}
		
	}
	
	@Override
	public void dispose() 
	{ 
		map.dispose();
		lose.dispose();
		stage.dispose();
		skin.dispose();
		apple.dispose();
		//life3.dispose();
		//life2.dispose();
		//life1.dispose();
		watch.dispose();
		star.dispose();
		background.dispose();
		//middleBackground.dispose();
	}
	
	void Pause()
	{
		main.setScreen(new PantallaPause(main));
	
	}
	
	void start()
	{
		SavingGrannyMain.win5=true;
		
		appleCount = 0;
		appleLabel.setText("x "+GameScreenWolf.appleCount);
		scoreCount = 0;
		score.setText("x "+GameScreenWolf.scoreCount);
		lifeCount = 3;
		lifeLabel.setText("x "+GameScreenWolf.lifeCount);
		
		
		if(MapWolf.flag1)
		{
			MapWolf.processMapMetadata();
			MapWolf.flag1=false;
		}
		
		gameState = GameStateWolf.Running;
	}
	
	
	void GameOver()
	{
		
		batch.begin();
		
		batch.draw(lose, camera.position.x - lose.getWidth() * 0.5f, camera.position.y - lose.getHeight() * 0.5f, 
					lose.getWidth(), lose.getHeight()); 
		
			
			
		batch.end();
				
		count += Gdx.graphics.getDeltaTime();
		
		if(count > 1)
		{
			gameState = GameStateWolf.Start;	
		}
		
	}
	
	void running()
	{
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))
		{ 
			GameMusic2.dispose();
			if(Cover.flagLanguageEsp)
			{
				main.setScreen(new PantallaMenuEsp(main));
			}
			else if(Cover.flagLanguageEng)
			{
				main.setScreen(new PantallaMenu(main));
			}
			
		}	
		else
		{ 
			
			batch.begin();
			
			batch.draw(background,(camera.position.x * 0.1f)- background.getWidth() * 0.5f,0,background.getWidth(), background.getHeight());
			
			batch.end();	
			
			
			updateCameraWin2();
			
			map.draw(camera);
			
			stage.draw();
			
		}
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))
		{ 
			if(Cover.flagLanguageEsp)
			{
				main.setScreen(new PantallaMenuEsp(main));
			}
			else if(Cover.flagLanguageEng)
			{
				main.setScreen(new PantallaMenu(main));
			}
			
		}	
		
	}
	
	
	void finish()
	{
		main.flagLevel5=true;
		
		SavingGrannyMain.win5=false;
		
		FinalSceneCamera();
		
		count2 += Gdx.graphics.getDeltaTime();
		
		if(count2>4 && count2<6)
		{
			updateCameraWin();
		}
		
		if(count2 > 6)
		{
			if(Cover.flagLanguageEsp == true)
			{
				main.setScreen(new PantallaMenuEsp(main));
			}
			
			if(Cover.flagLanguageEng == true)
			{
				main.setScreen(new PantallaMenu(main));
			}

		}
	}
	
	private void FinalSceneCamera() 
	{
		batch.begin();
		
			
		batch.draw(finalFrame, camera.position.x - finalFrame.getWidth() * 0.5f, camera.position.y - finalFrame.getHeight() * 0.5f, 
					finalFrame.getWidth(), finalFrame.getHeight()); 
		
		
	
		batch.end();
		
	}
	
	
	private void updateCameraWin() 
	{
		batch.begin();
		
			
		if(Cover.flagLanguageEsp)
		{
			batch.draw(continueEsp, camera.position.x - continueEsp.getWidth() * 0.5f, camera.position.y - continueEsp.getHeight() * 0.5f, 
					continueEsp.getWidth(), continueEsp.getHeight()); 
		}
		else if(Cover.flagLanguageEng)
		{
			batch.draw(continueEng, camera.position.x - continueEng.getWidth() * 0.5f, camera.position.y - continueEng.getHeight() * 0.5f, 
					continueEng.getWidth(), continueEng.getHeight()); 
		}
		
		
	
		
		batch.end();
		
	}
	
	private void updateCameraWin2()
	{
		
		camera.position.x = map.getPlayer().getCenterPosition().x; 
		camera.position.y = map.getPlayer().getCenterPosition().y;

		TiledMapTileLayer layer = (TiledMapTileLayer)map.getMap().getLayers().get(0); 

		float cameraMinX = viewport.getWorldWidth() * 0.5f; 
		float cameraMinY = viewport.getWorldHeight() * 0.5f; 
		float cameraMaxX = layer.getWidth() * layer.getTileWidth() - cameraMinX; 
		float cameraMaxY = layer.getHeight() * layer.getTileHeight() - cameraMinY; 

		camera.position.x = MathUtils.clamp(camera.position.x, cameraMinX, cameraMaxX); 
		camera.position.y= MathUtils.clamp(camera.position.y, cameraMinY, cameraMaxY); 
                                                                                       
		camera.update();
	}
}
