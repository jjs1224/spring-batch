package io.springbatch.springbatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ExecutionContextTasklet3 implements Tasklet {

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
      throws Exception {
    System.out.println("step3 was executed");
    //예외발생시 다음단계로 넘어가지 않고 실패가 되어 재실행이 가능하게됨.
    Object name = chunkContext.getStepContext().getStepExecution().getExecutionContext()
        .get("name");

    if (name == null) {
      chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext()
          .put("name", "user1"); //실패시 실패값을 저장하도록함.
      throw new RuntimeException(("step3 was failed"));
    }
    return RepeatStatus.FINISHED;
  }
}
