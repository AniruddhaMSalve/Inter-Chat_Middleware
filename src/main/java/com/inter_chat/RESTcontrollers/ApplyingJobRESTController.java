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

import com.inter_chat.Inter_Chat_Backend.dao.ApplyingJobDAO;
import com.inter_chat.Inter_Chat_Backend.model.ApplyingJob;

@RestController
public class ApplyingJobRESTController {
	@Autowired
	ApplyingJobDAO applyingJobDAO;

	// working
	@GetMapping("/showAllApplyingJob")
	public ResponseEntity<List<ApplyingJob>> showAllApplyingJob() {
		List<ApplyingJob> listApplyingJob = applyingJobDAO.listApplyingJob();
		if (listApplyingJob.size() > 0)
			return new ResponseEntity(listApplyingJob, HttpStatus.OK);
		else
			return new ResponseEntity(listApplyingJob, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@PostMapping("/postApplyingJob")
	public ResponseEntity<String> postApplyingJob(@RequestBody ApplyingJob applyingJob) {
		applyingJob.setAppliedDate(new Date());

		if (applyingJobDAO.postApplyingJob(applyingJob))
			return new ResponseEntity("Applied for job", HttpStatus.OK);
		else
			return new ResponseEntity("Not applied for job", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@PutMapping("/updateApplyingJob/{applyJobId}")
	public ResponseEntity<String> updateApplyingJob(@PathVariable("applyJobId") int applyJobId,
			@RequestBody ApplyingJob applyingJob) {
		ApplyingJob applyJob = applyingJobDAO.getApplyingJob(applyJobId);

		applyJob.setAppliedDate(new Date());
		applyJob.setLoginName(applyingJob.getLoginName());
		applyJob.setJobId(applyingJob.getJobId());

		if (applyingJobDAO.updateApplyingJob(applyJob))
			return new ResponseEntity<String>("Applied Job Updated", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Applied Job Not Updated", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@GetMapping("/deleteApplyingJob/{applyJobId}")
	public ResponseEntity<String> deleteApplyingJob(@PathVariable("applyJobId") int applyJobId) {
		ApplyingJob applyingJob = applyingJobDAO.getApplyingJob(applyJobId);

		if (applyingJobDAO.deleteApplyingJob(applyingJob))
			return new ResponseEntity("Applied Job Deleted", HttpStatus.OK);
		else
			return new ResponseEntity("Applied Job Not Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}