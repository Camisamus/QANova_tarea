package Pages.Telemercados;

import Utils.DriverContext;
import Utils.Validaciones;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Inicio {
    public Inicio(){
        PageFactory.initElements(DriverContext.getDriver(),this);
    }

    @FindBy(xpath = "//*[@id=\"busca_nova_api\"]")
    WebElement filtro;
    @FindBy(xpath = "//*[@id=\"app-header\"]/div[3]/div/div/div[2]/div[2]/div")
    WebElement botonBuscar;

    public void buscaralgo(){
        Validaciones.validarObjeto(filtro,"Boton Buscar");
        filtro.click();
        Validaciones.validarObjeto(botonBuscar,"Boton Buscar");
        botonBuscar.click();
    }

}
