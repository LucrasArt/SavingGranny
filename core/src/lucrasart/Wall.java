package lucrasart;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Wall extends Entity 
{ 

public static final float WIDTH = 70; 
public static final float HEIGHT = 70; 

private Texture frame1; 

private Animation animation;
static float stateTime; 



public Wall() 
{
	super(WIDTH, HEIGHT, new Rectangle());
	
	stateTime = 0;
	
	frame1 = new Texture("Map/rock1.png"); 

	animation = new Animation(0, new TextureRegion(frame1));
;
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
	
}

}
