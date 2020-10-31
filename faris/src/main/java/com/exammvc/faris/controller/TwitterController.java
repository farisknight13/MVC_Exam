package com.exammvc.faris.controller;

import com.exammvc.faris.entity.HashTagModel;
import com.exammvc.faris.entity.TwitterModel;
import com.exammvc.faris.service.HashTagService;
import com.exammvc.faris.service.TwitterService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

@Controller
public class TwitterController {

    @Autowired
    private HashTagService hashSer;

    @Autowired
    private TwitterService twitSer;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("count",hashSer.count());
        return "index";
    }

    @GetMapping("/insert")
    public String insert(@ModelAttribute HashTagModel hashModel,Model model){
        model.addAttribute("hash",hashModel);
        return "InsertHashTag";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id){
        hashSer.deleteById(id);
        return "redirect:/hashTag";
    }

    @GetMapping("/hashTag")
    public String showHashTag(Model model){
        model.addAttribute("hash",hashSer.findAll());
        return "ShowHashTag";
    }

    @GetMapping("/save")
    public String saveHash(HashTagModel hashModel){
        String hash = hashModel.getHashtag();
        hash = hash.toUpperCase();
        hashModel.setHashtag(hash.toUpperCase());
        if(Pattern.matches("[A-Z]+",hash)){
            hashSer.saveHashTag(hashModel);
            return "redirect:/hashTag";
        }
        return "Fail";
    }

    @GetMapping("/twit")
    public String showTwit(Model model){
        model.addAttribute("user",twitSer.findAll());
        return "ShowMessage";
    }

    @GetMapping("/create")
    public String create() throws NotFoundException {
        twitSer.creatUser();
        return "redirect:/twit" ;
    }



    /*@GetMapping("/Top")
    public String topIO(Model model) throws NotFoundException{
        for(long i =1;i<927;i++){
            TwitterModel twit = twitSer.findById(i);
            String mes = twit.getMessage();
            int count = 0;
            char[] ch = new char[mes.length()];
            for (int j = 0; i < mes.length(); i++) {
                ch[j] = mes.charAt(j);
                if(ch[j].equals('I')||ch[j].equals('O')){
                    count++;
                }
            }

        }
        return "ShowTopIO";
    }*/
}
