package kuit.springbasic.web.controller;

import kuit.springbasic.web.dao.UserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserDao userDao;



}
