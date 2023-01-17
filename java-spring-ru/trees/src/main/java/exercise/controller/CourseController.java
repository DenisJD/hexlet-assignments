package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
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
        Course course = this.courseRepository.findById(id);
        String parentPath = course.getPath();
        List<Long> parentIds = getIds(parentPath);

        return this.courseRepository.findAllById(parentIds);
    }

    private List<Long> getIds(String path) {
        if (path != null) {
            return Stream.of(path.split(DOT))
                    .map(Long::valueOf)
                    .collect(Collectors.toList());
        } else {
            return List.of();
        }
    }
    // END

}
