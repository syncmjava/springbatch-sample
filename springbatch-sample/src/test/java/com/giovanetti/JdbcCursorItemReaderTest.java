package com.giovanetti;

import com.giovanetti.support.BatchProperties;
import com.giovanetti.support.JdbcCursorItemReaderSupplier;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestUtilsConfiguration.class})
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class JdbcCursorItemReaderTest {

    @ClassRule
    public final static BatchProperties batchProperties = BatchProperties.getDefault();

    @Inject
    private JdbcCursorItemReader<String> itemReader;

    @BeforeClass
    public static void setupClass() {
        batchProperties.flush();
    }

    @Test
    public void read() {

        assertThat(JdbcCursorItemReaderSupplier.get(itemReader, itemReader::read))
                .isNotEmpty()
                .hasSize(2)
                .containsExactly("1", "2");
    }

}