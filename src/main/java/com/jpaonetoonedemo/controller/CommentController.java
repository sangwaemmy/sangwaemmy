/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jpaonetoonedemo.controller;

import com.jpaonetoonedemo.exception.ResourceNotFoundException;
import com.jpaonetoonedemo.model.Comment;
import com.jpaonetoonedemo.repository.CommentRepository;
import com.jpaonetoonedemo.repository.TutorialRepository;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @ApiOperation(value = "Getting all comments by a tutorial")
    @GetMapping("/{tutorialId}/comment")
    public ResponseEntity<Map<String, Object>> getCommentByTutorial(@PathVariable(value = "tutorialId") long tutorialId) {

        if (!tutorialRepository.existsById(tutorialId)) {
            throw new ResourceNotFoundException("The tutorial you provided doesn't exist");
        }
        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);

        Page<List<Comment>> commentsByTutorial = commentRepository.findByTutorialId(tutorialId, firstPageWithTwoElements);

        Pageable paging = PageRequest.of(1, 5);

        Map<String, Object> response = new HashMap<>();
        response.put("tutorials", commentsByTutorial);
        response.put("currentPage", commentsByTutorial.getNumber());
        response.put("totalItems", commentsByTutorial.getTotalElements());
        response.put("totalPages", commentsByTutorial.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @ApiOperation(value = "Get Comments ")
    @GetMapping()
    public ResponseEntity<List<Comment>> getallComment() {
        List<Comment> comments = new ArrayList<>();
        commentRepository.findAll().forEach(comments::add);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a single Comment By ID")
    public ResponseEntity<Comment> getCommentByTutorialId(@PathVariable(value = "id") Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found comment with id " + id));
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping("/{tutorialId}")
    @ApiOperation(value = "Creating a single Comment")
    public ResponseEntity<Comment> createComment(@PathVariable(value = "tutorialId") Long tutorial_id, @RequestBody Comment commentRequest) {
        Comment comment = tutorialRepository.findById(tutorial_id).map(tutorial -> {
            commentRequest.setTutorial(tutorial);
            return commentRepository.save(commentRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorial_id));
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updating  a single Comment")
    public ResponseEntity<Comment> updateComment(@PathVariable(value = "id") long id, @RequestBody Comment commentRequest) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        comment.setContent(commentRequest.getContent());
        return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.OK);

    }
}
