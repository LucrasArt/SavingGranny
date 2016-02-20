package lucrasart;

import lucrasart.GameScreen1.GameState;
import lucrasart.GameScreen2.GameState1;
import lucrasart.GameScreenPantano.GameStatePantano;
import lucrasart.GameScreenCave.GameStateCave;
import lucrasart.GameScreenWolf.GameStateWolf;

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
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;


public class PantallaPause extends AbstractScreen {

	private Texture fondo;
	
	SpriteBatch batch;
	Texture titulo;
	Texture play;
	Texture quit;
	
	
	private Stage stage;
	private Skin skin;
	private ImageButton buttonPlay, buttonExit;
	
	private Dialog dialog;
	
	Table tableMenu;
	
	static boolean flagPause = false;
	
	public static Sound clickSound;
	
	public PantallaPause(SavingGrannyMain main) 
	{
		super(main);

	}

	
	@Override
	public void show() {
		
		clickSound = Gdx.audio.newSound(Gdx.files.internal("Audio/click2.wav"));
		
		fondo = new Texture("Menu/fondo.png");
		Image background = new Image(fondo);
		
		batch = new SpriteBatch();
		titulo = new Texture(Gdx.files.internal("Menu/SV.png"));
		play = new Texture(Gdx.files.internal("Menu/resumeButton.png"));
		quit = new Texture(Gdx.files.internal("Menu/quitButton.png"));
		
		
		Image title = new Image(titulo); 
		title.setPosition(200, 360);
		
		
		
		skin = new Skin (Gdx.files.internal("skin/uiskin.json"));
		
		TextureRegion playR = new TextureRegion(play);
		TextureRegionDrawable textDraw = new TextureRegionDrawable (playR);
		
		ImageButtonStyle ibs = new ImageButtonStyle(skin.get(ButtonStyle.class));
		
		ibs.imageUp = textDraw;
		
		buttonPlay = new ImageButton(ibs);

		
		TextureRegion exitR = new TextureRegion(quit);
		TextureRegionDrawable textExitDraw = new TextureRegionDrawable (exitR);
		
		ImageButtonStyle ibsE = new ImageButtonStyle(skin.get(ButtonStyle.class));
		
		ibsE.imageUp = textExitDraw;
		
		 buttonExit = new ImageButton(ibsE);
		
		
		
		stage = new Stage();
		
		tableMenu = new Table();
		
		
		stage.addActor(background);
		stage.addActor(title);
		stage.addActor(tableMenu);
		
		
		Gdx.input.setInputProcessor(stage);
		

		tableMenu.add(buttonPlay).pad(10, 10, 10, 10).width(200).height(50);
		tableMenu.row();
		tableMenu.add(buttonExit).pad(10, 10, 10, 10).width(200).height(50);
		
		tableMenu.setPosition(500, 200);
		tableMenu.setVisible(true);
		
		
		
		
		 buttonPlay.addListener(new ClickListener()
		  {
		        @Override
		        public void clicked(InputEvent event, float x, float y)
		         {
		        	if(GameScreen1.pause)
		        	{
		        		clickSound.play();
		        		main.setScreen(new GameScreen1(main));
		        		GameScreen1.gameState = GameState.Running;
		        		GameScreen1.GameMusic1.play();
		        		flagPause=false;
		        		GameScreen1.pause=false;
		        	}
		        	if(GameScreen2.pause)
		        	{
		        		clickSound.play();
		        		main.setScreen(new GameScreen2(main));
			        	GameScreen2.gameState = GameState1.Running;
			        	GameScreen2.GameMusic2.play();
			        	flagPause=false;
			        	GameScreen2.pause=false;
		        	}
		        	
		        	if(GameScreenPantano.pause)
		        	{
		        		clickSound.play();
		        		main.setScreen(new GameScreenPantano(main));
			        	GameScreenPantano.gameState = GameStatePantano.Running;
			        	GameScreenPantano.GameMusic2.play();
			        	flagPause=false;
			        	GameScreenPantano.pause=false;
		        	}
		        	
		        	if(GameScreenCave.pause)
		        	{
		        		clickSound.play();
		        		main.setScreen(new GameScreenCave(main));
			        	GameScreenCave.gameState = GameStateCave.Running;
			        	GameScreenCave.GameMusic1.play();
			        	flagPause=false;
			        	GameScreenCave.pause=false;
		        	}
		        	if(GameScreenWolf.pause)
		        	{
		        		clickSound.play();
		        		main.setScreen(new GameScreenWolf(main));
			        	GameScreenWolf.gameState = GameStateWolf.Running;
			        	GameScreenWolf.GameMusic2.play();
			        	flagPause=false;
			        	GameScreenWolf.pause=false;
		        	}
		        	
		        }
		    });
		 
		 buttonExit.addListener(new ClickListener()
		  {
		        @Override
		       public void clicked(InputEvent event, float x, float y)
		       {
		        	//Dialog
		        	clickSound.play();
					Label message = new Label("Dialog: Exit?", skin);
					TextButton tb1 = new TextButton("Yes", skin);
					tb1.addListener( new ClickListener()
					{             
						@Override
						public void clicked(InputEvent event, float x, float y) 
						{
							//Gdx.app.exit();
							if(Cover.flagLanguageEsp)
							{
								main.setScreen(new PantallaMenuEsp(main));
							}
							else if(Cover.flagLanguageEng)
							{
								main.setScreen(new PantallaMenu(main));
							}
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
		  
		 
		 
	
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
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

