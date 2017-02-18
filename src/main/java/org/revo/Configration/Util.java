package org.revo.Configration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.revo.Domain.IndexedSong;
import org.revo.Domain.User;
import org.revo.Repository.SongRepository;
import org.revo.Service.IndexedSongService;
import org.revo.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.sql.DataSource;

import static java.util.stream.Collectors.toList;

/**
 * Created by ashraf on 18/01/17.
 */
@Configuration
public class Util {
    @Bean
    public CookieCsrfTokenRepository csrfTokenRepository() {
        return CookieCsrfTokenRepository.withHttpOnlyFalse();
    }

    @Bean
    public AuditorAware<User> aware(UserService userService) {
        return userService::current;
    }

    @Bean
    CommandLineRunner runner(UserService userService, AppEnv appEnv, SongRepository songRepository, IndexedSongService indexedSongService) {
        return x -> {
            if (userService.count() == 0) {
                appEnv.getUsers().forEach(userService::save);
            }
            if ((indexedSongService.count() != songRepository.count()) && songRepository.count() > 0) {
                indexedSongService.deleteAll();
                indexedSongService.save(songRepository.findAll().stream().map(IndexedSong::indexedSong).collect(toList()));
            }
        };
    }

    @Bean
    public Cloudinary cloudinary(AppEnv appEnv) {
        AppEnv.Cloudinary cloudinary = appEnv.getCloudinary();
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudinary.getName(),
                "api_key", cloudinary.getKey(),
                "api_secret", cloudinary.getSecret()));
    }


    @Bean
    UserDetailsService userDetailsService(UserService userService) {
        return s -> userService.findByEmail(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}