package lucrasart;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class EnemyPig extends Entity 
{ 

	
public static final float WIDTH = 70; 
public static final float HEIGHT = 97; 

private Texture frame1, frame2,frame3,frame4; 
private Animation animation;
static float stateTime; 


public EnemyPig() 
{
	super(WIDTH, HEIGHT, new Rectangle());
	
	stateTime = 0;

	frame1 = new Texture("Enemies/boar1.png"); 
	frame2 = new Texture("Enemies/boar2.png");
	frame3 = new Texture("Enemies/boar3.png");
	frame4 = new Texture("Enemies/boar4.png");
	
	animation = new Animation(15.5f, new TextureRegion(frame1),new TextureRegion(frame2), new TextureRegion(frame3),new TextureRegion(frame4));
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

public void dispose() {
	frame1.dispose();
	frame2.dispose();
	frame3.dispose();
	frame4.dispose();
}
}
