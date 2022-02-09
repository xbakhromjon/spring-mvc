package uz.elmurodov.models;


import java.util.UUID;

public class Book {
    private UUID id;
    private String name;
    private int pageCount;

    public Book() {
    }

    public Book(String bookName, int pageCount) {
        this.id = UUID.randomUUID();
        this.name = bookName;
        this.pageCount = pageCount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
