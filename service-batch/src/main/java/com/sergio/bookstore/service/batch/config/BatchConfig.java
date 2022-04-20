package com.sergio.bookstore.service.batch.config;

import com.sergio.bookstore.service.batch.dto.BatchFileUser;
import com.sergio.bookstore.service.batch.entities.BookstoreUser;
import com.sergio.bookstore.service.batch.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Bean
    public Job updateUsersJob(Step importUsersStep, Step updateAgeTasklet) {
        return jobBuilderFactory.get("updateUsersJob")
                .incrementer(new RunIdIncrementer())
                .start(importUsersStep)
                .next(updateAgeTasklet)
                .build();
    }

    @Bean
    public Step updateAgeTasklet() {
        return stepBuilderFactory.get("updateAgeTasklet")
                .tasklet((stepContribution, chunkContext) -> {
                    List<BookstoreUser> users = userRepository.findAll();
                    users.forEach(user -> user.setAge(ChronoUnit.YEARS.between(user.getBirthDate(), LocalDate.now())));
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step importUsersStep() {
        return stepBuilderFactory.get("importUsersStep")
                .<BatchFileUser, BookstoreUser>chunk(100)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public ItemReader<BatchFileUser> reader() {
        return new FlatFileItemReaderBuilder<BatchFileUser>()
                .name("userReader")
                .resource(new ClassPathResource("users.csv"))
                .linesToSkip(1)
                .delimited()
                .delimiter(";")
                .names("id", "name", "email", "birth_date")
                .targetType(BatchFileUser.class)
                .build();
    }

    @Bean
    public ItemProcessor<BatchFileUser, BookstoreUser> processor() {
        return item -> {
            Random r = new Random();
            String generatedPassword = r.ints(48, 122)
                    .limit(16)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            return new BookstoreUser(item.getId(),
                    item.getEmail().split("@")[0],
                    passwordEncoder.encode(generatedPassword),
                    LocalDate.parse(item.getBirthDate()),
                    0
                    );
        };
    }

    @Bean
    public ItemWriter<BookstoreUser> writer() {
        return userRepository::saveAll;
    }
}
