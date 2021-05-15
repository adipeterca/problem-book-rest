package xyz.problembook.entities;

import javax.persistence.*;

/**
 * Class describing the 'avatar' table from the database.
 * The URL must be a local path to the 'Avatars' directory. (or not?)
 */
@Entity
@Table(name = "avatar")
public class AvatarEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "url")
    private String url;

    public AvatarEntity() {
    }

    public AvatarEntity(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
