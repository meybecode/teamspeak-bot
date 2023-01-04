package de.meybecode.teamspeak.http.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "additions")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
public class AdditionalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "additional_id", nullable = false)
    private Long additionalId;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private LogUserEntity user;
    @OneToOne
    @JoinColumn(name = "executor_id", nullable = false)
    private LogUserEntity executor;
    @OneToOne
    @JoinColumn(name = "action_id", nullable = false)
    private TeamSpeakActionEntity teamSpeakAction;
}
