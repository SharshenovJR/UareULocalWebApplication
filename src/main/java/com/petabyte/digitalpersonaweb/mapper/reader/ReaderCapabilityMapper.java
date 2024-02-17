package com.petabyte.digitalpersonaweb.mapper.reader;

import com.digitalpersona.uareu.Reader;
import com.petabyte.digitalpersonaweb.dto.reader.ReaderCapabilityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReaderCapabilityMapper {

    public ReaderCapabilityDto toDto(Reader.Capabilities capability) {
        if (capability == null) return null;
        return ReaderCapabilityDto.builder()
                .canCapture(capability.can_capture)
                .canStream(capability.can_stream)
                .canExtractFeatures(capability.can_extract_features)
                .canMatch(capability.can_match)
                .canIdentify(capability.can_identify)
                .hasFingerprintStorage(capability.has_fingerprint_storage)
                .indicatorType(capability.indicator_type)
                .hasPowerManagement(capability.has_power_management)
                .hasCalibration(capability.has_calibration)
                .pivCompliant(capability.piv_compliant)
                .resolutions(capability.resolutions)
                .build();
    }

}
