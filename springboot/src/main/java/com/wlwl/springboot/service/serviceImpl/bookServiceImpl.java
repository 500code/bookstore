package com.wlwl.springboot.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wlwl.springboot.entity.Book;
import com.wlwl.springboot.mapper.bookMapper;
import com.wlwl.springboot.service.bookService;
import org.springframework.stereotype.Service;


//@Service
//public class UserLoginServiceImpl extends ServiceImpl<loginMapper, User> implements userLoginService {
//}
@Service
public class bookServiceImpl extends ServiceImpl<bookMapper, Book> implements bookService {
}
