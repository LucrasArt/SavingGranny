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


public class GameScreen1 extends AbstractScreen 
{ 
	enum GameState {Start, Running, GameOver, Finish}//, Pause}
	
	public static GameState gameState;
	
	public static Music GameMusic1; 
	
	private Map map;
	
	private Texture lose;
	private Stage stage;
	private Skin skin;
	private Table table,table1,table2,table3;
	private Texture apple;
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
	
	static Image vida3,vida2,vida1;
	static Image manzana;
	static Image reloj;
	static Image estrella;
	
	Texture background;
	Texture middleBackground;
	Player player;
	
	
	public GameScreen1(SavingGrannyMain main) {
		super(main);
		
		Media.create();
		
		if(PantallaPause.flagPause)
		{
			gameState = GameState.Running;
			//PantallaPause.flagPause=false;
		}
		else
		{
		gameState = GameState.Start;
		}
		
		
		map = new Map(main, batch);
		
		lose = new Texture (Gdx.files.internal("Scenes/gameOver.png"));
		
		
		apple = new Texture("Items/manzana.png");
		life3 = new Texture("Items/mini3heart.png");
		life2 = new Texture("Items/mini2heart.png");
		life1 = new Texture("Items/mini1heart.png");
		watch = new Texture("Items/reloj.png");
		star= new Texture("Items/star.png");
		
		manzana = new Image(apple); 
		vida3 = new Image(life3); 
		vida2 = new Image(life2); 
		vida1 = new Image(life1); 
		reloj = new Image(watch);
		estrella = new Image(star);

		skin = new Skin (Gdx.files.internal("skin/uiskin.json"));
		
		appleLabel = new Label ("x "+appleCount,skin);
		lifeLabel = new Label ("x "+lifeCount,skin);
		score = new Label ("x "+scoreCount,skin);
		time = new Label ("x "+timeCount,skin);
		
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
		
		background = new Texture("Map/sky1.png");

		middleBackground = new Texture("Map/medievalBackgroundColor4.png");
		
	}
	
	@Override
	public void show()
	{
		SavingGrannyMain.GameMusic.stop();
		
		GameMusic1 = Gdx.audio.newMusic(Gdx.files.internal("Audio/Dog_Park.mp3"));
		GameMusic1.play();
		GameMusic1.setLooping(true);
		
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
		life3.dispose();
		life2.dispose();
		life1.dispose();
		watch.dispose();
		star.dispose();
		background.dispose();
		middleBackground.dispose();
	}
	
	
	void start()
	{
		SavingGrannyMain.win=true;

		appleCount = 0;
		appleLabel.setText("x "+GameScreen1.appleCount);
		scoreCount = 0;
		score.setText("x "+GameScreen1.scoreCount);
		lifeCount = 3;
		lifeLabel.setText("x "+GameScreen1.lifeCount);
		

		 if(Map.flag1)
		 {
			 Map.processMapMetadata();
			 Map.flag1=false;
		 }
		
		gameState = GameState.Running;
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
			gameState = GameState.Start;	
		}
		
	}
	
	void running()
	{
		if(Map.life1)	
		{
			table.setVisible(false);
			table2.setVisible(false);
			table3.setVisible(true);
			
			Map.life1=false;
		}
		
		if(Map.life2)	
		{	
			table.setVisible(false);
			table2.setVisible(true);
			table3.setVisible(false);
			
			Map.life2=false;
		}
		
		if(Map.life3)	
		{
			table.setVisible(true);
			table2.setVisible(false);
			table3.setVisible(false);
			
			Map.life3=false;
		}
			
	
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))
		{ 
			GameMusic1.stop();
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
			
			batch.draw(background, camera.position.x - background.getWidth() * 0.5f, camera.position.y - background.getHeight() * 0.5f, 
					background.getWidth(), background.getHeight()); 
		
			batch.draw(middleBackground,(camera.position.x  * 0.3f)- background.getWidth() * 0.5f,0,middleBackground.getWidth(), middleBackground.getHeight());
			
			batch.end();	
			
			updateCamera();
			
			map.draw(camera);	
				
			stage.draw();

		}
		
	}
	
/*	void Pause()
	{
		main.setScreen(new PantallaPause(main));
	
	}
*/	
	
	void finish()
	{
		main.flagLevel1=true;
		
		SavingGrannyMain.win=false;
		
		 main.setScreen(new SelectLevel(main));
		 //main.setScreen(new GameScreen2(main));
		
	}
	
	private void updateCamera() 
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
