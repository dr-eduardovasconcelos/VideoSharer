package com.devcaotics.videoSharer.webSocket;

import org.springframework.stereotype.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/rest")
@CrossOrigin("https://videosharer.onrender.com/")
public class Controller{
  @GetMapping("/ids")
  public String init(){ 
    
      return ServiceHolder.getIds();
    
  }

}
