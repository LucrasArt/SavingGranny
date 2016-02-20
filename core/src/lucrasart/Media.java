package lucrasart;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;



public class Media {
	
	public static Sound dieSound; 
	public static Sound painSound; 
	
	public static Sound ovejaSound;
	public static Sound pigSound;
	public static Sound ratSound;
	public static Sound spiderSound;
	
	public static Sound wolfSound;
	public static Sound wolfPainSound;
	
	public static Sound coinSound; 
	public static Sound lifeSound;
	public static Sound checkpointSound;
	public static Sound appleSound;
	public static Sound basketSound;
	
	public static Sound finishSound;
	public static Sound endSound;
	
	//public Media()
	public static void create()
	{
	dieSound = Gdx.audio.newSound(Gdx.files.internal("Audio/injure.mp3"));
	painSound = Gdx.audio.newSound(Gdx.files.internal("Audio/auch.mp3"));
	
	finishSound = Gdx.audio.newSound(Gdx.files.internal("Audio/aplauso2.wav"));
	endSound = Gdx.audio.newSound(Gdx.files.internal("Audio/trumpets2.wav"));
	
	
	coinSound = Gdx.audio.newSound(Gdx.files.internal("Audio/coin.wav"));
	lifeSound = Gdx.audio.newSound(Gdx.files.internal("Audio/powerup-success.wav"));
	checkpointSound = Gdx.audio.newSound(Gdx.files.internal("Audio/Ship_Bell.mp3"));
	appleSound = Gdx.audio.newSound(Gdx.files.internal("Audio/Ball.mp3"));
	basketSound = Gdx.audio.newSound(Gdx.files.internal("Audio/cestazo.wav"));
	
	wolfSound = Gdx.audio.newSound(Gdx.files.internal("Audio/wolfStart.mp3"));
	wolfPainSound = Gdx.audio.newSound(Gdx.files.internal("Audio/CoyoteCall.wav"));
	
	ratSound = Gdx.audio.newSound(Gdx.files.internal("Audio/rat.mp3"));
	ovejaSound = Gdx.audio.newSound(Gdx.files.internal("Audio/baaaa.mp3"));
	spiderSound = Gdx.audio.newSound(Gdx.files.internal("Audio/spider.mp3"));
	pigSound = Gdx.audio.newSound(Gdx.files.internal("Audio/pigfarm2.wav"));
	
	}
	
	public static void dispose()
	{
		 dieSound.dispose();; 
		 painSound.dispose();; 
		
		 ovejaSound.dispose();;
		 pigSound.dispose();;
		 ratSound.dispose();;
		 spiderSound.dispose();;
		
		 wolfSound.dispose();;
		 wolfPainSound.dispose();;
		
		 coinSound.dispose();; 
		 lifeSound.dispose();;
		 checkpointSound.dispose();;
		 appleSound.dispose();;
		 basketSound.dispose();;
		
		 finishSound.dispose();;
		 endSound.dispose();
	}

}
