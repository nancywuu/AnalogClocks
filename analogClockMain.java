/** LAB FOUR
 * @author NANCY WU
 * VERSION 3.0
 **/

import javafx.application.Application;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.stage.WindowEvent;
import javafx.geometry.Insets;
import java.util.TimeZone;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream; 
import java.io.File;
import javafx.geometry.Bounds;
import javafx.animation.RotateTransition;
import javafx.animation.ParallelTransition;
import javafx.util.Duration;
import javafx.animation.Interpolator;


public class analogClockMain extends Application{
	private VBox menuVBox = new VBox(10);
	private ScrollPane scrollPaneMain = new ScrollPane();
	private Scene mainMenu = new Scene(scrollPaneMain, 600, 1000);
	private Button apiaButton = new Button("Pacific/Apia"); //GMT+11
	private Button honoluluButton = new Button("Pacific/Honolulu"); //GMT+10
	private Button losAngelesButton = new Button("America/Los Angeles"); //GMT+9
	private Button denverButton = new Button("America/Denver"); //GMT+8 
	private Button mexicoCityButton = new Button("America/Mexico_City"); //GMT+7
	private Button cubaButton = new Button("Cuba"); //GMT+6
	private Button montserratButton = new Button("America/Montserrat"); //GMT+5
	private Button buenosAiresButton = new Button("America/Buenos_Aires"); //GMT+4
	private Button southGeorgiaButton = new Button("Atlantic/South_Georgia"); //GMT+3
	private Button capeVerdeButton = new Button("Atlantic/Cape_Verde"); //GMT+2
	private Button banjulButton = new Button("Africa/Banjul"); //GMT+1
	private Button londonButton = new Button("Europe/London"); //GMT+0
	private Button amsterdamButton = new Button("Europe/Amsterdam"); //GMT-1
	private Button helsinkiButton = new Button("Europe/Helsinki"); //GMT-2
	private Button moscowButton = new Button("Europe/Moscow"); //GMT-3
	private Button samaraButton = new Button("Europe/Samara"); //GMT-4
	private Button calcuttaButton = new Button("Asia/Calcutta"); //GMT-5
	private Button chagosButton = new Button("Indian/Chagos"); //GMT-6
	private Button shanghaiButton = new Button("Asia/Shanghai"); //GMT-7
	private Button hongKongButton = new Button("Hongkong"); //GMT-8
	private Button japanButton = new Button("Japan"); //GMT-9
	private Button guamButton = new Button("Pacific/Guam"); //GMT-10
	private Button southPoleButton = new Button("Antarctica/South_Pole"); //GMT-11
	private Button fijiButton = new Button("Pacific/Fiji"); //GMT-12
	private Button enderburyButton = new Button("Pacific/Enderbury"); //GMT-13
	private Button kiritimatiButton = new Button("Pacific/Kiritimati"); //GMT-14
	private static Calendar cal;
	
	/**
	* NAME: setTimeZone
	* FUNCTION: Creates a new timezone to input into the createClock method
	* @param String i: is the name of the city/timezone
	**/

