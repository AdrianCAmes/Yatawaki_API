package com.orchestrator.orchestrator.utils;

import com.orchestrator.orchestrator.model.HiddenRecord;
import com.orchestrator.orchestrator.model.dto.hiddenrecord.request.HiddenRecordChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.hiddenrecord.request.HiddenRecordCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.hiddenrecord.request.HiddenRecordUpdateRequestDto;

public interface HiddenRecordUtils {
    HiddenRecord buildDomainFromCreateRequestDto(HiddenRecordCreateRequestDto hiddenRecordCreateRequestDto) throws IllegalAccessException;
    HiddenRecord buildDomainFromUpdateRequestDto(HiddenRecordUpdateRequestDto hiddenRecordUpdateRequestDto) throws IllegalAccessException;
    HiddenRecord buildDomainFromChangeRequestDto(HiddenRecordChangeRequestDto hiddenRecordChangeRequestDto) throws IllegalAccessException;
    HiddenRecordCreateRequestDto buildCreateRequestDtoFromDomain(HiddenRecord hiddenRecord) throws IllegalAccessException;
    HiddenRecordUpdateRequestDto buildUpdateRequestDtoFromDomain(HiddenRecord hiddenRecord) throws IllegalAccessException;
    HiddenRecordChangeRequestDto buildChangeRequestDtoFromDomain(HiddenRecord hiddenRecord) throws IllegalAccessException;
}
