package lucrasart;

//import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class EnemySpider extends Entity 
{ 

enum StateE { alive , dead }	
static StateE stateE;
	
public static final float WIDTH = 133; 
public static final float HEIGHT = 100; 

private Texture frame1; 
private Animation animation;
static float stateTime; 

 static float countF=0, count=0;
 
 static boolean flagWalk= false;

public EnemySpider() 
{
	super(WIDTH, HEIGHT, new Rectangle());
	
	stateE = StateE.alive;
	
	stateTime = 0;

	frame1 = new Texture("Enemies/SpiderLeg.png"); 
	
	animation = new Animation(0, new TextureRegion(frame1));
	//animation.setPlayMode(PlayMode.LOOP);

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
