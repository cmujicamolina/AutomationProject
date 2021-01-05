package framework;

import java.time.*;
import java.util.function.Function;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PadrePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected int timeout;
		
	public PadrePage(WebDriver driver) {
		this.driver = driver;
		timeout = 20;
		wait = new WebDriverWait(this.driver,timeout);
		PageFactory.initElements(this.driver, this);
	}
	
    public void clickearElemento(final WebElement elemento){
        FluentWait scrollAndclick = new FluentWait(driver);
        scrollAndclick.pollingEvery(Duration.ofMillis(4000));
        scrollAndclick.withTimeout(Duration.ofSeconds(3000)).until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                try{
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].scrollIntoView(true);",elemento);
                    if(elemento.isDisplayed() && elemento.isEnabled()){
                        elemento.click();
                    }else{
                        return false;
                    }
                    //wait.until(ExpectedConditions.elementToBeClickable(elemento));

                    return true;
                }catch (Exception e){
                    return false;
                }
            }
        });
    }

    public void enviarTexto(final WebElement elemento, final String valor){
        FluentWait scrollAndSendKeys = new FluentWait(driver);
        scrollAndSendKeys.pollingEvery(Duration.ofMillis(4000));
        scrollAndSendKeys.withTimeout(Duration.ofSeconds(3000)).until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                try{
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].scrollIntoView(true);",elemento);
                    if(elemento.isEnabled()){
                        elemento.sendKeys(valor);
                    }else{
                        return false;
                    }
                    return true;
                }catch (Exception e){
                    return false;
                }
            }
        });
    }    

    public String obtenerTextoElemento(final WebElement elemento){
        scrollearVariableTexto(elemento);
        //wait.until(ExpectedConditions.visibilityOf(elemento));
        return elemento.getText();
    }

    public void scrollearVariableTexto(final WebElement elemento){
        FluentWait scrollAndSendKeys = new FluentWait(driver);
        scrollAndSendKeys.pollingEvery(Duration.ofMillis(4000));
        scrollAndSendKeys.withTimeout(Duration.ofSeconds(3000)).until(new Function<WebDriver, Boolean>() {            
            public Boolean apply(WebDriver driver) {
                try{
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].scrollIntoView(true);",elemento);

                    if(elemento.isDisplayed() && elemento.getText().length()!=0){
                        return true;
                    }else{
                        return false;
                    }
                }catch (Exception e){
                    return false;
                }
            }
        });
    }
    
    
    
	
}		 