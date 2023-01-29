package com.example.FilmwebJavaProject.controller;

import com.example.FilmwebJavaProject.entity.*;
import com.example.FilmwebJavaProject.service.*;
import com.example.FilmwebJavaProject.service.FilmmakerService;
import com.example.FilmwebJavaProject.service.GenreService;
import com.example.FilmwebJavaProject.service.RankingService;
import com.example.FilmwebJavaProject.service.MovieService;
import com.example.FilmwebJavaProject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sun.security.x509.GeneralName;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    private FilmmakerService filmmakerService;

    private GenreService genreService;

    private  RankingService rankingService;

    private  MovieService movieService;
    //private final MovieService movieService;

    private  ImageService imageService;


    public AdminController(UserService userService, FilmmakerService filmmakerService, GenreService genreService,
                           MovieService movieService, ImageService imageService,RankingService rankingService) {
        this.userService = userService;
        this.filmmakerService = filmmakerService;
        this.genreService = genreService;
        this.movieService = movieService;
        this.rankingService = rankingService;
        this.imageService = imageService;

    }

    @GetMapping("/panel")
    public String adminPanel(){


        return "admin/admin-panel";

    }

    @GetMapping("/list")
    public String listUsers(Model theModel){

        List<User> userList = userService.findAll();

        theModel.addAttribute("users", userList);

        return "admin/list-users";

    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user){

        userService.save(user);

        return "redirect:/admin/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showUserFormForUpdate(@RequestParam("userId")int theId, Model theModel){

        User user = userService.findById(theId);

        List<Role> roleList = userService.findRoles();

        theModel.addAttribute("user", user);

        theModel.addAttribute("listRoles", roleList);

        return "admin/roles-form";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") int theId){

        userService.deleteById(theId);

        return "redirect:/admin/list";
    }

    //-----------------------------------------------------------------------------------------------------------------
    //--------------------------------------------FILMMAKER------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------

    @GetMapping("/filmmakers/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        Filmmaker filmmaker = new Filmmaker();


        theModel.addAttribute("filmmaker", filmmaker);


        return "/filmmaker/add-form";
    }

    @GetMapping("/filmmakers/showFormForUpdate")
    public String showFilmmakerFormForUpdate(@RequestParam("filmmakerId")int id, Model theModel){

        Filmmaker filmmaker = filmmakerService.findById(id);


        theModel.addAttribute("filmmaker", filmmaker);


        return "/filmmaker/add-form";
    }


    @PostMapping("/saveFilmmaker")
    public String saveFilmmaker(@Valid @ModelAttribute("filmmaker") Filmmaker filmmaker,
                                BindingResult theBindingResult){


        if (theBindingResult.hasErrors()){

            return "/filmmaker/add-form";
        }



        filmmakerService.save(filmmaker);


        return "redirect:/admin/filmmakers/list";
    }

    @GetMapping("/filmmakers/list")
    public String listFilmmakers(Model theModel){

        List<Filmmaker> filmmakerList = filmmakerService.findAll();

        theModel.addAttribute("filmmakers", filmmakerList);

        return "/filmmaker/list";

    }

    @GetMapping("/filmmakers/delete")
    public String deleteFilmmaker(@RequestParam("filmmakerId") int id){

        filmmakerService.deleteById(id);

        return "redirect:/admin/filmmakers/list";
    }

    //-----------------------------------------------------------------------------------------------------------------
    //--------------------------------------------GENRE----------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------

    @GetMapping("/genres/showFormForAdd")
    public String showGenreFormForAdd(Model theModel) {

        Genre genre = new Genre();


        theModel.addAttribute("genre", genre);


        return "/genre/add-form";
    }

    @GetMapping("/genres/showFormForUpdate")
    public String showGenreFormForUpdate(@RequestParam("genreId")int id, Model theModel){

        Genre genre = genreService.findById(id);


        theModel.addAttribute("genre", genre);


        return "/genre/add-form";
    }


    @PostMapping("/saveGenre")
    public String saveGenre(@Valid @ModelAttribute("genre") Genre genre,
                                BindingResult theBindingResult,
                                Model theModel){


        if (theBindingResult.hasErrors()){

            return "/genre/add-form";
        }

        Genre existing = genreService.findGenreByName(genre.getName());
        if (existing != null ){
            theModel.addAttribute("editError", genre.getName() + " genre already exists.");

            return "/genre/add-form";
        }


        genreService.save(genre);


        return "redirect:/admin/genres/list";
    }

    @GetMapping("/genres/list")
    public String listGenres(Model theModel){

        List<Genre> genreList = genreService.findAll();

        theModel.addAttribute("genres", genreList);

        return "/genre/list";

    }

    @GetMapping("/genres/delete")
    public String deleteGenre(@RequestParam("genreId") int id) {

        genreService.deleteById(id);

        return "redirect:/admin/genres/list";
    }

    //-----------------------------------------------------------------------------------------------------------------
    //--------------------------------------------MOVIE----------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------

    @GetMapping("/movies/showFormForAdd")
    public String showMovieFormForAdd(Model theModel) {

        Movie movie = new Movie();

        theModel.addAttribute("movie", movie);

        List<Genre> genreList = genreService.findAll();

        theModel.addAttribute("genreList", genreList);


        return "/movie/add-form";
    }

    @GetMapping("/movies/showFormForUpdate")
    public String showMovieFormForUpdate(@RequestParam("movieId")int id, Model theModel){

        Movie movie = movieService.findById(id);

        theModel.addAttribute("movie", movie);

        List<Genre> genreList = genreService.findAll();

        theModel.addAttribute("genreList", genreList);


        return "/movie/add-form";
    }


    @PostMapping("/saveMovie")
    public String saveMovie(@Valid @ModelAttribute("movie") Movie movie,
                            BindingResult theBindingResult,
                            Model theModel){

        if (theBindingResult.hasErrors()){

            List<Genre> genreList = genreService.findAll();

            theModel.addAttribute("genreList", genreList);

            return "/movie/add-form";
        }

        movieService.save(movie);

        return "redirect:/admin/movies/list";
    }

    @GetMapping("/movies/showMovieFilmmakers")
    public String showMovieFilmmakers(@RequestParam("movieId")int id, Model theModel){

        Movie movie = movieService.findById(id);

        theModel.addAttribute("movie", movie);

        List<Filmmakers_movies> filmmakers_movies = movieService.findAllFilmmakersByMovieId(id);

        theModel.addAttribute("filmmakers", filmmakers_movies);

        return "/movie/filmmakerList";
    }

    @GetMapping("/movies/showMovieFilmmakersFormForAdd")
    public String showMovieFilmmakersFormForAdd(@RequestParam("movieId")int id, Model theModel) {

        Filmmakers_movies filmmakers_movies = new Filmmakers_movies();

        theModel.addAttribute("filmmakers_movies", filmmakers_movies);

        Movie movie = movieService.findById(id);

        theModel.addAttribute("movie", movie);

        List<Filmmaker> filmmakerList = filmmakerService.findAll();

        theModel.addAttribute("filmmakerList", filmmakerList);


        return "/movie/add-filmmakerToMovie-form";
    }

    @GetMapping("/movies/showMovieFilmmakersFormForUpdate")
    public String showMovieFilmmakersFormForUpdate(@RequestParam("movieId")int id,@RequestParam("filmmakerId")int filmmakerId, Model theModel) {

        Filmmakers_movies filmmakers_movies = movieService.findFilmmakers_moviesByMovieIdAndFilmmakerId(id, filmmakerId);

        theModel.addAttribute("filmmakers_movies", filmmakers_movies);

        Movie movie = movieService.findById(id);

        theModel.addAttribute("movie", movie);

        List<Filmmaker> filmmakerList = filmmakerService.findAll();

        theModel.addAttribute("filmmakerList", filmmakerList);


        return "/movie/add-filmmakerToMovie-form";
    }



    @PostMapping("/saveFilmmakerToMovie")
    public String saveFilmmakerToMovie(@RequestParam("movieId")int id, @Valid @ModelAttribute("filmmakers_movies") Filmmakers_movies filmmakers_movies){

        Movie movie = movieService.findById(id);

        filmmakers_movies.setMovie(movie);

        movieService.saveFilmmakersMovies(filmmakers_movies);

        return "redirect:/admin/movies/showMovieFilmmakers?movieId="+movie.getId();
    }

    @GetMapping("/movies/deleteFilmmakerFromMovie")
    public String deleteFilmmakerFromMovie(@RequestParam("movieId") int id, @RequestParam("filmmakerId")int filmmakerId) {

        Filmmakers_movies filmmakers_movies = movieService.findFilmmakers_moviesByMovieIdAndFilmmakerId(id, filmmakerId);

        movieService.delete(filmmakers_movies);

        return "redirect:/admin/movies/showMovieFilmmakers?movieId="+id;
    }


    @GetMapping("/movies/list")
    public String listMovies(Model theModel){

        List<Movie> movieList = movieService.findAll();

        theModel.addAttribute("movies", movieList);

        return "movie/list";

    }

    @GetMapping("/movies/delete")
    public String deleteMovie(@RequestParam("movieId") int id) {

        Movie movie = movieService.findById(id);

        List<Filmmakers_movies> filmmakers_movies = movie.getFilmmakers_movies();

        List<Review> reviewList = movie.getReviews();

        List<ImageEntity> imageEntityList = (List<ImageEntity>) movie.getImages();

        List<Ranking> rankings = (List<Ranking>) movie.getRankings();

        List<Genre> genres = (List<Genre>) movie.getGenres();

        for(Filmmakers_movies filmmakersMovies : filmmakers_movies){
            movieService.delete(filmmakersMovies);
        }

        movie.setFilmmakers_movies(new ArrayList<>());

        for(Review review : reviewList){
            userService.deleteReviewById(review.getId());
        }

        movie.setReviews(new ArrayList<>());

        for(ImageEntity image : imageEntityList){
            image.getMovies().remove(movie);
            imageService.save(image);
        }

        movie.setImages(new ArrayList<>());

        for(Ranking ranking : rankings){
           Rankings_movies rankings_movies = rankingService.findRankings_moviesByRankingIdAndMovieId(ranking.getId(),movie.getId());
           rankingService.delete(rankings_movies);
        }

        movie.setRankings(new ArrayList<>());
        movie.setGenres(new ArrayList<>());

        movieService.save(movie);


        movieService.deleteById(id);

        return "redirect:/admin/movies/list";
    }




    //-----------------------------------------------------------------------------------------------------------------
    //--------------------------------------------RANKINGS-------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------



    @GetMapping("/rankings/showFormForAdd")
    public String showRankingFormForAdd(Model theModel) {

        Ranking ranking = new Ranking();


        theModel.addAttribute("ranking", ranking);


        return "/ranking/add-form";
    }

    @GetMapping("/rankings/showFormForUpdate")
    public String showRankingFormForUpdate(@RequestParam("rankingId")int id, Model theModel){

        Ranking ranking = rankingService.findById(id);


        theModel.addAttribute("ranking", ranking);


        return "/ranking/add-form";
    }


    @GetMapping("/rankings/showMoviesInRanking")
    public String showMoviesInRanking(@RequestParam("rankingId")int id, Model theModel){

        Ranking ranking = rankingService.findById(id);

        theModel.addAttribute("ranking", ranking);

        List<Rankings_movies> ranking_movies = rankingService.findAllByRankingId(id);

        theModel.addAttribute("movies", ranking_movies);

        return "/ranking/movieList";
    }

    @GetMapping("/rankings/showMoviesInRankingFormForAdd")
    public String showMoviesInRankingFormForAdd(@RequestParam("rankingId")int id, Model theModel) {

        Rankings_movies rankings_movies = new Rankings_movies();

        theModel.addAttribute("rankings_movies", rankings_movies);

        Ranking ranking = rankingService.findById(id);

        theModel.addAttribute("ranking", ranking);

        List<Movie> movieList = movieService.findAll();

        theModel.addAttribute("movieList", movieList);


        return "/ranking/add-movieToRanking-form";
    }

    @GetMapping("/rankings/showMoviesInRankingFormForUpdate")
    public String showMoviesInRankingFormForUpdate(@RequestParam("rankingId")int id,@RequestParam("movieId")int movieId, Model theModel) {

        Rankings_movies rankings_movies = rankingService.findRankings_moviesByRankingIdAndMovieId(id, movieId);

        theModel.addAttribute("rankings_movies", rankings_movies);

        Ranking ranking = rankingService.findById(id);

        theModel.addAttribute("ranking", ranking);

        List<Movie> movieList = movieService.findAll();

        theModel.addAttribute("movieList", movieList);


        return "/ranking/add-movieToRanking-form";
    }

    @GetMapping("/rankings/deleteMovieFromRanking")
    public String deleteMovieFromRanking(@RequestParam("rankingId") int id, @RequestParam("movieId")int movieId) {

        Rankings_movies rankings_movies = rankingService.findRankings_moviesByRankingIdAndMovieId(id, movieId);

        rankingService.delete(rankings_movies);

        return "redirect:/admin/rankings/showMoviesInRanking?rankingId="+id;
    }

    @PostMapping("/saveMovieToRanking")
    public String saveMovieToRanking(@RequestParam("rankingId")int id, @Valid @ModelAttribute("rankings_movies") Rankings_movies rankings_movies){


        Ranking ranking = rankingService.findById(id);

        rankings_movies.setRanking(ranking);

        rankingService.saveRankingsMovies(rankings_movies);

        return "redirect:/admin/rankings/showMoviesInRanking?rankingId="+ranking.getId();
    }
    @PostMapping("/saveRanking")
    public String saveRanking(@Valid @ModelAttribute("ranking") Ranking ranking,
                            BindingResult theBindingResult,
                            Model theModel){


        if (theBindingResult.hasErrors()){

            return "/ranking/add-form";
        }

        Ranking existing = rankingService.findRankingByName(ranking.getName());
        if (existing != null ){
            theModel.addAttribute("editError", ranking.getName() + " ranking already exists.");

            return "/ranking/add-form";
        }


        rankingService.save(ranking);


        return "redirect:/admin/rankings/list";
    }

    @GetMapping("/rankings/list")
    public String listRankings(Model theModel){

        List<Ranking> rankingList = rankingService.findAll();

        theModel.addAttribute("rankings", rankingList);

        return "/ranking/list";

    }

    @GetMapping("/rankings/delete")
    public String deleteRanking(@RequestParam("rankingId") int id) {

        Ranking ranking = rankingService.findById(id);

        for(Rankings_movies rankings_movies : ranking.getRankings_moviesList()){
            rankingService.delete(rankings_movies);
        }

        rankingService.deleteById(id);

        return "redirect:/admin/rankings/list";
    }

    //-----------------------------------------------------------------------------------------------------------------
    //--------------------------------------------IMAGE----------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------

    @GetMapping("/images/showFormForAdd")
    public String showImageFormForAdd(Model theModel) {

        ImageEntity image = new ImageEntity();

        theModel.addAttribute("image", image);

        List<Movie> movies = movieService.findAll();

        theModel.addAttribute("movies", movies);

        return "/image/add-form";
    }

    @GetMapping("/images/showFormForUpdate")
    public String showImageFormForUpdate(@RequestParam("imageId")int id, Model theModel){

        ImageEntity image = imageService.findById(id);

        theModel.addAttribute("image", image);

        List<Movie> movies = movieService.findAll();

        theModel.addAttribute("movies", movies);


        return "/image/add-form";
    }


    @PostMapping("/saveImage")
    public String saveImage(@Valid @ModelAttribute("image") ImageEntity image,
                            BindingResult theBindingResult,
                            Model theModel){


        if (theBindingResult.hasErrors()){

            return "/image/add-form";
        }

//        ImageEntity existing = imageService.findImageEntityByImage_path(image.getImagePath());
//        if (existing != null ){
//            theModel.addAttribute("editError", " image with that link already exists.");
//
//            return "/image/add-form";
//        }


        imageService.save(image);

        for (Movie movie : image.getMovies()){
            movie.getImages().add(image);
            movieService.save(movie);
        }


        return "redirect:/admin/images/list";
    }

    @GetMapping("/images/list")
    public String listImages(Model theModel){

        List<ImageEntity> imageList = imageService.findAll();

        theModel.addAttribute("images", imageList);

        return "/image/list";

    }

    @GetMapping("/images/delete")
    public String deleteImage(@RequestParam("imageId") int id) {

        ImageEntity image = imageService.findById(id);

        List<Movie> movieList = (List<Movie>) image.getMovies();

        for(Movie movie : movieList){
            movie.getImages().remove(image);
            movieService.save(movie);
        }

        image.setMovies(new ArrayList<>());

        imageService.deleteById(id);

        return "redirect:/admin/images/list";
    }

}
