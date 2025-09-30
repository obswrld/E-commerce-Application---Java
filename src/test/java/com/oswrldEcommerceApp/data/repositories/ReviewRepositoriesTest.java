package com.oswrldEcommerceApp.data.repositories;

import com.obswrldEcommerceApp.Main;
import com.obswrldEcommerceApp.data.models.Review;
import com.obswrldEcommerceApp.data.repositories.ReviewRepositories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Main.class)
public class ReviewRepositoriesTest {

    @Autowired
    ReviewRepositories reviewRepositories;

    private Review review1;
    private Review review2;

    @BeforeEach
    public void setup() {
        reviewRepositories.deleteAll();

        review1 = Review.builder()
                .customerId("customer-123")
                .productId("product-123")
                .rating(4)
                .comment("Delivery are very safe and responsible")
                .createdAt(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())
                .build();


        review2 = Review.builder()
                .customerId("customer-333")
                .productId("product-123")
                .rating(3)
                .comment("Love the response of the site")
                .createdAt(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())
                .build();

        reviewRepositories.saveAll(List.of(review1, review2));
    }

    @Test
    public void testFindByProductId() {
        List<Review> reviews = reviewRepositories.findByProductId("product-123");
        assertThat(reviews).hasSize(2);
        assertThat(reviews.get(0).getProductId()).isEqualTo("product-123");
    }

    @Test
    public void testFindByCustomerId() {
        List<Review> reviews = reviewRepositories.findByCustomerId("customer-123");
        assertThat(reviews).hasSize(1);
        assertThat(reviews.get(0).getCustomerId()).isEqualTo("customer-123");
        assertThat(reviews.get(0).getComment()).isEqualTo("Delivery are very safe and responsible");
    }
}
