package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage extends BasePage {

    private final SelenideElement deleteButton = $("#delete-record-undefined"),
            confirmButton = $("#closeSmallModal-ok"),
            listEmptyLabel = $(".rt-noData");

    @Step("Открытие страницы профиля")
    public ProfilePage openPage() {
        open("/profile");
        $("#userName-label").shouldHave(text("Books : "));
        this.removeBanners();
        return this;
    }

    @Step("Удаление книги")
    public ProfilePage deleteBook() {
        deleteButton.click();
        confirmButton.click();
        switchTo().alert().accept();
        return this;
    }

    @Step("Проверка, что книга удалена")
    public void checkBookIsDeleted() {
        listEmptyLabel.should(exist);
    }
}
