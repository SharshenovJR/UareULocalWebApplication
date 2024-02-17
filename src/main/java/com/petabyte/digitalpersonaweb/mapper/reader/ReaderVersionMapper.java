package com.petabyte.digitalpersonaweb.mapper.reader;

import com.digitalpersona.uareu.Reader;
import com.petabyte.digitalpersonaweb.dto.reader.ReaderVersionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReaderVersionMapper {

    public ReaderVersionDto toDto(Reader.VersionInfo versionInfo) {
        if (versionInfo == null) return null;
        return ReaderVersionDto.builder()
                .major(versionInfo.major)
                .minor(versionInfo.minor)
                .maintenance(versionInfo.maintenance)
                .build();
    }

}
