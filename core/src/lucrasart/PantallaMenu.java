package lucrasart;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

//import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
public class PantallaMenu extends AbstractScreen {

	SpriteBatch batch;
	Texture titulo;
	Texture play;
	Texture quit;
	Texture stuff;
	Texture stuffN;
	Texture back;
	Texture about;
	Texture controlsImage;
	
	private Stage stage;
	private Skin skin;
	private ImageButton buttonPlay, buttonExit, buttonStuff,buttonBack, buttonBack2, buttonAbout;
	
	private Dialog dialog;
	
	Table tableMenu, tableStuff, tableAbout;
	
	public PantallaMenu(SavingGrannyMain main) {
		super(main);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		titulo = new Texture(Gdx.files.internal("titulo1.png"));
		play = new Texture(Gdx.files.internal("start1.png"));
		quit = new Texture(Gdx.files.internal("exit1.png"));
		stuff = new Texture(Gdx.files.internal("stuff1.png"));
		stuffN = new Texture(Gdx.files.internal("stuff-inside.png"));
		back = new Texture (Gdx.files.internal("back.png"));
		about = new Texture(Gdx.files.internal("about.png"));
		controlsImage = new Texture (Gdx.files.internal("controls.png"));
		
		Image title = new Image(titulo); 
		title.setPosition(200, 360);
		
		Image mesagge = new Image(stuffN);
		
		Image control = new Image(controlsImage);
		//control.setPosition(200, 200);
		
		skin = new Skin (Gdx.files.internal("skin/uiskin.json"));
		
		TextureRegion playR = new TextureRegion(play);
		TextureRegionDrawable textDraw = new TextureRegionDrawable (playR);
		
		ImageButtonStyle ibs = new ImageButtonStyle(skin.get(ButtonStyle.class));
		
		ibs.imageUp = textDraw;
		
		buttonPlay = new ImageButton(ibs);
		

		//buttonPlay = new ImageButton(textDraw);
		
		TextureRegion exitR = new TextureRegion(quit);
		TextureRegionDrawable textExitDraw = new TextureRegionDrawable (exitR);
		
		ImageButtonStyle ibsE = new ImageButtonStyle(skin.get(ButtonStyle.class));
		
		ibsE.imageUp = textExitDraw;
		
		 buttonExit = new ImageButton(ibsE);
		
		
		TextureRegion backR = new TextureRegion(back);
		TextureRegionDrawable textBackDraw = new TextureRegionDrawable (backR);
		 
		ImageButtonStyle ibsB = new ImageButtonStyle(skin.get(ButtonStyle.class));
			
		ibsB.imageUp = textBackDraw;
		
		buttonBack = new ImageButton(ibsB);
		buttonBack2 = new ImageButton(ibsB);
		
		
		//boton stuff
		TextureRegion stuffB = new TextureRegion(stuff);
		TextureRegionDrawable textStuffDraw = new TextureRegionDrawable (stuffB);
		
		ImageButtonStyle ibsS = new ImageButtonStyle(skin.get(ButtonStyle.class));
		
		ibsS.imageUp = textStuffDraw;
		
		 buttonStuff = new ImageButton(ibsS);
	
		//fin boton stuff
		
		 
		 
			//boton about
			TextureRegion aboutB = new TextureRegion(about);
			TextureRegionDrawable textAboutDraw = new TextureRegionDrawable (aboutB);
			
			ImageButtonStyle ibsA = new ImageButtonStyle(skin.get(ButtonStyle.class));
			
			ibsA.imageUp = textAboutDraw;
			
			 buttonAbout = new ImageButton(ibsA);
		
			//fin boton about
		stage = new Stage();
		
		tableMenu = new Table();
		tableStuff = new Table();
		tableAbout = new Table();
		//table.setFillParent(true);
		
		stage.addActor(title);
		stage.addActor(tableMenu);
		stage.addActor(tableStuff);
		stage.addActor(tableAbout);
		//button.setPosition(50,50);
		
		Gdx.input.setInputProcessor(stage);
		
		//table.add(title).pad(10, 10, 10, 10).colspan(2);
		//table.row();
		tableMenu.add(buttonPlay).pad(10, 10, 10, 10).width(200).height(50);
		tableMenu.row();
		tableMenu.add(buttonStuff).pad(10, 10, 10, 10).width(200).height(50);
		tableMenu.row();
		tableMenu.add(buttonAbout).pad(10, 10, 10, 10).width(200).height(50);
		tableMenu.row();
		tableMenu.add(buttonExit).pad(10, 10, 10, 10).width(200).height(50);
		
		tableMenu.setPosition(500, 200);
		tableMenu.setVisible(true);
		
		tableStuff.add(control).pad(10, 10, 10, 10).colspan(2).width(400).height(200);
		tableStuff.row();
		tableStuff.add(buttonBack).pad(10, 10, 10, 10).width(200).height(50);
		tableStuff.setPosition(500, 200);
		tableStuff.setVisible(false);
		
		tableAbout.add(mesagge).pad(10, 10, 10, 10).colspan(2).width(600).height(200);
		tableAbout.row();
		tableAbout.add(buttonBack2).pad(10, 10, 10, 10).width(200).height(50);
		tableAbout.setPosition(500, 200);
		tableAbout.setVisible(false);
		
		
		
		 buttonPlay.addListener(new ClickListener()
		  {
		        @Override
		        public void clicked(InputEvent event, float x, float y)
		         {

		        	main.setScreen(new GameScreen(main));
		        	
		        }
		    });
		 
		 buttonExit.addListener(new ClickListener()
		  {
		        @Override
		       public void clicked(InputEvent event, float x, float y)
		       {
		        	//Dialog
					
					Label message = new Label("Dialog: Exit?", skin);
					TextButton tb1 = new TextButton("Yes", skin);
					tb1.addListener( new ClickListener()
					{             
						@Override
						public void clicked(InputEvent event, float x, float y) 
						{
							Gdx.app.exit();
						};
					});
					TextButton tb2 = new TextButton("No", skin);


					dialog = new Dialog("", skin);
					dialog.setKeepWithinStage(false);
					dialog.getContentTable().row().colspan(1).center();
					dialog.getContentTable().add(message);
					dialog.row().colspan(2);
					dialog.button(tb1);
					dialog.button(tb2);
					dialog.setModal(true);
					//dialog.setBackground(trd);
					dialog.pack();
					dialog.setPosition(75, 130 - dialog.getHeight());
					dialog.show(stage);
		       }
		    });
		  
		 
		 buttonStuff.addListener(new ClickListener()
		  {
		        @Override
		        public void clicked(InputEvent event, float x, float y)
		         {
		     
		        	//main.setScreen(new GameScreen(main));
		        	//batch.draw(stuffN, 0, 0);
		        	tableMenu.setVisible(false);
		        	tableStuff.setVisible(true);
		        	
		        }
		    });
		 
		 buttonAbout.addListener(new ClickListener()
		  {
		        @Override
		        public void clicked(InputEvent event, float x, float y)
		         {

		        	tableMenu.setVisible(false);
		        	tableAbout.setVisible(true);
		        	
		        }
		    });
		
		 
		 buttonBack.addListener(new ClickListener()
		  {
		        @Override
		        public void clicked(InputEvent event, float x, float y)
		         {

		        	tableMenu.setVisible(true);
		        	tableStuff.setVisible(false);
		        	tableAbout.setVisible(false);
		        	
		        }
		    });
		 
		 buttonBack2.addListener(new ClickListener()
		  {
		        @Override
		        public void clicked(InputEvent event, float x, float y)
		         {

		        	tableMenu.setVisible(true);
		        	tableStuff.setVisible(false);
		        	tableAbout.setVisible(false);
		        	
		        }
		    });
		
	
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.49f, 0.62f, 0.97f, 1);
		//Gdx.gl.glClearColor(0.174f, 0.223f, 0.218f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		
		
		batch.begin();
		

		//batch.draw(titulo,200,50,500,500);
		stage.draw();
		
		batch.end();
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		titulo.dispose();
		stage.dispose();
		skin.dispose();
		
	}
}

