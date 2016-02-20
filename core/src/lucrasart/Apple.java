package lucrasart;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Apple extends Entity
{ 

	public static final float WIDTH = 30; 
	public static final float HEIGHT = 30; 
	
	
	private Texture frame1; 
	private Animation animation;
	private float stateTime; 

	public Apple(Vector2 pos) 
	{
		//antes estaba en 5 la velocidad de la manzana
		super(WIDTH,HEIGHT,new Rectangle(),new Vector2 (7,0),pos);
		
		stateTime = 0; 
		
		frame1 = new Texture("Items/manzana.png"); 
		animation = new Animation(0.5f, new TextureRegion(frame1));
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
		
		position.add(direction);
	}
	
	public void dispose() { 
		frame1.dispose();
	}
	
}

