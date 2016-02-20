package lucrasart;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Wolf extends Entity 
{ 
	
public static final float WIDTH = 128; 
public static final float HEIGHT = 200; 

private Texture frame1,frame2,frame3,frame4,frame5,frame6,frame7,frame8;
private TextureRegion frame1L, frame2L,frame3L,frame4L,frame5L,frame6L,frame7L,frame8L;
private Animation animation, animationL;

private Texture frame11,frame12,frame13,frame14,frame15,frame16;
private TextureRegion frame11L,frame12L,frame13L,frame14L,frame15L,frame16L;

private Animation stunAnimation,stunAnimationL;

private Animation blinkAnimation,blinkAnimationL;

private float stateTime; 

public static boolean facesRight;

enum WolfState {alive, stunned, dead};

static WolfState state;



public Wolf() 
{
	super(WIDTH, HEIGHT, new Rectangle());
	
	stateTime = 0;
	
	facesRight=false;
	state = WolfState.alive;
	
	
	frame1 = new Texture("Enemies/WolfAnimation1.png"); 
	frame2 = new Texture("Enemies/wolfAnimation2.png");
	frame3 = new Texture("Enemies/wolfAnimation3.png");
	frame4 = new Texture("Enemies/wolfAnimation4.png");
	frame5 = new Texture("Enemies/wolfAnimation5.png");
	frame6 = new Texture("Enemies/wolfAnimationPunch1.png");
	frame7 = new Texture("Enemies/wolfAnimationPunch2.png");
	frame8 = new Texture("Enemies/wolfAnimationPunch3.png");
	
	frame11 = new Texture("Enemies/stun1.png"); 
	frame12 = new Texture("Enemies/stun2.png");
	frame13 = new Texture("Enemies/stun3.png");
	frame14 = new Texture("Enemies/stun4.png");
	frame15 = new Texture("Enemies/stun5.png");
	frame16 = new Texture("Enemies/stun6.png");

	
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
	
	frame11L = new TextureRegion(frame11);
	frame11L.flip(true, false);
	frame12L = new TextureRegion(frame12);
	frame12L.flip(true, false);
	frame13L = new TextureRegion(frame13);
	frame13L.flip(true, false);
	frame14L = new TextureRegion(frame14);
	frame14L.flip(true, false);
	frame15L = new TextureRegion(frame15);
	frame15L.flip(true, false);
	frame16L = new TextureRegion(frame16);
	frame16L.flip(true, false);
	
	animation = new Animation(0.1f, new TextureRegion(frame1),new TextureRegion(frame2),new TextureRegion(frame3),new TextureRegion(frame4),new TextureRegion(frame5), new TextureRegion(frame1),new TextureRegion(frame2),new TextureRegion(frame3),new TextureRegion(frame4),new TextureRegion(frame5),new TextureRegion(frame6),new TextureRegion(frame7),new TextureRegion(frame8));
	animation.setPlayMode(PlayMode.LOOP);
	animationL = new Animation(0.1f, new TextureRegion(frame1L),new TextureRegion(frame2L),new TextureRegion(frame3L),new TextureRegion(frame4L),new TextureRegion(frame5L), new TextureRegion(frame1L),new TextureRegion(frame2L),new TextureRegion(frame3L),new TextureRegion(frame4L),new TextureRegion(frame5L),new TextureRegion(frame6L),new TextureRegion(frame7L),new TextureRegion(frame8L));
	animationL.setPlayMode(PlayMode.LOOP);
	
	stunAnimation = new Animation(0.1f, new TextureRegion(frame11),new TextureRegion(frame12),new TextureRegion(frame13),new TextureRegion(frame14),new TextureRegion(frame15), new TextureRegion(frame16));
	stunAnimation.setPlayMode(PlayMode.LOOP);
	stunAnimationL = new Animation(0.1f, new TextureRegion(frame11L),new TextureRegion(frame12L),new TextureRegion(frame13L),new TextureRegion(frame14L),new TextureRegion(frame15L), new TextureRegion(frame16L));
	stunAnimationL.setPlayMode(PlayMode.LOOP);
	
	blinkAnimation = new Animation(0.01f, new TextureRegion(frame11));
	blinkAnimation.setPlayMode(PlayMode.LOOP);
	blinkAnimationL = new Animation(0.01f,new TextureRegion(frame11L));
	blinkAnimationL.setPlayMode(PlayMode.LOOP);
}

@Override
public void draw(SpriteBatch batch) 
{

	switch(state)
	{
	case alive:
		
		if(Wolf.facesRight)
		{
			
			setRegion(animationL.getKeyFrame(stateTime));
		}
		if(!Wolf.facesRight)
		{
			setRegion(animation.getKeyFrame(stateTime));
		}
		
		
		break;
		
	case stunned:
		
		if(Wolf.facesRight)
		{
			
			setRegion(stunAnimationL.getKeyFrame(stateTime));
		}
		if(!Wolf.facesRight)
		{
			setRegion(stunAnimation.getKeyFrame(stateTime));
		}
		
		break;
		
	case dead:

		if(Wolf.facesRight)
		{
		
			setRegion(blinkAnimation.getKeyFrame(stateTime));
		
		}
		if(!Wolf.facesRight)
		{

			setRegion(blinkAnimationL.getKeyFrame(stateTime));
		
		}
		
		break;
		
		
	}
	
	super.draw(batch);
}

public void update() {
	stateTime += Gdx.graphics.getDeltaTime(); 
}

public void dispose() {
	frame1.dispose();
	frame2.dispose();
	frame3.dispose();
	frame4.dispose();
	frame5.dispose();
	frame6.dispose();
	frame7.dispose();
	frame8.dispose();
	frame11.dispose();
	frame12.dispose();
	frame13.dispose();
	frame14.dispose();
	frame15.dispose();
	frame16.dispose();
	
}

}
