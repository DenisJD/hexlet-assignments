package exercise.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {

    // BEGIN
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Lob
    String content;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    Post post;
    // END
}
