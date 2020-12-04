//Page 391 in the JavaFX Textbook
package spherejavafx;

import java.util.Scanner;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;


public class SphereJavaFX extends Application {
    
   @Override 
    public void start(Stage primaryStage) {
       
        Scanner input = new Scanner(System.in);
        System.out.println("What would you like the zoom of your globe to be. (default = 30)");
        zoom = input.nextInt();
        System.out.println("What axis would you like your globe to rotate on. (default = y) Enter x or y.");
        axis = input.next();
        System.out.println(axis);
        System.out.println("How often would you like your globe to spin{in milliseconds}. (default = 5000)");
        spin = input.nextInt();
        System.out.println("How many spins do you want the globe to do. Enter a negative number for indefinite. (default = -1)");
        cycles = input.nextInt();
        
        StackPane root = new StackPane();
        
        //root.getChildren().add(btn);
        Scene scene = new Scene(root, 300, 300);
        PerspectiveCamera camera = new PerspectiveCamera(true);
        
        //Backs the camera away from the scene by 1000 units
        camera.setTranslateZ(-1000);
        
        //This is the range of which the camera will render objects
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);
        
        //The default field of view for the scene is 30 but change to suit
        
        camera.setFieldOfView(zoom);
        scene.setCamera(camera);
        
        //This sets up my sphere
        Sphere mysphere = new Sphere(200);
        mysphere.setTranslateX(-180);
        mysphere.setTranslateY(-100);
        mysphere.setTranslateZ(100);
        root.getChildren().add(mysphere);
        
        //This sets up the image of the earth to wrap around my sphere
        Image earthImage = new Image("file:earth.jpg");
        PhongMaterial earthPhong = new PhongMaterial();
        earthPhong.setDiffuseMap(earthImage);
        mysphere.setMaterial(earthPhong);
        
        //This rotates my sphere
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(mysphere);
        rotate.setDuration(Duration.millis(spin));
        switch(axis){
            case "x":
                rotate.setAxis(Rotate.X_AXIS);
                break;
                case "y":
                rotate.setAxis(Rotate.Y_AXIS);
                break; 
                default: 
                    rotate.setAxis(Rotate.Y_AXIS);
        }
        rotate.setByAngle(-360);
        rotate.setCycleCount(cycles);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.play();
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static int zoom;
    public static String axis;
    public static int spin;
    public static int cycles;
    
    public static void main(String[] args) {
        
        launch(args);
    }
    
}
