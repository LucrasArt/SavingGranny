package lucrasart;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Player extends Entity{ 
	public static final float WIDTH = 60; 
	public static final float HEIGHT = 120; 
	public static final float VELOCITY = 350f; 
	public static final float JUMP = 500f; 
	public static final int MAX_JUMP_RANGUE = 30; 
	
	public static final float Damping = 0.87f;
	public static final float Gravity = -18;
	public static final float Max_velocity = 10;
	public static final float Max_jump_speed = 10f;
	public static final float Long_jump_press = 150l;
	
	private Texture frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8; 
	private TextureRegion frame1L, frame2L, frame3L, frame4L, frame5L, frame6L, frame7L, frame8L;

	private Texture down, fight1, fight2, fight3, fight4;
	private TextureRegion downL, fight1L, fight2L, fight3L, fight4L;
	
	private Animation stand,jumping,walk;
	private Animation standL,jumpingL,walkL;
	
	private Animation fight, fightL;
	private Animation abajo, abajoL;

	public static boolean facesRight;
	enum State {standing, walking, jumping, falling, down, fight};
	static State state;
	

	public static float stateTime; 
	private boolean jump, inFloor; 
	private int jumpRange; 
	
	//static Vector2 Velocity;
	//static Vector2 Acceleration;
	
	public Player() {
		super(WIDTH, HEIGHT, new Rectangle());
		
		stateTime = 0;
		inFloor = true; 
		jumpRange = 0;
		setPosition(0, 0);
		
		//Velocity = new Vector2();
		//Acceleration = new Vector2();
		
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
		//walk.setPlayMode(PlayMode.LOOP_PINGPONG);
		
		
		frame1 = new Texture("Player/Frame1.png");
		frame2 = new Texture("Player/Frame2.png");
		frame3 = new Texture("Player/Frame3.png");
		frame4 = new Texture("Player/Frame4.png");
		frame5 = new Texture("Player/Frame5.png");
		frame6 = new Texture("Player/Frame6.png");
		frame7 = new Texture("Player/Frame7.png");
		frame8 = new Texture("Player/CaperucitaJump.png");
		
		frame1L = new TextureRegion (frame1);
		frame1L.flip(true, false);
		frame2L = new TextureRegion (frame2);
		frame2L.flip(true, false);
		frame3L = new TextureRegion (frame3);
		frame3L.flip(true, false);
		frame4L = new TextureRegion (frame4);
		frame4L.flip(true, false);
		frame5L = new TextureRegion (frame5);
		frame5L.flip(true, false);
		frame6L = new TextureRegion (frame6);
		frame6L.flip(true, false);
		frame7L = new TextureRegion (frame7);
		frame7L.flip(true, false);
		frame8L = new TextureRegion (frame8);
		frame8L.flip(true, false);
		

		down = new Texture("Player/P-agachada.png");
		fight1 = new Texture("Player/Pcestazo1.png");
		fight2 = new Texture("Player/Pcestazo2.png");
		fight3 = new Texture("Player/Pcestazo3.png");
		fight4 = new Texture("Player/Pcestazo4.png");
		
		downL = new TextureRegion(down);
		downL.flip(true, false);
		fight1L = new TextureRegion(fight1);
		fight1L.flip(true, false);
		fight2L = new TextureRegion(fight2);
		fight2L.flip(true, false);
		fight3L = new TextureRegion(fight3);
		fight3L.flip(true, false);
		fight4L = new TextureRegion(fight4);
		fight4L.flip(true, false);
		
		
		stand = new Animation(0,new TextureRegion(frame1));
		standL = new Animation(0,new TextureRegion(frame1L));
		
		jumping = new Animation(0,new TextureRegion(frame8));
		jumpingL = new Animation(0,new TextureRegion(frame8L));
		
		//estaba a 0.1
		walk = new Animation(0.09f, new TextureRegion(frame2), new TextureRegion(frame3), new TextureRegion(frame4), new TextureRegion(frame5), new TextureRegion(frame6),  new TextureRegion(frame7));
		walk.setPlayMode(PlayMode.LOOP_PINGPONG); 
		
		walkL = new Animation(0.09f, new TextureRegion(frame2L), new TextureRegion(frame3L), new TextureRegion(frame4L), new TextureRegion(frame5L), new TextureRegion(frame6L),  new TextureRegion(frame7L));
		walkL.setPlayMode(PlayMode.LOOP_PINGPONG); 
		
		abajo = new Animation (0, new TextureRegion(down));
		abajoL = new Animation (0, new TextureRegion(downL));
		
		fight = new Animation (0.01f,new TextureRegion(fight1),new TextureRegion(fight2),new TextureRegion(fight3),new TextureRegion(fight4));
		fight.setPlayMode(PlayMode.LOOP);
		fightL = new Animation (0.01f,new TextureRegion(fight1L),new TextureRegion(fight2L),new TextureRegion(fight3L),new TextureRegion(fight4L));
		fightL.setPlayMode(PlayMode.LOOP_PINGPONG);
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
		case down:
			if(Player.facesRight)
			{
				
				setRegion(abajo.getKeyFrame(stateTime));
			}
			if(!Player.facesRight)
			{
				setRegion(abajoL.getKeyFrame(stateTime));
			}
			
			break;
			
		case fight:
			if(Player.facesRight)
			{
				
				setRegion(fight.getKeyFrame(stateTime));
			}
			if(!Player.facesRight)
			{
				setRegion(fightL.getKeyFrame(stateTime));
			}
			
			break;	
		
		}
		//super.draw2(batch);
		
		 
		super.draw(batch);
	}
	
	public void update(Array<Rectangle> platforms) { 
		boolean right, left, down, up;
		right = left = down = up = true; 
		
		for(Rectangle rectangle : platforms) {
			
		
			if(downCollision(rectangle)) { 
				inFloor = true;
				jumpRange = 0;
				down = false;
			}
			
			if(rightCollision(rectangle))
				right = false;
			
			if(leftCollision(rectangle)) 
				left = false;
			
			if(upCollision(rectangle)) 
				up = false;
		}
		
		if((Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) &right)
		{
			translate(VELOCITY * Gdx.graphics.getDeltaTime(), 0);
			//getVelocity().x = Max_velocity * Gdx.graphics.getDeltaTime();
			stateTime += Gdx.graphics.getDeltaTime();
			if (inFloor)
			{
				state = State.walking;
			}
			facesRight= true;
		}
		if((Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) & left)
		{
			translate(-VELOCITY * Gdx.graphics.getDeltaTime(), 0);
			//getVelocity().x = -Max_velocity * Gdx.graphics.getDeltaTime();
			stateTime += Gdx.graphics.getDeltaTime();
			if (inFloor)
			{
				state = State.walking;
			}
			facesRight=false;
		}
			if((Gdx.input.isKeyPressed(Keys.SPACE) || Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) && inFloor) 
		{ 
				jump = true;
				inFloor = false;
				state = State.jumping;
		}
		
		
		if(jump)
		{ 
			if(up) 
			{
				translate(0, JUMP * Gdx.graphics.getDeltaTime());
				++jumpRange;
				
			}
			else
			{
				jumpRange = MAX_JUMP_RANGUE;
			}
		}
		else if(down) 
		{
			translate(0, -VELOCITY * Gdx.graphics.getDeltaTime());
			state = State.falling;
			
			
		}
	
		if(jumpRange >= MAX_JUMP_RANGUE) 
		{ 
			jump = false;
			
		}
		
	
		if (state != State.walking)
		{
			if (inFloor)
			{
			state = State.standing;
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.F) )
		{
			
				state = State.fight;
				stateTime += Gdx.graphics.getDeltaTime();
			
			
		}
	}
	
	public boolean rightCollision(Rectangle rectangle) { 
		Rectangle auxRectangle = new Rectangle(body.x, body.y, body.width, body.height);
		//auxRectangle.x += Max_velocity * Gdx.graphics.getDeltaTime();
		auxRectangle.x += VELOCITY * Gdx.graphics.getDeltaTime();
		return auxRectangle.overlaps(rectangle);
	}
	
	public boolean leftCollision(Rectangle rectangle) {
		Rectangle auxRectangle = new Rectangle(body.x, body.y, body.width, body.height);
		//auxRectangle.x -= Max_velocity * Gdx.graphics.getDeltaTime();
		auxRectangle.x -= VELOCITY * Gdx.graphics.getDeltaTime();
		return auxRectangle.overlaps(rectangle);
	}
	
	public boolean upCollision(Rectangle rectangle) { 
		Rectangle auxRectangle = new Rectangle(body.x, body.y, body.width, body.height);
		//auxRectangle.y += Max_velocity * Gdx.graphics.getDeltaTime();
		auxRectangle.y += VELOCITY * Gdx.graphics.getDeltaTime();
		return auxRectangle.overlaps(rectangle);
	}
	
	public boolean downCollision(Rectangle rectangle) { 
		Rectangle auxRectangle = new Rectangle(body.x, body.y, body.width, body.height);
		//auxRectangle.y -= Max_velocity * Gdx.graphics.getDeltaTime();
		auxRectangle.y -=VELOCITY * Gdx.graphics.getDeltaTime();
		return auxRectangle.overlaps(rectangle);
	}
	
	public void dispose() { 
	//CaperuTexture.dispose();
		frame1.dispose();
		frame2.dispose();
		frame3.dispose();
		frame4.dispose();
		frame5.dispose();
		frame6.dispose();
		frame7.dispose();
		frame8.dispose();
		down.dispose();
		fight1.dispose();
		fight2.dispose();
		fight3.dispose();
		fight4.dispose();
	
	}


}
