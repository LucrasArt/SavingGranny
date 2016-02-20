package lucrasart;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Basket extends Entity { 
	
	//public static Texture APPLE = new Texture("manzana.png");
	
	public static final float WIDTH = 60; 
	public static final float HEIGHT = 60; 
	//public static final float direction = 5;
	
	
	private Texture frame1, frame2; 
	private Animation animation;
	private float stateTime; 

	public Basket(Vector2 pos) {
		super(WIDTH,HEIGHT,new Rectangle(),pos);
		//super(WIDTH,HEIGHT,new Rectangle(),new Vector2 (5,0),pos);
		
		stateTime = 0;
		//setPosition(0, 0); 
		
		frame1 = new Texture("Items/manzana.png"); 
		animation = new Animation(0.5f, new TextureRegion(frame1));
		//animation = new Animation(0.5f, new TextureRegion(frame1), new TextureRegion(frame2)); // Las intoducimos en la animacion con una velocidad indicada en el primer parametro
		animation.setPlayMode(PlayMode.LOOP); // Ponemos que se ejecute en bucle.
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		
		
		setRegion(animation.getKeyFrame(stateTime));
		super.draw(batch); 
	}
	
	public void update() { 
		stateTime += Gdx.graphics.getDeltaTime(); 
		
		
	}
	
	public void dispose() { 
		frame1.dispose();
		frame2.dispose();
	}
	
	/*public boolean checkEnd()
	{
		return position.x >= 512;
	}*/
	
	/*public boolean checkEnd2()
	{
		return getPosition().y >= -MyGdxGame.WIDTH;
	}*/
	
	
}

