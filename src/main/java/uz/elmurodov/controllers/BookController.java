package uz.elmurodov.controllers;


import com.google.common.collect.Lists;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/*@Controller
@ResponseBody*/

@Controller
@RequestMapping("/book/*")
public class BookController {

    private final Mapper mapper;

    List<Book> books = Lists.newArrayList();

    {
        books.add(new Book(UUID.randomUUID(), "Abkar", "Akbar", 2));
        books.add(new Book(UUID.randomUUID(), "Hayot Yolim(Yohud kun.uz)", "Muslim", 300));
    }


    @Autowired
    public BookController(Mapper mapper) {
        this.mapper = mapper;
    }


    @RequestMapping(value = "create", method = RequestMethod.GET)
    private String createPage() {
        return "/book/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    private String create(@ModelAttribute BookCreateDto dto) {
        System.out.println("dto = " + dto);
        books.add(mapper.toEntity(dto));
        return "redirect:/book/list";
    }

    @ResponseBody
    @RequestMapping(value = "{id}/detail/", method = RequestMethod.GET)
    private String details(@PathVariable UUID id) {
        System.out.println("id = " + id);
        return "" + id;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    private String bookListPage(Model model) {
        model.addAttribute("books", books);
        return "/book/list";
    }


}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Book {
    UUID uuid;
    String name;
    String author;
    int pageCount;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
class BookCreateDto {
    String name;
    String author;
    int pageCount;
}

@Component
class Mapper {
    Book toEntity(BookCreateDto dto) {
        Book book = new Book();
        book.setUuid(UUID.randomUUID());
        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        book.setPageCount(dto.getPageCount());
        return book;
    }
}