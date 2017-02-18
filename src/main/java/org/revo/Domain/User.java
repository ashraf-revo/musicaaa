package org.revo.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.revo.Util.ViewDetails;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

/**
 * Created by ashraf on 18/01/17.
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties("authorities")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "music_user")
public class User extends BaseUser {
    @NotBlank
    @Column(length = 30)
    @JsonView(ViewDetails.user.class)
    private String name;
    //    @URL
    @Column(length = 100)
    @JsonView(ViewDetails.user.class)
    private String imageUrl = "/assets/images/a0.png";
    @NotBlank
    @Column(length = 15)
    @JsonView(ViewDetails.user.class)
    private String phone;
    @JsonView(ViewDetails.user.class)
    private String info;
    @Email
    @NotBlank
    @Column(length = 30, unique = true)
    @JsonView(ViewDetails.user.class)
    private String email;
    @JsonProperty(access = WRITE_ONLY)
    @NotBlank
    @Column(length = 60)
    @JsonView(ViewDetails.user.class)
    private String password;
    @JsonProperty(access = WRITE_ONLY)
    @Transient
    @JsonView(ViewDetails.user.class)
    private String currentPassword;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @JsonView(ViewDetails.userSongs.class)
    private List<Song> songs = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @JsonView(ViewDetails.userLikes.class)
    private List<Like> likes = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @JsonView(ViewDetails.userViews.class)
    private List<View> views = new ArrayList<>();
    @Transient
    @JsonProperty(access = WRITE_ONLY)
    private MultipartFile image;


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public User(Long id) {
        super.setId(id);
    }
    public User(Long id, String name, String imageUrl, String phone, String info, String email, Date createdDate) {
        super.setId(id);
        this.name = name;
        this.imageUrl = imageUrl;
        this.phone = phone;
        this.info = info;
        this.email = email;
        super.setCreatedDate(createdDate);
    }
    public User(Long id, String name, String imageUrl, String phone, String info, String email, Date createdDate,String password,boolean enable,boolean locked) {
        super.setId(id);
        this.name = name;
        this.imageUrl = imageUrl;
        this.phone = phone;
        this.info = info;
        this.email = email;
        super.setCreatedDate(createdDate);
        this.password = password;
        super.setEnable(enable);
        super.setLocked(locked);
    }

}