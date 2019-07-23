package com.qf.controller;

import com.qf.bean.Role2;
import com.qf.service.RoleService;
import com.qf.util.FastDfsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MyController {
@Resource
private RoleService roleService;

    @RequestMapping("/upload")
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("file")MultipartFile multipartFile){
        Map<String ,Object> map=new HashMap<>();
        try {
            String name=multipartFile.getOriginalFilename();
            String suffix=name.substring(name.lastIndexOf(".")+1);
            byte[] b=multipartFile.getBytes();
            FastDfsUtil fastDfsUtil=new FastDfsUtil();
           String[] s=fastDfsUtil.upload(b,suffix);
           StringBuilder stringBuilder=new StringBuilder("http://10.9.29.111:82/");
           if(s!=null){
               for(int i=0;i<s.length;i++){

                   stringBuilder.append(s[i]);
                   if (i==0){
                       stringBuilder.append("/");
                   }

               }
           }

               String url=stringBuilder.toString();
              map.put("status",200);
              map.put("msg","success");
              map.put("url",url);
              return map;


        } catch (IOException e) {
            e.printStackTrace();
        }
         map.put("status",500);
        map.put("msg","fail");
         return map;

    }


    @RequestMapping("/add")
    @ResponseBody
    public Map<String ,Object> save(Role2 role2,String images){
        Map<String,Object> map=new HashMap<>();
        role2.setRolePicture(images);
        int f=roleService.insert(role2);
        if (f!=0){
            map.put("status","200");
            map.put("msg","success");
        }
        else {
            map.put("msg","fail");
            map.put("status","500");
        }
        return map;
    }

}
