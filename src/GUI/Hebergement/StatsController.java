package GUI.Hebergement;

import Entités.hebergement;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import Tools.MyConnexion;
import javafx.stage.Stage;

public class StatsController implements Initializable {

    @FXML
    private BarChart<String, Number> bar;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        int a = 0;
        int j = 0;
        int n = -1;
        XYChart.Series set = new XYChart.Series<>();
        set.setName("hotels");
        /*  CategoryAxis xAxis    = new CategoryAxis();
         xAxis.setLabel("hotels");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("nombre de reservation");

        BarChart     bar = new BarChart(xAxis, yAxis);
        
        
        XYChart.Series dataSeries1 = new XYChart.Series();
            dataSeries1.setName("2014");

            dataSeries1.getData().add(new XYChart.Data("Desktop", 178));
            dataSeries1.getData().add(new XYChart.Data("Phone"  , 65));
            dataSeries1.getData().add(new XYChart.Data("Tablet"  , 23));


            bar.getData().add(dataSeries1);*/

        List names = new ArrayList<>();
        List lesid = new ArrayList<>();

        Connection cnx;
        cnx = MyConnexion.getInstance().getCnx();
        String query;
        query = "select * from hebergement";

        try {
            PreparedStatement ste;
            ste = cnx.prepareStatement(query);
            ResultSet rs;
            rs = ste.executeQuery();

            //rs.next();
            while (rs.next()) {
                lesid.add(rs.getInt("id_hbg"));
                //int b = rs.getInt("id_hbg");
                names.add(rs.getString("nom_hotel"));
                //String c = rs.getString("nom_hotel");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println(lesid);
        System.out.println(names);
        int i;

        for (i = 0; i < lesid.size(); i++) {
            System.out.println(lesid.get(i));
            String query1;
            query1 = "select idRes from reservation where idHebr=" + lesid.get(i) + "";

            try {
                j = 0;
                PreparedStatement ste1;
                ste1 = cnx.prepareStatement(query1);
                ResultSet rs1;
                rs1 = ste1.executeQuery();
                while (rs1.next()) {
                    j++;
                }
                System.out.println(j);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            n = n + 1;
            set.getData().add(new XYChart.Data(names.get(n), j));

            System.out.println(n);

            bar.getData().add(set);

        }
        bar.getData().addAll(set);

    }

}
