package com.studys.web.controllers;

import com.studys.web.models.Course;
import com.studys.web.models.User;
import com.studys.web.other.Session;
import com.studys.web.repo.CourseRepository;
import com.studys.web.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/")
    public String home(Model model) {
        if(Session.activeuser == null)
        {
            model.addAttribute("title", "StudyS - Домашня сторінка");
            return "home";
        }
        else
        {
            return "redirect:/courses";
        }
    }

    @GetMapping("/courses")
    public String courses(Model model)
    {
        if(Session.activeuser == null)
        {
            return ("redirect:/");
        }
        else
        {
            model.addAttribute("title", "StudyS - Курси");
            model.addAttribute("activeuser", Session.activeuser);
            Iterable<Course> courses = courseRepository.findAll();
            List<Course> list = new ArrayList<>();
            courses.forEach(list::add);
            List<Course> res = new ArrayList<>();
            for(int i = 0; i < list.size(); i++)
            {
                if(list.get(i).getCreator() == Session.activeuser.getUserid())
                {
                    res.add(list.get(i));
                }
            }
            model.addAttribute("courses", res);
            return "courses";
        }
    }

    @GetMapping("/create_course")
    public String createcourse(Model model)
    {
        if(Session.activeuser == null)
        {
            return ("redirect:/");
        }
        else
        {
            model.addAttribute("title", "StudyS - Створення курсу");
            model.addAttribute("activeuser", Session.activeuser);
            return "createcourse";
        }
    }

    @PostMapping("/create_course")
    public String AddCourse(@RequestParam String title, Model model)
    {
        Course course = new Course(title, "", Session.activeuser.getUserid());
        courseRepository.save(course);
        return "redirect:/";
    }

    @GetMapping("/registration")
    public  String registration(Model model) {
        model.addAttribute("title", "StudyS - Регістрація");
        return  "registration";
    }

    @PostMapping("/registration")
    public String AddUser(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam String type, Model model)
    {
        User user = new User(username, email, password, type);
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "StudyS - Вхід");
        return  "login";
    }

    @PostMapping("/login")
    public String SetUser(@RequestParam String email, @RequestParam String password, Model model)
    {
        Iterable<User> users = userRepository.findAll();
        List<User> result = new ArrayList<User>();
        users.forEach(result::add);
        for (int i = 0; i < result.size(); i++)
        {
            if(result.get(i).getEmail().equals(email) && result.get(i).getPassword().equals(password))
            {
                Session.activeuser = result.get(i);
                return "redirect:/courses";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/courses/{id}")
    public String incourse(@PathVariable(value = "id") long courseid, Model model) {
        model.addAttribute("title", "StudyS - Вхід");
        return  "redirect:/courses/" + courseid + "/lessons";
    }

    @GetMapping("/courses/{id}/lessons")
    public String lessons(@PathVariable(value = "id") long courseid, Model model) {
        model.addAttribute("title", "StudyS - Уроки");

        return  "alllessons";
    }

    @GetMapping("/courses/{id}/lessons/{lessonid}")
    public String lessons(@PathVariable(value = "id") long courseid, @PathVariable(value = "lessonid") long lessonid, Model model) {
        model.addAttribute("title", "StudyS - 1. Побудова классів");
        return  "lesson";
    }
}
