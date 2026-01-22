package cognivanta.cognivantaSite.dto;

import jakarta.validation.constraints.NotNull;

public class ApplicationStatusUpdateDTO {

    @NotNull(message = "Status is required")
    private String status;

    public ApplicationStatusUpdateDTO() {
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}
