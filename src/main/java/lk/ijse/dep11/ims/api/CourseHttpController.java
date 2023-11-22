package lk.ijse.dep11.ims.api;

import lk.ijse.dep11.ims.to.CourseTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/course")
@CrossOrigin
public class CourseHttpController {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = "application/json",consumes = "application/json")
    public String createCourse(@RequestBody  CourseTO course){
        return "Post Mapping";
    }

    @PatchMapping(value = "/{CourseId}",consumes = "application/json")
    public void updateCourse(@PathVariable int CourseId, @RequestBody CourseTO course){
        System.out.println("Patch mapping");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{CourseId}")
    public void deleteCourse(@PathVariable int CourseId){
        System.out.println("Delete Mapping");
    }
    @GetMapping(value = "/{CourseId}",produces = "application/json")
    public CourseTO getCoursetDetails(){
        System.out.println("Get Mapping");
        return null;
    }

    @GetMapping(produces = "application/json")
    public List<CourseTO> getAllStudents(){
        System.out.println("Get All Mapping");
        return new ArrayList<>();
    }
}
