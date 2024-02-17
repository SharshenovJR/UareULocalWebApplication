package com.petabyte.digitalpersonaweb.service.impl;

import com.digitalpersona.uareu.*;
import com.digitalpersona.uareu.jni.Dpfj;
import com.petabyte.digitalpersonaweb.dto.reader.FingerprintDto;
import com.petabyte.digitalpersonaweb.dto.reader.ReaderDto;
import com.petabyte.digitalpersonaweb.mapper.fingerprint.FingerprintMapper;
import com.petabyte.digitalpersonaweb.mapper.reader.ReaderMapper;
import com.petabyte.digitalpersonaweb.service.DeviceService;
import com.petabyte.digitalpersonaweb.service.capture.CaptureService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final ReaderMapper readerMapper;
    private final CaptureService captureService;
    private final FingerprintMapper fingerprintMapper;
    private final Dpfj dpfj = new Dpfj();

    private final int FALSE_POSITIVE_RATE = Engine.PROBABILITY_ONE / 100000;
    private final Fmd.Format FORMAT = Fmd.Format.ANSI_378_2004;

    @Getter
    private Fmd fmd;

    @Override
    public List<ReaderDto> getDevices() throws UareUException {
        ReaderCollection collection = UareUGlobal.GetReaderCollection();
        collection.GetReaders();
        return collection.stream()
                .map(readerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BufferedImage getCapturedImage() throws UareUException, InterruptedException {

        Reader.CaptureResult result = makeCapture();
        if (result == null || result.image == null) {
            throw new RuntimeException("Failed to capture fingerprint image!");
        }

        Fid.Fiv view = result.image.getViews()[0];
        BufferedImage bufferedImage = new BufferedImage(view.getWidth(), view.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        bufferedImage.getRaster().setDataElements(0, 0, view.getWidth(), view.getHeight(), view.getImageData());

        return bufferedImage;
    }

    @Override
    public FingerprintDto capture() throws UareUException, InterruptedException {
        Reader.CaptureResult captureResult = makeCapture();

        if (captureResult == null || captureResult.image == null)
            throw new UareUException(96075807);

        Engine engine = UareUGlobal.GetEngine();

        Fmd fmd = engine.CreateFmd(captureResult.image, FORMAT);
        return fingerprintMapper.toDto(fmd);
    }

    @Override
    public Fmd enroll() throws UareUException, InterruptedException {
        Reader.CaptureResult result = makeCapture();

        if (result == null || result.image == null) {
            throw new RuntimeException("Failed to capture fingerprint image!");
        }

        Engine engine = UareUGlobal.GetEngine();

        Fmd fmd = engine.CreateFmd(result.image, FORMAT);

        this.fmd = fmd;
        return fmd;
    }

    @Override
    public boolean compare() throws UareUException, InterruptedException {
        Reader.CaptureResult result = makeCapture();

        if (result == null || result.image == null) {
            throw new RuntimeException("Failed to capture fingerprint image!");
        }

        Engine engine = UareUGlobal.GetEngine();
        Fmd fmd = engine.CreateFmd(result.image, FORMAT);

        Fmd.Format format = fmd.getFormat();
        byte[] data = fmd.getData();

        Fmd imported = dpfj.import_fmd(data, format, format);

        int compared = engine.Compare(this.fmd, 0, imported, 0);
        return compared < FALSE_POSITIVE_RATE;
    }




    public Reader.CaptureResult makeCapture() throws UareUException, InterruptedException {
        ReaderCollection collection = UareUGlobal.GetReaderCollection();
        collection.GetReaders();

        if (collection.isEmpty()) {
            throw new RuntimeException("No fingerprint reader available!");
        }

        Reader reader = collection.get(0);


        return captureService.capture(reader, false);
    }
}
