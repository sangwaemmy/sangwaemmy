/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jpaonetoonedemo.repository;

import com.jpaonetoonedemo.model.Comment;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author SANGWA
 */
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {

//    @Query(value=" select c.* ,t.* from comments c , tutorials t where c.tutorial_id= t.id ",nativeQuery = true)
    Page<List<Comment>> findByTutorialId(long id, Pageable pageable);
}
