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

import lucrasart.GameScreenWolf;
import lucrasart.GameScreenWolf.GameStateWolf;
import lucrasart.Huntsman.HuntsmanState;
import lucrasart.Player.State;
import lucrasart.Wolf.WolfState;

public class MapWolf { 
	
	private SavingGrannyMain main;
	private SpriteBatch batch;
	public static TiledMap map; 
	private TmxMapLoader loader; 
	private OrthogonalTiledMapRenderer renderer; 
	
	float count=0;
	
	Vector2 position;
	public Entity entity;
	public Vector2 pos;
	private static  Array<Entity> entities = new Array<Entity>();

	public static Player player; 
	
	public static Wolf wolf;
	
	public static Granny granny;
	
	public static Huntsman huntsman;
	
	public static CheckPoint checkPointC;
	boolean kk=false;
	
	private boolean injureFlag=false;
	private float injureCount=0;
	
	public static Array<Rectangle> platforms; 
	
	public static Rectangle start; 
	public static Rectangle goal;
	
	public static Array<Coin> coins;
	public static Array<Life> lifes;
	public static Array<Wall> walls; 

	public static boolean flag1 = false;
	
	public static Rectangle checkPoint;
	
	int WolfLife = 100;
	
	float countApple=0;
	
	static boolean flag858=false;
	
	float ttCount=0;
	int tCount=0;
	
	float punchCount=0;
	
	boolean visibleHuntsmanFlag=false;
	boolean visibleWallFlag=false;
	boolean wallFlag=false;
	
	float stunnedCount=0;
	boolean WolfDead=false;
	boolean AlmostWolfDead = false;
	
	float hunterFightCount=0;
	boolean hunterFight=false;
	boolean ifThereIsACombat=true;
	
	float auxPositionX, auxPositionY;
	boolean wolfRetardFlag,wolfRetardFlag2,wolfRetardFlag3,wolfRetardFlag4,wolfRetardFlag5=false;
	float wolfRetardCount=0;
	boolean WolfDead2, WolfDead3, WolfDead4, WolfDead5=false;
	boolean WolfAlive,WolfAlive2, WolfAlive3, WolfAlive4, WolfAlive5=false;
	
	boolean fallenWallFlag=false;
	
	Rectangle SueloPausa; 
	
