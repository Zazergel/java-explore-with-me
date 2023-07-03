package ru.practicum.stats_server.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import ru.practicum.stats_dto.model.ViewStats;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "stats", schema = "public")
@NamedNativeQuery(name = "getStatsByUris",
        query = "select a.name as app, s.uri, count(s.user_ip) as hits from stats s join apps a " +
                "on s.app_id = a.id " +
                "where s.created > :start " +
                "and  s.created < :end and (s.uri in :uris) " +
                "group by s.uri, a.name order by hits desc", resultSetMapping = "ViewStatsModel")
@NamedNativeQuery(name = "getStatsByUrisDistinctIp",
        query = "select a.name as app, s.uri, count(distinct s.user_ip) as hits from stats s join apps a " +
                "on s.app_id = a.id " +
                "where s.created > :start " +
                "and  s.created < :end and (s.uri in :uris) " +
                "group by s.uri, a.name order by hits desc", resultSetMapping = "ViewStatsModel")
@NamedNativeQuery(name = "getAllStats",
        query = "select a.name as app, s.uri, count(s.user_ip) as hits from stats s join apps a " +
                "on s.app_id = a.id " +
                "where s.created > :start " +
                "and  s.created < :end " +
                "group by s.uri, a.name order by hits desc", resultSetMapping = "ViewStatsModel")
@NamedNativeQuery(name = "getAllStatsDistinctIp",
        query = "select a.name as app, s.uri, count(distinct s.user_ip) as hits from stats s join apps a " +
                "on s.app_id = a.id " +
                "where s.created > :start " +
                "and  s.created < :end " +
                "group by s.uri, a.name order by hits desc", resultSetMapping = "ViewStatsModel")
@SqlResultSetMapping(name = "ViewStatsModel", classes = {
        @ConstructorResult(
                columns = {
                        @ColumnResult(name = "app", type = String.class),
                        @ColumnResult(name = "uri", type = String.class),
                        @ColumnResult(name = "hits", type = Long.class),
                },
                targetClass = ViewStats.class
        )
})
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "uri", nullable = false)
    String uri;
    @Column(name = "user_ip", nullable = false, length = 15)
    String ip;
    @Column(name = "created", nullable = false)
    LocalDateTime timestamp;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "app_id")
    private App app;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Stats that = (Stats) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}