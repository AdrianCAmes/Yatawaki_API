package com.orchestrator.orchestrator.utils.impl;

import com.orchestrator.orchestrator.model.HiddenRecord;
import com.orchestrator.orchestrator.model.dto.hiddenrecord.request.HiddenRecordChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.hiddenrecord.request.HiddenRecordCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.hiddenrecord.request.HiddenRecordUpdateRequestDto;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.HiddenRecordUtils;
import com.orchestrator.orchestrator.utils.constants.UnlockableStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HiddenRecordUtilsImpl implements HiddenRecordUtils {
    private final GeneralUtils generalUtils;

    @Override
    public HiddenRecord buildDomainFromCreateRequestDto(HiddenRecordCreateRequestDto hiddenRecordCreateRequestDto) throws IllegalAccessException {
        HiddenRecord hiddenRecord = new HiddenRecord();
        hiddenRecord.setStatus(UnlockableStatusConstants.ACTIVE.getValue());
        generalUtils.mapFields(hiddenRecordCreateRequestDto, hiddenRecord);
        return hiddenRecord;
    }

    @Override
    public HiddenRecord buildDomainFromUpdateRequestDto(HiddenRecordUpdateRequestDto hiddenRecordUpdateRequestDto) throws IllegalAccessException {
        HiddenRecord hiddenRecord = new HiddenRecord();
        generalUtils.mapFields(hiddenRecordUpdateRequestDto, hiddenRecord);
        return hiddenRecord;
    }

    @Override
    public HiddenRecord buildDomainFromChangeRequestDto(HiddenRecordChangeRequestDto hiddenRecordChangeRequestDto) throws IllegalAccessException {
        HiddenRecord hiddenRecord = new HiddenRecord();
        generalUtils.mapFields(hiddenRecordChangeRequestDto, hiddenRecord);
        return hiddenRecord;
    }

    @Override
    public HiddenRecordCreateRequestDto buildCreateRequestDtoFromDomain(HiddenRecord hiddenRecord) throws IllegalAccessException {
        HiddenRecordCreateRequestDto hiddenRecordCreateRequestDto = new HiddenRecordCreateRequestDto();
        generalUtils.mapFields(hiddenRecord, hiddenRecordCreateRequestDto);
        return hiddenRecordCreateRequestDto;
    }

    @Override
    public HiddenRecordUpdateRequestDto buildUpdateRequestDtoFromDomain(HiddenRecord hiddenRecord) throws IllegalAccessException {
        HiddenRecordUpdateRequestDto hiddenRecordUpdateRequestDto = new HiddenRecordUpdateRequestDto();
        generalUtils.mapFields(hiddenRecord, hiddenRecordUpdateRequestDto);
        return hiddenRecordUpdateRequestDto;
    }

    @Override
    public HiddenRecordChangeRequestDto buildChangeRequestDtoFromDomain(HiddenRecord hiddenRecord) throws IllegalAccessException {
        HiddenRecordChangeRequestDto hiddenRecordChangeRequestDto = new HiddenRecordChangeRequestDto();
        generalUtils.mapFields(hiddenRecord, hiddenRecordChangeRequestDto);
        return hiddenRecordChangeRequestDto;
    }
}
