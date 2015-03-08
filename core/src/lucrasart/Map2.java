package lucrasart;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import lucrasart.GameScreen.GameState;
import lucrasart.Player.State;

public class Map2{ 
	
	private SavingGrannyMain main;
	private SpriteBatch batch;
	public static TiledMap map; 
	private TmxMapLoader loader; 
	private OrthogonalTiledMapRenderer renderer; 
	
	public static Player player; 
	public Sound dieSound; 
	public static Array<Rectangle> platforms; 
	public static Array<Enemy> enemies; 
	public static Rectangle start; 
	public static Rectangle goal;
	
	public static Array<Coin> coins;
	
	
	
	public static boolean flag1 = false;
	
	
	public Map2(SavingGrannyMain main, SpriteBatch batch) {
		this.main = main;
		loader = new TmxMapLoader();
		map = loader.load("tiledMap2.tmx");
		this.batch = batch;
		renderer = new OrthogonalTiledMapRenderer(map, batch);
		dieSound = Gdx.audio.newSound(Gdx.files.internal("Gag-Sound.wav"));
		
		processMapMetadata();
	}

	public void draw(OrthographicCamera camera) { 
		renderer.setView(camera);
		renderer.render();
		
		update();
		
		batch.begin();
		player.draw(batch);
		
		for(Enemy enemy : enemies)
		{
			enemy.draw(batch);
		}
		
		for(Coin coin : coins)
		{
			coin.draw(batch);
		}
		
		batch.end();
	}
	
	private void update() { 
		
		
		player.update(platforms); 
		
		for(Coin coin : coins)
		{
			if(player.getBody().overlaps(coin.getBody())) 
			{	
				GameScreen.appleCount++;
				GameScreen.appleLabel.setText("x"+GameScreen.appleCount);
				GameScreen.scoreCount += 10;
				GameScreen.score.setText("Score "+GameScreen.scoreCount);
				
				GameScreen.coinSound.play();
				coins.removeValue(coin, false);
				
			
			}
		}
		for(Enemy enemy : enemies) {
			enemy.update();
			if(player.getBody().overlaps(enemy.getBody())) 
			{	
				if(Player.state == State.falling)
				{
					enemies.removeValue(enemy, false);
					GameScreen.ovejaSound.play();
					GameScreen.scoreCount += 30;
					GameScreen.score.setText("Score "+GameScreen.scoreCount);
				}
				
				else
				{
					GameScreen.lifeCount--;
					GameScreen.lifeLabel.setText("x"+GameScreen.lifeCount);
					reset();
				}
			}	
		}
		
		if(player.getBody().overlaps(goal)) { 
			//main.win2 = true;
			main.win = true;
			GameScreen.finishSound.play();
		}
		if(player.getPosition().y < 0)
		{
			GameScreen.lifeCount--;
			GameScreen.lifeLabel.setText("x"+GameScreen.lifeCount);
			reset();
		}
		
		if(GameScreen.lifeCount == 0)
		{
			main.gameOver=true;
			flag1 = true;
			GameScreen.gameState = GameState.GameOver;
		}
	}
	
	
	
	private void reset() { 
		player.setPosition(start.x, start.y);
		dieSound.play();
	}
	
	public void dispose() {
		map.dispose();
		renderer.dispose();
		player.dispose();
		for(Enemy enemy : enemies)
			enemy.dispose();
		for(Coin coin : coins)
			coin.dispose();
	}
	
	public TiledMap getMap() {
		return map;
	}

	public Player getPlayer() { 
		return player;
	}

	static void processMapMetadata() {
	//private void processMapMetadata() {
		platforms = new Array<Rectangle>(); 
		enemies = new Array<Enemy>();
		coins = new Array<Coin>();

		MapObjects objects = map.getLayers().get("Objects").getObjects(); 

		for (MapObject object : objects) {
			String name = object.getName();
			RectangleMapObject rectangleObject = (RectangleMapObject) object;
			Rectangle rectangle = rectangleObject.getRectangle();

			if(name.equals("PlayerStart")) 
				start = rectangle;
			if(name.equals("PlayerGoal"))
				goal = rectangle;
			
			if(name.equals("coin"))
			{
				Coin coin = new Coin();
				
				coin.setPosition(rectangle.x, rectangle.y);
				
				coins.add(coin);
			}
		}
		
		objects = map.getLayers().get("Physics").getObjects();
		
		for (MapObject object : objects) {
			RectangleMapObject rectangleObject = (RectangleMapObject) object;
			Rectangle rectangle = rectangleObject.getRectangle();
			platforms.add(rectangle); 
		} 
			
		
		objects = map.getLayers().get("Enemies").getObjects();
		for (MapObject object : objects) {
			RectangleMapObject rectangleObject = (RectangleMapObject) object; 
			Rectangle rectangle = rectangleObject.getRectangle();
			Enemy enemy = new Enemy ();
			enemy.setPosition(rectangle.x, rectangle.y);
			enemies.add(enemy);	
		}
		player = new Player(); 
		
		player.setPosition(start.x, start.y);
	}
}
