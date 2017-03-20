package com.lardi.service;

import com.lardi.model.Record;
import com.lardi.repository.RecordRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ellik on 19.03.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class RecordServiceTest {

    @Mock
    RecordRepository recordRepository;

    @InjectMocks
    RecordService recordService;

    @Test
    public void shouldSaveRecord() throws Exception {
        Record record = new Record();
        Record persistedRecord = new Record();
        persistedRecord.setId(3L);
        when(recordRepository.save(record)).thenReturn(persistedRecord);
        Record saved = recordService.save(record);
        assertThat(saved, notNullValue());
        assertThat(saved.getId(), notNullValue());
        assertThat(persistedRecord, equalTo(saved));
        verify(recordRepository).save(record);
    }

    @Test
    public void findOne() throws Exception {
        Record persistedRecord = new Record();
        persistedRecord.setId(8L);
        when(recordRepository.findOne(8L)).thenReturn(persistedRecord);
        Record findRecord = recordService.findOne(8L);
        assertThat(findRecord,notNullValue());
        assertThat(findRecord.getId(),notNullValue());
        assertThat(findRecord, equalTo(persistedRecord));
        verify(recordRepository).findOne(8L);
    }


}