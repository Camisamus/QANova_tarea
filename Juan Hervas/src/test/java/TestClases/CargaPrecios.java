package TestClases;

import Pages.Telemercados.Busqueda;
import Pages.Telemercados.Inicio;
import Utils.Conexion;
import com.google.protobuf.StringValue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CargaPrecios {
    public void cargar(){
        Inicio ini = new Inicio();
        ini.buscaralgo();
        ArrayList<String[]> SKUS = traerSku(1);
        ArrayList<String[]> SKUS2 = new ArrayList<>() ;
        Busqueda bus = new Busqueda();
        for (String[] element :SKUS) {
            if(element[1].equals("0")) {
                continue;
            }
            bus.buscar(element[1]);
            int precio = bus.getpreacio();
            String p = String.valueOf(precio);
            String[] k = {element[0],element[1],p};
            SKUS2.add(k);
            //System.out.println(element[0]+":"+element[1] +" $$ "+p);
        }
        ingresarPrecios(SKUS2);
    }

    public void ingresarPrecios(ArrayList<String[]> SKUS){
        Conexion con = new Conexion();
        try {
            String j = "INSERT into precio (SKU, Precio) values ";
            for(int i=0; i<SKUS.size();i++){
                j = j+"(?,?)";
                if(i != SKUS.size()-1){
                    j =j+", ";
                } else{
                    j =j+";";
                }
            }
            java.sql.Connection son = con.conectar();
            PreparedStatement stmt = son.prepareStatement(j);
            int l = 1;
            for(int i=0; i<SKUS.size();i++){
                stmt.setInt(l,Integer.parseInt(SKUS.get(i)[0]));
                l++;
                stmt.setInt(l,Integer.parseInt(SKUS.get(i)[2]));
                l++;
            }
            stmt.execute();

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:");
        }

    };
    public ArrayList<String[]> traerSku(int tienda){
        ArrayList<String[]> SKUS = new ArrayList<>();
        Conexion con = new Conexion();
        try (
            java.sql.Connection son = con.conectar();
                PreparedStatement stmt = son.prepareStatement( "SELECT " +
                        "s.SKU, " +
                        "s.ID " +
                        "from " +
                        "glowecoi_test.producto prod, " +
                        "glowecoi_test.tienda ti, " +
                        "glowecoi_test.sku s " +
                        "where " +
                        "s.Tienda = ti.ID AND  " +
                        "s.Producto = prod .ID AND  " +
                        "ti.ID = ?")) {
            stmt.setInt(1,tienda);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                String[] a = {rs.getString("ID"),rs.getString("SKU")};
                SKUS.add(a);
            }

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:");
        }
        return SKUS;
    }


    public void comparar(){
        ArrayList<String[]> SKUS = new ArrayList<>();
        Conexion con = new Conexion();
        Date k = new Date();
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        String fecha =form.format(k);
        try (
                java.sql.Connection son = con.conectar();
                PreparedStatement stmt = son.prepareStatement( "SELECT " +
                        "p.ID, " +
                        "p.precio, " +
                        "s.producto, " +
                        "p2.ID " +
                        "from " +
                        "glowecoi_test.precio p, " +
                        "glowecoi_test.producto p2, " +
                        "glowecoi_test.sku s " +
                        "where " +
                        "p.SKU = s.ID AND  " +
                        "s.producto = p2.ID AND  " +
                        "p.fecha = ?")) {
            stmt.setString(1,fecha);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                String[] a = {rs.getString("ID"),rs.getString("precio"),rs.getString("producto"),rs.getString("ID")};
                SKUS.add(a);
            }

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:");
        }



        return;
    }

}
