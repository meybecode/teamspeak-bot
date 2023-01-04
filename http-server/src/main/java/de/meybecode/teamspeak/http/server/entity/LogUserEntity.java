package de.meybecode.teamspeak.http.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
public class LogUserEntity {

    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;
    private String uniqueId;
    private String userName;

}
