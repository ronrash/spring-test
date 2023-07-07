package ronrash.springcontainers.model.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PersonRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;
    @NotNull(message = "name should be provided")
    private String name;
    @NotBlank(message = "should be provided")
    private String identityDetail;
    @Positive
    private int age;
}
