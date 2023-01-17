package exercise.controller;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import exercise.model.Course;
import exercise.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final String DOT = "\\.";

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCorses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping(path = "/{id}/previous")
    public Iterable<Course> getPreviousCourses(@PathVariable long id) {
        String parentPath = this.courseRepository.findById(id).getPath();
        if (parentPath != null) {
            Iterable<Long> parentIds = Stream.of(parentPath.split(DOT)).map(Long::valueOf).collect(Collectors.toList());
            return this.courseRepository.findAllById(parentIds);
        } else {
            return new ArrayList<>();
        }
    }
    // END

}
