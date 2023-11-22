package lk.ijse.dep11.ims.api;

import lk.ijse.dep11.ims.to.CourseTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
@CrossOrigin
public class CourseHttpController {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = "application/json",consumes = "application/json")
    public CourseTO createTask(@RequestBody @Validated(CourseTO.Create.class) CourseTO course){
        return course;
    }
}
