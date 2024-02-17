package com.petabyte.digitalpersonaweb.mapper.reader;

import com.digitalpersona.uareu.Reader;
import com.petabyte.digitalpersonaweb.dto.reader.ReaderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReaderMapper {
    private final ReaderDescriptionMapper readerDescriptionMapper;
    private final ReaderCapabilityMapper readerCapabilityMapper;

    public ReaderDto toDto(Reader reader) {
        if (reader == null) return null;
        return ReaderDto.builder()
                .description(readerDescriptionMapper.toDto(reader.GetDescription()))
                .capability(readerCapabilityMapper.toDto(reader.GetCapabilities()))
                .build();
    }
}
