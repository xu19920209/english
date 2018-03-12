package com.tuyue.webModules.course.bean;

import com.tuyue.pojo.Bhour;
import com.tuyue.webModules.schoolAndPerson.bean.PersonZiYuanBean;

import java.util.List;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/9.
 */
public class CourseTreeBean {
    private Integer id;
    private String name;
    private String path;
    private List<course> children;
    public static class course{
        private Integer id;
        private String name;
        private String path;
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public course(Integer id, String name, String path) {
            this.id = id;
            this.name = name;
            this.path = path;
        }

        public course() {
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public CourseTreeBean(Integer id, String name, String path, List<course> children) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.children = children;
    }

    public List<course> getChildren() {
        return children;
    }

    public void setChildren(List<course> children) {
        this.children = children;
    }

    public CourseTreeBean() {
    }
}
