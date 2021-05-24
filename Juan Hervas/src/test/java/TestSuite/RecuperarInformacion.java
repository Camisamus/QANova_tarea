package TestSuite;

import TestClases.*;
import TestClases.Model.Producto;
import Utils.Conexion;
import Utils.Constants.Navegador;
import Utils.DriverContext;
import Utils.ReadProperties;
import Utils.Reporte.PdfQaNovaReports;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RecuperarInformacion {


    @BeforeTest
    public void setUp(){
        Conexion con = new Conexion();
        //PdfQaNovaReports.createPDF();
        try {
            java.sql.Connection son = con.conectar();
            String k = "";
            try (PreparedStatement stmt = son.prepareStatement( "SELECT " +
                    "ti.Web " +
                    "from " +
                    "glowecoi_test.tienda ti " +
                    "where " +
                    "ti.ID = ?")) {
                stmt.setString(1,"1");
                ResultSet rs = stmt.executeQuery();

                while (rs.next()){
                   k= rs.getString("Web");
                }
                DriverContext.setUp(Navegador.Chrome, k);

            } catch (SQLException sqle) {
                System.out.println("Error en la ejecución:");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @AfterTest
    public void end(){
        DriverContext.closeDriver();
        //PdfQaNovaReports.closePDF();
    }

    @Test
    public void prueba001(){
        CargaPrecios inicio = new CargaPrecios();
        inicio.cargar();
    }
     @Test
    public void prueba002(){
         CargaPrecios inicio = new CargaPrecios();
         inicio.comparar();
    }

}
