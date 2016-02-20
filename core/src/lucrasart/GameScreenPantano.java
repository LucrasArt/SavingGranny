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

public class GameScreenPantano extends AbstractScreen { 
	enum GameStatePantano {Start, Running, GameOver, Finish}//, Pause}
	
	public static GameStatePantano gameState;
	
	public static Music GameMusic2;
	
	private MapPantano map;
	private Texture lose;
	
	private Stage stage;
	private Skin skin;
	private Table table,table1,table2,table3;
	private Texture apple;
	//private Texture life;
	private Texture life3, life2,life1;
	private Texture watch;
	private Texture star;
	public static Label appleLabel, lifeLabel, score, time;
	public static int appleCount=0, lifeCount=3, scoreCount=0; 
	public static int timeCount= 0;
	
	public static int vidas = 3;
	
	private float count = 0;
	
	public static boolean checkpoint = false;
	public static boolean checkpoint2 = false;
	
	static float posX,posY=0;
	static boolean pause=false;
	
	Texture background,middleBackground;
	
	public GameScreenPantano(SavingGrannyMain main)
	{
		super(main);
		
		Media.create();
		
		gameState = GameStatePantano.Start;
	
		map = new MapPantano(main, batch);

		lose = new Texture (Gdx.files.internal("Scenes/gameOver.png"));
		
		apple = new Texture("Items/manzana.png");
		//life = new Texture("Items/heart.png");
		life3 = new Texture("Items/mini3heart.png");
		life2 = new Texture("Items/mini2heart.png");
		life1 = new Texture("Items/mini1heart.png");
		watch = new Texture("Items/reloj.png");
		star= new Texture("Items/star.png");
		Image manzana = new Image(apple); 
		//Image vida = new Image(life);
		Image vida3 = new Image(life3); 
		Image vida2 = new Image(life2); 
		Image vida1 = new Image(life1); 
		Image reloj = new Image(watch);
		Image estrella = new Image(star);

		skin = new Skin (Gdx.files.internal("skin/uiskin.json"));
		
		appleLabel = new Label ("x "+appleCount,skin);
		lifeLabel = new Label ("x "+lifeCount,skin);
		score = new Label ("x "+scoreCount,skin);
		time = new Label (" "+timeCount,skin);
		
		stage = new Stage();
		
		table = new Table();
		table1 = new Table();
		table2 = new Table();
		table3 = new Table();
		
		stage.addActor(table);
		stage.addActor(table1);
		stage.addActor(table2);
		stage.addActor(table3);
		
		Gdx.input.setInputProcessor(stage);
		
		table.add(vida3).top().left().width(30).height(30);
		table.setPosition(Gdx.graphics.getWidth()*0.065f, Gdx.graphics.getHeight()*0.93f);
		table.setVisible(true);
		
		table2.add(vida2).top().left().width(30).height(30);
		table2.setPosition(Gdx.graphics.getWidth()*0.065f, Gdx.graphics.getHeight()*0.93f);
		table2.setVisible(false);
	
		table3.add(vida1).top().left().width(30).height(30);
		table3.setPosition(Gdx.graphics.getWidth()*0.065f, Gdx.graphics.getHeight()*0.93f);
		table3.setVisible(false);

		table1.add().top().left().width(30).height(30);
		table1.add(lifeLabel).top().left().padRight(750);
		table1.add(estrella).top().left().width(30).height(30);
		table1.add(score).top().right();
		table1.row();
		table1.add(manzana).top().left().width(30).height(30);
		table1.add(appleLabel).top().left();
		table1.add(reloj).top().right().width(30).height(30);
		table1.add(time).top().right();
		table1.setPosition(Gdx.graphics.getWidth()*0.48f, Gdx.graphics.getHeight()*0.9f);
		table1.setVisible(true);
		
		background = new Texture("Map/fondoPantano4.png");
		middleBackground = new Texture("Map/middlegroundPantano2.png");
		
	}
	
	@Override
	public void show()
	{
		SavingGrannyMain.GameMusic.stop();

		GameScreenPantano.GameMusic2 = Gdx.audio.newMusic(Gdx.files.internal("Audio/Lurking1.mp3"));
	
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
			
		/*case Pause:
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
		life3.dispose();
		life2.dispose();
		life1.dispose();
		watch.dispose();
		star.dispose();
		background.dispose();
		middleBackground.dispose();
	}
	
	void Pause()
	{
		main.setScreen(new PantallaPause(main));
	
	}
	
	void start()
	{
		SavingGrannyMain.win3=true;
		

		appleCount = 0;
		appleLabel.setText("x "+GameScreenPantano.appleCount);
		scoreCount = 0;
		score.setText("x "+GameScreenPantano.scoreCount);
		lifeCount = 3;
		lifeLabel.setText("x "+GameScreenPantano.lifeCount);
		
		
		if(MapPantano.flag1)
		{
			MapPantano.processMapMetadata();
			MapPantano.flag1=false;
		}
		
		gameState = GameStatePantano.Running;
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
			gameState = GameStatePantano.Start;	
		}
		
	}
	
	void running()
	{
		if(MapPantano.life1)	
		{
			table.setVisible(false);
			table2.setVisible(false);
			table3.setVisible(true);
			
			MapPantano.life1=false;
		}
		
		if(MapPantano.life2)	
		{
			table.setVisible(false);
			table2.setVisible(true);
			table3.setVisible(false);
			
			MapPantano.life2=false;
		}
		
		if(MapPantano.life3)	
		{
			table.setVisible(true);
			table2.setVisible(false);
			table3.setVisible(false);
			
			MapPantano.life3=false;
		}
			
		
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
		
			batch.draw(background,(camera.position.x * 0.8f)- background.getWidth() * 0.5f,0,background.getWidth(), background.getHeight());
			
			batch.draw(middleBackground,(camera.position.x  * 0.3f)- background.getWidth() * 0.5f,0,middleBackground.getWidth(), middleBackground.getHeight());
			
			batch.end();	

			
			updateCameraWin2();
			
			map.draw(camera);
	
			stage.draw();
			
		}
		
		
		
	}
	
	
	void finish()
	{
		main.flagLevel3=true;
		
		SavingGrannyMain.win3=false;
		 
		
		//main.setScreen(new GameScreenCave(main));
		main.setScreen(new SelectLevel(main));
		
		
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
