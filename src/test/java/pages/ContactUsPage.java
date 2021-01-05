package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import framework.PadrePage;

public class ContactUsPage extends PadrePage {
	@FindBy(className = "page-subheading")
    WebElement lblHeader;

    //Drop Down
    @FindBy(xpath = "//select[@id='id_contact']/option[text()='Customer service']")
    WebElement optWebmasterSH;

    //Casilla texto
    @FindBy(id = "email")
    WebElement txtEmail;

    //Casilla texto
    @FindBy(id = "id_order")
    WebElement txtOrderReference;

    @FindBy(id = "fileUpload")
    WebElement inputSubirArchivo;

    @FindBy(id = "message")
    WebElement txtMessage;

    @FindBy(id = "submitMessage")
    WebElement btnSend;

    @FindBy(className = "alert-success")
    WebElement messageAlertSuccess;

    public ContactUsPage(WebDriver driver) {
		super(driver);
	}

    public String llenarFormulario() throws InterruptedException{
        String mensaje ="";
        //clickearElemento();

        wait.until(ExpectedConditions.visibilityOf(lblHeader));
        clickearElemento(optWebmasterSH);
        Thread.sleep(4000);
        enviarTexto(txtEmail,"aprendiendoselenium@gmail.com");
        Thread.sleep(4000);
        enviarTexto(txtOrderReference,"12345678-2020");
        Thread.sleep(4000);
        enviarTexto(txtMessage, "Mensaje de prueba");
        Thread.sleep(4000);
        String ruta = System.getProperty("user.dir") + "\\src\\main\\resources\\dataset.txt";
        enviarTexto(inputSubirArchivo, ruta);
        clickearElemento(btnSend);
        Thread.sleep(4000);

        mensaje =  obtenerTextoElemento(messageAlertSuccess);

        return mensaje;

    }

}
