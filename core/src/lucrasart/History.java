package lucrasart;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class History extends AbstractScreen {
	
	private Texture historia,history;
	//private float time = 0;
	


	public History(SavingGrannyMain main) {
		super(main);
		// TODO Auto-generated constructor stub
		
		
		

	}
	
	
	@Override
	public void show()
	{
		//cover = new Texture("cover4.png");
		historia = new Texture("Menu/historia-fondo.png");
		history = new Texture("Menu/history2-fondo.png");

	}
	
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);    
		                                                   
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		
		batch.begin();
		
		
		if(Cover.flagLanguageEsp)
		{
			batch.draw(historia,0,0,history.getWidth(), history.getHeight()); // Dibujamos la textura
		
		
			if(Gdx.input.isKeyPressed(Keys.ANY_KEY))
			{
			
			
				main.setScreen(new PantallaMenuEsp(main));
			}
		}
		
		if(Cover.flagLanguageEng)
		{
			batch.draw(history,0,0,history.getWidth(), history.getHeight());
			
			if(Gdx.input.isKeyPressed(Keys.ANY_KEY))
			{
				main.setScreen(new PantallaMenu(main));
			
			}
		
		//}
		}	
		batch.end();
		
		
			
			
		
		
			
		}
	
	
	@Override
	public void dispose() { 
	
		history.dispose();
	}

}
