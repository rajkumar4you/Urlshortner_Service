package com.project.urlshortener;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.urlshortener.model.UrlShortnerModel;

@Repository
public interface ShortURLRepository extends JpaRepository<UrlShortnerModel, String>{

}
