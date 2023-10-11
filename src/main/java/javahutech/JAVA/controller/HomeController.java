package javahutech.JAVA.controller;

import javahutech.JAVA.entity.Movie;
import javahutech.JAVA.services.CartService;
import javahutech.JAVA.services.CategoryService;
import javahutech.JAVA.services.MovieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public  String home(Model model)
    {
        List<Movie> movies = movieServices.getAllMovies();
        model.addAttribute("movies",movies);
        return  "home/index";
    }

    @Autowired
    private MovieServices movieServices;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/checkout-success")
    public String Trang(){
        return  "home/succes";
    }

    @GetMapping("/details/{id}")
    public String Details(@PathVariable("id") Long id, Model model) {
        Movie movie = movieServices.getMovieById(id);
        if (movie == null) {
            // Handle book not found error
            return "error";
        }
        model.addAttribute("movie", movie);
        model.addAttribute("categories", categoryService.getAllCategories());

        List<Movie> books = movieServices.getBooksByCategoryName("hehe",8);
        model.addAttribute("movies", books);


        return "home/details";
    }


}