package Pages.Telemercados;

import Utils.DriverContext;
import Utils.Espera;
import Utils.Validaciones;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Busqueda {

    public Busqueda(){
        PageFactory.initElements(DriverContext.getDriver(),this);
    }

    @FindBy(xpath = "//*[@id=\"busca_nova_api\"]")
    WebElement filtro;
    @FindBy(xpath = "//*[@id=\"app-header\"]/div[3]/div/div/div[2]/div[2]/div")
    WebElement botonBuscar;
    public void buscar(String sku){
        Validaciones.validarObjeto(filtro,"");
        filtro.clear();
        filtro.sendKeys(sku);
        filtro.click();
        Validaciones.validarObjeto(filtro,"");
        botonBuscar.click();

    }
    public int getpreacio(){
        int precio =0;
        try {
            Espera.esperar(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> productos = DriverContext.getDriver().findElements(By.className("product"));
        for (WebElement elemento : productos) {
            List<WebElement> spanprecios = elemento.findElements(By.className("precio"));
            for (WebElement elemento1 : spanprecios) {
                Validaciones.validarObjeto(elemento1, "objeto encontrado");
                String p1 = elemento1.findElements(new By.ByTagName("a")).get(0).getText();
                String p2 =  p1.replaceAll("[^0-9]", "");
                //*[@id="ResultItems_25306662"]/div/ul[1]/li[1]/div/div[2]/span[5]
                precio  = Integer.parseInt(p2);
                break;
            }
            break;
        }

        return precio;
    }
}
