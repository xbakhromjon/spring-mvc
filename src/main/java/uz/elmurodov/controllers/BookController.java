package uz.elmurodov.controllers;


import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.elmurodov.dto.BookCreateDto;
import uz.elmurodov.exceptions.NotFoundException;
import uz.elmurodov.mappers.BookMapper;
import uz.elmurodov.models.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/book/*")
public class BookController {

    private final BookMapper mapper;

    List<Book> books = Lists.newArrayList();

    {
        books.add(new Book(UUID.randomUUID(), "Abkar", "Akbar", 2));
        books.add(new Book(UUID.randomUUID(), "Hayot Yolim(Yohud kun.uz)", "Muslim", 300));
    }


    @Autowired
    public BookController(BookMapper mapper) {
        this.mapper = mapper;
    }


    @RequestMapping(value = "create/", method = RequestMethod.GET)
    private String createPage() {
        return "/book/create";
    }

    @RequestMapping(value = "delete/{id}/", method = RequestMethod.GET)
    private ModelAndView deletePage(ModelAndView modelAndView, @PathVariable String id) {
        Optional<Book> optionalBook = books.stream()
                .filter(book -> book.getId().toString().equals(id)).findFirst();

//        if (optionalBook.isEmpty()) {
//            modelAndView.setViewName("error/404");
//            modelAndView.addObject("message", String.format("Book with id %s not found", id));
//            return modelAndView;
//        }

        if (optionalBook.isEmpty())
            throw new NotFoundException(String.format("Book with id %s not found", id), HttpStatus.NOT_FOUND);

        modelAndView.setViewName("book/delete");
        modelAndView.addObject("book", optionalBook.get());
        return modelAndView;
    }

//    @ExceptionHandler({RuntimeException.class})
//    public String errorjon(RuntimeException e, Model model) {
//        model.addAttribute("message", e.getMessage());
//        return "error/404";
//    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    private String details(@PathVariable UUID id) {
        System.out.println("id = " + id);
        return "" + id;
    }

    @RequestMapping(value = "list/", method = RequestMethod.GET)
    private String bookListPage(Model model) {
        model.addAttribute("books", books);
        return "book/list";
    }


    @RequestMapping(value = "create/", method = RequestMethod.POST)
    private String create(@ModelAttribute BookCreateDto dto) {
        System.out.println("dto = " + dto);
        Book book = mapper.toEntity(dto);
        books.add(book);
        return "redirect:/book/list/";
    }

    @RequestMapping(value = "delete/{id}/", method = RequestMethod.POST)
    private String delete(@PathVariable String id) {
        books.removeIf(book -> book.getId().toString().equals(id));
        return "redirect:/book/list/";
    }


}