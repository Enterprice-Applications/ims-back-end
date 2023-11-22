package lk.ijse.dep11.ims.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherTO implements Serializable {
    @Null(message = "Id should be empty")
    private Integer id;
    @NotBlank(message = "name should not be empty")
    private String name;
    @NotBlank(message = "contact should not be empty")
    private String contact;
}
