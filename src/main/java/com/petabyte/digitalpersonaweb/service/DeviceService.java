package com.petabyte.digitalpersonaweb.service;

import com.digitalpersona.uareu.Fmd;
import com.digitalpersona.uareu.UareUException;
import com.petabyte.digitalpersonaweb.dto.reader.FingerprintDto;
import com.petabyte.digitalpersonaweb.dto.reader.ReaderDto;

import java.awt.image.BufferedImage;
import java.util.List;

public interface DeviceService {
    List<ReaderDto> getDevices() throws UareUException;
    BufferedImage getCapturedImage() throws UareUException, InterruptedException;
    FingerprintDto capture() throws UareUException, InterruptedException;
    Fmd enroll() throws UareUException, InterruptedException;
    boolean compare() throws UareUException, InterruptedException;
}
