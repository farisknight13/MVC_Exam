package com.exammvc.faris.service;


import com.exammvc.faris.entity.HashTagModel;
import com.exammvc.faris.entity.TwitterModel;
import com.exammvc.faris.repository.HashTagRepository;
import com.exammvc.faris.repository.TwitterRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class TwitterService {

    @Autowired
    private TwitterRepository twitre;

    @Autowired
    private HashTagService hashSer;

    @Autowired
    private HashTagRepository hashre;

    public List<TwitterModel> findAll(){
        return twitre.findAll();
    }

    public TwitterModel saveTwit(TwitterModel twit){
        return twitre.save(twit);
    }

    public TwitterModel findById(Long id) throws NotFoundException {
        return twitre.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
    }

    public String randomString(){
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while ((salt.length()<30)) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return  saltStr;
    }

    public void creatUser() throws NotFoundException {
        for (int i=1;i<927;i++){
            TwitterModel twit = new TwitterModel();
            String mes = randomString();
            Long n = ThreadLocalRandom.current().nextLong(1,hashre.count());
            HashTagModel hash = hashSer.findById(n);
            String message = mes+"#"+(hash.getHashtag());
            twit.setMessage(message);
            saveTwit(twit);
        }
    }

}
