package lucrasart;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Cover extends AbstractScreen {
	
	private Texture cover;
	
	public static boolean flagLanguageEsp, flagLanguageEng = false;
	
	private Texture flagEsp,flagEng;
	
	Stage stage;
	
	Skin skin;
	
	Table table;
	
	ImageButton buttonEsp,buttonEng;
	
	public static Sound clickSound;


	public Cover(SavingGrannyMain main) 
	{
		super(main);

		

	}
	
	
	@Override
	public void show()
	{
		clickSound = Gdx.audio.newSound(Gdx.files.internal("Audio/click2.wav"));
		
		cover = new Texture("Scenes/CoverN.png");
		Image coverOK = new Image(cover);
		
		flagEsp = new Texture("Menu/flagS.png");
		flagEng = new Texture("Menu/flagE.png");
		
		skin = new Skin (Gdx.files.internal("skin/uiskin.json"));
		
		stage = new Stage();
		
		table = new Table();
		
		//boton 1
		TextureRegion playR = new TextureRegion(flagEsp);
		TextureRegionDrawable textDraw = new TextureRegionDrawable (playR);
		
		ImageButtonStyle ibsS = new ImageButtonStyle(skin.get(ButtonStyle.class));
		
		ibsS.imageUp = textDraw;
		
		buttonEsp = new ImageButton(ibsS);
		//fin boton 1
		
		
		//boton 2
		TextureRegion playE = new TextureRegion(flagEng);
		TextureRegionDrawable textDrawE = new TextureRegionDrawable (playE);
				
		ImageButtonStyle ibsE = new ImageButtonStyle(skin.get(ButtonStyle.class));
				
		ibsE.imageUp = textDrawE;
				
		buttonEng = new ImageButton(ibsE);
		//fin boton 2
		
		stage.addActor(coverOK);
		stage.addActor(table);
		
		Gdx.input.setInputProcessor(stage);
		
		table.add(buttonEsp).pad(10, 10, 10, 10).width(50).height(50);
		//table.row();
		table.add(buttonEng).pad(10, 10, 10, 10).width(50).height(50);
		table.row();
		
		table.setPosition(500, 200);
		
		
		buttonEsp.addListener(new ClickListener()
		  {
		        @Override
		        public void clicked(InputEvent event, float x, float y)
		         {

		        	main.setScreen(new History(main));
		        	flagLanguageEsp = true;
		        	clickSound.play();
		        	
		        }
		    });
		
		
		buttonEng.addListener(new ClickListener()
		  {
		        @Override
		        public void clicked(InputEvent event, float x, float y)
		         {

		        	main.setScreen(new History(main));
		        	flagLanguageEng = true;
		        	clickSound.play();
		        	
		        }
		    });
		 
		
	}
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);    
		                                                   
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		
		batch.begin();
		
		stage.act();
		

		stage.draw();

		batch.end();
		
	
		}
	
	
	@Override
	public void dispose() { 
	
		cover.dispose();
		clickSound.dispose();
	}

}
