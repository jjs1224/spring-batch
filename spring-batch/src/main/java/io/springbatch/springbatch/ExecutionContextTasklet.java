package io.springbatch.springbatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

public class ExecutionContextTasklet implements Tasklet {

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
      throws Exception {
    System.out.println("step1 was execute!!");
    ExecutionContext jobExCon = contribution.getStepExecution().getJobExecution()
        .getExecutionContext();
    ExecutionContext stepExCon = contribution.getStepExecution().getExecutionContext();

    String jobName = chunkContext.getStepContext().getStepExecution().getJobExecution()
        .getJobInstance().getJobName(); //공유됨
    String stepName = chunkContext.getStepContext().getStepExecution().getStepName(); //공유되지않음.

    //공유가 가능하도록 첫번째 step에서 정의하여 저장함.
    if (jobExCon.get("jobName") == null) {
      jobExCon.put("jobName", jobName);
    }
    if (stepExCon.get("stepName") == null) {
      stepExCon.put("stepName", jobName);
    }
    return RepeatStatus.FINISHED;
  }
}
