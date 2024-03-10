package io.springbatch.springbatch;

import javax.sql.DataSource;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;

//@Configuration
public class CustomBatchConfigurer extends BasicBatchConfigurer {

  private DataSource dataSource;

  protected CustomBatchConfigurer(
      BatchProperties properties,
      DataSource dataSource,
      TransactionManagerCustomizers transactionManagerCustomizers) {
    super(properties, dataSource, transactionManagerCustomizers);
    this.dataSource = dataSource;
  }

  @Override
  protected JobRepository createJobRepository() throws Exception {
    JobRepositoryFactoryBean bean = new JobRepositoryFactoryBean();
    bean.setDataSource(dataSource);
    bean.setTransactionManager(getTransactionManager());
    bean.setIsolationLevelForCreate("ISOLATION_REAE_DOMMITTED");
    bean.setTablePrefix("SYSTEM");
    return bean.getObject();
  }
}
