package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // BEGIN
    @GetMapping(path = "/{postId}/comments")
    public Iterable<Comment> getCommentsByPost(@PathVariable long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @GetMapping(path = "/{postId}/comments/{commentId}")
    public Comment getCommentByPost(@PathVariable long postId,
                                    @PathVariable long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
    }

    @PostMapping(path = "/{postId}/comments")
    public void createComment(@PathVariable long postId,
                              @RequestBody Comment comment) {
        Post post = postRepository.findById(postId)
                        .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @PatchMapping(path = "/{postId}/comments/{commentId}")
    public void updateComment(@PathVariable long postId,
                              @PathVariable long commentId,
                              @RequestBody Comment commentDetails) {
        Comment comment = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        comment.setContent(commentDetails.getContent());
        commentRepository.save(comment);
    }

    @DeleteMapping(path = "/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable long postId,
                              @PathVariable long commentId) {
        Comment comment = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        commentRepository.delete(comment);
    }
    // END
}
