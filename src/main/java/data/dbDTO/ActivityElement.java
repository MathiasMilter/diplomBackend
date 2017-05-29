package data.dbDTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.mongodb.morphia.annotations.Reference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 11-05-2017.
 */
@Data
@NoArgsConstructor
public class ActivityElement extends BaseDTO {

    @Reference
    private List<ActivitySubElement> subElements = new ArrayList<>();
    private String hyperLink;
    private String GoogleSheetId;
    private String title;


    private ActivityElementType activityElementType = ActivityElementType.Native; //Pr default native

    public enum ActivityElementType{
        Native, Link, GoogleSheet
    }

}
