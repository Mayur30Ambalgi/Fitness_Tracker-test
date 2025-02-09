package com.fitness.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitness.exceptionhandler.ResourceNotFoundException;
import com.fitness.model.ActivityLog;
import com.fitness.model.User;
import com.fitness.repository.ActivityLogRespository;
import com.fitness.repository.UserRepository;
import com.fitness.repository.WorkoutPlanRepository;

@Service
public class ActivitylogServiceImpl implements ActivitylogService {
	
	@Autowired
	private  ActivityLogRespository activityLogRepository;
	
	@Autowired
    private  UserRepository userRepository;
   
	@Autowired
    private  WorkoutPlanRepository workoutPlanRepository;

	@Override
	public ActivityLog createActivityLog(Long userId, Long workoutPlanId, ActivityLog activityLog) {
		User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        activityLog.setUser(user);
        activityLog.setWorkoutPlan(workoutPlanRepository.findById(workoutPlanId)
                .orElseThrow(() -> new ResourceNotFoundException("Workout Plan not found with ID: " + workoutPlanId)));
        activityLog.setActivityTime(LocalDateTime.now()); 
        return activityLogRepository.save(activityLog);
	}

	@Override
	public List<ActivityLog> getActivityLogsByUser(Long userId) {
		User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        return activityLogRepository.findByUser(user);
	}

	@Override
	public ActivityLog getActivityLogById(Long id) {
		return activityLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity Log not found with ID: " + id));
	}

	@Override
	public ActivityLog updateActivityLog(Long id, ActivityLog activityLog) {
		ActivityLog existingLog = activityLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity Log not found with ID: " + id));
        existingLog.setDuration(activityLog.getDuration());
        existingLog.setNotes(activityLog.getNotes());
        return activityLogRepository.save(existingLog);
	}

	@Override
	public void deleteActivityLog(Long id) {
		 if (!activityLogRepository.findById(id).isPresent()) {
		        throw new ResourceNotFoundException("Activity Log not found with ID: " + id);
		    }
		    activityLogRepository.deleteById(id);
	}

}
