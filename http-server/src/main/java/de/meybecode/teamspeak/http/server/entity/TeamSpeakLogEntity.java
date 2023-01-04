package de.meybecode.teamspeak.http.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "logs")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
public class TeamSpeakLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id", nullable = false)
    private Long logId;
    @OneToOne
    @JoinColumn(name = "action_id")
    private TeamSpeakActionEntity teamSpeakAction;
    @OneToOne
    @JoinColumn(name = "user_id")
    private LogUserEntity logUser;
    private long createdAt;

}
