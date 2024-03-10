package io.springbatch.springbatch;

import java.util.Date;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobLauncherController {

  @Autowired
  private Job job;

  @Autowired
  private JobLauncher jobLauncher;
  @Autowired
  private BasicBatchConfigurer basicBatchConfigurer; //비동기식

  @PostMapping("/batch")
  public String lanunch(@RequestBody Member member)
      throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
    JobParameters jobParameters = new JobParametersBuilder()
        .addString("id", member.getId())
        .addDate("date", new Date())
        .toJobParameters();
    //application.yml 에 job.enabled = false로 해야 수동으로 이것을 실행한다.
//    jobLauncher.run(job, jobParameters); //동기식
    //비동기식
    SimpleJobLauncher jobLauncher1 = (SimpleJobLauncher) basicBatchConfigurer.getJobLauncher();
    jobLauncher1.setTaskExecutor(new SimpleAsyncTaskExecutor());
    jobLauncher1.run(job, jobParameters);

    return "batch completed";
  }

}
