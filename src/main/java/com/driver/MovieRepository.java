package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class MovieRepository
{
    Map<String, Movie> movieMap ;
    Map<String, Director> directorMap;
    Map<String, List<String>> pairMap;

    public MovieRepository(Map<String, Movie> movieMap, Map<String, Director> directorMap, Map<String, List<String>> pairMap) {
        this.movieMap = movieMap;
        this.directorMap = directorMap;
        this.pairMap = pairMap;
    }

    // 1) add movie to db movieMap
    public String addMovie(Movie movie)
    {
        String name = movie.getName();
        if (!movieMap.containsKey(name))
        {
            movieMap.put(name, movie);
            return "Movie added successfully";
        }
        return "Movie is already present";
    }

    // 2) add director to db directorMap
    public String addDirector(Director director)
    {
        String name = director.getName();
        if (!directorMap.containsKey(name))
        {
            directorMap.put(name, director);
            return "Director added successfully";
        }
        return "Director is already present";
    }

    // 3) add movie-director pair to db pairMap
    public String addMovieDirectorPair(String mName, String dName)
    {
        if (!movieMap.containsKey(mName) || !directorMap.containsKey(dName))
        {
            return "Movie or Director not found in data base";
        }
        if (pairMap.containsKey(dName))
        {
            pairMap.get(dName).add(mName);     // if director alreday exists then, get its movie name arraylist and add
                                                //   current new movie to that arraylist
        }
        else
        {                                               // if director is not exists then, create new arraylist of string
            List<String> mList = new ArrayList<>();      // and add current new movie name to it.
            mList.add(mName);                            // now add this director name and arraylist to hashmap
            pairMap.put(dName, mList);
        }
        return "Movie-Director pair added successfully";
    }

    // 4) get movie by name from db movieMap
    public Movie getMovieByName(String mName)
    {
        if (!movieMap.containsKey(mName))
        {
            return null;
        }
        return movieMap.get(mName);
    }

    // 5) get director by name from db directorMap
    public Director getDirectorByName(String dName)
    {
        if (!directorMap.containsKey(dName))
        {
            return null;
        }
        return directorMap.get(dName);
    }

    // 6) get list of movie names of particular director from db pairMap
    public List<String>  getMoviesByDirectorName(String dName)
    {
        return pairMap.get(dName);
    }

    // 7) get all movies from db movieMap
    public List<String> findAllMovies()
    {
        List<String> ans = new ArrayList<>();
        for (String n: movieMap.keySet())
        {
            ans.add(n);
        }
        return ans;
    }

    // 8) delet all movies of particular director from db movieMap, & that director from directorMap & taht paiir from pairMap
    public String deleteDirectorByName(String dName)
    {
        List<String> movies = pairMap.get(dName);
        for (int i=0; i<movies.size(); i++)            // removing movies from movieMap
        {
            if (movieMap.containsKey(movies.get(i)))
            {
                movieMap.remove(movies.get(i));
            }
        }
        pairMap.remove(dName);                        // removing pair from pairMap
        if (directorMap.containsKey(dName))           // removing director from directorMap
        {
            directorMap.remove(dName);
        }
        return "Director and its movies removed successfully";
    }

    // 9) delet all directors from directorMap & all songs by that directors from movieMap
    // No need to delet anything from pairMap
    public String deleteAllDirectors()
    {
        for (String dir: pairMap.keySet())
        {
            List<String> list = pairMap.get(dir);
            for (int i=0; i<list.size(); i++)
            {
                if (movieMap.containsKey(list.get(i)))
                {
                    movieMap.remove(list.get(i));
                }
            }
            directorMap.remove(dir);
        }
        return "All directors and their respective movies are deleted";
    }
}
