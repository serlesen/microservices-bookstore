package com.sergio.bookstore.service.user.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(
        name = "bookstore_user",
        schema = "service_users"
)
public class BookstoreUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_user_generator")
    @GenericGenerator(
            name = "sequence_user_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "service_users.seq_user"),
                    @Parameter(name = "initial_value", value = "1000"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;
}
