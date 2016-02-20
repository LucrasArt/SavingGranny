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

import lucrasart.Clue.ClueState;
import lucrasart.GameScreenPantano.GameStatePantano;
import lucrasart.Player.State;

public class MapPantano{ 
	
	private SavingGrannyMain main;
	private SpriteBatch batch;
	public static TiledMap map; 
	private TmxMapLoader loader; 
	private OrthogonalTiledMapRenderer renderer; 
	
	public static Player player;
	public static CheckPoint checkPointC;
	boolean kk=false;
	public static Array<Rectangle> platforms; 
	public static Array<EnemySpider> enemies; 
	public static Rectangle start;
	public static Clue clue;
	public static Rectangle checkPoint;
	
	public static DeadEfect deadEfect;
	public static Vector2 deadEfectPosition;
	public static boolean deadEfectFlag=false, deadEfectFlag2=false;
	public static float deadEfectCount=0;
	
	private boolean injureFlag=false;
	private float injureCount=0;
	
	public static Array<Coin> coins;
	public static Array<Life> lifes;
	
	public static boolean win2 = false;
	
	public static boolean flag1 = false;
	
	public static boolean flag858 = false;
	public static boolean flag959 = false;
	
	private static  Array<Entity> entities = new Array<Entity>();
	
	float count=0;
	
	float ttCount=0;
	int tCount=0;
	
	float countWalk =0;
	
	static boolean life3=false,life2=false,life1=false;
	
