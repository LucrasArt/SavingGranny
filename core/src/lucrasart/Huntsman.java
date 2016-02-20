package lucrasart;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Huntsman extends Entity 
{ 

public static boolean facesRight;

enum HuntsmanState { alive , fight }	
static HuntsmanState state;
	

public static final float WIDTH = 121;
public static final float HEIGHT = 180; 

private Texture frame1, frame2,frame3,frame4,frame5,frame6,frame7,frame8,frame9,frame10; 
private TextureRegion frame1L, frame2L,frame3L,frame4L,frame5L,frame6L,frame7L,frame8L,frame9L,frame10L;
private Animation animation,animationL,fightAnimation,fightAnimationL;
static float stateTime; 



public Huntsman() 
{
	super(WIDTH, HEIGHT, new Rectangle());
	
	state = HuntsmanState.alive;
	
	facesRight=true;
	
	stateTime = 0;
	setPosition(0, 0);
	
	frame1 = new Texture("Friends/miniCazador.png"); 
	frame1L = new TextureRegion(frame1);
	frame1L.flip(true, false);
	
	frame2 = new Texture("Friends/huntsmanFightNew1.png"); 
	frame2L = new TextureRegion(frame2);
	frame2L.flip(true, false);
	
	frame3 = new Texture("Friends/huntsmanFightNew2.png"); 
	frame3L = new TextureRegion(frame3);
	frame3L.flip(true, false);
	
	frame4 = new Texture("Friends/huntsmanFightNew3.png"); 
	frame4L = new TextureRegion(frame4);
	frame4L.flip(true, false);
	
	frame5 = new Texture("Friends/huntsmanFightNew4.png"); 
	frame5L = new TextureRegion(frame5);
	frame5L.flip(true, false);
	
	frame6 = new Texture("Friends/huntsmanFightNew5.png"); 
	frame6L = new TextureRegion(frame6);
	frame6L.flip(true, false);
	
	frame7 = new Texture("Friends/huntsmanFightNew6.png"); 
	frame7L = new TextureRegion(frame7);
	frame7L.flip(true, false);
	
	frame8 = new Texture("Friends/huntsmanFight7.png"); 
	frame8L = new TextureRegion(frame8);
	frame8L.flip(true, false);

	frame9 = new Texture("Friends/huntsmanFightNew8.png"); 
	frame9L = new TextureRegion(frame9);
	frame9L.flip(true, false);
	
	frame10 = new Texture("Friends/huntsmanFightNew9.png"); 
	frame10L = new TextureRegion(frame10);
	frame10L.flip(true, false);
	
	animation = new Animation(0, new TextureRegion(frame1));
	animationL = new Animation(0, new TextureRegion(frame1L));
	
	fightAnimation = new Animation(0.05f, new TextureRegion(frame2),new TextureRegion(frame3),new TextureRegion(frame4),new TextureRegion(frame5),new TextureRegion(frame6),new TextureRegion(frame7),new TextureRegion(frame8),new TextureRegion(frame9),new TextureRegion(frame10));
	fightAnimation.setPlayMode(PlayMode.LOOP);
	fightAnimationL = new Animation(0.05f, new TextureRegion(frame2L),new TextureRegion(frame3L),new TextureRegion(frame4L),new TextureRegion(frame5L),new TextureRegion(frame6L),new TextureRegion(frame7L),new TextureRegion(frame8L),new TextureRegion(frame9L),new TextureRegion(frame10L));
	fightAnimationL.setPlayMode(PlayMode.LOOP);
}

@Override
public void draw(SpriteBatch batch) 
{
	
	switch(state)
	{
		case alive:
			
			if(Huntsman.facesRight)
			{
				setRegion(animation.getKeyFrame(stateTime)); 
			}
			else
			{
				setRegion(animationL.getKeyFrame(stateTime)); 
			}
			break;
		
		case fight:
			
			if(Huntsman.facesRight)
			{
				setRegion(fightAnimation.getKeyFrame(stateTime)); 
			}
			else
			{
				setRegion(fightAnimationL.getKeyFrame(stateTime)); 
			}
			break;
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
	frame9.dispose();
	frame10.dispose();
}
}
