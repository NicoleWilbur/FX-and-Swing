/***
 =================
 Name: Nicole Wilbur

 Project Name: CSC372-CTA03 -- PORTFOLIO MILESTONE #1 Corrections

 4/11/21 updates: comments along side code.
 Summary: changed the display date time label color to show better on orange colored background.
 ==================
 ***/

package sample;
import java.io.*;
import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class OrangeDateTime extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Text dateTimeLabel = new Text("Date and Time: ");
            TextField dateTimeDisplay = new TextField();

            MenuBar menuBar = new MenuBar();
            Menu mainMenu = new Menu("Menu");
            MenuItem displayDT = new MenuItem("Display Date & Time");
            MenuItem printFile = new MenuItem("Print to File");
            MenuItem colorFun = new MenuItem("Color Fun");
            MenuItem closeApp = new MenuItem("Close");
            menuBar.getMenus().addAll(mainMenu);
            mainMenu.getItems().addAll(displayDT, printFile, colorFun, closeApp);

            BorderPane borderPane = new BorderPane();
            borderPane.setMinSize(500, 200);
            borderPane.setTop(menuBar);

            GridPane gridPane = new GridPane();
            gridPane.setMinSize(400, 100);
            gridPane.setAlignment(Pos.CENTER);
            borderPane.setCenter(gridPane);

            displayDT.setOnAction(actionEvent ->
            {
                gridPane.getColumnConstraints().add(new ColumnConstraints(200));
                gridPane.add(dateTimeLabel, 0, 0);
                gridPane.add(dateTimeDisplay, 0, 1, 8, 1);
                DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String whatTime = LocalDateTime.now().toString();
                dateTimeDisplay.setText(whatTime);
            });

            printFile.setOnAction(actionEvent ->
            {
                String file = "/Users/killersheltie/log.txt";
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter(file);
                    DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String whatTime = LocalDateTime.now().toString();
                    dateTimeDisplay.setText(whatTime);
                    String text = dateTimeDisplay.getText();
                    fileWriter.write(text);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            colorFun.setOnAction(actionEvent ->
                    {
                        int min = (int) Math.ceil(95);
                        int max = (int) Math.floor(40);
                        int hslHueRandom = (int) Math.floor(Math.random() * (max - min + 1) + min);
                        String hslString = "hsl(24,100%," + hslHueRandom + "%)";
                        gridPane.setBackground(new Background(new BackgroundFill(Color.web(hslString),
                                null, null)));
                        dateTimeLabel.setFill(Color.web("White"));      //4-11-21 changed label text color to make
                    }                                                   // more visible on the orange background
            );

            closeApp.setOnAction(actionEvent ->
            {
                Platform.exit();
            });

            Scene scene = new Scene(borderPane);
            stage.setTitle("Module 3 CTA Option 2");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Whoops, something went wrong, please try again");
            alert.showAndWait();
        }
    }
    public static void main(String args[]){
        launch(args);
    }
}