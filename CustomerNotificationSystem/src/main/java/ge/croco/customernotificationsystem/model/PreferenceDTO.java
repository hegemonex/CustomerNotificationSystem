package ge.croco.customernotificationsystem.model;

import lombok.Data;

@Data
public class PreferenceDTO {
    private Long id;
    private Boolean emailOptIn;
    private Boolean smsOptIn;
    private Boolean postalOptIn;
}
