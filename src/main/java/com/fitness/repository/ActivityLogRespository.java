package com.fitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitness.model.ActivityLog;
import com.fitness.model.User;


@Repository
public interface ActivityLogRespository extends JpaRepository<ActivityLog, Long> {
	 List<ActivityLog> findByUser(User user);
}
