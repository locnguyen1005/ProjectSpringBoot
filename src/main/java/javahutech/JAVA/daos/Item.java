package javahutech.JAVA.daos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Long movieId;
    private String movieName;
    private Double gia;
    private int quantity;
}
