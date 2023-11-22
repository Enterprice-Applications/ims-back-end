package lk.ijse.dep11.ims.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseTO implements Serializable {
    @Null(message = "Id should be empty")
    private Integer id;

    @NotNull(message = "Name can not be empty")
    private String name;

    @NotNull(message = "Course duration can not be empty")
    private Integer duration_in_moth;


}
