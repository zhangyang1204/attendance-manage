package cn.zyity.attendancemanage.service;

import cn.zyity.attendancemanage.bean.Course;
import cn.zyity.attendancemanage.dao.ICourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    ICourseDao courseDao;

    public void addCourse(Course course) {
        courseDao.addCourse(course);
    }

    public List<Course> findAllCourse() {
        return courseDao.findAllCourse();
    }

    public List<Course> findAllCourseByTeacherId(int id) {
        return courseDao.findAllCourseByTeacherId(id);
    }
    public List<Course> findAllCourseByStudentId(int id) {
        return courseDao.findAllCourseByStudentId(id);
    }
}
