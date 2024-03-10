package io.springbatch.springbatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

public class ExecutionContextTasklet2 implements Tasklet {

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
      throws Exception {
    System.out.println("step2 was executed");

    ExecutionContext jobExCon = contribution.getStepExecution().getJobExecution()
        .getExecutionContext();
    ExecutionContext stepExCon = contribution.getStepExecution().getExecutionContext();

    System.out.println("jobName : " + jobExCon.get("jobName")); //step간 공유가 되어 가져오게됨.
    System.out.println("stepName: " + stepExCon.get("stepName")); //step은 공유가 되지않음.
    //없는 경우 다시 공유되도록 저장.
    String stepName = chunkContext.getStepContext().getStepExecution().getStepName();

    if (stepExCon.get("stepName") == null) {
      stepExCon.put("stepName", stepName);
    }
    return RepeatStatus.FINISHED;
  }
}
