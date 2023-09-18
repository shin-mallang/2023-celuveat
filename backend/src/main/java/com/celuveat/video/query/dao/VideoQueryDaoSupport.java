package com.celuveat.video.query.dao;

import com.celuveat.restaurant.command.domain.Restaurant;
import com.celuveat.video.command.domain.Video;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoQueryDaoSupport extends JpaRepository<Video, Long> {

    @EntityGraph(attributePaths = {"restaurant", "celeb"})
    List<Video> findAllByRestaurantIdIn(List<Long> restaurantIds);

    List<Video> findAllByRestaurantIn(List<Restaurant> restaurants);

    @EntityGraph(attributePaths = {"celeb"})
    List<Video> findAllByRestaurant(Restaurant restaurant);
}