	private void setTimeZone(String i){
		cal = GregorianCalendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone(i));
	    createClock(i, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));

	}

	/**
	* NAME: fileChecker
	* FUNCTION: checks if image files exist
	* @return boolean: whether files exist or not
	**/

	private boolean fileChecker(){
		File f1 = new File("./face.png");
		File f2 = new File("./secondHand.png");
		File f3 = new File("./minuteHand.png");
		File f4 = new File("./hourHand.png");

		if(f1.exists() && f2.exists() && f3.exists() && f4.exists()){
			return true;
		} else {
			return false;		
		}
	}

	/**
	* NAME: createClock
	* FUNCTION: Creates a pop up of a clock from the desired city/timezone
	* @param String timeZone: name of the timezone
	* @param String region: name of the city where timezone is located
	* @param int hour: current hour of the timezone at the time of creation
	* @param int minute: current minute of the timezone at the time of creation
	* @param int second: current second of the timezone at the time of creation
	**/

	private void createClock(String region, int hour, int minute, int second){
		Stage clockWindow = new Stage();
		clockWindow.initModality(Modality.NONE);
		Group rootClock = new Group();
		Scene clockScene = new Scene(rootClock, 500, 500);
		clockWindow.setTitle(region); 

		double secondDegrees = second * 6;
		double minuteDegrees = (minute + secondDegrees / 360) * 6;
		double hourDegrees = (hour + (minuteDegrees / 360)) * 30;

		Image clockFace = new Image("./face.png");
		Image secondHand = new Image("./secondHand.png");
		Image minuteHand = new Image("./minuteHand.png");
		Image hourHand = new Image("./hourHand.png");

		ImageView clockFaceView = new ImageView(clockFace);
		clockFaceView.setPreserveRatio(true);
      	clockFaceView.fitWidthProperty().bind(clockWindow.widthProperty());
      	clockFaceView.fitHeightProperty().bind(clockWindow.heightProperty());
      	
		ImageView secondHandView = new ImageView(secondHand);
      	secondHandView.setFitHeight(500); 
      	secondHandView.setFitWidth(500);
		ImageView minuteHandView = new ImageView(minuteHand);
      	minuteHandView.setFitHeight(500); 
      	minuteHandView.setFitWidth(500);
		ImageView hourHandView = new ImageView(hourHand);
      	hourHandView.setFitHeight(500); 
      	hourHandView.setFitWidth(500);

      	secondHandView.setRotate(360 + secondDegrees);
      	minuteHandView.setRotate(360 + minuteDegrees);
      	hourHandView.setRotate(360 + hourDegrees);
      	clockWindow.minWidthProperty().bind(clockScene.heightProperty());
    	clockWindow.minHeightProperty().bind(clockScene.widthProperty());

      	clockFaceView.fitWidthProperty().addListener((obs, oldVal, newVal) -> {
      		Bounds boundsOfImage = clockFaceView.localToScene(clockFaceView.getBoundsInLocal());
      		double xRatio = boundsOfImage.getMaxX() / 500;
      		double yRatio = boundsOfImage.getMaxY() / 500;

      		secondHandView.setTranslateX(xRatio);
      		secondHandView.setTranslateY(yRatio);
      		secondHandView.setFitWidth(500 * xRatio);
      		secondHandView.setFitHeight(500 * yRatio);

      		minuteHandView.setTranslateX(xRatio);
      		minuteHandView.setTranslateY(yRatio);
      		minuteHandView.setFitWidth(500 * xRatio);
      		minuteHandView.setFitHeight(500 * yRatio);

      		hourHandView.setTranslateX(xRatio);
      		hourHandView.setTranslateY(yRatio);
      		hourHandView.setFitWidth(500 * xRatio);
      		hourHandView.setFitHeight(500 * yRatio);
      	});

      	clockFaceView.fitHeightProperty().addListener((obs, oldVal, newVal) -> {
      		Bounds boundsOfImage = clockFaceView.localToScene(clockFaceView.getBoundsInLocal());
      		double xRatio = boundsOfImage.getMaxX() / 500;
      		double yRatio = boundsOfImage.getMaxY() / 500;

      		secondHandView.setTranslateX(xRatio);
      		secondHandView.setTranslateY(yRatio);
      		secondHandView.setFitWidth(500 * xRatio);
      		secondHandView.setFitHeight(500 * yRatio);

      		minuteHandView.setTranslateX(xRatio);
      		minuteHandView.setTranslateY(yRatio);
      		minuteHandView.setFitWidth(500 * xRatio);
      		minuteHandView.setFitHeight(500 * yRatio);

      		hourHandView.setTranslateX(xRatio);
      		hourHandView.setTranslateY(yRatio);
      		hourHandView.setFitWidth(500 * xRatio);
      		hourHandView.setFitHeight(500 * yRatio);
      	});

      	RotateTransition rotateSecondHandView = new RotateTransition(Duration.seconds(60), secondHandView);
	    rotateSecondHandView.setByAngle(360);
	    rotateSecondHandView.setInterpolator(Interpolator.LINEAR);
	    rotateSecondHandView.setCycleCount(Animation.INDEFINITE);

	    RotateTransition rotateMinuteHandView = new RotateTransition(Duration.minutes(60), minuteHandView);
	    rotateMinuteHandView.setByAngle(360);
	    rotateMinuteHandView.setInterpolator(Interpolator.LINEAR);
	    rotateMinuteHandView.setCycleCount(Animation.INDEFINITE);

	    RotateTransition rotateHourHandView = new RotateTransition(Duration.hours(12), hourHandView);
	    rotateHourHandView.setByAngle(360);
	    rotateHourHandView.setInterpolator(Interpolator.LINEAR);
	    rotateHourHandView.setCycleCount(Animation.INDEFINITE);
	 
		ParallelTransition p = new ParallelTransition(rotateSecondHandView, rotateMinuteHandView, rotateHourHandView);
		p.play();

		rootClock.getChildren().addAll(clockFaceView, minuteHandView, hourHandView, secondHandView);

		clockWindow.setScene(clockScene);		      
		clockWindow.show();
	}

	/**
	* NAME: setMainMenu
	* FUNCTION: sets up the main menu for the user
	**/
	private void setMainMenu(){
		menuVBox.getChildren().clear(); 

		Text welcomeMain = new Text();
		welcomeMain.setText("SELECT A TIMEZONE TO CREATE A CLOCK");
		welcomeMain.setFont(new Font(20));

		menuVBox.getChildren().addAll(
		welcomeMain, 
		losAngelesButton, 
		honoluluButton, 
		apiaButton, 
		denverButton, 
		mexicoCityButton, 
		cubaButton, 
		montserratButton, 
		buenosAiresButton, 
		southGeorgiaButton, 
		capeVerdeButton, 
		banjulButton, 
		londonButton,
		amsterdamButton,
		helsinkiButton,
		moscowButton,
		samaraButton,
		calcuttaButton,
		chagosButton,
		shanghaiButton,
		hongKongButton,
		japanButton,
		guamButton,
		southPoleButton,
		fijiButton,
		enderburyButton,
		kiritimatiButton
		);

	}

	/**
	* NAME: setButtons
	* FUNCTION: sets up the actionevents for the timezone buttons
	**/

	private void setButtons(){
		losAngelesButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("America/Los_Angeles");
			}
		});

		honoluluButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("Pacific/Honolulu");
			}
		});

		apiaButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("Pacific/Apia");
			}
		});

		denverButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("America/Denver");
			}
		});

		mexicoCityButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("America/Mexico_City");
			}
		});

		cubaButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("Cuba");
			}
		});

		montserratButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("America/Montserrat");
			}
		});

		buenosAiresButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("America/Buenos_Aires");
			}
		});

		southGeorgiaButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("Atlantic/South_Georgia");
			}
		});

		capeVerdeButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("Atlantic/Cape_Verde");
			}
		});

		banjulButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("Africa/Banjul");
			}
		});

		londonButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("Europe/London");
			}
		});

		amsterdamButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("Europe/Amsterdam");
			}
		});

		helsinkiButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("Europe/Helsinki");
			}
		});

		moscowButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("Europe/Moscow");
			}
		});

		samaraButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("Europe/Samara");
			}
		});

		calcuttaButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("Asia/Calcutta");
			}
		});

		chagosButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("Indian/Chagos");
			}
		});

		shanghaiButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("Asia/Shanghai");
			}
		});

		hongKongButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("Asia/Hong_Kong");
			}
		});

		japanButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("Japan");
			}
		});

		guamButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("Pacific/Guam");
			}
		});

		southPoleButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("Antarctica/South_Pole");
			}
		});

		fijiButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("Pacific/Fiji");
			}
		});

		enderburyButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("Pacific/Enderbury");
			}
		});

		kiritimatiButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	setTimeZone("Pacific/Kiritimati");
			}
		});
	}

	/**
	* NAME: main
	* FUNCTION: launches application
	**/

	public static void main(String[] args) {
    	Application.launch(args);
	}

	/**
	* NAME: start
	* FUNCTION: initiates the program and calls menu scene
	**/

    @Override
    public void start(Stage primaryStage) {
    	menuVBox.setPadding(new Insets(20, 20, 20, 20));
    	scrollPaneMain.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPaneMain.setContent(menuVBox);
    	if(fileChecker()){
    		setMainMenu();
    	} else {
    		Text filesNotFound = new Text();
			filesNotFound.setText("IMAGE FILES CANNOT BE FOUND");
			filesNotFound.setFont(new Font(20));

			Text filesNotFound2 = new Text();
			filesNotFound2.setText("PLEASE MAKE SURE THEY ARE IN THE FOLDER");
			filesNotFound2.setFont(new Font(20));

			menuVBox.getChildren().addAll(filesNotFound, filesNotFound2);
    	}

    	setButtons();

    	primaryStage.setScene(mainMenu);
        primaryStage.show();
    }
}