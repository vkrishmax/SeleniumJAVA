I never expected ExpectedConditions
I was having a brief trawl through the Selenium codebase and noticed something I hadn’t seen before. An ExpectedConditions class which exposes a bunch of static methods to save me having to write code.

And it does save me having to write code.

For example in the past I would have written something like this:


````````````````````````````````````````````````````````````````````````````
driver.get("http://compendiumdev.co.uk/selenium/calculate.php");
new WebDriverWait(driver,10).
                  until(new TitleContainsCondition("Selenium"));
```````````````````````````````````````````````````````````````````````````


This opens the driver to the page and waits until the Title contains the text “Selenium”.

And I had to write code to support this i.e. my TitleContainsCondition class:



````````````````````````````````````````````````````````````````````````````
public class TitleContainsCondition implements ExpectedCondition {          
    private String subMenuText;

    public TitleContainsCondition(final String subMenuText) {
        this.subMenuText=subMenuText;
    }

    @Override
    public Boolean apply(@Nullable WebDriver webDriver) {
        return webDriver.getTitle().contains(this.subMenuText);
    }
}
````````````````````````````````````````````````````````````````````````````


But now, I don’t have to, the ExpectedConditions class has a method for this common contingency.


````````````````````````````````````````````````````````````````````````````
driver.get("http://compendiumdev.co.uk/selenium/calculate.php");
new WebDriverWait(driver,10).
                 until(ExpectedConditions.titleContains("Selenium"));
````````````````````````````````````````````````````````````````````````````



I used a similar approach in my production code, I typically have a factory for the ExpectedConditions so that I can write.

````````````````````````````````````````````````````````````````````````````

driver.get("http://compendiumdev.co.uk/selenium/calculate.php");
new WebDriverWait(driver,10).
                 until(WaitFor.titleContainsCondition("Selenium"));
````````````````````````````````````````````````````````````````````````````


I’ll keep my WaitFor Class hanging around because it still does things that are not common to most folk, since we test applications which are domain specific.

And if you haven’t noticed the ExpectedConditions before, now might be a good time to try them out.

`````````````````````````````````````````$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$                                                        
WebDriver Waits Examples
Home >> Selenium Tutorials >> WebDriver Waits Examples

 

We can use WebDriverWait class in many different cases. When ever we need to perform any operation on element, we can use webdriver wait to check if the element is Present or visible or enabled or disabled or Clickable etc.

We will look into different examples for all the above scenarios:

isElementPresent:
``````````````````
Below is the syntax to check for the element presence using WebDriverWait. Here we need to pass locator and wait time as the parameters to the below method. Here it is checking that an element is present on the DOM of a page or not. That does not necessarily mean that the element is visible. ExpectedConditions will return true once the element is found in the DOM.


@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

WebDriverWait wait = new WebDriverWait(driver, waitTime);
wait.until(ExpectedConditions.presenceOfElementLocated(locator)); 

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


We should use presenceOfElementLocated when we don't care about the element visible or not, we just need to know if it's on the page.

We can also use the below syntax which is used to check if the element is present or not. We can return true ONLY when the size of elements is greater than Zero. That mean there exists atleast one element.

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

WebElement element = driver.findElements(By.cssSelector(""));
element.size()>0;
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


isElementClickable
````````````````````
Below is the syntax for checking an element is visible and enabled such that we can click on the element. We need to pass wait time and the locator as parameters.

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

WebDriverWait wait = new WebDriverWait(driver, waitTime);
wait.until(ExpectedConditions.elementToBeClickable(locator));

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


isElementVisible
``````````````````
Below is the syntax to check if the element is present on the DOM of a page and visible. Visibility means that the element is not just displayed but also should also has a height and width that is greater than 0.


@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

WebDriverWait wait = new WebDriverWait(driver, waitTime);
wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


We can also use the below to check the element to be visible by WebElement.

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

WebDriverWait wait = new WebDriverWait(driver, waitTime);
 wait..until(ExpectedConditions.visibilityOf(element));

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


We can also use the below to check all elements present on the web page are visible. We need to pass list of WebElements.

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

List<WebElement> linkElements = driver.findelements(By.cssSelector('#linkhello'));
WebDriverWait wait = new WebDriverWait(driver, waitTime);
 wait..until(ExpectedConditions.visibilityOfAllElements(linkElements));

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


isElementInVisible
``````````````````````
Below is the syntax which is used for checking that an element is either invisible or not present on the DOM.

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

WebDriverWait wait = new WebDriverWait(driver, waitTime);
wait.until(ExpectedConditions.invisibilityOfElementLocated(locator)); 
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


isElementEnabled
``````````````````
Below is the syntax which is used to check if the element is enabled or not
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


WebElement element = driver.findElement(By.id(""));
element.isEnabled();

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


isElementDisplayed
````````````````````
Below is the syntax which is used to check if the element is displayed or not. It returns false when the element is not present in DOM.

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

WebElement element = driver.findElement(By.id(""));
element.isDisplayed();
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


Wait for invisibility of element
``````````````````````````````````
Below is the syntax which is used to check for the Invisibility of element with text

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

WebDriverWait wait = new WebDriverWait(driver, waitTime);
wait.until(ExpectedConditions.invisibilityOfElementWithText(by));

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


Wait for invisibility of element with Text
``````````````````````````````````````````````
Below is the syntax which is used for checking that an element with text is either invisible or not present on the DOM.


@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

WebDriverWait wait = new WebDriverWait(driver, waitTime);
wait.until(ExpectedConditions.invisibilityOfElementWithText(by, strText));
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