	public MapWolf(SavingGrannyMain main, SpriteBatch batch) {
		this.main = main;
		loader = new TmxMapLoader();
		map = loader.load("Map/tiledMapWolf.tmx");
		this.batch = batch;
		renderer = new OrthogonalTiledMapRenderer(map, batch);
		
		SueloPausa = new Rectangle(0,0,70,70);
		
	
		
		if(PantallaPause.flagPause)
		{
			player.setPosition2(GameScreenWolf.posX, GameScreenWolf.posY);
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

		//if(!WolfDead )
		if(WolfAlive || WolfAlive2 || WolfAlive3 || WolfAlive4 || WolfAlive5)
		{
			wolf.draw(batch);
		}
		
		granny.draw(batch);
		
		if(visibleHuntsmanFlag)
		{
			huntsman.draw(batch);
		}
		
		checkPointC.draw(batch);
		
		for(Coin coin : coins)
		{
			coin.draw(batch);
		}
		for(Apple apple : getApples())
		{
			apple.draw(batch);
		}
		
		if(visibleWallFlag)
		{
			for(Wall wall : walls)
			{
				wall.draw(batch);
			}
		}
		batch.end();
	}
	
	private void update() 
	{ 
		
		ttCount+= Gdx.graphics.getDeltaTime();
		tCount = (int) ttCount ;
		GameScreenWolf.timeCount += tCount;
		
		GameScreenWolf.time.setText(""+GameScreenWolf.timeCount);
		
		//Desactivado
		/*
		if(Gdx.input.isKeyPressed(Keys.P))
		{
			PantallaPause.flagPause=true;
			GameScreenWolf.pause=true;
			//GameScreenWolf.gameState = GameStateWolf.Pause;
			GameScreenWolf.GameMusic2.pause();
			GameScreenWolf.posX=player.position.x;
			GameScreenWolf.posY=player.position.y;
			
		}
		*/
		

		player.update3(platforms); 
		player.update2(Gdx.graphics.getDeltaTime()); 
		
		granny.update();
		huntsman.update();
		
		checkPointC.update();
		
		if(player.getBody().overlaps(SueloPausa))
		{
			player.setPosition2(player.body.x , SueloPausa.y + SueloPausa.height);
		}
		
		
		for(Coin coin : coins)
		{
			coin.update();
			
			if(player.getBody().overlaps(coin.getBody())) 
			{	
				GameScreenWolf.appleCount++;
				GameScreenWolf.appleLabel.setText("x "+GameScreenWolf.appleCount);
				GameScreenWolf.scoreCount += 10;
				GameScreenWolf.score.setText("x "+GameScreenWolf.scoreCount);
				
				coins.removeValue(coin, false);
				
			
			}
		}
		
		for(Life life : lifes)
		{
			life.update();
			
			if(player.getBody().overlaps(life.getBody())) 
			{	
				GameScreenWolf.lifeCount++;
				GameScreenWolf.lifeLabel.setText("x "+GameScreenWolf.lifeCount);
				
				GameScreenWolf.scoreCount += 20;
				GameScreenWolf.score.setText("x "+GameScreenWolf.scoreCount);
				
				Media.lifeSound.play();
				lifes.removeValue(life, false);
				
			
			}
		}
		
		for(Apple apple : getApples())
		{
			apple.update();
			
			if(Player.handLeft)
			{
				apple.setDirection(-400,0);
				
				countApple += Gdx.graphics.getDeltaTime();
				
				if(countApple > 0.5f)
				{
					
					entities.removeValue(apple, false);
					
					GameScreenWolf.appleCount--;
					GameScreenWolf.appleLabel.setText("x "+GameScreenWolf.appleCount);
					
					countApple = 0;
					
				}
				
				Player.handLeft=false;
			}
			else
			{
				countApple += Gdx.graphics.getDeltaTime();
				
				if(countApple > 0.5f)
				{
				
				entities.removeValue(apple, false);
				
				GameScreenWolf.appleCount--;
				GameScreenWolf.appleLabel.setText("x "+GameScreenWolf.appleCount);
				
				countApple = 0;
				
				}
		
			}
		}	
		
		if(ifThereIsACombat)
		{
		
			if(player.getBody().overlaps(huntsman.getBody()))
			{
				wallFlag=true;
				visibleWallFlag=true;
				fallenWallFlag=true;
			}
		}
		
		
		if(fallenWallFlag)
		{
		for(Wall wall : walls)
		{
			wall.update();
		
			if(player.bottom.overlaps(wall.getBody()))
			{
				Player.velocityY = 0;
				player.setPosition2(player.body.x , wall.getBody().y + wall.height);
				Player.inFloor= true;
				Player.state = State.standing;
			
				player.key=true;
			}
		
		
			if(Player.state == State.falling)
			{
		
				if(player.top.overlaps(wall.getBody()) && !player.left.overlaps(wall.getBody()) && !player.right.overlaps(wall.getBody()) )
				{
			
					Player.velocityY = 0;
					player.setPosition2(player.body.x , wall.getBody().y - player.body.height );
				}
			
				if(player.top.overlaps(wall.getBody()) && player.left.overlaps(wall.getBody()))
				{
			
					Player.velocityY = 0;
					player.setPosition2(wall.getBody().x + wall.getBody().width , player.body.y );
				}
				if(player.top.overlaps(wall.getBody()) && player.right.overlaps(wall.getBody()))
				{
			
					Player.velocityY = 0;
					player.setPosition2(wall.getBody().x - player.body.width , player.body.y );
				}	
			}
		
			if(player.left.overlaps(wall.getBody()))
			{
				Player.velocityY = 0;
				player.setPosition2(wall.getBody().x + wall.getBody().width , player.body.y );
				if(Player.inFloor)
				{
					//state = State.walking;
					Player.state = State.stuck;
				}
			}
	
		
			if(player.right.overlaps(wall.getBody()))
			{

				Player.velocityY = 0;
				player.setPosition2(wall.getBody().x - player.body.width , player.body.y );
				if(Player.inFloor)
				{
					//state = State.walking;
					Player.state = State.stuck;
				}
			}
		}
		}
		
		
		
		if(wallFlag)
		{
			
			
		
		//if(WolfLife > 0)
		if(!WolfDead)
			
		{
			WolfAlive=true;
			
			wolf.update();

			if(wolf.position.x > player.position.x && wolf.position.x > 900)
			{
				Wolf.facesRight=false;
				count += Gdx.graphics.getDeltaTime(); 
		
				if (count > 0.50f) 
				{
					wolf.body.x=wolf.position.x -= 100;
					count=0;
				}
			
			}
		
			
			
		
			if(wolf.position.x < player.position.x  && wolf.position.x < 1750)
			{
				count += Gdx.graphics.getDeltaTime(); 
				Wolf.facesRight=true;
			
			
				if (count > 0.5f) 
				{
				
					wolf.body.x=wolf.position.x += 100;
					count=0;
				}
			
			}
			
		if( (player.getBody().overlaps(wolf.getBody()))) 
		{
			
		
			if(Player.state == State.falling || Player.state == State.jumping)	
			{

				if(player.bottom.overlaps(wolf.getBody())) 
				{
			
					Player.stateTime += Gdx.graphics.getDeltaTime();

					WolfLife -= 20;
					GameScreenWolf.wolfCount-= 20;
					GameScreenWolf.wolfLabel.setText("Wolf Life "+GameScreenWolf.wolfCount);
				
					Media.wolfPainSound.play();
				
					player.position.y = wolf.position.y + wolf.height;
				
					flag858=true;
					
					auxPositionX=wolf.position.x;
					auxPositionY=wolf.position.y;
					
					wolf.body=null;
					
					wolfRetardFlag=true;
					
					WolfDead=true;
					WolfAlive=false;
					
					
//					WolfLife=0;
					
					
					GameScreenWolf.scoreCount += 30;
					GameScreenWolf.score.setText("x "+GameScreenWolf.scoreCount);
					
				}
			}
				
				else
				{
						
						GameScreenWolf.lifeCount--;
						GameScreenWolf.lifeLabel.setText("x "+GameScreenWolf.lifeCount);
					
						Media.painSound.play();
					
						injureFlag=true;
					
						//dar un paso hacia atrás
						if(Player.facesRight)
						{
							//reset();
							player.setPosition2(player.position.x-70, player.position.y);
						}
						else
						{
							player.setPosition2(player.position.x+70, player.position.y);
						}
					
				}
			
		}
	}
		if(WolfAlive2)
		{
		
		wolf.update();

		
		if(wolf.position.x > player.position.x && wolf.position.x > 900)
		{
			Wolf.facesRight=false;
			count += Gdx.graphics.getDeltaTime(); 
	
			if (count > 0.50f) 
			{
				wolf.body.x=wolf.position.x -= 100;
				count=0;
			}
		
		}
	
	
		if(wolf.position.x < player.position.x  && wolf.position.x < 1750)
		{
			count += Gdx.graphics.getDeltaTime(); 
			Wolf.facesRight=true;
		
		
			if (count > 0.5f) 
			{
			
				wolf.body.x=wolf.position.x += 100;
				count=0;
			}
		
		}
		
	if( (player.getBody().overlaps(wolf.getBody()))) 
	{
		
	
		if(Player.state == State.falling || Player.state == State.jumping)	
		{

			if(player.bottom.overlaps(wolf.getBody())) 
			{
		
				Player.stateTime += Gdx.graphics.getDeltaTime();

				WolfLife -= 20;
				GameScreenWolf.wolfCount-= 20;
				GameScreenWolf.wolfLabel.setText("Wolf Life "+GameScreenWolf.wolfCount);
			
				Media.wolfPainSound.play();
			
				player.position.y = wolf.position.y + wolf.height;
			
				flag858=true;
				
				auxPositionX=wolf.position.x;
				auxPositionY=wolf.position.y;
				
				wolf.body=null;
				
				wolfRetardFlag2=true;
				WolfAlive2=false;
			
				
//				WolfLife=0;
				
				
				GameScreenWolf.scoreCount += 30;
				GameScreenWolf.score.setText("x "+GameScreenWolf.scoreCount);
				
			}
		}
			
			else
			{
					
					GameScreenWolf.lifeCount--;
					GameScreenWolf.lifeLabel.setText("x "+GameScreenWolf.lifeCount);
				
					Media.painSound.play();
				
					injureFlag=true;
				
					//dar un paso hacia atrás
					if(Player.facesRight)
					{
						player.setPosition2(player.position.x-70, player.position.y);
					}
					else
					{
						player.setPosition2(player.position.x+70, player.position.y);
					}
	
			}
	
	}
	
	
	}
		
		if(WolfAlive3)
		{
		
		wolf.update();

	
		if(wolf.position.x < player.position.x  && wolf.position.x < 1750)
		{
			count += Gdx.graphics.getDeltaTime(); 
			Wolf.facesRight=true;
		
		
			if (count > 0.5f) 
			{

				wolf.body.x=wolf.position.x += 100;
				count=0;
			}
		
		}
		
	if( (player.getBody().overlaps(wolf.getBody()))) 
	{
		
	
		if(Player.state == State.falling || Player.state == State.jumping)	
		{

			if(player.bottom.overlaps(wolf.getBody())) 
			{
		
				Player.stateTime += Gdx.graphics.getDeltaTime();

				WolfLife -= 20;
				GameScreenWolf.wolfCount-= 20;
				GameScreenWolf.wolfLabel.setText("Wolf Life "+GameScreenWolf.wolfCount);
			
				Media.wolfPainSound.play();
			
				player.position.y = wolf.position.y + wolf.height;
			
				flag858=true;
				
				auxPositionX=wolf.position.x;
				auxPositionY=wolf.position.y;
				
				wolf.body=null;
				
				wolfRetardFlag3=true;
				WolfAlive3=false;
			
				
//				WolfLife=0;
				
				
				GameScreenWolf.scoreCount += 30;
				GameScreenWolf.score.setText("x "+GameScreenWolf.scoreCount);
				
			}
		}
			

			else
			{
					
					GameScreenWolf.lifeCount--;
					GameScreenWolf.lifeLabel.setText("x "+GameScreenWolf.lifeCount);
				
					Media.painSound.play();
				
					injureFlag=true;
				
					//dar un paso hacia atrás
					if(Player.facesRight)
					{
						player.setPosition2(player.position.x-70, player.position.y);
					}
					else
					{

						player.setPosition2(player.position.x+70, player.position.y);
					}

					
			}
	}
		}
	
		if(WolfAlive4)
		{
		
		wolf.update();

	
		if(wolf.position.x < player.position.x  && wolf.position.x < 1750)
		{
			count += Gdx.graphics.getDeltaTime(); 
			Wolf.facesRight=true;
		
		
			if (count > 0.5f) 
			{
			
				wolf.body.x=wolf.position.x += 100;
				count=0;
			}
		
		}
		
	if( (player.getBody().overlaps(wolf.getBody()))) 
	{
		
	
		if(Player.state == State.falling || Player.state == State.jumping)	
		{

			if(player.bottom.overlaps(wolf.getBody())) 
			{
		
				Player.stateTime += Gdx.graphics.getDeltaTime();

				WolfLife -= 20;
				GameScreenWolf.wolfCount-= 20;
				GameScreenWolf.wolfLabel.setText("Wolf Life "+GameScreenWolf.wolfCount);
			
				Media.wolfPainSound.play();
			
				player.position.y = wolf.position.y + wolf.height;
			
				flag858=true;
				
				auxPositionX=wolf.position.x;
				auxPositionY=wolf.position.y;
				
				wolf.body=null;
				
				wolfRetardFlag4=true;
				WolfAlive4=false;
			
				
//				WolfLife=0;
				
				
				GameScreenWolf.scoreCount += 30;
				GameScreenWolf.score.setText("x "+GameScreenWolf.scoreCount);
				
			}
		}
			 
			else
			{
					
					GameScreenWolf.lifeCount--;
					GameScreenWolf.lifeLabel.setText("x "+GameScreenWolf.lifeCount);
				
					Media.painSound.play();
				
					injureFlag=true;
				
					//dar un paso hacia atrás
					if(Player.facesRight)
					{
	
						player.setPosition2(player.position.x-70, player.position.y);
					}
					else
					{
		
						player.setPosition2(player.position.x+70, player.position.y);
					}
					
			}
		}
	}
	
	
	if(WolfAlive5)
	{
	
	wolf.update();


	
	if(wolf.position.x > player.position.x && wolf.position.x > 900)
	{
		Wolf.facesRight=false;
		count += Gdx.graphics.getDeltaTime(); 

		if (count > 0.50f) 
		{
			wolf.body.x=wolf.position.x -= 100;
			count=0;
		}
	
	}


	if(wolf.position.x < player.position.x  && wolf.position.x < 1750)
	{
		count += Gdx.graphics.getDeltaTime(); 
		Wolf.facesRight=true;
	
	
		if (count > 0.5f) 
		{
	
			wolf.body.x=wolf.position.x += 100;
			count=0;
		}
	
	}
	
if( (player.getBody().overlaps(wolf.getBody()))) 
{
	

	if(Player.state == State.falling || Player.state == State.jumping)	
	{

		if(player.bottom.overlaps(wolf.getBody())) 
		{
	
			Player.stateTime += Gdx.graphics.getDeltaTime();

			WolfLife -= 20;
			GameScreenWolf.wolfCount-= 20;
			GameScreenWolf.wolfLabel.setText("Wolf Life "+GameScreenWolf.wolfCount);
		
			Media.wolfPainSound.play();
		
			player.position.y = wolf.position.y + wolf.height;
		
			flag858=true;
			
			auxPositionX=wolf.position.x;
			auxPositionY=wolf.position.y;
			
			wolf.body=null;
			

			WolfLife=0;
			
			
			GameScreenWolf.scoreCount += 30;
			GameScreenWolf.score.setText("x "+GameScreenWolf.scoreCount);
			
		}
	}
		
		else
		{
				
				GameScreenWolf.lifeCount--;
				GameScreenWolf.lifeLabel.setText("x "+GameScreenWolf.lifeCount);
			
				Media.painSound.play();
			
				injureFlag=true;
			
				//dar un paso hacia atrás
				if(Player.facesRight)
				{
					player.setPosition2(player.position.x-70, player.position.y);
				}
				else
				{
					player.setPosition2(player.position.x+70, player.position.y);
				}

		}
}
		
		
	}
	//else
	if(WolfLife == 0)
	{
		//wolf.body=null;
		
		wallFlag=false;
		//visibleWallFlag=false;
		
		AlmostWolfDead=true;
		
	}
		}	
		
		

		
		if(wolfRetardFlag)
		{
			wolfRetardCount += Gdx.graphics.getDeltaTime();
			
			if(wolfRetardCount > 0.5f)
			{
			
			wolf = new Wolf(); 
			
			if(Wolf.facesRight)
			{
				wolf.setPosition(auxPositionX, auxPositionY);
			}
			if(!Wolf.facesRight)
			{
				wolf.setPosition(auxPositionX, auxPositionY);
			}
			
			wolfRetardCount=0;
			
			wolfRetardFlag=false;
			WolfAlive2=true;
			
			}
		
		}
		
		if(wolfRetardFlag2)
		{
			wolfRetardCount += Gdx.graphics.getDeltaTime();
			
			if(wolfRetardCount > 0.5f)
			{
			
			wolf = new Wolf(); 
			
			if(Wolf.facesRight)
			{
				wolf.setPosition(auxPositionX, auxPositionY);
			}
			if(!Wolf.facesRight)
			{
				wolf.setPosition(auxPositionX, auxPositionY);
			}
			
			
			wolfRetardCount=0;
			wolfRetardFlag2=false;
			WolfAlive3=true;
			
			}
		
		}
		
		if(wolfRetardFlag3)
		{
			wolfRetardCount += Gdx.graphics.getDeltaTime();
			
			if(wolfRetardCount > 0.5f)
			{
			
			wolf = new Wolf(); 
			
			if(Wolf.facesRight)
			{
				wolf.setPosition(auxPositionX, auxPositionY);
			}
			if(!Wolf.facesRight)
			{
				wolf.setPosition(auxPositionX, auxPositionY);
			}
			
			
			wolfRetardCount=0;
			wolfRetardFlag3=false;
			WolfAlive4=true;
			
			}
		
		}
		if(wolfRetardFlag4)
		{
			wolfRetardCount += Gdx.graphics.getDeltaTime();
			
			if(wolfRetardCount > 0.5f)
			{
			
			wolf = new Wolf(); 
			
			if(Wolf.facesRight)
			{
				wolf.setPosition(auxPositionX, auxPositionY);
			}
			if(!Wolf.facesRight)
			{
				wolf.setPosition(auxPositionX, auxPositionY);
			}
			
			
			wolfRetardCount=0;
			wolfRetardFlag4=false;
			WolfAlive5=true;
			
			}
		
		}
		
		if(AlmostWolfDead)
		{
			Wolf.state = WolfState.stunned;
			
			stunnedCount += Gdx.graphics.getDeltaTime(); 

			if(Wolf.facesRight)
			{
				Huntsman.facesRight=false;
				huntsman.position.x = wolf.position.x+wolf.width;
				huntsman.position.y = 140;
			}
			else
			{
				Huntsman.facesRight=true;
				huntsman.position.x = wolf.position.x-huntsman.width;
				huntsman.position.y = 140;
			}
			
			if(stunnedCount > 2)
			{
				//wolf.body=null;
				//WolfDead=true;
				stunnedCount=0;
				
				visibleHuntsmanFlag=true;
				hunterFight=true;
				
				AlmostWolfDead=false;
				
				
			}
			
			
			
		}
		
		if(hunterFight)
		{
			Wolf.state = WolfState.dead;
			
			Huntsman.state = HuntsmanState.fight;
			
			hunterFightCount += Gdx.graphics.getDeltaTime(); 
			
			if(hunterFightCount>2)
			{
				
				Huntsman.state = HuntsmanState.alive;
				
				WolfDead=true;
				WolfAlive5=false;
				wolf.body=null;
				
				hunterFightCount=0;
				ifThereIsACombat=false;
				huntsman.body=null;
				
				visibleWallFlag=false;
				fallenWallFlag=false;
				
				hunterFight=false;
			}
			
		}
	
		if(GameScreenWolf.wolfCount == 20)
		{
			Media.wolfSound.play();
			
			
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
		
		
		if(player.getBody().overlaps(goal)) { 
			GameScreenWolf.gameState = GameStateWolf.Finish;
			GameScreenWolf.GameMusic2.stop();
			
			Media.endSound.play();
			
			GameScreenWolf.wolfCount = 100;
			GameScreenWolf.wolfLabel.setText("Wolf Life "+GameScreenWolf.wolfCount);
		}
		
		if(kk==false)
		{
		if(player.getBody().overlaps(checkPointC.getBody())) 
		{ 
	
			GameScreenWolf.checkpoint = true;
			
			Media.checkpointSound.play();
			
			checkPointC.body=null;
			
			kk=true;
			
		}
		}
		
		
		if(player.getPosition().y < 0)
		{
			GameScreenWolf.lifeCount--;
			GameScreenWolf.lifeLabel.setText("x "+GameScreenWolf.lifeCount);
			if(GameScreenWolf.checkpoint)
			{
				player.setPosition2(checkPoint.x, checkPoint.y);
				Media.dieSound.play();
			}
			else
			{	
			reset();
			
			
			}
		}
		
		if(GameScreenWolf.lifeCount == 0)
		{
			main.gameOver=true;
			flag1 = true;
			GameScreenWolf.checkpoint = false;
			GameScreenWolf.gameState = GameStateWolf.GameOver;
			kk=false;
			
			wallFlag=false;
			visibleWallFlag=false;
			
			WolfLife=100;
			GameScreenWolf.wolfCount = 100;
			GameScreenWolf.wolfLabel.setText("Wolf Life "+GameScreenWolf.wolfCount);
		}
	}
	
	
	
	private void reset() 
	{ 
		player.setPosition(start.x, start.y);
		Media.dieSound.play();
	}
	
	public void dispose() 
	{
		map.dispose();
		renderer.dispose();
		player.dispose();
		checkPointC.dispose();
		for(Coin coin : coins)
			coin.dispose();
		for(Wall wall : walls)
		{
			wall.dispose();
		}
		for(Apple apple : getApples())
			apple.dispose();
		for(Basket basket : getBasket())
			basket.dispose();
		
		wolf.dispose();
		granny.dispose();
		huntsman.dispose();
	}
	
	public TiledMap getMap() {
		return map;
	}

	public Player getPlayer() { 
		return player;
	}

	static void processMapMetadata() {

		platforms = new Array<Rectangle>(); 
		coins = new Array<Coin>();
		lifes = new Array<Life>();
		walls= new Array<Wall>();

		MapObjects objects = map.getLayers().get("Objects").getObjects(); 

		for (MapObject object : objects) {
			String name = object.getName();
			RectangleMapObject rectangleObject = (RectangleMapObject) object;
			Rectangle rectangle = rectangleObject.getRectangle();

			if(name.equals("PlayerStart")) 
				start = rectangle;
			if(name.equals("PlayerGoal"))
				goal = rectangle;
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
		
		objects = map.getLayers().get("Wall").getObjects();
		
		for (MapObject object : objects) {
			RectangleMapObject rectangleObject = (RectangleMapObject) object;
			Rectangle rectangle = rectangleObject.getRectangle();
			
			Wall wall = new Wall();
			
			wall.setPosition(rectangle.x, rectangle.y);
			
			walls.add(wall);
		} 
			
		objects = map.getLayers().get("Wolf").getObjects();
		
		for (MapObject object : objects) {
			RectangleMapObject rectangleObject = (RectangleMapObject) object;
			Rectangle rectangle = rectangleObject.getRectangle();
			
			wolf= new Wolf();
			
			wolf.setPosition(rectangle.x, rectangle.y);
		}
		
		
		objects = map.getLayers().get("Granny").getObjects();
		
		for (MapObject object : objects) {
			RectangleMapObject rectangleObject = (RectangleMapObject) object;
			Rectangle rectangle = rectangleObject.getRectangle();
			
			granny= new Granny();
			
			granny.setPosition(rectangle.x, rectangle.y);
		}
		

		objects = map.getLayers().get("Huntsman").getObjects();
		
		for (MapObject object : objects) {
			RectangleMapObject rectangleObject = (RectangleMapObject) object;
			Rectangle rectangle = rectangleObject.getRectangle();
			
			huntsman= new Huntsman();
			
			huntsman.setPosition(rectangle.x, rectangle.y);
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
