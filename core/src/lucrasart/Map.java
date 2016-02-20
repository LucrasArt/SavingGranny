package lucrasart;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;




import lucrasart.GameScreen1;
import lucrasart.Player;
import lucrasart.GameScreen1.GameState;
import lucrasart.Player.State;


public class Map { 
	
	private SavingGrannyMain main;
	private SpriteBatch batch;
	public static TiledMap map; 
	private TmxMapLoader loader; 
	private OrthogonalTiledMapRenderer renderer; 
	
	Vector2 position;
	public Entity entity;
	public Vector2 pos;
	private static  Array<Entity> entities = new Array<Entity>();
	
	public static Player player; 
	public static CheckPoint checkPointC;
	public static Array<Rectangle> platforms; 
	public static Array<Enemy> enemies; 
	public static Rectangle start; 
	public static Clue clue;
	
	public static DeadEfect deadEfect;
	public static Vector2 deadEfectPosition;
	public static boolean deadEfectFlag=false, deadEfectFlag2=false;
	public static float deadEfectCount=0;
	
	private boolean injureFlag=false;
	private float injureCount=0;
	
	public static Array<Coin> coins;
	public static Array<Life> lifes;

	public static boolean flag1 = false;
	
	public static Rectangle checkPoint;
	public static boolean flag858=false;

	boolean kk=false;
	
	float count = 0; 
	
	float ttCount=0;
	int tCount=0;
	
	static boolean life3=false,life2=false,life1=false;
	
	Rectangle SueloPausa;
	
	
	public Map(SavingGrannyMain main, SpriteBatch batch)
	{
		this.main = main;
		loader = new TmxMapLoader();
		map = loader.load("Map/tiledMap-Town.tmx");
		this.batch = batch;
		renderer = new OrthogonalTiledMapRenderer(map, batch);
		
		SueloPausa = new Rectangle(0,0,70,70);
		
		if(PantallaPause.flagPause)
		{
			player.setPosition2(GameScreen1.posX, GameScreen1.posY);
			SueloPausa.setPosition(GameScreen1.posX, GameScreen1.posY - 120);
			kk=true;
		}
		else
		{	
			processMapMetadata();
			
		}
		
	}

	public void draw(OrthographicCamera camera) 
	{ 
		renderer.setView(camera);
		renderer.render();
		
		update();
		
		batch.begin();

		player.draw(batch);

		checkPointC.draw(batch);
		
		clue.draw(batch);
		
		if(deadEfectFlag)
		{
			deadEfect.draw(batch);
		}
		
		for(Enemy enemy : enemies)
		{
			enemy.draw(batch);
		}
		
		for(Coin coin : coins)
		{
			coin.draw(batch);
		}
		for(Life life : lifes)
		{
			life.draw(batch);
		}
		for(Apple apple : getApples())
		{
			apple.draw(batch);
		}
		
		batch.end();
	}
	
