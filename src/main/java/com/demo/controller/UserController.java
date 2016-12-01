package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.model.User;
import com.demo.service.UserService;

import java.util.List;

@Controller
public class UserController {
 
	@Autowired
	private UserService userService;
    @RequestMapping(value="index")
    public ModelAndView index(User user){
    	userService.insertUser(user);
        ModelAndView mav=new ModelAndView();
        mav.setViewName("index");
        mav.addObject("user",user);
        return mav;
    }
    @RequestMapping(value="/GoSocket")
    public ModelAndView index(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("GoSocket");
        return mav;
    }

    @RequestMapping(value = "demo")
    @ResponseBody
    public ModelAndView demo(){
        List<User> userList= userService.selectDemo();
        User user=userList.get(0);
        ModelAndView mav=new ModelAndView();
        mav.setViewName("index");
        mav.addObject("user",user);
        return mav;
    }
     
}