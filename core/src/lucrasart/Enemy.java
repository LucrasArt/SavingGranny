package lucrasart;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Enemy extends Entity 
{ 

public static final float WIDTH = 94; 
public static final float HEIGHT = 70;

private Texture frame1, frame2,frame3;
private Animation animation;
static float stateTime; 


public Enemy() {
	super(WIDTH, HEIGHT, new Rectangle());
	
	stateTime = 0;
	setPosition(0, 0);
	
	frame1 = new Texture("Enemies/Oveja1.png"); 
	frame2 = new Texture("Enemies/Oveja2.png"); 
	frame3 = new Texture("Enemies/Oveja3.png"); 
	
	
	animation = new Animation(5.5f, new TextureRegion(frame1), new TextureRegion(frame2), new TextureRegion(frame3), new TextureRegion(frame2));
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
}
}
