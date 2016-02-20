package lucrasart;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Clue extends Entity 
{
 
		public static final float WIDTH = 100; 
		public static final float HEIGHT = 100; 
		
		private Texture frame1, frame2,frame3,frame4; 
		private Animation animation1,animation2,animation3,animation4;
		private float stateTime; 
		
		enum ClueState {medallion,stick,boot,teeth};
		static ClueState state;

		public Clue() {
			super(WIDTH, HEIGHT, new Rectangle());
			
			stateTime = 0;
			setPosition(0, 0); 
			
			state = ClueState.medallion;
			
			frame1 = new Texture("Items/Mmedalla.png"); 
			frame2 = new Texture("Items/Mbaston.png"); 
			frame3 = new Texture("Items/Mbota.png"); 
			frame4 = new Texture("Items/Mdentadura.png");
			
			animation1 = new Animation(0, new TextureRegion(frame1));
			//animation1.setPlayMode(PlayMode.LOOP); 
			
			animation2 = new Animation(0,new TextureRegion(frame2));
			//animation2.setPlayMode(PlayMode.LOOP); 
			
			animation3 = new Animation(0,new TextureRegion(frame3));
			//animation3.setPlayMode(PlayMode.LOOP); 
			
			animation4 = new Animation(0,new TextureRegion(frame4));
			//animation4.setPlayMode(PlayMode.LOOP); 
		}
		
		@Override
		public void draw(SpriteBatch batch) 
		{
			
			switch(state)
			{
			case medallion:
			{
			setRegion(animation1.getKeyFrame(stateTime));
			break;
			}
			case stick:
			{
			setRegion(animation2.getKeyFrame(stateTime));
			break;
			}
			case boot:
			{
			setRegion(animation3.getKeyFrame(stateTime));
			break;
			}
			case teeth:
			{
			setRegion(animation4.getKeyFrame(stateTime));
			break;
			}
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
		}
	}