	private void update() 
	{ 

		ttCount+= Gdx.graphics.getDeltaTime();
		tCount = (int) ttCount;
		GameScreen1.timeCount += tCount;
		GameScreen1.time.setText(""+GameScreen1.timeCount);
	
		//Desactivado
		/*
		if(Gdx.input.isKeyPressed(Keys.P))
		{
			PantallaPause.flagPause=true;
			GameScreen1.pause=true;
			//GameScreen1.gameState = GameState.Pause;
			GameScreen1.GameMusic1.pause();
			GameScreen1.posX=player.position.x;
			GameScreen1.posY=player.position.y;
			
		}
		*/
		
 
		player.update3(platforms); 
		player.update2(Gdx.graphics.getDeltaTime()); 
		
		
		checkPointC.update();
		
		clue.update();
		
		if(player.getBody().overlaps(SueloPausa))
		{
			player.setPosition2(player.body.x , SueloPausa.y + SueloPausa.height);
		}
		
	
		
		for(Apple apple : getApples())
		{
			apple.update();
			
			if(Player.handLeft)
			{
				apple.setDirection(-400,0);
				
				count += Gdx.graphics.getDeltaTime();
				
				if(count > 0.5f)
				{
					
					entities.removeValue(apple, false);
					
					GameScreen1.appleCount--;
					GameScreen1.appleLabel.setText("x "+GameScreen1.appleCount);
					
					count = 0;
					
				}
				
				Player.handLeft=false;
				
			}
			else if(!Player.handLeft)
			{
				count += Gdx.graphics.getDeltaTime();
			
				if(count > 0.5f)
				{
				
					entities.removeValue(apple, false);
				
					GameScreen1.appleCount--;
					GameScreen1.appleLabel.setText("x "+GameScreen1.appleCount);
				
					count = 0;
				}
		
			}
		}	
			
		
		
		for(Coin coin : coins)
		{
			coin.update();
			if(player.getBody().overlaps(coin.getBody())) 
			{	
				GameScreen1.appleCount++;
				GameScreen1.appleLabel.setText("x "+GameScreen1.appleCount);
				GameScreen1.scoreCount += 10;
				GameScreen1.score.setText("x "+GameScreen1.scoreCount);
				
				Media.coinSound.play();
				coins.removeValue(coin, false);
				
			
			}
		}
		
		
		for(Life life : lifes)
		{
			life.update();
			if(player.getBody().overlaps(life.getBody())) 
			{	
				GameScreen1.lifeCount++;
				GameScreen1.lifeLabel.setText("x "+GameScreen1.lifeCount);
				
				GameScreen1.scoreCount += 20;
				GameScreen1.score.setText("x "+GameScreen1.scoreCount);
				
				Media.lifeSound.play();
				lifes.removeValue(life, false);
				
			
			}
		}
		
		for(Enemy enemy : enemies)
		{
			enemy.update();
			
			for(Apple apple : getApples())
			{
				
				
				if(apple.getBounds().overlaps(enemy.getBounds()))
				{
					entities.removeValue(apple, false);
					enemies.removeValue(enemy, false);
					
					GameScreen1.appleCount--;
					GameScreen1.appleLabel.setText("x "+GameScreen1.appleCount);
					GameScreen1.scoreCount += 30;
					GameScreen1.score.setText("x "+GameScreen1.scoreCount);
					
					Media.ovejaSound.play();
					
					
				}
				
			}
			
				for(Basket basket : getBasket())
				{
				
					if(basket.getBounds().overlaps(enemy.getBounds()))
					{
						entities.removeValue(basket, false);
						enemies.removeValue(enemy, false);
					
						GameScreen1.appleLabel.setText("x "+GameScreen1.appleCount);
						GameScreen1.scoreCount += 30;
						GameScreen1.score.setText("x "+GameScreen1.scoreCount);
					
						Media.ovejaSound.play();
					}
				
				}
			
			if(player.getBody().overlaps(enemy.getBody())) 
			{	
				
				if(Player.state == State.falling || Player.state == State.jumping)
				{
					
					if(player.bottom.overlaps(enemy.getBody())) 
					{
					
						Player.stateTime += Gdx.graphics.getDeltaTime();
					
						Media.ovejaSound.play();
					
						enemies.removeValue(enemy, false);
					
						GameScreen1.scoreCount += 30;
						
						GameScreen1.score.setText("x "+GameScreen1.scoreCount);
					
						deadEfectFlag=true;
					
						deadEfect= new DeadEfect();
					
						deadEfect.setPosition(enemy.body.x, enemy.body.y);
					
						player.position.y = deadEfect.position.y + deadEfect.height;
					
						flag858=true;
					
						deadEfectFlag2=true;
					
					}
				}
				
				else
				{
					
					Media.dieSound.play();

					
						GameScreen1.vidas--;
				
						
						injureFlag=true;
						
						
						//dar un paso hacia atrás
						if(Player.facesRight)
						{
							player.setPosition2(player.position.x-30, player.position.y);
							Media.painSound.play();
						}
						else
						{
							player.setPosition2(player.position.x+30, player.position.y);
							Media.painSound.play();
						}
				
						
						if(GameScreen1.vidas==2)
						{
							life2=true;
							
						}
						if(GameScreen1.vidas==1)
						{
							life1=true;
						}
						
						if(GameScreen1.vidas==0)
						{	
							life3=true;
							GameScreen1.lifeCount--;
							GameScreen1.lifeLabel.setText("x "+GameScreen1.lifeCount);
							GameScreen1.vidas=3;
						}
					
				}
			
			}
		}
		
		
		if(deadEfectFlag2)
		{
			deadEfect.update();
			
		deadEfectCount += Gdx.graphics.getDeltaTime();
		
		if(deadEfectCount > 0.8)
		{

			deadEfect.update();

				
			deadEfectFlag=false;
			deadEfectCount=0;
			deadEfect.body=null;
			deadEfectFlag2=false;

			
		}
	}	
		
		
		if(injureFlag)
		{
			Player.state = State.injure;
			
			injureCount += Gdx.graphics.getDeltaTime();
			
			if(injureCount > 0.2)
			{
				Player.state = State.standing;
				
				injureFlag=false;
				injureCount=0;
			}
		}
		

		if(player.getBody().overlaps(clue.getBody())) 
		{ 
			GameScreen1.GameMusic1.stop();
			Media.finishSound.play();
			GameScreen1.gameState = GameState.Finish;
		}
		
if(player.getBody().overlaps(checkPoint)) 	
{
	GameScreen1.checkpoint = true;
	
	if(kk==false)
	{
		if(player.getBody().overlaps(checkPointC.body)) 
		{ 
			
			Media.checkpointSound.play();
			
			checkPointC.body=null;
			
			kk=true;
			
		}
	}	
}		
		if(player.getPosition().y < 0)
		{
			GameScreen1.vidas=3;
			
			GameScreen1.lifeCount--;
			GameScreen1.lifeLabel.setText("x "+GameScreen1.lifeCount);
			if(GameScreen1.checkpoint)
			{
				player.setPosition(checkPoint.x, checkPoint.y);
				Media.dieSound.play();
			}
			else
			{	
			reset();
			}
		}
		
		if(GameScreen1.lifeCount == 0)
		{
			GameScreen1.vidas=3;
			main.gameOver=true;
			flag1 = true;
			GameScreen1.checkpoint = false;
			GameScreen1.gameState = GameState.GameOver;
			//kk=false;
		}
	}
	
	
	
