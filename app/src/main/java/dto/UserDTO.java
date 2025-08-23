package dto;

import java.util.List;

import Model.User;

public class UserDTO {
    public Long id;
    public String username;
    public String password;
    public String email;
    public List<String> watchedMovies;
    public List<String> wishList;

    public static UserDTO fromEntity(User e) {
        UserDTO d = new UserDTO();
        d.id = e.getId();
        d.username = e.getUsername();
        d.email = e.getEmail();
        d.watchedMovies = e.getWatchedMovies();
        d.wishList = e.getWishList();
        return d;
    }

    public User toEntity() {
        return new User(id, username, email, watchedMovies, wishList,password);
    }
}
