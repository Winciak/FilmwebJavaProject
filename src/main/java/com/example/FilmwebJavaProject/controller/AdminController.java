package com.example.FilmwebJavaProject.controller;

import com.example.FilmwebJavaProject.entity.*;
import com.example.FilmwebJavaProject.service.FilmmakerService;
import com.example.FilmwebJavaProject.service.GenreService;
import com.example.FilmwebJavaProject.service.RankingService;
import com.example.FilmwebJavaProject.service.MovieService;
import com.example.FilmwebJavaProject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    private FilmmakerService filmmakerService;

    private GenreService genreService;

    private RankingService rankingService;

    private MovieService movieService;


    public AdminController(UserService userService, FilmmakerService filmmakerService, GenreService genreService, MovieService movieService,RankingService rankingService) {
        this.userService = userService;
        this.filmmakerService = filmmakerService;
        this.genreService = genreService;
        this.movieService = movieService;
        this.rankingService = rankingService;
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

//        Movie existing = movieService.findMovieByTitle(movie.getTitle());
//        if (existing != null ){
//            theModel.addAttribute("editError", movie.getTitle() + " movie already exists.");
//
//            List<Genre> genreList = genreService.findAll();
//
//            theModel.addAttribute("genreList", genreList);
//
//            return "/movie/add-form";
//        }

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

    @PostMapping("/saveFilmmakerToMovie")
    public String saveFilmmakerToMovie(@RequestParam("movieId")int id, @Valid @ModelAttribute("filmmakers_movies") Filmmakers_movies filmmakers_movies,
                            BindingResult theBindingResult){


        Movie movie = movieService.findById(id);

        filmmakers_movies.setMovie(movie);

        movieService.saveFilmmakersMovies(filmmakers_movies);

        return "redirect:/admin/movies/showMovieFilmmakers?movieId="+movie.getId();
    }

    @GetMapping("/movies/list")
    public String listMovies(Model theModel){

        List<Movie> movieList = movieService.findAll();

        theModel.addAttribute("movies", movieList);

        return "movie/list";

    }

    @GetMapping("/movies/delete")
    public String deleteMovie(@RequestParam("movieId") int id) {

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

    //showMoviesInRanking


    @GetMapping("/rankings/showMovieFilmmakers")
    public String showMoviesInRanking(@RequestParam("movieId")int id, Model theModel){

        Movie movie = movieService.findById(id);

        theModel.addAttribute("movie", movie);

        List<Filmmakers_movies> filmmakers_movies = movieService.findAllFilmmakersByMovieId(id);

        theModel.addAttribute("filmmakers", filmmakers_movies);

        return "/movie/filmmakerList";
    }

    @GetMapping("/rankings/showMoviesInRankingFormForAdd")
    public String showMoviesInRankingFormForAdd(@RequestParam("rankingId")int id, Model theModel) {

        Filmmakers_movies filmmakers_movies = new Filmmakers_movies();

        theModel.addAttribute("filmmakers_movies", filmmakers_movies);

        Movie movie = movieService.findById(id);

        theModel.addAttribute("movie", movie);

        List<Filmmaker> filmmakerList = filmmakerService.findAll();

        theModel.addAttribute("filmmakerList", filmmakerList);


        return "/movie/add-filmmakerToMovie-form";
    }

    @PostMapping("/saveMovieToRanking")
    public String saveMovieToRanking(@RequestParam("movieId")int id, @Valid @ModelAttribute("filmmakers_movies") Filmmakers_movies filmmakers_movies,
                                       BindingResult theBindingResult){


        Movie movie = movieService.findById(id);

        filmmakers_movies.setMovie(movie);

        movieService.saveFilmmakersMovies(filmmakers_movies);

        return "redirect:/admin/rankings/showMovieFilmmakers?movieId="+movie.getId();
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

        rankingService.deleteById(id);

        return "redirect:/admin/rankings/list";
    }

}