	private void reset() 
	{ 
		player.setPosition2(start.x, start.y);
		Media.dieSound.play();
	}
	
	public void dispose() 
	{
		map.dispose();
		renderer.dispose();
		player.dispose();
		checkPointC.dispose();
		clue.dispose();
		deadEfect.dispose();
		for(Enemy enemy : enemies)
			enemy.dispose();
		for(Coin coin : coins)
			coin.dispose();
		for(Apple apple : getApples())
			apple.dispose();
		for(Basket basket : getBasket())
			basket.dispose();
	}
	
	public TiledMap getMap() {
		return map;
	}

	public Player getPlayer() { 
		return player;
	}
	
	static void processMapMetadata() 
	{
		platforms = new Array<Rectangle>(); 
		enemies = new Array<Enemy>();
		coins = new Array<Coin>();
		lifes = new Array<Life>();

		MapObjects objects = map.getLayers().get("Objects").getObjects(); 

		for (MapObject object : objects) {
			String name = object.getName();
			RectangleMapObject rectangleObject = (RectangleMapObject) object;
			Rectangle rectangle = rectangleObject.getRectangle();

			if(name.equals("PlayerStart")) 
				start = rectangle;
			if(name.equals("PlayerGoal"))
			{	

				clue = new Clue();
				
				clue.setPosition(rectangle.x, rectangle.y);
			}
				
			if(name.equals("CheckPoint"))
			{
				checkPoint = rectangle;
				
				checkPointC = new CheckPoint();
				
				checkPointC.setPosition(rectangle.x, rectangle.y);
			}
			if(name.equals("coin"))
			{
				Coin coin = new Coin();
				
				coin.setPosition(rectangle.x, rectangle.y);
				
				coins.add(coin);
			}
			
			if(name.equals("extra"))
			{
				Life life = new Life();
				
				life.setPosition(rectangle.x, rectangle.y);
				
				lifes.add(life);
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
		
		player.setPosition2(start.x, start.y);
		
	
	}
	
	
	public static void addEntity(Entity entity)
	{
		entities.add(entity);
	}
	
	private Array<Apple> getApples()
	{
		Array<Apple> ret = new Array<Apple>();
		for(Entity e: entities)
			if(e instanceof Apple)
			{
				ret.add((Apple)e);
				
			}
		return ret;
		
	}
	
	
	private Array<Basket> getBasket()
	{
		Array<Basket> ret = new Array<Basket>();
		for(Entity e: entities)
			if(e instanceof Basket)
			{
				ret.add((Basket)e);
				
			}
		return ret;
		
	}
}
