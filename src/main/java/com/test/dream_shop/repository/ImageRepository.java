package com.test.dream_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.dream_shop.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{
    

}
