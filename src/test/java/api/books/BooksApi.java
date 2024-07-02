package api.books;

import api.auth.AuthorizationApi;
import io.qameta.allure.Step;
import models.AddBookRequestModel;
import models.IsbnModel;

import static io.restassured.RestAssured.given;
import static specs.BookSpec.*;

public class BooksApi {

    @Step("Добавление книги")
    public void addBookToProfile(String isbn) {
        IsbnModel book = new IsbnModel();
        book.setIsbn(isbn);

        AddBookRequestModel bookData = new AddBookRequestModel();
        bookData.setUserId(AuthorizationApi.getCookies(AuthorizationApi.USER_ID));
        bookData.setCollectionOfIsbns(new IsbnModel[]{book});

        given(addBookRequestSpec)
                .body(bookData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(addBookSuccessResponseSpec);
    }
}
