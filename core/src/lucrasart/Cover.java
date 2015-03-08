package lucrasart;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class Cover extends AbstractScreen {
	
	private Texture cover;
	private float time = 0;
	


	public Cover(SavingGrannyMain main) {
		super(main);
		// TODO Auto-generated constructor stub
		
		
		

	}
	
	
	@Override
	public void show()
	{
		cover = new Texture("cover4.png");
	}
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);    
		                                                   
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		
		batch.begin();
		
		batch.draw(cover, 0,0,cover.getWidth(), cover.getHeight()); // Dibujamos la textura
		
		time += Gdx.graphics.getDeltaTime();
		if(time > 2)
		{
		//if(Gdx.input.isKeyPressed(Keys.ANY_KEY))
		//{
			main.setScreen(new PantallaMenu(main));
		
		//}
		}	
		batch.end();
		
		
			
			
		
		
			
		}
	
	
	@Override
	public void dispose() { 
	
		cover.dispose();
	}

}
