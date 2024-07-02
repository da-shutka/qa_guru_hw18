package api.books;

import api.auth.AuthorizationApi;
import io.qameta.allure.Step;
import models.AddBookRequestModel;
import models.AddBookResponseModel;
import models.IsbnModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.BookSpec.bookAdditionSpec;
import static specs.BookSpec.bookAdditionSuccessResponseSpec;

public class BooksApi {

    @Step("Добавление книги")
    public void AddBookToProfile(String isbn) {
        IsbnModel book = new IsbnModel();
        book.setIsbn(isbn);

        AddBookRequestModel bookData = new AddBookRequestModel();
        bookData.setUserId(AuthorizationApi.getCookies(AuthorizationApi.USER_ID));
        bookData.setCollectionOfIsbns(new IsbnModel[]{book});

        AddBookResponseModel addBookResponse = step("Добавление товара", () ->
                given(bookAdditionSpec)
                        .header("Authorization", "Bearer " + AuthorizationApi.getCookies(AuthorizationApi.TOKEN))
                        .body(bookData)
                        .when()
                        .post("/BookStore/v1/Books")
                        .then()
                        .spec(bookAdditionSuccessResponseSpec)
                        .extract().as(AddBookResponseModel.class)
        );
    }
}
