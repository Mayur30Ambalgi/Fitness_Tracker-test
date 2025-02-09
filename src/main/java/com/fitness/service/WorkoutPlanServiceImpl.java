package com.fitness.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitness.exceptionhandler.ResourceNotFoundException;
import com.fitness.model.User;
import com.fitness.model.WorkoutPlan;
import com.fitness.repository.UserRepository;
import com.fitness.repository.WorkoutPlanRepository;

@Service
public class WorkoutPlanServiceImpl implements WorkoutplanService {
	@Autowired
	private  WorkoutPlanRepository workoutPlanRepository;
	
	@Autowired
    private  UserRepository userRepository;

	@Override
	public WorkoutPlan createWorkoutPlan(WorkoutPlan workoutPlan, Long userId) {
		 User user = userRepository.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
	        workoutPlan.setUser(user);
	        return workoutPlanRepository.save(workoutPlan);
	}

	@Override
	public List<WorkoutPlan> getAllWorkoutPlans() {
		return workoutPlanRepository.findAll();
	}

	@Override
	public WorkoutPlan getWorkoutPlanById(Long id) {
		return workoutPlanRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Workout Plan not found with ID: " + id));
	}

	@Override
	public WorkoutPlan updateWorkoutPlan(Long id, WorkoutPlan updatedWorkoutPlan) {
		 WorkoutPlan existingPlan = getWorkoutPlanById(id);
	        existingPlan.setName(updatedWorkoutPlan.getName());
	        existingPlan.setDescription(updatedWorkoutPlan.getDescription());
	        existingPlan.setDurationInDays(updatedWorkoutPlan.getDurationInDays());
	        existingPlan.setDifficulty(updatedWorkoutPlan.getDifficulty());
	        return workoutPlanRepository.save(existingPlan);
	}

	@Override
	public void deleteWorkoutPlan(Long id) {
		WorkoutPlan workoutPlan = getWorkoutPlanById(id);
        workoutPlanRepository.delete(workoutPlan);
	}

}
