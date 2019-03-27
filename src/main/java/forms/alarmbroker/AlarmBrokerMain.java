package forms.alarmbroker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.Constants;

public class AlarmBrokerMain extends Application {
    private static final int HEIGHT = 400;
    private static final int WIDTH = 600;

    private Scene scene;

    public void start(Stage primaryStage) throws Exception {
        // Set login views
        scene = new Scene((Parent) FXMLLoader.load(getClass().getResource(Constants.ALARM_BROKER_VIEW)), WIDTH, HEIGHT);

        // Set window
        primaryStage.setScene(scene);
        primaryStage.setTitle("alarm Broker");
        primaryStage.setMinWidth(WIDTH);
        primaryStage.setMinHeight(HEIGHT);

        // Show window
        primaryStage.show();
    }
}
