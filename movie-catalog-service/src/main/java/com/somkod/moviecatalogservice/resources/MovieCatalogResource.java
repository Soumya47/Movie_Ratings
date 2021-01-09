package com.somkod.moviecatalogservice.resources;

import com.netflix.discovery.DiscoveryClient;
import com.somkod.moviecatalogservice.models.CatalogItem;
import com.somkod.moviecatalogservice.models.Movie;
import com.somkod.moviecatalogservice.models.Rating;
import com.somkod.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        // get all rated movie Ids

        UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/4"+ userId,
                UserRating.class);

        return ratings.getUserRating().stream().map(rating -> {
            // for each movie Id , call movie-info-service
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);


            // put them all together
            return new CatalogItem(movie.getName(),"Historic Drama",rating.getRating());
        })
                .collect(Collectors.toList());
    }
}
