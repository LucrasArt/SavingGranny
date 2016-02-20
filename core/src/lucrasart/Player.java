package lucrasart;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;


public class Player extends Entity
{ 
	public static final float WIDTH = 60; 
	public static final float HEIGHT = 120; 
	public static final float VELOCITY = 350f; 

	private Texture frame100;
	private TextureRegion frame100L;
	
	private Texture frame1,frame8,frameN1,frameN2,frameN3, frameN4, frameN5,frameN6,frameN7, frameN8; 
	private TextureRegion frame1L, frame8L,frameN1L,frameN2L,frameN3L,frameN4L, frameN5L,frameN6L,frameN7L,frameN8L;

	private Texture Down, fight1, fight2, fight3, fight4;
	private TextureRegion downL, fight1L, fight2L, fight3L, fight4L;
	
	private Animation pain,painL;
	
	private Animation stand,jumping,walk;
	private Animation standL,jumpingL,walkL;
	
	private Animation fight, fightL;
	private Animation abajo, abajoL;

	private Animation fixed, fixedL;
	
	public static boolean facesRight;
	
	enum State {standing, walking, jumping, falling, low, fighting, injure, stuck};
	
	static State state;
	
	public static float stateTime; 
	
	public static boolean  inFloor; 

	public static float count=0, flag=0;
	
	long lastFire;
	
	static float velocityY;
	
	Rectangle bottom, left, right, top, full;
	
	Rectangle auxRectangle;
	
	boolean key = false;

