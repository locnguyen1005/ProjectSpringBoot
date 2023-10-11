package javahutech.JAVA.repository;

import javahutech.JAVA.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICateogoryRepository extends JpaRepository<Category, Long> {
}
