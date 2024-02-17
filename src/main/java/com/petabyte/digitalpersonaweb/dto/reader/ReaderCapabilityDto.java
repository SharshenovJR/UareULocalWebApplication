package com.petabyte.digitalpersonaweb.dto.reader;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReaderCapabilityDto {
    private boolean canCapture;
    private boolean canStream;
    private boolean canExtractFeatures;
    private boolean canMatch;
    private boolean canIdentify;
    private boolean hasFingerprintStorage;
    private int indicatorType;
    private boolean hasPowerManagement;
    private boolean hasCalibration;
    private boolean pivCompliant;
    private int[] resolutions;
}