	static boolean handLeft = false;
	
	
	public Player() 
	{
		
		super(WIDTH, HEIGHT, new Rectangle());
		
		full   = new Rectangle(0, 0, 60, 120);
		bottom = new Rectangle(20, 0, 25, 16);
		left   = new Rectangle(0, 16, 30, 88);
		right  = new Rectangle(30, 16, 30, 88);
		top    = new Rectangle(0, 104, 60, 16);
		
		Media.create();
		
		stateTime = 0;
		
		inFloor = false;
		
		velocityY = 0;
		
		state = State.standing;
		
		facesRight = true;
		//CaperuTexture = new Texture ("Player/animacionCaperu3.png");
		//CaperuTextureL = new TextureRegion (CaperuTexture);
		//CaperuTextureL.flip(true, false);
		//TextureRegion[] regions = TextureRegion.split(CaperuTexture,60,120)[0];
		//TextureRegion[] regionsL = TextureRegion.split(CaperuTextureL,60,120)[0];
		//stand = new Animation(0,regions[0]);
		//jumping = new Animation(0,regions[7]);
		//walk = new Animation(0.15f,regions[0],regions[1],regions[2],regions[3],regions[4]);
		//walk = new Animation(0.09f,regions[0],regions[1],regions[2],regions[3],regions[4],regions[5],regions[6]);
		//walk.setPlayMode(PlayMode.LOOP);
		
		
		frame100= new Texture("Player/injureFrame.png");
		frame100L=new TextureRegion(frame100);
		frame100L.flip(true, false);
		

		frame1 = new Texture("Player/Frame1.png");
		frame8 = new Texture("Player/CaperucitaJump2.png");
		
		frameN1= new Texture("Player/Mini-AnimacionN1-2.png");
		frameN2= new Texture("Player/Mini-AnimacionN2-2.png");
		frameN3= new Texture("Player/Mini-AnimacionN3-3.png");
		frameN4= new Texture("Player/Mini-AnimacionN4-2.png");
		frameN5= new Texture("Player/Mini-AnimacionN5-2.png");
		frameN6= new Texture("Player/Mini-AnimacionN6-2.png");
		frameN7= new Texture("Player/Mini-AnimacionN7-2.png");
		frameN8= new Texture("Player/Mini-AnimacionN8-2.png");

		
		frameN1L= new TextureRegion(frameN1);
		frameN1L.flip(true, false);
		frameN2L= new TextureRegion(frameN2);
		frameN2L.flip(true, false);
		frameN3L= new TextureRegion(frameN3);
		frameN3L.flip(true, false);
		frameN4L= new TextureRegion(frameN4);
		frameN4L.flip(true, false);
		frameN5L= new TextureRegion(frameN5);
		frameN5L.flip(true, false);
		frameN6L= new TextureRegion(frameN6);
		frameN6L.flip(true, false);
		frameN7L= new TextureRegion(frameN7);
		frameN7L.flip(true, false);
		frameN8L= new TextureRegion(frameN8);
		frameN8L.flip(true, false);
		
		
		frame1L = new TextureRegion (frame1);
		frame1L.flip(true, false);
		
		frame8L = new TextureRegion (frame8);
		frame8L.flip(true, false);
		

		Down = new Texture("Player/P-agachada.png");
		fight1 = new Texture("Player/Pcestazo1.png");
		fight2 = new Texture("Player/Pcestazo2.png");
		fight3 = new Texture("Player/Pcestazo3.png");
		fight4 = new Texture("Player/Pcestazo4.png");
		
		downL = new TextureRegion(Down);
		downL.flip(true, false);
		fight1L = new TextureRegion(fight1);
		fight1L.flip(true, false);
		fight2L = new TextureRegion(fight2);
		fight2L.flip(true, false);
		fight3L = new TextureRegion(fight3);
		fight3L.flip(true, false);
		fight4L = new TextureRegion(fight4);
		fight4L.flip(true, false);
		
		
		pain= new Animation(0,new TextureRegion(frame100));
		painL= new Animation(0,new TextureRegion(frame100L));
		
		stand = new Animation(0,new TextureRegion(frame1));
		standL = new Animation(0,new TextureRegion(frame1L));
		
		jumping = new Animation(0,new TextureRegion(frame8));
		jumpingL = new Animation(0,new TextureRegion(frame8L));
		
		//estaba a 0.1 estaba despues a 0.09f
		walk = new Animation(0.05f, new TextureRegion(frameN1), new TextureRegion(frameN2), new TextureRegion(frameN3), new TextureRegion(frameN4), new TextureRegion(frameN5),  new TextureRegion(frameN6), new TextureRegion(frameN7),new TextureRegion(frameN8));
		walk.setPlayMode(PlayMode.LOOP); 
		
		walkL = new Animation(0.05f, new TextureRegion(frameN1L), new TextureRegion(frameN2L), new TextureRegion(frameN3L), new TextureRegion(frameN4L), new TextureRegion(frameN5L),  new TextureRegion(frameN6L), new TextureRegion(frameN7L),new TextureRegion(frameN8L));
		walkL.setPlayMode(PlayMode.LOOP); 
		
		abajo = new Animation (0, new TextureRegion(Down));
		abajoL = new Animation (0, new TextureRegion(downL));
		
		fight = new Animation (0.01f,new TextureRegion(fight1),new TextureRegion(fight2),new TextureRegion(fight3),new TextureRegion(fight4));
		fight.setPlayMode(PlayMode.LOOP);
		fightL = new Animation (0.01f,new TextureRegion(fight1L),new TextureRegion(fight2L),new TextureRegion(fight3L),new TextureRegion(fight4L));
		fightL.setPlayMode(PlayMode.LOOP_PINGPONG);
		
		fixed= new Animation (0, new TextureRegion(frame1));
		fixedL = new Animation (0, new TextureRegion(frame1L));
		
	}
	
	
	@Override
	public void draw(SpriteBatch batch) { 
		
	switch(state)
	{
		case standing:
			
			if(Player.facesRight)
			{
				
				setRegion(stand.getKeyFrame(stateTime));
			}
			if(!Player.facesRight)
			{
				setRegion(standL.getKeyFrame(stateTime));
			}
			
			
			break;
			
		case walking:
			if(Player.facesRight)
			{
				
				setRegion(walk.getKeyFrame(stateTime));
			}
			if(!Player.facesRight)
			{
				setRegion(walkL.getKeyFrame(stateTime));
			}
			
			
			break;
		
		case jumping:
		case falling:
			if(Player.facesRight)
			{
				
				setRegion(jumping.getKeyFrame(stateTime));
			}
			if(!Player.facesRight)
			{
				setRegion(jumpingL.getKeyFrame(stateTime));
			}
			
			break;
			
		case low:
			if(Player.facesRight)
			{
				
				setRegion(abajo.getKeyFrame(stateTime));
			}
			if(!Player.facesRight)
			{
				setRegion(abajoL.getKeyFrame(stateTime));
			}
			
			break;
			
		case fighting:
			if(Player.facesRight)
			{
				
				setRegion(fight.getKeyFrame(stateTime));
			}
			if(!Player.facesRight)
			{
				setRegion(fightL.getKeyFrame(stateTime));
			}
			
			break;	
			
		case injure:
			if(Player.facesRight)
			{
				
				setRegion(pain.getKeyFrame(stateTime));
			}
			if(!Player.facesRight)
			{
				setRegion(painL.getKeyFrame(stateTime));
			}
			
			break;	
			
		case stuck:
			if(Player.facesRight)
			{
				
				setRegion(fixed.getKeyFrame(stateTime));
			}
			if(!Player.facesRight)
			{
				setRegion(fixedL.getKeyFrame(stateTime));
			}
			
			break;	
		
	}
		
		super.draw(batch);
		
	}
	
	
	public void update2(float delta)
	{
		if (Gdx.graphics.getDeltaTime() > .2) delta = 0.05f;
		
		velocityY -= 1000 * Gdx.graphics.getDeltaTime();
		
		setPosition2(body.x , body.y + (velocityY*Gdx.graphics.getDeltaTime()));
		
	}
	
