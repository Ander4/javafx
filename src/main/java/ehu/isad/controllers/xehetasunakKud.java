package ehu.isad.controllers;

import ehu.isad.Book;
import ehu.isad.Liburuak;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class xehetasunakKud {

    private Liburuak mainApp;

    @FXML
    private Label izenbLabel;

    @FXML
    private Label argiLabel;

    @FXML
    private Label orriLabel;

    @FXML
    private Button atzeraBotoi;

    @FXML
    void onClick(ActionEvent event) {

        mainApp.liburuakErakutsi();

    }


    public void setMainApp(Liburuak liburuak) {

        this.mainApp = liburuak;

    }

    public void setIzenbLabel(String pIzenburu) {
        this.izenbLabel.setText(pIzenburu);

    }

    public void setArgiLabel(String pArgitaletxe) {
        this.argiLabel.setText(pArgitaletxe);

    }

    public void setOrriLabel(String pOrri){
        this.orriLabel.setText(pOrri);
    }

}


