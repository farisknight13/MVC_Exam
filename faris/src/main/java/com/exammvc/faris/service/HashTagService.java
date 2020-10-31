package com.exammvc.faris.service;

import com.exammvc.faris.entity.HashTagModel;
import com.exammvc.faris.repository.HashTagRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HashTagService {

    @Autowired
    private HashTagRepository hashre;

    public List<HashTagModel> findAll(){
        return hashre.findAll();
    }

    public HashTagModel saveHashTag(HashTagModel hash){
        return hashre.save(hash);
    }

    public String count() {
        Long count = hashre.count();
        String result = "จำนวน HashTag ทั้งหมด "+count+" HashTag";
        return result;
    }

    public HashTagModel findById(Long id) throws NotFoundException {
        return hashre.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
    }

    public void deleteById(Long id) {
        hashre.deleteById(id);
    }
}
