package lucrasart;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class EnemyRat extends Entity 
{ 

public static final float WIDTH = 70; 
public static final float HEIGHT = 24;

private Texture frame1, frame2,frame3,frame4,frame5,frame6,frame7,frame8; 
private TextureRegion frame1L, frame2L,frame3L,frame4L,frame5L,frame6L,frame7L,frame8L;
private Animation animation,animationL;
static float stateTime; 

static boolean facesRight;

public EnemyRat() 
{
	super(WIDTH, HEIGHT, new Rectangle());

	stateTime = 0;
	setPosition(0, 0);
	
	facesRight=false;
	
	frame1 = new Texture("Enemies/rat1.png"); 
	frame2 = new Texture("Enemies/rat2.png");
	frame3 = new Texture("Enemies/rat3.png");
	frame4 = new Texture("Enemies/rat4.png");
	frame5 = new Texture("Enemies/rat5.png");
	frame6 = new Texture("Enemies/rat6.png");
	frame7 = new Texture("Enemies/rat7.png");
	frame8 = new Texture("Enemies/rat8.png");
	
	frame1L = new TextureRegion(frame1);
	frame1L.flip(true, false);
	frame2L = new TextureRegion(frame2);
	frame2L.flip(true, false);
	frame3L = new TextureRegion(frame3);
	frame3L.flip(true, false);
	frame4L = new TextureRegion(frame4);
	frame4L.flip(true, false);
	frame5L = new TextureRegion(frame5);
	frame5L.flip(true, false);
	frame6L = new TextureRegion(frame6);
	frame6L.flip(true, false);
	frame7L = new TextureRegion(frame7);
	frame7L.flip(true, false);
	frame8L = new TextureRegion(frame8);
	frame8L.flip(true, false);
	
	//Estaba la velocidad a 0.1
	animation = new Animation(0.5f, new TextureRegion(frame1),new TextureRegion(frame2),new TextureRegion(frame3),new TextureRegion(frame4),new TextureRegion(frame5),new TextureRegion(frame6),new TextureRegion(frame7),new TextureRegion(frame8));
	animation.setPlayMode(PlayMode.LOOP);
	animationL = new Animation(0.5f, new TextureRegion(frame1L),new TextureRegion(frame2L),new TextureRegion(frame3L),new TextureRegion(frame4L),new TextureRegion(frame5L),new TextureRegion(frame6L),new TextureRegion(frame7L),new TextureRegion(frame8L));
	animationL.setPlayMode(PlayMode.LOOP);
}

@Override
public void draw(SpriteBatch batch) 
{
	
	if(!facesRight)
	{
		setRegion(animation.getKeyFrame(stateTime)); 
	}
		
	if(facesRight)
	{
		setRegion(animationL.getKeyFrame(stateTime)); 
	}
		

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
