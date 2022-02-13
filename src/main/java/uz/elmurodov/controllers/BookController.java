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



    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    private ModelAndView deletePage(ModelAndView modelAndView, @PathVariable String id) {
        Optional<Book> optionalBook = books.stream()
                .filter(book -> book.getId().toString().equals(id)).findFirst();

        if (optionalBook.isEmpty())
            throw new NotFoundException(String.format("Book with id %s not found", id), HttpStatus.NOT_FOUND);

        modelAndView.setViewName("book/delete");
        modelAndView.addObject("book", optionalBook.get());
        return modelAndView;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    private String bookListPage(Model model) {
        model.addAttribute("books", books);
        return "book/list";
    }


    @RequestMapping(value = "create/", method = RequestMethod.POST)
    private String create(@ModelAttribute BookCreateDto dto) {
        System.out.println("dto = " + dto);
        Book book = mapper.toEntity(dto);
        books.add(book);
        return "redirect:/book/list";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    private String delete(@PathVariable String id) {
        books.removeIf(book -> book.getId().toString().equals(id));
        return "redirect:/book/list";
    }


    @RequestMapping("update/{id}")
    private ModelAndView update(@PathVariable String id, ModelAndView modelAndView) {
        Optional<Book> optional = books.stream().filter(book -> book.getId().toString().equals(id)).findFirst();
        Book book = optional.orElseThrow(() -> new NotFoundException(String.format("Book not found %s", id)));
        modelAndView.addObject("book", book);
        modelAndView.setViewName("book/update");
        return modelAndView;
    }


    // book update bn book create bir xil
    // TODO: 2/13/2022 Keyin almashtirish kerak
    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    private String update(@PathVariable String id, @ModelAttribute BookCreateDto bookCreateDto) {
        for (Book book : books) {
            if (book.getId().toString().equals(id)) {
                book.setName(bookCreateDto.getName());
                book.setAuthor(bookCreateDto.getAuthor());
                book.setPageCount(bookCreateDto.getPageCount());
                break;
            }
        }
        return "redirect:/book/list/";
    }


    @RequestMapping("detail/{id}")
    private ModelAndView detail(@PathVariable String id, ModelAndView modelAndView) {
        Optional<Book> optional = books.stream().filter(book -> book.getId().toString().equals(id)).findFirst();
        Book book = optional.orElseThrow(() -> new NotFoundException(String.format("Book not found %s", id)));
        modelAndView.addObject("book", book);
        modelAndView.setViewName("/book/detail");
        return modelAndView;
    }


}