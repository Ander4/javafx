package ehu.isad.controllers.ui;

import ehu.isad.Book;
import ehu.isad.Liburuak;
import ehu.isad.controllers.db.ZerbitzuKud;
import ehu.isad.utils.Sarea;
import ehu.isad.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Properties;
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
    private ImageView irudiView;

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

    public void setArgiLabel(Book lib) throws SQLException, IOException {
        //this.argiLabel.setText(pArgitaletxe);

        String pArgitaletxe;
        if (ZerbitzuKud.getInstance().badagoDBan("argitaletxea",lib.getIsbn())){

            pArgitaletxe = ZerbitzuKud.getInstance().lortu("argitaletxea",lib.getIsbn());

        }else{

            Sarea sare = new Sarea();
            Book liburua= sare.readFromUrl(lib.getIsbn());
            pArgitaletxe = liburua.getDetails().getPublishers();
            ZerbitzuKud.getInstance().gehituArgitaletxea(pArgitaletxe,lib.getIsbn());

        }

        this.argiLabel.setText(pArgitaletxe);

    }

    public void setOrriLabel(Book lib) throws IOException, SQLException {

        String pOrri;
        if (ZerbitzuKud.getInstance().badagoDBan("orriKop",lib.getIsbn())){

            pOrri = ZerbitzuKud.getInstance().lortu("orriKop",lib.getIsbn());

        }else{

            Sarea sare = new Sarea();
            Book liburua= sare.readFromUrl(lib.getIsbn());
            pOrri = liburua.getDetails().getPages();
            ZerbitzuKud.getInstance().gehituOrriKop(pOrri,lib.getIsbn());

        }
        this.orriLabel.setText(pOrri);

    }

    public void setIrudiView(Book lib) throws IOException, SQLException {

        String irudi;
        if (ZerbitzuKud.getInstance().badagoDBan("irudia",lib.getIsbn())){

            String argazki = ZerbitzuKud.getInstance().lortu("irudia",lib.getIsbn());
            Properties properties= Utils.lortuEzarpenak();
            String path="file:///"+properties.getProperty("imagePath")+argazki;
            irudi=path;

        }else {
            Sarea sare = new Sarea();
            Book liburua = sare.readFromUrl(lib.getIsbn());
            irudi = liburua.getThumbnail_url();
            irudi = irudi.replace("-S", "-M");
            this.gordeIrudia(irudi,lib);
            ZerbitzuKud.getInstance().gehituLiburua(lib.getTitle()+".jpg",lib.getIsbn());


        }
        this.irudiView.setImage(createImage(irudi));

    }

    private void gordeIrudia(String url,Book lib) throws IOException {
        String izena=lib.getTitle();
        Properties properties= Utils.lortuEzarpenak();
        String path=properties.getProperty("imagePath")+izena+".jpg";
        try(InputStream in = new URL(url).openStream()){
            Files.copy(in, Paths.get(path));}

    }

    private Image createImage(String url) throws IOException {
        URLConnection conn = new URL(url).openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        try (InputStream stream = conn.getInputStream()) {
            return new Image(stream);
        }
    }


}


