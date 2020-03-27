package km.hw55.microgram4.util;

import km.hw55.microgram4.model.*;
import km.hw55.microgram4.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Configuration
public class DatabasePreloader {
    private Random r = new Random();

    @Bean
    CommandLineRunner generateGibberish(UserRepository usersRepo, PublicationRepository publicationRepo,
                                        CommentRepository commentRepo, SubscribtionRepository subscribtionRepo,
                                        LikeRepository likesRepo) {
        return args -> {
            // todo users repo initialization
            usersRepo.deleteAll();
            subscribtionRepo.deleteAll();
            publicationRepo.deleteAll();
            commentRepo.deleteAll();
            likesRepo.deleteAll();

            var users = Stream.generate(User::make).limit(10).collect(toList());

            // todo publications repo initialization
            int i = 0;
            List<Publication> publications = new ArrayList<>();
            while (i < 30) {
                var u = users.get(r.nextInt(users.size()));
                var p = Publication.make(u);
                u.plusPublication();
                publications.add(p);
                users.add(u);
                i++;
            }
//            var ser = User.make("kad");
//            usersRepo.save(ser);
//            var us = usersRepo.findBylogin("kad");
//            System.out.println(us);
//            publicationRepo.saveAll(publications);
//            userId = users.get(4).getId();
//            System.out.println(userId);
//            var map = StreamSupport.stream(publicationRepo.findAll().spliterator(), true)
//                    .collect(Collectors.groupingBy(Publication::getUser, TreeMap::new, Collectors.toList()));
//            map.get(userId).forEach((v) -> System.out.println(v.getDescription() + "  " + v.getUser() + "\n"));

            //todo comments for publications
            i = 0;
            List<Comment> comments = new ArrayList<>();
            while (i < 20) {
                Publication commentFor = publications.get(r.nextInt(publications.size()));
                User commentator = users.get(r.nextInt(users.size()));
                LocalDate pubDate = commentFor.getDate();
                var c = Comment.make(commentator, commentFor, pubDate);
                commentFor.plusComment();
                publications.add(commentFor);
                comments.add(c);
                i++;
            }

            //todo likes for publications
            i = 0;
            List<Like> likes = new ArrayList<>();
            while (i < 30) {
                User liker = users.get(r.nextInt(users.size()));
                Publication publ = publications.get(r.nextInt(publications.size()));
                LocalDate pubDate = publ.getDate();
                var l = Like.make(liker, publ, pubDate);
                publ.plusLike();
                publications.add(publ);
                likes.add(l);
                i++;
            }

            // todo subscribtions repo initialization
            i = 0;
            List<Subscribtion> subscribtions = new ArrayList<>();
            while (i < 20) {
                int subT = r.nextInt(users.size() - 1);
                int subR = subT + 1 <= users.size() ? subT + 1 : subT - 1;
                var subscT = users.get(subT);
                var subscR = users.get(subR);
                subscT.plusSubscribersCount();
                subscR.plusSubscribtionsCount();
                subscribtions.add(Subscribtion.make(subscT, subscR));
                i++;
            }
            usersRepo.saveAll(users);
            publicationRepo.saveAll(publications);
            commentRepo.saveAll(comments);
            likesRepo.saveAll(likes);
            subscribtionRepo.saveAll(subscribtions);

            System.out.println("\nUsers:");
            usersRepo.findAll().forEach(u -> System.out.println(u));
            System.out.println("\nPublications:");
            AtomicInteger pi = new AtomicInteger(1);
            publicationRepo.findAll().forEach(pu -> System.out.println((pi.getAndIncrement()) + pu.toString()));
            System.out.println("\nComments:");
            commentRepo.findAll().forEach(c -> System.out.println(c));
            System.out.println("\nSubscribtions");
            subscribtionRepo.findAll().forEach(s -> System.out.println(s));

        };
    }
}
