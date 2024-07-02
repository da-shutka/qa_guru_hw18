package tests;

import api.books.BooksApi;
import helpers.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;


public class DemoQATests extends TestBase {

    private final ProfilePage profilePage = new ProfilePage();

    @Test
    @WithLogin
    @DisplayName("Удаление книги из списка на странице профиля пользователя")
    public void DeleteBookFromTheList() throws InterruptedException {

        BooksApi book = new BooksApi();
        book.addBookToProfile("9781449365035");
        profilePage.deleteBook();
        profilePage.checkBookIsDeleted();
    }
}
