import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormTest {
    @Test
    void shouldSubmitRequest(){
        open("http://localhost:9999");
        SelenideElement form = $("form.form.form_size_m.form_theme_alfa-on-white");
        form.$("[text]").setValue("Иван Петров");
        form.$("[tel]").setValue("+79991112233");
        form.$("[checkbox]").click();
        form.$("[button]").click();
        $("[paragraph paragraph_theme_alfa-on-white]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));


    }
}
