package javahutech.JAVA.services;


import javahutech.JAVA.entity.Movie;
import javahutech.JAVA.repository.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServices {
    @Autowired
    private IMovieRepository movieRepository;
    public List<Movie> getAllMovies()
    {
        return movieRepository.findAll();
    }
    public Movie getMovieById(Long id)
    {
        return movieRepository.findById(id).orElse(null);
    }

    public  void addMovie(Movie movie)
    {
        movieRepository.save(movie);
    }
    public  void updateMovie(Movie movie)
    {
        movieRepository.save(movie);
    }
    public void deleteMovie(Long id)
    {
        movieRepository.deleteById(id);
    }

    public List<Movie> searchMovie(String keyword) {
        return movieRepository.searchMovie(keyword);
    }


    public List<Movie> getBooksByCategoryName(String categoryName, int limit) {
        return movieRepository.findByCategoryNameOrderByCreationTimeDesc(categoryName, PageRequest.of(0, limit));
    }

    public List<Movie> getBooksByCategoryName(String categoryName) {
        return movieRepository.findByCategoryName(categoryName);
    }
}
