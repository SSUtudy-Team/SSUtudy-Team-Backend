package SSU.SSUtudyWith.service;

import SSU.SSUtudyWith.dto.Lecture.LectureResponseDTO;
import SSU.SSUtudyWith.util.WebDriverUtil;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LectureService {
    @Transactional
    public LectureResponseDTO findLecture() {
        LectureResponseDTO lectureResponseDTO = new LectureResponseDTO();
        //크롬 드라이버 설정
        WebDriver driver = WebDriverUtil.getChromeDriver();
        List<WebElement> webElementList = new ArrayList<>();
        String url = "https://ecc.ssu.ac.kr/sap/bc/webdynpro/sap/zcmw2100?sap-language=KO#";
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        if (!ObjectUtils.isEmpty(driver)) {
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        }

        WebElement linesToggle = driver.findElement(By.xpath("//*[@id=\"WD015E-btn\"]"));
        linesToggle.click();
        WebElement fiveHundLines = driver.findElement(By.xpath("//*[@id=\"WD0165\"]"));
        fiveHundLines.click();


        WebElement plant = driver.findElement(By.xpath("//*[@id=\"WDFB-scrl\"]"));

        System.out.println("-------" + plant.getAttribute("textContent") + "-------");
        List<WebElement> colleges = plant.findElements(By.className("lsListbox__value"));
        for (WebElement college : colleges) {
            String text = college.getAttribute("textContent");
            System.out.println("******" + text + "******");
            lectureResponseDTO.getIT_Department().put(text, new HashMap<>());
        }

        WebElement test = driver.findElement(By.xpath("//*[@id=\"WDFA-btn\"]"));
        test.click();

        test = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"WD0103\"]")));
        test.click();

        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        System.out.println("%%%%%%%%" + colleges.get(7).getAttribute("textContent") + "%%%%%%%");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }


//GLC = 컨테이너 목록


        System.out.println("%%%%%%%% success in moveToElement %%%%%%%%%%%%%%");



        // 학부 리스트 가져오기
        WebElement plant2 = driver.findElement(By.xpath("//*[@id=\"WD0109-scrl\"]"));
        System.out.println("******" + plant2.getAttribute("textContent") + "******");
        List<WebElement> departments = plant2.findElements(By.className("lsListbox__value"));

        int departLength = departments.size();

        for (int i =0;i<departLength;i++) {
             plant2 = driver.findElement(By.xpath("//*[@id=\"WD0109-scrl\"]"));
             departments = plant2.findElements(By.className("lsListbox__value"));
            WebElement department =  departments.get(i);

            String text = department.getAttribute("textContent");
            System.out.println("******" + text + "******");
            lectureResponseDTO.getIT_Department().get("IT대학").put(text, new HashMap<>());

            WebElement toggle = driver.findElement(By.xpath("//*[@id=\"WD0108-btn\"]"));
            toggle.click();

            department.click();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // InterruptedException을 처리하는 코드 작성
                e.printStackTrace();
            }
            //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


            WebElement plant3 = driver.findElement(By.xpath("//*[@id=\"WD010C-scrl\"]"));
            List<WebElement> specificDeparts = plant3.findElements(By.cssSelector("[ct='LIB_I']"));
            int specLength = specificDeparts.size();
            //By.cssSelector("[ct='LIB_I']")

            for (int j=0;j<specLength;j++) {
                 plant3 = driver.findElement(By.xpath("//*[@id=\"WD010C-scrl\"]"));
                 specificDeparts = plant3.findElements(By.cssSelector("[ct='LIB_I']"));
                 WebElement specificDepart = specificDeparts.get(j);
                String text2 = specificDepart.getAttribute("textContent");
                System.out.println("$$$$" + text2 + "$$$$");
                lectureResponseDTO.getIT_Department().get("IT대학").get(text).put(text2,new HashSet<>());

                //학과 드롭다운 토글
                WebElement toggle2 = driver.findElement(By.xpath("//*[@id=\"WD010B-btn\"]"));
                toggle2.click();
                specificDepart.click();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // InterruptedException을 처리하는 코드 작성
                    e.printStackTrace();
                }

                WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"WD010E\"]"));
                searchButton.click();

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    // InterruptedException을 처리하는 코드 작성
                    e.printStackTrace();
                }

                List<WebElement> subjects = driver.findElements(By.cssSelector("td[id^='WD'][subct='STC'][ut='2'][cc='6']"));
                for (WebElement subject : subjects){
                    String text3 = subject.getAttribute("outerText");
                    System.out.println("&&"+text3);
                    lectureResponseDTO.getIT_Department().get("IT대학").get(text).get(text2).add(text3);
                }

                //td[id^='WD'][subct='STC'][ut='2'][cc='6']






            }


//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                // InterruptedException을 처리하는 코드 작성
//                e.printStackTrace();
//            }



        }


//            test = driver.findElement(By.xpath("//*[@id=\"WDFA-btn\"]"));
//            test.click();
//            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
//            test = driver.findElement(By.xpath("//*[@id=\"WD0103\"]"));
//            test.click();
//            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);


            return lectureResponseDTO;

        }
    }


