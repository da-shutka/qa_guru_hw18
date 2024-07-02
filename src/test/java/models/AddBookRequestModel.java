package models;

import lombok.Data;

@Data
public class AddBookRequestModel {

    String userId;
    IsbnModel[] collectionOfIsbns;
}
