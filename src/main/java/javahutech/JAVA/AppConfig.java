package javahutech.JAVA;

import javahutech.JAVA.entity.Movie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.*;

@Configuration
public class AppConfig {
    @Bean
    public List<Movie> getBooks()
    {
        List<Movie> movies = new ArrayList<>();

        Movie book1 = new Movie();
        book1.setId(1L);
        book1.setPhim("Lập trình Web Spring MVC");
        book1.setNhasanxuat("Ánh Nguyễn");
        book1.setGia(10.99);

        movies.add(book1);


        Movie book2 = new Movie();
        book1.setId(1L);
        book1.setPhim("Lập trình Web Spring MVC");
        book1.setNhasanxuat("Ánh Nguyễn");
        book1.setGia(10.99);

        movies.add(book2);

        Movie book3 = new Movie();
        book1.setId(1L);
        book1.setPhim("Lập trình Web Spring MVC");
        book1.setNhasanxuat("Ánh Nguyễn");
        book1.setGia(10.99);
        movies.add(book3);


        return movies;
    }
}
