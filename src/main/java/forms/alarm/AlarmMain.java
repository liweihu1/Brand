package forms.alarm;

import javafx.scene.Parent;
import javafx.scene.Scene;
import utilities.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class AlarmMain extends Application {
    private static final int HEIGHT = 400;
    private static final int WIDTH = 600;

    private Scene scene;

    public void start(Stage primaryStage) throws Exception {
        // Set login views
        scene = new Scene((Parent) FXMLLoader.load(getClass().getResource(Constants.ALARM_VIEW)), WIDTH, HEIGHT);

        // Set window
        primaryStage.setScene(scene);
        primaryStage.setTitle("alarm");
        primaryStage.setMinWidth(WIDTH);
        primaryStage.setMinHeight(HEIGHT);

        // Show window
        primaryStage.show();
    }
}
