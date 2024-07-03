package tests;

import api.books.BooksApi;
import helpers.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;


public class DemoQATests extends TestBase {

    private final ProfilePage profilePage = new ProfilePage();
    private final BooksApi book = new BooksApi();

    @Test
    @WithLogin
    @DisplayName("Удаление книги из списка на странице профиля пользователя")
    public void DeleteBookFromTheList() {
        String isbn = "9781449365035";

        book.addBookToProfile(isbn);
        profilePage
                .openPage()
                .deleteBook()
                .checkBookIsDeleted();
    }
}
