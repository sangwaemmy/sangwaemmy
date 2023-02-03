/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jpaonetoonedemo.repository;

import com.jpaonetoonedemo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author SANGWA
 */
public interface CommentRepository extends JpaRepository<Comment, Long>{
    
}
