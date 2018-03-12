package com.tuyue.webModules.schoolAndPerson.bean;

import java.util.List;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/8.
 */
public class PersonZiYuanBean {
    private Integer id;
    private String name;
    private String path;
    private Integer parentId;
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    private List<Person> children;
    public static class Person{
        private Integer id;
        private String name;
        private String path;
        private Integer parentId;
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

        public Person(Integer id, String name, String path, Integer parentId) {
            this.id = id;
            this.name = name;
            this.path = path;
            this.parentId = parentId;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }

        public Person() {
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

    public List<Person> getChildren() {
        return children;
    }

    public void setChildren(List<Person> children) {
        this.children = children;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public PersonZiYuanBean(Integer id, String name, String path, Integer parentId, List<Person> children) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.parentId = parentId;
        this.children = children;
    }

    @Override
    public String toString() {
        return "PersonZiYuanBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", parentId=" + parentId +
                ", children=" + children +
                '}';
    }

    public PersonZiYuanBean() {
    }
}
