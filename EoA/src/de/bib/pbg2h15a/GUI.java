package de.bib.pbg2h15a;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GUI{
	/**
	 * @author pbd2h15aho
	 */	
	
	private static final float SCALE_X=Gdx.graphics.getWidth()/100;
	private static final float SCALE_Y=Gdx.graphics.getHeight()/100;
	
	private static final String[] PFADE={"img/Buttons/GUI/Timer_BGLabel.png"				//0
									   	,"img/Buttons/GUI/SpielerDead_BGLabel.png"
									   	,"img/Buttons/GUI/SpielerDead_BGLabel2.png"
									   	,"img/Buttons/GUI/Spieler1_BGLabel.png"
									   	,"img/Buttons/GUI/Spieler2_BGLabel.png"
									   	,"img/Buttons/GUI/Spieler3_BGLabel.png"				//5
									   	,"img/Buttons/GUI/Spieler4_BGLabel.png"
									   	,"img/Buttons/GUI/ZurueckIconButton.png"
									   	,"img/Buttons/GUI/PauseIconButton.png"
									   	,"img/Buttons/GUI/ZurueckIconButtonPressed.png"
									   	,"img/Buttons/GUI/PauseIconButtonPressed.png"		//10
									   	,"img/Buttons/GUI/BombeButton.png"
									   	,"img/Buttons/GUI/UpButton.png"
									   	,"img/Buttons/GUI/DownButton.png"
									   	,"img/Buttons/GUI/RightButton.png"
									   	,"img/Buttons/GUI/LeftButton.png"					//15
									   	,"img/Buttons/GUI/BombeButtonPressed.png"
									   	,"img/Buttons/GUI/UpButtonPressed.png"
									   	,"img/Buttons/GUI/DownButtonPressed.png"
									   	,"img/Buttons/GUI/RightButtonPressed.png"
									   	,"img/Buttons/GUI/LeftButtonPressed.png"			//20
										};
	
	//Label
	private Label lbTimer;
	private Label lbP1;
	private Label lbP2;
	private Label lbP3;
	private Label lbP4;
	
	//Images
	private Image imTimer;
	private Image[] imgsPlayers;
	
	//Textures
	private Texture txDP;
	private Texture txDP2;
	
	//TextureRegionDrawables
	private TextureRegionDrawable tdDP;
	private TextureRegionDrawable tdDP2;
	private TextureRegionDrawable tdBtnBack;
	private TextureRegionDrawable tdBtnPause;
	private TextureRegionDrawable tdBtnBomb;
	private TextureRegionDrawable tdBtnUp;
	private TextureRegionDrawable tdBtnDown;
	private TextureRegionDrawable tdBtnRight;
	private TextureRegionDrawable tdBtnLeft;
	
	private TextureRegionDrawable tdBtnBackPressed;
	private TextureRegionDrawable tdBtnPausePressed;
	private TextureRegionDrawable tdBtnBombPressed;
	private TextureRegionDrawable tdBtnUpPressed;
	private TextureRegionDrawable tdBtnDownPressed;
	private TextureRegionDrawable tdBtnRightPressed;
	private TextureRegionDrawable tdBtnLeftPressed;
	
	
	//ImageButtons
	private ImageButton iBtnBack;
	private ImageButton iBtnPause;
	private ImageButton iBtnBomb;
	private ImageButton iBtnUp;
	private ImageButton iBtnDown;
	private ImageButton iBtnRight;
	private ImageButton iBtnLeft;
	
	//Tables
	private Table tblMenueBtns;
	private Table tblLabels;
	private Table tblSteuerungBnt;
	
	//Font
	private BitmapFont bitfont;
	
	//Objects
	private Stage stage;
	private Timer time;
	
	private Player[] players;
	private boolean[] alife={true,true,true,true};
	
	/**
	 * @author pbd2h15aho
	 * The GUI is the Graphical User Interface, which enables the User to control the game through mouse controls
	 */	
	public GUI(Timer time, Player p1, Player p2, Player p3, Player p4) {
		super();
		this.time = time;
		players= new Player[4];
		players[0]=p1;
		players[1]=p2;
		players[2]=p3;
		players[3]=p4;
		
		txDP= new Texture(PFADE[1]);
		txDP2= new Texture(PFADE[2]);
		tdDP=new TextureRegionDrawable(new TextureRegion(txDP));
		tdDP2=new TextureRegionDrawable(new TextureRegion(txDP2));
		
		bitfont = new BitmapFont();
		bitfont.setColor(Color.WHITE);

		createMenuButtons();
		createControlButtons();
		createLabels();
		createImages();
		createListeners();
		
		stage= new Stage();
		Gdx.input.setInputProcessor(stage);
		for(int i=0; i<imgsPlayers.length;i++){
			stage.addActor(imgsPlayers[i]);
		}
		stage.addActor(imTimer);
		stage.addActor(lbTimer);
		stage.addActor(tblLabels);
		stage.addActor(tblMenueBtns);
		stage.addActor(tblSteuerungBnt);
		stage.addActor(iBtnBomb);
		
	}
	
	/**
	 * @author pbd2h15aho
	 * The public method render renders the GUI 
	 */	
	public void render(SpriteBatch sb){
		bitfont.draw(sb,"Puffer", 0, 0);
		
		lbTimer.setText(""+(int)time.getTime());
		showIsAlife();
		stage.draw();	
	}
	
	/**
	 * @author pbd2h15aho
	 *isAlifeAnzeigen() is a private method, which changes the BackgroundImages connected to the Players number of lives.
	 */	
	private void showIsAlife(){
		//TODO connect to Player.isAlife
		for(int i=0; i<imgsPlayers.length;i++){
			if(players[i].getLife()==0&&alife[i]){
				if(i<2){
					imgsPlayers[i].setDrawable(tdDP);
				}else{
					imgsPlayers[i].setDrawable(tdDP2);
				}
				alife[i]=false;
			}
		}
	}

	/**
	 * @author pbd2h15aho
	 * The private method createMenueButtons initializes the Buttons which are connected to the Menu
	 * and sets their position on the stage
	 */	
	private void createMenuButtons(){
		tdBtnBack= 	new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[7])));
		tdBtnPause= new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[8])));
		tdBtnBackPressed= 	new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[9])));
		tdBtnPausePressed= 	new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[10])));
		
		iBtnBack= new ImageButton(tdBtnBack,tdBtnBackPressed);
		iBtnPause=new ImageButton(tdBtnPause,tdBtnPausePressed);
		
		tblMenueBtns= new Table();
		tblMenueBtns.add(iBtnBack);
		tblMenueBtns.add().size(2*SCALE_X,0);
		
		//TODO Check if NetworkGame or SinglePlayer
		tblMenueBtns.add(iBtnPause);
		
		tblMenueBtns.setFillParent(true);
		tblMenueBtns.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		tblMenueBtns.setPosition(Gdx.graphics.getWidth()/2-SCALE_X*8 , Gdx.graphics.getHeight()/2-5.3f*SCALE_Y);
	}
	
	/**
	 * @author pbd2h15aho
	 * The private method createLabels initializes the Labels which are connected to the Players and the Timer
	 * and sets their position on the stage
	 */	
	private void createLabels(){
		Label.LabelStyle lbStyle=new Label.LabelStyle();
		lbStyle.font=bitfont;
		lbStyle.fontColor=Color.WHITE;
		String text=""+(int)time.getTime();
		
		lbTimer=new Label(text,lbStyle);
		lbTimer.setPosition(Gdx.graphics.getWidth()/2-text.length()*3, Gdx.graphics.getHeight()-6*SCALE_Y);
		
		lbP1= new Label(players[0].getName(),lbStyle);
		lbP2= new Label(players[1].getName(),lbStyle);
		lbP3= new Label(players[2].getName(),lbStyle);
		lbP4= new Label(players[3].getName(),lbStyle);
		
		tblLabels= new Table();
		tblLabels.add(lbP1).size(15*SCALE_X);
		tblLabels.add().size(5*SCALE_X);
		tblLabels.add(lbP2).size(15*SCALE_X);
		tblLabels.add().size(12.5f*SCALE_X);
		tblLabels.add(lbP3).size(15*SCALE_X);
		tblLabels.add().size(5*SCALE_X);
		tblLabels.add(lbP4).size(10*SCALE_X);
		tblLabels.setFillParent(true);
		tblLabels.setPosition(0,Gdx.graphics.getHeight()/2-13.6f*SCALE_Y);
	}
	
	/**
	 * @author pbd2h15aho
	 * The private method createControlButtons initializes the Buttons which are connected to the Controls
	 * and sets their position on the stage
	 */	
	private void createControlButtons(){
		tdBtnBomb= 	new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[11])));
		tdBtnUp= 	new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[12])));
		tdBtnDown= 	new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[13])));
		tdBtnRight= new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[14])));
		tdBtnLeft= 	new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[15])));
		tdBtnBombPressed=	new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[16])));
		tdBtnUpPressed=		new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[17])));
		tdBtnDownPressed=	new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[18])));
		tdBtnRightPressed=	new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[19])));
		tdBtnLeftPressed=	new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[20])));
				
		iBtnBomb= 	new ImageButton(tdBtnBomb,tdBtnBombPressed);
		iBtnUp= 	new ImageButton(tdBtnUp,tdBtnUpPressed);
		iBtnDown= 	new ImageButton(tdBtnDown,tdBtnDownPressed);
		iBtnRight= 	new ImageButton(tdBtnRight,tdBtnRightPressed);
		iBtnLeft= 	new ImageButton(tdBtnLeft,tdBtnLeftPressed);
				
		iBtnBomb.setPosition(Gdx.graphics.getWidth()-12.5f*SCALE_X,10*SCALE_Y);
				
		tblSteuerungBnt= new Table();
		tblSteuerungBnt.add();
		tblSteuerungBnt.add(iBtnUp);
		tblSteuerungBnt.add();
		tblSteuerungBnt.row();
		tblSteuerungBnt.add(iBtnLeft);
		tblSteuerungBnt.add();
		tblSteuerungBnt.add(iBtnRight);
		tblSteuerungBnt.row();
		tblSteuerungBnt.add();
		tblSteuerungBnt.add(iBtnDown);
		tblSteuerungBnt.add();
			
		tblSteuerungBnt.setPosition(8.8f*SCALE_X,20*SCALE_Y);
	}
	
	/**
	 * @author pbd2h15aho
	 * The private method createImages initializes the Images
	 * and sets their position on the stage
	 */	
	private void createImages() {
		
		imTimer= new Image(new Texture(PFADE[0]));
		imTimer.setPosition(Gdx.graphics.getWidth()/2-imTimer.getWidth()/2-0.3f*SCALE_X,
							Gdx.graphics.getHeight()-imTimer.getHeight()-0.93f*SCALE_Y);
		
		imgsPlayers= new Image[4];
		imgsPlayers[0] = new Image(new Texture(PFADE[3]));
		imgsPlayers[1] = new Image(new Texture(PFADE[4]));
		imgsPlayers[2] = new Image(new Texture(PFADE[5]));
		imgsPlayers[3] = new Image(new Texture(PFADE[6]));
		
		imgsPlayers[0].setPosition(15*SCALE_X-imgsPlayers[0].getWidth()/2, 
								   Gdx.graphics.getHeight()-imgsPlayers[0].getHeight()-10.6f*SCALE_Y);
		imgsPlayers[1].setPosition(35*SCALE_X-imgsPlayers[1].getWidth()/2, 
								   Gdx.graphics.getHeight()-imgsPlayers[1].getHeight()-10.6f*SCALE_Y);
		imgsPlayers[2].setPosition(Gdx.graphics.getWidth()-35*SCALE_X-imgsPlayers[2].getWidth()/2,
								   Gdx.graphics.getHeight()-imgsPlayers[2].getHeight()-10.6f*SCALE_Y);
		imgsPlayers[3].setPosition(Gdx.graphics.getWidth()-15*SCALE_X-imgsPlayers[3].getWidth()/2, 
								   Gdx.graphics.getHeight()-imgsPlayers[3].getHeight()-10.6f*SCALE_Y);
	}
	
	/**
	 * @author pbd2h15aho
	 * The private method createListeners initializes the Buttons Listeners and connects them to their functionalities
	 */	
	private void createListeners(){ 
		//TODO back to Menu
			iBtnBack.addListener(new InputListener() {
				@Override
				public boolean handle(Event event) {
					if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
						System.out.println("Back:click");
						return true;
					}
					
					return false;
				}
			});
			
		//TODO connect with PauseMenu
			iBtnPause.addListener(new InputListener() {	
				@Override
				public boolean handle(Event event) {
					if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
						System.out.println("Pause:click");
						return true;
					}
					return false;}
			});

		//TODO connect to  moveup()
			iBtnUp.addListener(new InputListener() {	
				@Override
				public boolean handle(Event event) {
					if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
						
						System.out.println("Up:click");
						return true;
					}
					return false;
				}
			});
			
		//TODO connect to movedown()
			iBtnDown.addListener(new InputListener() {	
				@Override
				public boolean handle(Event event) {
					if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
						System.out.println("Down:click");
						return true;
					}
					return false;
				}
			});
			
		//TODO connect to move left
			iBtnLeft.addListener(new InputListener() {	
				@Override
				public boolean handle(Event event) {
					if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
						System.out.println("Left:click");
						return true;
					}
					return false;
				}
			});
			
		//TODO connect to move right
			iBtnRight.addListener(new InputListener() {	
				@Override
				public boolean handle(Event event) {
					if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
						System.out.println("Right:click");
						return true;
					}
					return false;
				}
			});
		
		//TODO connect to dropBomb
			iBtnBomb.addListener(new InputListener() {	
				@Override	
				public boolean handle(Event event) {
					if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
						System.out.println("Bomb:click");
						return true;
					}
					return false;
				}
			});
	}
	
}
