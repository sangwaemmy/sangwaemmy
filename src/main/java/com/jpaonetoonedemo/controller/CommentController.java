/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jpaonetoonedemo.controller;

import com.jpaonetoonedemo.model.Comment;
import com.jpaonetoonedemo.model.Tutorial;
import com.jpaonetoonedemo.repository.CommentRepository;
import com.jpaonetoonedemo.repository.TutorialRepository;
import io.swagger.annotations.ApiOperation;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SANGWA
 */
@RestController
@RequestMapping("/rusumo_app/api/comments")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    TutorialRepository tutorialRepository;

    @ApiOperation(value = "Get Comments ")
    @GetMapping()
    public ResponseEntity<List<Comment>> getallComment() {
        List<Comment> comments = new ArrayList<>();
        commentRepository.findAll().forEach(comments::add);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Comment> createComment(@PathVariable(value = "tutorial_id") Long tutorial_id, @RequestBody Comment commentRequest) {
        Comment comment = tutorialRepository.findById(tutorial_id).map(tutorial -> {
            commentRequest.setTutorial(tutorial);
            return commentRepository.save(commentRequest);
        });
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
}
