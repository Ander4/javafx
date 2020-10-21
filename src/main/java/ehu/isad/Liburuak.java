package ehu.isad;

import ehu.isad.controllers.liburuKud;
import ehu.isad.controllers.xehetasunakKud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Liburuak extends Application {

    private Parent liburuUI;
    private Parent xehetasunakUI;

    private Stage stage;

    private liburuKud liburuKud;
    private xehetasunakKud xehetasunakKud;
    private Scene sceneLib;
    private Scene sceneXe;


    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        pantailakKargatu();

        stage.setTitle("OpenLibrary APIa aztertzen");
        stage.setScene(sceneLib);
        stage.show();
    }

    private void pantailakKargatu() throws IOException {

        FXMLLoader loaderKautotu = new FXMLLoader(getClass().getResource("/liburua.fxml"));
        liburuUI = (Parent) loaderKautotu.load();
        sceneLib = new Scene(liburuUI,600,450);
        liburuKud = loaderKautotu.getController();
        liburuKud.setMainApp(this);

        FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/Xehetasunak.fxml"));
        xehetasunakUI = (Parent) loaderMain.load();
        sceneXe = new Scene(xehetasunakUI,800,450);
        xehetasunakKud = loaderMain.getController();
        xehetasunakKud.setMainApp(this);
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void xehetasunakErakutsi(String pIzenburua, String pArgitaletxea, String pOrri) {
        xehetasunakKud.setIzenbLabel(pIzenburua);
        xehetasunakKud.setArgiLabel(pArgitaletxea);
        xehetasunakKud.setOrriLabel(pOrri);
        stage.setScene(sceneXe);
        stage.show();
    }
    public void liburuakErakutsi() {
        stage.setScene(sceneLib);
        stage.show();
    }
}