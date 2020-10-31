package com.exammvc.faris.repository;

import com.exammvc.faris.entity.HashTagModel;
import com.exammvc.faris.entity.TwitterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwitterRepository extends JpaRepository<TwitterModel, Long> {
}
