package org.revo.Domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.revo.Util.ViewDetails.song;
import org.revo.Util.ViewDetails.like;
import org.revo.Util.ViewDetails.view;
import org.revo.Util.ViewDetails.user;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by ashraf on 18/02/17.
 */
@MappedSuperclass
@Getter
@Setter
abstract class BaseEntity {
    @Id
    @GeneratedValue
    @JsonView({song.class,user.class,like.class,view.class})
    private Long id;
    @CreatedDate
    @JsonView({song.class,user.class,like.class,view.class})
    private Date createdDate = new Date();
}