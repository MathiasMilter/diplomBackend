package data.dbDTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Id;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Christian on 11-05-2017.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Embedded
public class CourseActivity {
    @NonNull
    private String Description;
    @NonNull ActivityStatus status = ActivityStatus.DRAFT;

    private Date startDate;
    private Date EndDate;

    @Embedded
    private List<ActivityElement> activityElementList = new ArrayList<>();

    public enum ActivityStatus{
        INVISIBLE, DRAFT, VISIBLE
    }
}
