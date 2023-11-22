package lk.ijse.dep11.ims.api;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lk.ijse.dep11.ims.to.TeacherTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/teachers")
public class TeacherHttpController {
    private final HikariDataSource pool;

    public TeacherHttpController(){
        HikariConfig config = new HikariConfig();
        config.setUsername("root");
        config.setPassword("root");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/dep11_ims");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.addDataSourceProperty("maximumPoolSize",10);
        pool = new HikariDataSource(config);
    }
    @PreDestroy
    public void destroy(){
        pool.close();

    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = "application/json", consumes = "application/json")
    public TeacherTO createTeacher(@RequestBody @Validated TeacherTO teacher) {
        try (Connection connection = pool.getConnection()) {

            PreparedStatement stm = connection.prepareStatement("INSERT INTO teacher (name, contact) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, teacher.getName());
            stm.setString(2, teacher.getContact());
            stm.executeUpdate();
            ResultSet generatedKeys = stm.getGeneratedKeys();
            generatedKeys.next();
            int id = generatedKeys.getInt(1);
            teacher.setId(id);
            return teacher;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value ="/{teacherId}", consumes = "application/json")
    public void updateTeacher(@PathVariable("teacherId") int teacherID,
                              @RequestBody @Validated TeacherTO teacherUpdate){
        try(Connection connection = pool.getConnection()){
            PreparedStatement stmExist = connection.prepareStatement("SELECT * FROM teacher WHERE id = ?");
            stmExist.setInt(1,teacherID);
            if(!stmExist.executeQuery().next()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task Not Found");
            }
            PreparedStatement stm = connection.prepareStatement("UPDATE teacher SET name = ?, contact = ? WHERE id=?");
            stm.setString(1, teacherUpdate.getName());
            stm.setString(2, teacherUpdate.getContact());
            stm.setInt(3, teacherID);
            stm.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping(produces = "application/json")
    public List<TeacherTO> getAllTeacher(){
        try (Connection connection = pool.getConnection()) {
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM teacher ORDER BY id");
            List<TeacherTO> teacherList = new LinkedList<>();
            while (rst.next()) {
                int id = rst.getInt("id");
                String name = rst.getString("name");
                String contact = rst.getString("contact");
                teacherList.add(new TeacherTO(id, name, contact));
            }
            return teacherList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value ="/{teacherId}", produces = "application/json")
    public TeacherTO getTeacher(@PathVariable int teacherId){
        try(Connection connection = pool.getConnection()){
            PreparedStatement stmExist = connection.prepareStatement("SELECT * FROM teacher WHERE id = ?");
            stmExist.setInt(1,teacherId);
            ResultSet rst = stmExist.executeQuery();
            if(rst.next()){
                int id = rst.getInt("id");
                String name = rst.getString("name");
                String contact = rst.getString("contact");
                return new TeacherTO(id, name, contact);
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher Not Found");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{teacherId}")
    public void deleteTeacher(@PathVariable int teacherId){
        try(Connection connection = pool.getConnection()){
            PreparedStatement stmExist = connection.prepareStatement("SELECT * FROM teacher WHERE id = ?");
            stmExist.setInt(1,teacherId);
            if(!stmExist.executeQuery().next()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task Not Found");
            }
            PreparedStatement stm = connection.prepareStatement("DELETE FROM teacher WHERE id=?");
            stm.setInt(1, teacherId);
            stm.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
