package ehu.isad.controllers;

import ehu.isad.Book;
import ehu.isad.Liburuak;
import ehu.isad.utils.Sarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class liburuKud implements Initializable {

    private Liburuak mainApp;

    @FXML
    private ComboBox<Book> hautatuCombo;

    @FXML
    private Button ikusiBotoi;

    @FXML
    void onClick(ActionEvent event) throws IOException {

        Book book = (Book)hautatuCombo.getValue();
        Sarea sare = new Sarea();
        Book liburua= sare.readFromUrl(book.getIsbn());
        mainApp.xehetasunakErakutsi(liburua.getDetails().getTitle(),liburua.getDetails().getPublishers(),liburua.getDetails().getPages());

    }

    private void comboDatuak(){

        hautatuCombo.setConverter(new StringConverter<Book>() {
            @Override
            public String toString(Book book) {
                if (book == null)
                    return "";
                return book.getTitle();
            }

            @Override
            public Book fromString(String string) {
                return null;
            }
        });

    }

    private void comboHaieratu(){

        this.comboDatuak();

        ObservableList<Book> books = FXCollections.observableArrayList();
        books.addAll(
                new Book("1491910399", "R for Data Science"),
                new Book("1491946008", "Fluent Python"),
                new Book("9781491906187", "Data Algorithms")
        );
        hautatuCombo.setItems(books);
        hautatuCombo.setEditable(false);

    }

    public void setMainApp(Liburuak liburuak) {

        this.mainApp = liburuak;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.comboHaieratu();

    }
}
