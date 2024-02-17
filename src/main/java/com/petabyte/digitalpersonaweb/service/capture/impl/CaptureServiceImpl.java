package com.petabyte.digitalpersonaweb.service.capture.impl;

import com.digitalpersona.uareu.Fid;
import com.digitalpersona.uareu.Reader;
import com.digitalpersona.uareu.UareUException;
import com.petabyte.digitalpersonaweb.service.capture.CaptureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CaptureServiceImpl implements CaptureService {

    @Override
    public Reader.CaptureResult capture(Reader reader, boolean stream) throws UareUException, InterruptedException {
        try {
            reader.Open(Reader.Priority.EXCLUSIVE);
        } catch (UareUException e) {
            if (e.getCode() == 96075807) reader.Close();
            reader.Open(Reader.Priority.EXCLUSIVE);
        }

        boolean fingerDetected = false;
        while (!fingerDetected) {
            System.out.println("Waiting for finger...");
            Reader.Status rs = reader.GetStatus();
            if (rs.status == Reader.ReaderStatus.READY) {
                fingerDetected = true;
            } else {
                Thread.sleep(500); // Adjust sleep time as needed
            }
        }

        return reader.Capture(Fid.Format.ANSI_381_2004, Reader.ImageProcessing.IMG_PROC_DEFAULT, 500, 10000);
    }
}
