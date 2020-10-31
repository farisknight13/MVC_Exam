package com.exammvc.faris.repository;

import com.exammvc.faris.entity.HashTagModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashTagRepository extends JpaRepository<HashTagModel, Long> {
}
