package com.petabyte.digitalpersonaweb.mapper.fingerprint;

import com.digitalpersona.uareu.Fmd;
import com.petabyte.digitalpersonaweb.dto.reader.FingerprintDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FingerprintMapper {

    public FingerprintDto toDto(Fmd fmd) {
        if (fmd == null) return null;
        return FingerprintDto.builder()
                .format(fmd.getFormat())
                .data(fmd.getData())
                .build();
    }

}
