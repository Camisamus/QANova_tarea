package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Espera {
    public static boolean esperar(String element){
        boolean cargo = false;
        long limite = System.currentTimeMillis() + 6000;
        while(System.currentTimeMillis()<limite){
            try {
                WebElement exist = (WebElement) DriverContext.getDriver().findElement(By.xpath(element));
                if (exist.isDisplayed()){
                    cargo = true;
                    break;
                }
            }catch (Exception e){
                //System.out.println("Esperando carga de pagina, elemento en busqueda : "+element + System.currentTimeMillis());
            }
        }
        return cargo;
    }
    public static boolean esperar(int segundos) throws InterruptedException {
        Thread.sleep(segundos*1000);
        return true;
    }
}
