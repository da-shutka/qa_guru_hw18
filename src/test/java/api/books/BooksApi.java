package api.books;

import api.auth.AuthorizationApi;
import io.qameta.allure.Step;
import models.AddBookRequestModel;
import models.BookModel;

import static io.restassured.RestAssured.given;
import static specs.DemoQASpec.*;

public class BooksApi {

    @Step("Добавление книги")
    public void addBookToProfile(String isbn) {
        BookModel book = new BookModel();
        book.setIsbn(isbn);

        AddBookRequestModel bookData = new AddBookRequestModel();
        bookData.setUserId(AuthorizationApi.getCookieByName(AuthorizationApi.USER_ID));
        bookData.setCollectionOfIsbns(new BookModel[]{book});

        given(requestSpec)
                .header("Authorization", "Bearer " + AuthorizationApi.getCookieByName(AuthorizationApi.TOKEN))
                .body(bookData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(responseSpecWithStatusCode201);
    }
}
