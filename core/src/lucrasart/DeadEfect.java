package lucrasart;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Rectangle;

public class DeadEfect extends Entity
{
	
	public static final float WIDTH = 70; 
	public static final float HEIGHT = 70; 

	private Texture frame1,frame2,frame3,frame4,frame5,frame6,frame7,frame8;
	private Animation animation;
	private float stateTime; 

	public DeadEfect() 
	{
		super(WIDTH, HEIGHT, new Rectangle());
		
		stateTime = 0;
	
		frame1 = new Texture("Enemies/efectoMuerte1.png"); 
		frame2 = new Texture("Enemies/efectoMuerte2.png");
		frame3 = new Texture("Enemies/efectoMuerte3.png");
		frame4 = new Texture("Enemies/efectoMuerte4.png");
		frame5 = new Texture("Enemies/efectoMuerte5.png");
		frame6 = new Texture("Enemies/efectoMuerte6.png");
		frame7 = new Texture("Enemies/efectoMuerte7.png");
		frame8 = new Texture("Enemies/efectoMuerte8.png");
		
		animation = new Animation(0.1f, new TextureRegion(frame1),new TextureRegion(frame2),new TextureRegion(frame3),new TextureRegion(frame4),new TextureRegion(frame5),new TextureRegion(frame6),new TextureRegion(frame7),new TextureRegion(frame8));
		animation.setPlayMode(PlayMode.LOOP);
		
	}

	@Override
	public void draw(SpriteBatch batch) 
	{

		setRegion(animation.getKeyFrame(stateTime));
			
		super.draw(batch);
	}

	public void update() 
	{
		stateTime += Gdx.graphics.getDeltaTime(); 
		
	}

	public void dispose() 
	{
		frame1.dispose();
		frame2.dispose();
		frame3.dispose();
		frame4.dispose();
		frame5.dispose();
		frame6.dispose();
		frame7.dispose();
		frame8.dispose();
	}
	
	
	}