	public void update3(Array<Rectangle> platforms) 
	{ 

		for(Rectangle rectangle : platforms) 
		{
		
		if(bottom.overlaps(rectangle))
		{
			velocityY = 0;
			setPosition2(body.x , rectangle.y + rectangle.height);
			inFloor= true;
			state = State.standing;
			
			key=true;
		}
		
		
		if(state == State.falling)
		{
		
			if(top.overlaps(rectangle) && !left.overlaps(rectangle) && !right.overlaps(rectangle) )
			{
			
				velocityY = 0;
				setPosition2(body.x , rectangle.y - body.height );
			}
			
			if(top.overlaps(rectangle) && left.overlaps(rectangle))
			{
			
				velocityY = 0;
				setPosition2(rectangle.x + rectangle.width , body.y );
			}
			if(top.overlaps(rectangle) && right.overlaps(rectangle))
			{
			
				velocityY = 0;
				setPosition2(rectangle.x - body.width , body.y );
			}
		}
		
		if(left.overlaps(rectangle))
		{

			velocityY = 0;
			setPosition2(rectangle.x + rectangle.width , body.y );
			if(inFloor)
			{
				state = State.stuck;
			}
		}
	
		
		if(right.overlaps(rectangle))
		{
			velocityY = 0;
			setPosition2(rectangle.x - body.width , body.y );
			if(inFloor)
			{
				state = State.stuck;
			}
		}
		
		
		}
		

		if ((Gdx.input.isKeyPressed(Input.Keys.LEFT ) || Gdx.input.isKeyPressed(Input.Keys.A )))
		{
				moveLeft(Gdx.graphics.getDeltaTime());
			
			
			
		}
		if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D )))
		{
			
				moveRight(Gdx.graphics.getDeltaTime());
			
			
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isKeyPressed(Input.Keys.UP ) || Gdx.input.isKeyPressed(Input.Keys.W ) )
		{
			
			if(key)	
			{
			inFloor = false;
				
				state = State.jumping;
				
				if (velocityY == 0) 
				{
				   velocityY = 600;
				
				}
			}
			key=false;
			}
		
		if(velocityY != 0)
		{
			state = State.falling;
			inFloor=false;
		}
		
		//Lanzar manzanas		antes la letra G
				if (Gdx.input.isKeyPressed(Input.Keys.X) || Gdx.input.isKeyPressed(Input.Keys.K ))
				{
					//Si hay manzanas disponibles en la cesta se lanzara una 
							
					if(SavingGrannyMain.win)
					{
						if(GameScreen1.appleCount>0)
						{
							
							if (System.currentTimeMillis() - lastFire >= 500) 
							{
								
								Media.appleSound.play();
								
								
								if(facesRight)
								{
									Map.addEntity(new Apple(position.cpy().add(25,60)));
								}
								
							//lanzar hacia la izquierda
								else if(!facesRight)
								{
										
									handLeft=true;
									Map.addEntity(new Apple(position.cpy().add(-25,60)));
										
								}
								
								lastFire = System.currentTimeMillis();
							}
						}
						
					}
				
					if(SavingGrannyMain.win2)
					{
						if(GameScreen2.appleCount>0)
						{
							
							if (System.currentTimeMillis() - lastFire >= 500) 
							{
							
								Media.appleSound.play();
								
								if(facesRight)
								{
									Map2.addEntity(new Apple(position.cpy().add(25,60)));
								}
								
								//lanzar hacia la izquierda
								else if(!facesRight)
								{
									handLeft=true;
									Map2.addEntity(new Apple(position.cpy().add(-25,60)));
										
								}
								
								lastFire = System.currentTimeMillis();
							}
						}
					}
						
					
				
					if(SavingGrannyMain.win3)
					{
						if(GameScreenPantano.appleCount>0)
						{
							
							if (System.currentTimeMillis() - lastFire >= 500) 
							{
							
								Media.appleSound.play();
								if(facesRight)
								{
									MapPantano.addEntity(new Apple(position.cpy().add(25,60)));
								}
								
								//lanzar hacia la izquierda
								else if(!facesRight)
								{
									handLeft=true;
									MapPantano.addEntity(new Apple(position.cpy().add(-25,60)));
										
								}
								
								lastFire = System.currentTimeMillis();
							}
						}
					}
				
					if(SavingGrannyMain.win4)
					{
						if(GameScreenCave.appleCount>0)
						{
								
							if (System.currentTimeMillis() - lastFire >= 500) 
							{
								
								Media.appleSound.play();	
								if(facesRight)
								{
									MapCave.addEntity(new Apple(position.cpy().add(25,60)));
								}
									
								//lanzar hacia la izquierda
								else if(!facesRight)
								{
									handLeft=true;
									MapCave.addEntity(new Apple(position.cpy().add(-25,60)));
											
								}
									
								lastFire = System.currentTimeMillis();
							}
						}
					}
					
				
					if(SavingGrannyMain.win5)
					{
						if(GameScreenWolf.appleCount>0)
						{
								
							if (System.currentTimeMillis() - lastFire >= 500) 
							{
								
								Media.appleSound.play();	
								if(facesRight)
								{
									MapWolf.addEntity(new Apple(position.cpy().add(25,60)));
								}
									
								//lanzar hacia la izquierda
								else if(!facesRight)
								{
									handLeft=true;
									MapWolf.addEntity(new Apple(position.cpy().add(-25,60)));
											
								}
									
								lastFire = System.currentTimeMillis();
							}
						}
					}
					
				
				}	
				
				
				
						//cestazos 
						if(Gdx.input.isKeyPressed(Input.Keys.Z) || Gdx.input.isKeyPressed(Input.Keys.J ) )
						{
					
						state = State.fighting;
						stateTime += Gdx.graphics.getDeltaTime();
						if (System.currentTimeMillis() - lastFire >= 350) 
						{
						Media.basketSound.play();
						lastFire = System.currentTimeMillis();
						}
						
						if(facesRight == true)
						{
							Map.addEntity(new Basket(position.cpy().add(40,60)));
							Map2.addEntity(new Basket(position.cpy().add(40,60)));
							MapPantano.addEntity(new Basket(position.cpy().add(40,60)));
							MapCave.addEntity(new Basket(position.cpy().add(40,60)));
							MapWolf.addEntity(new Basket(position.cpy().add(40,60)));
						}
									
									
						//lanzar hacia la izquierda
						if(facesRight == false)
						{
										
							Map.addEntity(new Basket(position.cpy().add(-40,60)));
							Map2.addEntity(new Basket(position.cpy().add(-40,60)));
							MapPantano.addEntity(new Basket(position.cpy().add(-40,60)));
							MapCave.addEntity(new Basket(position.cpy().add(-40,60)));
							MapWolf.addEntity(new Basket(position.cpy().add(-40,60)));
						}
					
					
				}
						
						
						if(Map.flag858 || Map2.flag858 || MapPantano.flag858 || MapCave.flag858 || MapWolf.flag858) 
						{	
							velocityY=0;
							
							Player.state = State.low;
	
							flag += Gdx.graphics.getDeltaTime();

							if(flag > 0.3f )
							{	
								jump(Gdx.graphics.getDeltaTime());
							
								Map.flag858=false;
								Map2.flag858=false;
								MapPantano.flag858=false;
								MapCave.flag858=false;
								MapWolf.flag858=false;
								
								flag= 0;
							
								count += Gdx.graphics.getDeltaTime();
							}
							
						}			
	}
	
	
	public void moveLeft(float delta) 
	{
		
		int speed = 350;

		setPosition2(body.x - (speed * delta), body.y);

			if (inFloor)
			{
				state = State.walking;
			}
			facesRight = false;
			
		stateTime += Gdx.graphics.getDeltaTime();

	}
	
	public void moveRight(float delta) 
	{
		int speed = 350;
		
		setPosition2(body.x + (speed * delta), body.y);
		
			if (inFloor)
			{
				state = State.walking;
			}
			facesRight = true;

		stateTime += Gdx.graphics.getDeltaTime();
		
	}
	
	
	
	public void jump(float delta) 
	{
		
		int speed = 350;
		setPosition2(body.x + (speed * delta), body.y);
		

		if (velocityY == 0) 
		{
		   velocityY = 600;
		}

		state = State.jumping;
	
	}
	
	public boolean rightCollision(Rectangle rectangle) { 
		Rectangle auxRectangle = new Rectangle(body.x, body.y, body.width, body.height);
		auxRectangle.x += VELOCITY * Gdx.graphics.getDeltaTime();
		return auxRectangle.overlaps(rectangle);
	}
	
	public boolean leftCollision(Rectangle rectangle) {
		Rectangle auxRectangle = new Rectangle(body.x, body.y, body.width, body.height);
		auxRectangle.x -= VELOCITY * Gdx.graphics.getDeltaTime();
		return auxRectangle.overlaps(rectangle);
	}
	
	public boolean upCollision(Rectangle rectangle) { 
		Rectangle auxRectangle = new Rectangle(body.x, body.y, body.width, body.height);
		auxRectangle.y += 600 * Gdx.graphics.getDeltaTime();
		return auxRectangle.overlaps(rectangle);
	}
	
	public boolean downCollision(Rectangle rectangle) { 
		Rectangle auxRectangle = new Rectangle(body.x, body.y, body.width, body.height);
		auxRectangle.y -=600* Gdx.graphics.getDeltaTime();
		return auxRectangle.overlaps(rectangle);
	}
	
	public void dispose() { 
		frame1.dispose();
		frame8.dispose();
		frameN1.dispose();
		frameN2.dispose();
		frameN3.dispose();
		frameN4.dispose();
		frameN5.dispose();
		frameN6.dispose();
		frameN7.dispose();
		frameN8.dispose();
		frame100.dispose();
		Down.dispose();
		fight1.dispose();
		fight2.dispose();
		fight3.dispose();
		fight4.dispose();
	
	}


public void setPosition2(float x, float y) 
{ 

		bottom.x = x+20;
		bottom.y = y;

		left.x = x;
		left.y = y + 16;
		
		right.x = x + 30;
		right.y = y + 16;
		
		top.x = x;
		top.y = y + 104;
		
		position.x = x;
		position.y = y;
		body.setPosition(x, y);
	}
}
