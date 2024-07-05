package models;

import lombok.Data;

@Data
public class AddBookRequestModel {

    String userId;
    BookModel[] collectionOfIsbns;
}
