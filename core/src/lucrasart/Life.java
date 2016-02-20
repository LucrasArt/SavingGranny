package lucrasart;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Life extends Entity 
{ 
	public static final float WIDTH = 30; 
	public static final float HEIGHT = 30; 
	
	private Texture frame1, frame2,frame3,frame4,frame5,frame6; 
	private Animation animation;
	private float stateTime; 
	
	

	public Life() 
	{
		super(WIDTH, HEIGHT, new Rectangle());
		
		stateTime = 0;
		setPosition(0, 0); 
		
		frame1 = new Texture("Items/heart1.png"); 
		frame2 = new Texture("Items/heart2.png");
		frame3 = new Texture("Items/heart3.png"); 
		frame4 = new Texture("Items/heart4.png");
		frame5 = new Texture("Items/heart3.png"); 
		frame6 = new Texture("Items/heart2.png");
		animation = new Animation(0.05f, new TextureRegion(frame1),new TextureRegion(frame2),new TextureRegion(frame3),new TextureRegion(frame4),new TextureRegion(frame5),new TextureRegion(frame6));
		animation.setPlayMode(PlayMode.LOOP); 
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		
		
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
	}
}

