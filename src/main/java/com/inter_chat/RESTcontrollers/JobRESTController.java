//Complete and Working

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
import com.inter_chat.Inter_Chat_Backend.model.Job;

@RestController
public class JobRESTController {
	@Autowired
	JobDAO jobDAO;

	// working
	@GetMapping("/showAllJob")
	public ResponseEntity<List<Job>> showAllJob() {
		List<Job> listJob = jobDAO.listJob();

		if (listJob.size() > 0)
			return new ResponseEntity(listJob, HttpStatus.OK);
		else
			return new ResponseEntity(listJob, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@PostMapping("/postJob")
	public ResponseEntity<String> postJob(@RequestBody Job job) {
		job.setLastDate(new Date());
		job.setPostedDate(new Date());

		if (jobDAO.postJob(job))
			return new ResponseEntity("Job Posted", HttpStatus.OK);
		else
			return new ResponseEntity("Job Not Posted", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@PutMapping("/updateJob/{jobId}")
	public ResponseEntity<String> updateJob(@PathVariable("jobId") int jobId, @RequestBody Job job) {
		Job job1 = jobDAO.getJob(jobId);
		job1.setCompanyName(job.getCompanyName());
		job1.setDesignation(job.getDesignation());
		job1.setJobDesc(job.getJobDesc());
		job1.setJobName(job.getJobName());
		job1.setLastDate(new Date());
		job1.setPostedDate(new Date());
		job1.setSalary(job.getSalary());
		job1.setStatus(job.getStatus());

		if (jobDAO.updateJob(job1))
			return new ResponseEntity("Job Updated", HttpStatus.OK);
		else
			return new ResponseEntity("Job Not Updated", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@GetMapping("/deleteJob/{jobId}")
	public ResponseEntity<String> deleteJob(@PathVariable("jobId") int jobId) {
		Job job = jobDAO.getJob(jobId);

		if (jobDAO.deleteJob(job))
			return new ResponseEntity("Job Deleted", HttpStatus.OK);
		else
			return new ResponseEntity("Job Not Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}