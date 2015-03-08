package lucrasart;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GameScreen extends AbstractScreen { 
	enum GameState {Start, Running, GameOver}
	
	public static GameState gameState;
	
	
	
	private Map map;
	private Map2 map2;
	private Texture texture;
	private Texture lose;
	
	private Stage stage;
	private Skin skin;
	private Table table;
	private Texture apple;
	private Texture life;
	public static Label appleLabel, lifeLabel, score, time;
	public static int appleCount=0, lifeCount=3, scoreCount=0; 
	public static float timeCount=00.00f;
	
	
	public static Sound coinSound; 
	public static Sound ovejaSound;
	public static Sound finishSound;
	
	public GameScreen(SavingGrannyMain main) {
		super(main);
		
		gameState = GameState.Start;
		
		map = new Map(main, batch);
		map2 = new Map2(main, batch);
		texture = new Texture("finish.png");
		lose = new Texture (Gdx.files.internal("Dead.png"));
		
		coinSound = Gdx.audio.newSound(Gdx.files.internal("coin.wav"));
		ovejaSound = Gdx.audio.newSound(Gdx.files.internal("oveja.wav"));
		finishSound = Gdx.audio.newSound(Gdx.files.internal("aplauso2.wav"));
		
		apple = new Texture("manzana.png");
		life = new Texture("heart.png");
		Image manzana = new Image(apple); 
		Image vida = new Image(life); 

		skin = new Skin (Gdx.files.internal("skin/uiskin.json"));
		
		appleLabel = new Label ("x"+appleCount,skin);
		lifeLabel = new Label ("x"+lifeCount,skin);
		score = new Label ("Score "+scoreCount,skin);
		time = new Label ("Time "+timeCount,skin);
		
		stage = new Stage();
		
		table = new Table();
		table.setPosition(500, Gdx.graphics.getHeight()*0.9f);
		
		stage.addActor(table);
		
		Gdx.input.setInputProcessor(stage);
		
		table.add(vida).top().left().width(30).height(30);
		table.add(lifeLabel).top().left().padRight(800);
		table.add(score).top().right();
		table.row();
		table.add(manzana).top().left().width(30).height(30);
		table.add(appleLabel).top().left();
		table.add(time).top().right();
	}
	
	@Override
	public void render(float delta) { 
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
			
		}
		
		
		
	}
	
	@Override
	public void dispose() { 
		map.dispose();
	}
	
	
	void start()
	{
		appleCount = 0;
		appleLabel.setText("x"+GameScreen.appleCount);
		scoreCount = 0;
		score.setText("Score "+GameScreen.scoreCount);
		lifeCount = 3;
		lifeLabel.setText("x"+GameScreen.lifeCount);
		
		Map.processMapMetadata();
		
		gameState = GameState.Running;
	}
	
	
	void GameOver()
	{
		
		batch.begin();
		
			batch.draw(lose, camera.position.x - texture.getWidth() * 0.5f, camera.position.y - texture.getHeight() * 0.5f, 
					texture.getWidth(), texture.getHeight()); 
		
			
			
		batch.end();
				
				if(Gdx.input.isKeyPressed(Keys.ANY_KEY))
				{
					gameState = GameState.Start;	
				}
		
	}
	
	void running()
	{
		if(main.win)
		{
			updateCameraWin();
			
			
		}
		else if(main.win2)
		{
			updateCameraWin2();
			map2.draw(camera);
			
			
			stage.draw();
		}
			else
			{ 
			updateCamera();
			map.draw(camera);
			
			
			stage.draw();
			
			}
		
			if(Gdx.input.isKeyPressed(Keys.ESCAPE))
			{ 
		
			

				main.setScreen(new PantallaMenu(main));
			
			

			}	
		
	}
	
	
	private void updateCamera() { 
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
	
	private void updateCameraWin() {
		batch.begin();
		
			
			batch.draw(texture, camera.position.x - texture.getWidth() * 0.5f, camera.position.y - texture.getHeight() * 0.5f, 
				texture.getWidth(), texture.getHeight()); 
		
		batch.end();
		
	}
	
	private void updateCameraWin2() {
		// TODO Auto-generated method stub
		camera.position.x = map2.getPlayer().getCenterPosition().x; 
		camera.position.y = map2.getPlayer().getCenterPosition().y;

		TiledMapTileLayer layer = (TiledMapTileLayer)map2.getMap().getLayers().get(0); 

		float cameraMinX = viewport.getWorldWidth() * 0.5f; 
		float cameraMinY = viewport.getWorldHeight() * 0.5f; 
		float cameraMaxX = layer.getWidth() * layer.getTileWidth() - cameraMinX; 
		float cameraMaxY = layer.getHeight() * layer.getTileHeight() - cameraMinY; 

		camera.position.x = MathUtils.clamp(camera.position.x, cameraMinX, cameraMaxX); 
		camera.position.y= MathUtils.clamp(camera.position.y, cameraMinY, cameraMaxY); 
                                                                                       
		camera.update();
	}
}
