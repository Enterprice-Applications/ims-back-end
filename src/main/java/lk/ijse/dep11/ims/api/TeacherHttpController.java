package lk.ijse.dep11.ims.api;

import lk.ijse.dep11.ims.to.TeacherTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/teachers")
public class TeacherHttpController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = "application/json", consumes = "application/json")
    public TeacherTO createTeacher(@RequestBody @Validated TeacherTO details){
        System.out.println("Create Teacher");
        return new TeacherTO();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value ="/{teacherId}", consumes = "application/json")
    public void updateTeacher(@PathVariable("teacherId") int teacherID,
                              @RequestBody @Validated TeacherTO teacherUpdate){
        System.out.println("update Teacher");
    }

    @GetMapping(produces = "application/json")
    public List<TeacherTO> getAllTeacher(){
        System.out.println("getAllTeacher");
        return new ArrayList<>();
    }

    @GetMapping(value ="/{teacherId}", produces = "application/json")
    public void getTeacher(){
        System.out.println("getTeacher");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{teacherId}")
    public void deleteTeacher(@PathVariable String teacherId){
        System.out.println("Delete Teacher");
    }
}
