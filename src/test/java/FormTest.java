import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.cssSelector;

public class FormTest {
    @Test
    void shouldSubmitIfAllCorrect(){
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$("[type=text]").setValue("Иван Петров");
        form.$("[type=tel]").setValue("+79991112233");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldnotSubmitIfNameIncorrect(){
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$("[type=text]").setValue("Ivan Petrov");
        form.$("[type=tel]").setValue("+79991112233");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();
        $(".input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldnotSubmitIfNameEmpty(){
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$("[type=tel]").setValue("+79991112233");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();
        $(".input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldnotSubmitIfPhoneIncorrect(){
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$("[type=text]").setValue("Иван Петров");
        form.$("[type=tel]").setValue("+7999111223");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldnotSubmitIfPhoneEmpty(){
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$("[type=text]").setValue("Иван Петров");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldnotSubmitIfCheckboxEmpty(){
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$("[type=text]").setValue("Иван Петров");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();
        $("[class=checkbox__text]").shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}