	Rectangle SueloPausa;

	
	public MapPantano(SavingGrannyMain main, SpriteBatch batch) 
	{
		this.main = main;
		loader = new TmxMapLoader();
		map = loader.load("Map/tiledMap-Pantano.tmx");
		this.batch = batch;
		renderer = new OrthogonalTiledMapRenderer(map, batch);
		
		SueloPausa = new Rectangle(0,0,70,70);
		
		if(PantallaPause.flagPause)
		{
			player.setPosition2(GameScreenPantano.posX, GameScreenPantano.posY);
			SueloPausa.setPosition(GameScreenPantano.posX, GameScreenPantano.posY - SueloPausa.height);
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
		
		for(EnemySpider enemy : enemies)
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
	
	private void update() { 
		
		ttCount+= Gdx.graphics.getDeltaTime();
		tCount = (int) ttCount ;
		GameScreenPantano.timeCount += tCount;
		
		GameScreenPantano.time.setText(""+GameScreenPantano.timeCount);
		
	
		
		//Desactivado
		/*
		
		if(Gdx.input.isKeyPressed(Keys.P))
		{
			PantallaPause.flagPause=true;
			GameScreenPantano.pause=true;
			//GameScreenPantano.gameState = GameStatePantano.Pause;
			GameScreenPantano.GameMusic2.pause();
			GameScreenPantano.posX=player.position.x;
			GameScreenPantano.posY=player.position.y;
			
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
					
					GameScreenPantano.appleCount--;
					GameScreenPantano.appleLabel.setText("x "+GameScreenPantano.appleCount);
					
					count=0;
					
				}
				
				Player.handLeft=false;
			}
			else
			{
				count += Gdx.graphics.getDeltaTime();
				
			if(count > 0.5f)
			{
				
				entities.removeValue(apple, false);
				
				GameScreenPantano.appleCount--;
				GameScreenPantano.appleLabel.setText("x "+GameScreenPantano.appleCount);
				
				count=0;
				
			}
			}
		
		}	
		
		
		for(Coin coin : coins)
		{
			coin.update();
			
			if(player.getBody().overlaps(coin.getBody())) 
			{	
				GameScreenPantano.appleCount++;
				GameScreenPantano.appleLabel.setText("x "+GameScreenPantano.appleCount);
				GameScreenPantano.scoreCount += 10;
				GameScreenPantano.score.setText("x "+GameScreenPantano.scoreCount);
				
				Media.coinSound.play();
				coins.removeValue(coin, false);
				
			
			}
		}
		
		
		for(Life life : lifes)
		{
			life.update();
			
			if(player.getBody().overlaps(life.getBody())) 
			{	
				GameScreenPantano.lifeCount++;
				GameScreenPantano.lifeLabel.setText("x "+GameScreenPantano.lifeCount);
				
				GameScreenPantano.scoreCount += 20;
				GameScreenPantano.score.setText("x "+GameScreenPantano.scoreCount);
				
				Media.lifeSound.play();
				lifes.removeValue(life, false);
				
			
			}
		}
		
		for(EnemySpider enemy : enemies) {
			
			enemy.update();
			
			countWalk += Gdx.graphics.getDeltaTime();
			
			if(countWalk < 1)
			{
				EnemyRat.facesRight=false;	
	
				enemy.body.y=enemy.position.y -= 2;
	
				
			}
				
			if(countWalk > 2 )
			{
				EnemyRat.facesRight=true;	
		
				enemy.body.y=enemy.position.y += 2;
					
	
					
			}
			if(countWalk > 3)
			{
					
					countWalk = 0;
					
			}
			
			
			for(Apple apple : getApples())
			{
				
				if(apple.getBounds().overlaps(enemy.getBounds()))
				{
					entities.removeValue(apple, false);
					enemies.removeValue(enemy, false);
					
					GameScreenPantano.appleCount--;
					GameScreenPantano.appleLabel.setText("x "+GameScreenPantano.appleCount);
					GameScreenPantano.scoreCount += 30;
					GameScreenPantano.score.setText("x "+GameScreenPantano.scoreCount);
					Media.spiderSound.play();
					
					
				}
				
			}

			
				for(Basket basket : getBasket())
				{
				
					if(basket.getBounds().overlaps(enemy.getBounds()))
					{
						entities.removeValue(basket, false);
						enemies.removeValue(enemy, false);
					
						GameScreenPantano.appleLabel.setText("x "+GameScreenPantano.appleCount);
						GameScreenPantano.scoreCount += 30;
						GameScreenPantano.score.setText("x "+GameScreenPantano.scoreCount);
						Media.spiderSound.play();
					
					
					}
				
				}
			
			
			if(player.getBody().overlaps(enemy.getBody())) 
			{	
				
				
				if(Player.state == State.falling || Player.state == State.jumping)
				{
					if(player.bottom.overlaps(enemy.getBody())) 
					{
					
					Player.stateTime += Gdx.graphics.getDeltaTime();
					
					
					Media.spiderSound.play();
					enemies.removeValue(enemy, false);
					
					GameScreenPantano.scoreCount += 30;
					GameScreenPantano.score.setText("x "+GameScreenPantano.scoreCount);
					
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
					
						GameScreenPantano.vidas--;
				
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
					
						
						if(GameScreenPantano.vidas==2)
						{
							life2=true;
							
						}
						if(GameScreenPantano.vidas==1)
						{
							life1=true;
						}
						if(GameScreenPantano.vidas==0)
						{	
							GameScreenPantano.lifeCount--;
							GameScreenPantano.lifeLabel.setText("x "+GameScreenPantano.lifeCount);
							GameScreenPantano.vidas=3;
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
		
		
		if(player.getBody().overlaps(clue.getBody())) { 
			
			GameScreenPantano.gameState = GameStatePantano.Finish;

			Media.finishSound.play();

			GameScreenPantano.GameMusic2.stop();
			
		}
		
		if(kk==false)
		{	
		if(player.getBody().overlaps(checkPointC.getBody())) 
		{ 

			GameScreenPantano.checkpoint2 = true;
			
			Media.checkpointSound.play();
			
			checkPointC.body=null;
			
			kk=true;

		}
		}
		
		
		if(player.getPosition().y < 0)
		{
			GameScreenPantano.vidas=3;
			GameScreenPantano.lifeCount--;
			GameScreenPantano.lifeLabel.setText("x "+GameScreenPantano.lifeCount);
			if(GameScreenPantano.checkpoint2)
			{
				player.setPosition(checkPoint.x, checkPoint.y);
				Media.dieSound.play();
			}
			else
			{	
			reset();
			}
		}
		
		if(GameScreenPantano.lifeCount == 0)
		{
			GameScreenPantano.vidas=3;
			main.gameOver=true;
			flag1 = true;
			GameScreenPantano.checkpoint2 = false;
			GameScreenPantano.gameState = GameStatePantano.GameOver;
			kk=false;
		}
	}
	
	
	private void reset() 
	{ 
		player.setPosition(start.x, start.y);
		Media.dieSound.play();
	}
	
	public void dispose() {
		map.dispose();
		renderer.dispose();
		player.dispose();
		checkPointC.dispose();
		clue.dispose();
		deadEfect.dispose();
		for(EnemySpider enemy : enemies)
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
		enemies = new Array<EnemySpider>();
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
				
				Clue.state= ClueState.boot;
				
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
			EnemySpider enemy = new EnemySpider ();
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
