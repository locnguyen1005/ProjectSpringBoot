package javahutech.JAVA;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import javahutech.JAVA.daos.Item;
import javahutech.JAVA.entity.Movie;

import javahutech.JAVA.services.CartService;
import javahutech.JAVA.services.CategoryService;
import javahutech.JAVA.services.MovieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.io.FileOutputStream;


@Controller
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieServices movieServices;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CartService cartService;

    @GetMapping
    public String showAllMovies(Model model)
    {
        List<Movie> movies = movieServices.getAllMovies();
        model.addAttribute("movies",movies);
        return"movie/list";
    }

    @GetMapping("/add")
    public String addMovieForm(Model model)
    {
        model.addAttribute("movie", new Movie());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "movie/add";
    }

    @PostMapping("/add")
    public String addMovie(@Valid @ModelAttribute("movie") Movie movie, BindingResult bindingResult, @RequestParam("image") MultipartFile images, Model model ) throws IOException
    {
        if(bindingResult != null && bindingResult.hasErrors())
        {
            List<String> errors = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).toList();
            model.addAttribute("errors",errors);
            return "movie/add";
        }
        if (!images.isEmpty()) {
            String imageName = UUID.randomUUID().toString() + ".jpg";
            String uploadDir = "src/main/resources/static/images"; // Đường dẫn tới thư mục lưu trữ hình ảnh trên máy chủ

            byte[] imageData = images.getBytes();
            File imageFile = new File(uploadDir, imageName);
            FileOutputStream fos = new FileOutputStream(imageFile);
            fos.write(imageData);
            fos.close();

            movie.setImagePath("images/" + imageName); // Lưu đường dẫn tệp hình ảnh vào book
        }
        movieServices.addMovie(movie);
        return "redirect:/movies";
    }

    @GetMapping("/edit/{id}")
    public String editMovieForm (@PathVariable("id") Long id, Model model)
    {
        List<Movie> movies = movieServices.getAllMovies();
        Optional<Movie> editMovie= movies.stream()
                .filter(movie -> movie.getId().equals(id)).findFirst();
        if(editMovie.isPresent())
        {
            model.addAttribute("movie",editMovie.get());
            model.addAttribute("categories",categoryService.getAllCategories());
            return "movie/edit";
        }
        else
        {
            return"not-found";
        }
    }


    @PostMapping("/edit")
    public String editMovie(@ModelAttribute("movie") Movie updatedMovie){
        List<Movie> movies = movieServices.getAllMovies();
        movies.stream()
                .filter(movie ->movie.getId()==updatedMovie.getId())
                .findFirst()
                .ifPresent(movie ->movies.set(movies.indexOf(movie),updatedMovie));
        movieServices.updateMovie(updatedMovie);
        return  "redirect:/movies";
    }
    @GetMapping("/delete/{id}")
    public String deleteMovies(@PathVariable("id")Long id){
        Movie movie = movieServices.getMovieById(id);
        movieServices.deleteMovie(id);
        return "redirect:/movies";
    }
    @GetMapping("/search")
    public String searchMovie(Model model, @RequestParam String keyword){


        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("movies", movieServices.getAllMovies());
        model.addAttribute("movies", movieServices.searchMovie(keyword));

        return"movie/list";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(HttpSession session,
                            @RequestParam long id,
                            @RequestParam String name,
                            @RequestParam double price,
                            @RequestParam(defaultValue = "1") int quantity)
    {
        var cart = cartService.getCart(session);
        cart.addItems(new Item(id, name, price, quantity));
        cartService.updateCart(session, cart);
        return "redirect:/movies";
    }
}
