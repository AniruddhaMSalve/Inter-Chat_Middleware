package com.inter_chat.RESTcontrollers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inter_chat.Inter_Chat_Backend.dao.JobDAO;
import com.inter_chat.Inter_Chat_Backend.model.ApplyJob;
import com.inter_chat.Inter_Chat_Backend.model.Job;
import com.inter_chat.Inter_Chat_Backend.model.Job;

@RestController
public class JobRESTController {
	@Autowired
	JobDAO jobDAO;

	// Working
	@PostMapping(value = "/addJob")
	public ResponseEntity<String> addJob(@RequestBody Job job) {
		if (jobDAO.addJob(job)) {
			return new ResponseEntity<String>("Job Added- Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Job insert failed", HttpStatus.NOT_FOUND);
		}
	}

	// Working
	@GetMapping(value = "/showAllJob")
	public ResponseEntity<List<Job>> listJob() {
		List<Job> listJobs = jobDAO.listAllJobs();
		if (listJobs.size() != 0) {
			return new ResponseEntity<List<Job>>(listJobs, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Job>>(listJobs, HttpStatus.NOT_FOUND);
		}
	}

	// Working
	@PutMapping(value = "/updateJob/{jobId}")
	public ResponseEntity<String> updateJob(@PathVariable("jobId") int jobId, @RequestBody Job job) {
		System.out.println("Updating Job " + jobId);
		Job ujob = jobDAO.getJob(jobId);
		if (ujob == null) {
			System.out.println("Job with jobId " + jobId + " Not Found");
			return new ResponseEntity<String>("Update Job Failue", HttpStatus.NOT_FOUND);
		}

		ujob.setJobId(jobId);
		ujob.setCompany(job.getCompany());
		ujob.setLastDateToApply(job.getLastDateToApply());
		ujob.setJobDescription(job.getJobDescription());
		ujob.setJobDesignation(job.getJobDesignation());
		ujob.setLocation(job.getLocation());
		jobDAO.updateJob(ujob);
		return new ResponseEntity<String>("Update Job Success", HttpStatus.OK);
	}

	// working
	@GetMapping("/deleteJob/{jobId}")
	public ResponseEntity<String> deleteJob(@PathVariable("jobId") int jobId) {
		Job job = jobDAO.getJob(jobId);

		if (jobDAO.deleteJob(job))
			return new ResponseEntity<String>("Job Deleted", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Job Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Working
	@GetMapping(value = "/getJob/{jobId}")
	public ResponseEntity<Job> getJob(@PathVariable("jobId") int jobId) {
		System.out.println("Get Job " + jobId);
		Job job = jobDAO.getJob(jobId);
		if (job == null) {
			System.out.println("Job retrieval failure..");
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}
	}

	// Working
	@PostMapping(value = "/applyJob")
	public ResponseEntity<String> addJob(@RequestBody ApplyJob applyJob) {
		applyJob.setAppliedDate(new Date());
		if (jobDAO.applyJob(applyJob)) {
			return new ResponseEntity<String>("ApplyJob Added- Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Applyjob failed", HttpStatus.NOT_FOUND);
		}
	}

	// Working
	@GetMapping(value = "/listAppliedJobs/{loginName}")
	public ResponseEntity<List<ApplyJob>> listAppliedJob(@PathVariable("loginName") String loginName) {
		List<ApplyJob> listAppliedJobs = jobDAO.getAllAppliedJobDetails(loginName);
		if (listAppliedJobs.size() != 0) {
			return new ResponseEntity<List<ApplyJob>>(listAppliedJobs, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<ApplyJob>>(listAppliedJobs, HttpStatus.NOT_FOUND);
		}
	}
}