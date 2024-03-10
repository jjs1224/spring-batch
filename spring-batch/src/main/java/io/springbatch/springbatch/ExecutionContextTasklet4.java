package io.springbatch.springbatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ExecutionContextTasklet4 implements Tasklet {

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
      throws Exception {
    System.out.println("step4 was executed");
    //step 간 공유값은 jobExecution값을 가져옴.
    System.out.println(
        "name : " + chunkContext.getStepContext().getStepExecution().getJobExecution()
            .getExecutionContext().get("name"));

    return RepeatStatus.FINISHED;
  }
}
