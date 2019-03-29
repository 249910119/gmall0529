package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.user.Movie;
import com.atguigu.gmall.movie.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
    @Override
    public Movie getMovie(String id) {
        return new Movie("1","西游记");
    }
}
