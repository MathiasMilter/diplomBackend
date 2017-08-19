package business.interfaces;

import data.dbDTO.*;
import data.interfaces.PersistenceException;
import data.viewDTO.CourseAddUserInfo;
import data.viewDTO.CourseNameAndShort;
import rest.ElementNotFoundException;
import rest.ValidException;

import javax.ws.rs.container.ContainerRequestContext;
import java.nio.file.AccessDeniedException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Christian on 15-05-2017.
 */
public interface CourseController {

    List<Course> getCourses();

    Course getCourse(String id) throws PersistenceException, ValidException;

    Course createCourse(Course newCourse) throws ValidException, PersistenceException;

    Course updateCourse(Course updatedCourse) throws PersistenceException, ValidException, AccessDeniedException, auth.AccessDeniedException;

    String deleteCourse(String id) throws PersistenceException, ValidException;



    //CoursePlans

    CoursePlan getCoursePlan(String id) throws PersistenceException, ElementNotFoundException, ValidException;

    CoursePlan getCoursePlanTemplate();

    CoursePlan createCoursePlan(CoursePlan coursePlan) throws PersistenceException;

    CoursePlan updateCoursePlan(CoursePlan coursePlan) throws PersistenceException;

    //GoogleCoursePlans
    CoursePlan getGoogleCoursePlan(String id) throws ValidException, PersistenceException;


    Map<String, Collection<User>> getUsers(String courseId) throws ValidException, PersistenceException;

    void addUserToCourse(String courseId, CourseAddUserInfo userRoleInfo) throws ValidException, PersistenceException, ElementNotFoundException;

    void addUserToCourse(String courseID, User user, String role) throws PersistenceException, ValidException;

    void addUserToCourse(String courseId, User user) throws ValidException, PersistenceException, ElementNotFoundException;

    void removeUserFromCourse(String id, CourseAddUserInfo userRoleInfo) throws ValidException, PersistenceException;

    void updateCourseNameAndShort(String id, CourseNameAndShort courseNameAndShort) throws ValidException, PersistenceException;

    void updateUsesGoogleSheet(String id, Boolean usesGoogleSheet) throws ValidException, PersistenceException;

    void updateGoogleSheetId(String courseId, String SheetId) throws ValidException, PersistenceException;

    void syncCoursePlan(String id) throws ValidException, PersistenceException;

    List<Course> getMultiCourses(Collection<String> courseIds) throws ValidException, PersistenceException;

    Course adminAddLink(String id, EmbeddedLink link, ContainerRequestContext userFromContext) throws ValidException, PersistenceException, auth.AccessDeniedException;

    Course adminRemoveLink(String id, EmbeddedLink link, ContainerRequestContext requestContext) throws ValidException, PersistenceException, auth.AccessDeniedException;

    Course adminUpdateLinks(String id, List<EmbeddedLink> links, ContainerRequestContext requestContext) throws ValidException, PersistenceException, auth.AccessDeniedException;
}
