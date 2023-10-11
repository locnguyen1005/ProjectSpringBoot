package javahutech.JAVA.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import javahutech.JAVA.Validator.annotation.ValidCategoryId;
import javahutech.JAVA.Validator.annotation.ValidUserId;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


@Data
@Entity
@Table(name = "movie")

public class Movie
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Phim")
    @NotEmpty(message = "Phim must not be empty")
    @Size(max = 50,min = 1,message = "Phim must be less than 50 characters")
    private String Phim;

    public Movie(Long id, String phim, String nhasanxuat, Double gia, Category category) {
        this.id = id;
        Phim = phim;
        Nhasanxuat = nhasanxuat;
        Gia = gia;
        this.category = category;
    }
    public Movie() {
    }

    @Column(name = "Nhasanxuat")
    private String Nhasanxuat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhim() {
        return Phim;
    }

    public void setPhim(String phim) {
        Phim = phim;
    }

    public String getNhasanxuat() {
        return Nhasanxuat;
    }

    public void setNhasanxuat(String nhasanxuat) {
        Nhasanxuat = nhasanxuat;
    }

    public Double getGia() {
        return Gia;
    }

    public void setGia(Double gia) {
        Gia = gia;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Column(name = "Gia")
    @NotNull(message = "Gia is required")
    private Double Gia;

    @Column(name = "creation_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ValidCategoryId
    private Category category;

    @ManyToOne
    @JoinColumn(name ="user_id", referencedColumnName = "id")
    @ValidUserId
    private User user;


    @Transient
    private MultipartFile image;

    @Column(name = "image_path")
    private String imagePath;
}