import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "UserDTO", description = "DTO for transferring user data")
public class UserDTO {
    @Schema(description = "User ID")
    private Long id;

    @Schema(description = "User's username")
    private String username;

    @Schema(description = "User's email address")
    private String email;
    // Add any other necessary fields
}
