import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Selenide1HWTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com/";
    }

    @Test
    void selenide1HWTest(){

        String codeExample1 = "@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}";
        String codeExample2 = "class Tests {\n" +
                "  @RegisterExtension \n" +
                "  static SoftAssertsExtension softAsserts = new SoftAssertsExtension();\n" +
                "\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}";

        //Переход на страницу
        open("/selenide/selenide");

        //Переход в вики
        $("#wiki-tab").click();

        //Фильтрую значения в Pages
        $("#wiki-pages-filter").setValue("SoftAssertions");

        // Проверяю наличие SoftAssertions в Pages
        $(".wiki-rightbar").shouldHave(text("SoftAssertions"));

        //Переход на страницу SoftAssertions
        $$(".wiki-rightbar li").findBy(text("SoftAssertions")).click();
        $$(".wiki-rightbar li").findBy(text("3. Using JUnit5 extend test class")).click();

        //Проверка на наличие блока про JUnit5 с примерами кода
        $("#wiki-body").shouldHave(text("3. Using JUnit5 extend test class"));
        $$("#wiki-body").shouldHave(texts(codeExample1));
        $$("#wiki-body").shouldHave(texts(codeExample2));
    }

}
