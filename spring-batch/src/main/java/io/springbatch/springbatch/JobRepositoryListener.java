package io.springbatch.springbatch;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * job 실행에서 .listener(class명) 으로 정의.
 */
@Component
public class JobRepositoryListener implements JobExecutionListener {

  @Autowired
  private JobRepository jobRepository;

  @Override
  public void beforeJob(JobExecution jobExecution) {

  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    String jobName = jobExecution.getJobInstance().getJobName();
    JobParameters jobParamters = new JobParametersBuilder()
        .addString("requetDate", "20210102").toJobParameters(); //실행시 키값.

    JobExecution lastJobExectuion = jobRepository.getLastJobExecution(jobName, jobParamters);
    if (lastJobExectuion != null) {
      for (StepExecution execution : lastJobExectuion.getStepExecutions()) {
        BatchStatus status = execution.getStatus();
        System.out.println("status : " + status);
        ExitStatus exitStatus = execution.getExitStatus();
        System.out.println("exitStatus : " + exitStatus);
        String stepName = execution.getStepName();
        System.out.println("stepName : " + stepName);
      }
    }
  }
}
