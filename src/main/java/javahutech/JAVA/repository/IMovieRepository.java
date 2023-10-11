package javahutech.JAVA.repository;


import javahutech.JAVA.entity.Movie;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Long> {


    List<Movie> findByCategoryNameOrderByCreationTimeDesc(String categoryName, PageRequest of);
    List<Movie> findByCategoryName(String categoryName);
    @Query("""
 SELECT b FROM Movie b
 WHERE b.Phim LIKE %?1%
 OR b.Nhasanxuat LIKE %?1%
 OR b.category.name LIKE %?1%
 """)

    List<Movie> searchMovie(String keyword);
    }


