package lucrasart;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Entity { 
	protected Vector2 position;
	protected TextureRegion region; 
	protected float width; 
	protected float height; 
	protected Rectangle body; 
	
	public Entity(float width, float height, Rectangle body) { 
		this.position = new Vector2();
		this.width = width;
		this.height = height;
		this.body = body;
		body.width = width;
		body.height = height;
	}
	
	public void draw(SpriteBatch batch) { 
		if (region == null)
			return;
		
		batch.draw(region, position.x, position.y, width, height);
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void setPosition(float x, float y) { 
		position.x = x;
		position.y = y;
		body.setPosition(x, y);
	}
	
	public Vector2 getCenterPosition() { 
		return new Vector2(position.x + width * 0.5f, position.y + height * 0.5f);
	}
	
	public void translate(float x, float y) { 
		position.x += x;
		position.y += y;
		body.x += x;
		body.y += y;
	}
	
	public Rectangle getBody() { 
		return body;
	}
	
	public void setRegion(TextureRegion region) { 
		this.region = region;
	}
	
	public boolean collide(Entity e) { 
		return body.overlaps(e.body);
	}
	
	public static boolean collide(Entity e1, Entity e2) { 
		return e1.body.overlaps(e2.body);
	}
}

