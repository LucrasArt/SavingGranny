package lucrasart;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;


import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class SelectLevel extends AbstractScreen {

	private Texture fondo;
	
	SpriteBatch batch;
	
	Texture titulo;
	Texture level1;
	Texture level2;
	Texture level3;
	Texture level4;
	Texture level5;
	Texture back;
	
	private Stage stage;
	private Skin skin;
	private ImageButton buttonLevel1, buttonLevel2, buttonLevel3,buttonLevel4, buttonLevel5, buttonBack;
	
	Table tableMenu;
	
	public static Sound clickSound;
	
	public SelectLevel(SavingGrannyMain main)
	{
		super(main);
		
	}

	
	@Override
	public void show() 
	{
		
		clickSound = Gdx.audio.newSound(Gdx.files.internal("Audio/click2.wav"));
		
		fondo = new Texture("Menu/fondo.png");
		Image background = new Image(fondo);
		
		batch = new SpriteBatch();

		titulo = new Texture(Gdx.files.internal("Menu/SV.png"));
	
		level1 = new Texture(Gdx.files.internal("Map/fase1Map.png"));
			
		level2 = new Texture(Gdx.files.internal("Map/fase2Map.png"));
			
		level3 = new Texture(Gdx.files.internal("Map/fase3Map.jpg"));
			
		level4 = new Texture(Gdx.files.internal("Map/fase4Map.png"));
			
		back = new Texture (Gdx.files.internal("Menu/volverB.png"));
			
		level5 = new Texture(Gdx.files.internal("Map/fase5Map.jpg"));
	
		
		Image title = new Image(titulo); 
		title.setPosition(200, 360);
		
		skin = new Skin (Gdx.files.internal("skin/uiskin.json"));
		
		
		TextureRegion playR = new TextureRegion(level1);
		TextureRegionDrawable textDraw = new TextureRegionDrawable (playR);
		
		ImageButtonStyle ibs = new ImageButtonStyle(skin.get(ButtonStyle.class));
		
		ibs.imageUp = textDraw;
		
		buttonLevel1 = new ImageButton(ibs);
		
		TextureRegion exitR = new TextureRegion(level2);
		TextureRegionDrawable textExitDraw = new TextureRegionDrawable (exitR);
		
		ImageButtonStyle ibsE = new ImageButtonStyle(skin.get(ButtonStyle.class));
		
		ibsE.imageUp = textExitDraw;
		
		
		 buttonLevel2 = new ImageButton(ibsE);
		
		TextureRegion backR = new TextureRegion(back);
		TextureRegionDrawable textBackDraw = new TextureRegionDrawable (backR);
		 
		ImageButtonStyle ibsB = new ImageButtonStyle(skin.get(ButtonStyle.class));
			
		ibsB.imageUp = textBackDraw;
		
		buttonBack = new ImageButton(ibsB);
		
		TextureRegion back3 = new TextureRegion(level3);
		TextureRegionDrawable textBackDraw3 = new TextureRegionDrawable (back3);
		 
		ImageButtonStyle ibsB3 = new ImageButtonStyle(skin.get(ButtonStyle.class));
			
		ibsB3.imageUp = textBackDraw3;
		buttonLevel3 = new ImageButton(ibsB3);
		

		TextureRegion stuffB = new TextureRegion(level4);
		TextureRegionDrawable textStuffDraw = new TextureRegionDrawable (stuffB);
		
		ImageButtonStyle ibsS = new ImageButtonStyle(skin.get(ButtonStyle.class));
		
		ibsS.imageUp = textStuffDraw;
		
		 buttonLevel4 = new ImageButton(ibsS);


		TextureRegion aboutB = new TextureRegion(level5);
		TextureRegionDrawable textAboutDraw = new TextureRegionDrawable (aboutB);
			
		ImageButtonStyle ibsA = new ImageButtonStyle(skin.get(ButtonStyle.class));
			
		ibsA.imageUp = textAboutDraw;
			
		buttonLevel5 = new ImageButton(ibsA);
		
		stage = new Stage();
		
		tableMenu = new Table();


		stage.addActor(background);
		stage.addActor(title);
		stage.addActor(tableMenu);

		Gdx.input.setInputProcessor(stage);

		tableMenu.add(buttonLevel1).pad(10, 10, 10, 10).width(300).height(205);
		if(main.flagLevel1)
    	{
			tableMenu.add(buttonLevel2).pad(10, 10, 10, 10).width(300).height(205);
    	}
		if(main.flagLevel2)
    	{
			tableMenu.add(buttonLevel3).pad(10, 10, 10, 10).width(300).height(205);
    	}
		tableMenu.row();
		if(main.flagLevel3)
    	{
			tableMenu.add(buttonLevel4).pad(120, 10, 10, 10).width(300).height(205);
    	}
			tableMenu.add().pad(120, 10, 10, 10).width(300).height(205);
		if(main.flagLevel4)
    	{
			tableMenu.add(buttonLevel5).pad(120, 10, 10, 10).width(300).height(205);
    	}
		tableMenu.row();
		tableMenu.add(buttonBack).pad(10, 10, 10, 10).width(300).height(50).colspan(3);
		
		tableMenu.setPosition(500, 380);
		tableMenu.setVisible(true);
		
    
		
		
		 buttonLevel1.addListener(new ClickListener()
		  {
		        @Override
		        public void clicked(InputEvent event, float x, float y)
		         {	
		        	clickSound.play();
		        	main.setScreen(new GameScreen1(main));
		        	
		        	
		        }
		    });
		 
		
		  
		 
		 buttonLevel2.addListener(new ClickListener()
		  {
		        @Override
		        public void clicked(InputEvent event, float x, float y)
		         {
		        	
		        		clickSound.play();
		        		main.setScreen(new GameScreen2(main));
		        		
		        	
		        		
		        	
		        	
		        }
		    });
		 
		 buttonLevel3.addListener(new ClickListener()
		  {
		        @Override
		        public void clicked(InputEvent event, float x, float y)
		         {
		        	
		        	
		        	clickSound.play();
		        	main.setScreen(new GameScreenPantano(main));
		        	
		        	
				     
		        }
		    });
		
		 
		 buttonLevel4.addListener(new ClickListener()
		  {
		        @Override
		        public void clicked(InputEvent event, float x, float y)
		         {

		        		clickSound.play();
		        		main.setScreen(new GameScreenCave(main));
		        	
		        		
		        }
		    });
		 
		 buttonLevel5.addListener(new ClickListener()
		  {
		        @Override
		        public void clicked(InputEvent event, float x, float y)
		         {

		        
		        		clickSound.play();
		        		main.setScreen(new GameScreenWolf(main));
		        	
		        		
		        }
		    });
		 
		 
		 buttonBack.addListener(new ClickListener()
		  {
		        @Override
		        public void clicked(InputEvent event, float x, float y)
		         {
		        	if(Cover.flagLanguageEsp == true)
					{
		        		clickSound.play();
						main.setScreen(new PantallaMenuEsp(main));
						
					}
					
					if(Cover.flagLanguageEng == true)
					{
						clickSound.play();
						main.setScreen(new PantallaMenu(main));
						
					}
		        	
		        }
		    });
		 
		
		
	
	}

	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0f, 0f, 1);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
	
		batch.begin();
	
		stage.draw();
		
		batch.end();
	}
	
	@Override
	public void dispose() 
	{
		titulo.dispose();
		stage.dispose();
		skin.dispose();
		clickSound.dispose();
		
	}
}

