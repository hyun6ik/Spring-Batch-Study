package com.hyun6ik.batchstudy.job;

import com.hyun6ik.batchstudy.config.BatchTestConfig;
import com.hyun6ik.batchstudy.core.repository.PlainTextRepository;
import com.hyun6ik.batchstudy.core.repository.ResultTextRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@SpringBatchTest
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {PlainTextJobConfig.class, BatchTestConfig.class})
public class PlainTextJobConfigTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private PlainTextRepository plainTextRepository;

    @Autowired
    private ResultTextRepository resultTextRepository;

    @AfterEach
    public void tearDown() {
        plainTextRepository.deleteAll();
        resultTextRepository.deleteAll();
    }

    @Test
    void success_giveNoPlainText() throws Exception {
        //when
        final JobExecution execution = jobLauncherTestUtils.launchJob();
        //then
        assertThat(execution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
        assertThat(resultTextRepository.count()).isEqualTo(0);

    }
}
