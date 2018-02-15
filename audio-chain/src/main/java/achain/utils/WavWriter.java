package achain.utils;

import java.io.*;
import java.util.*;

public class WavWriter {

    static final int HEADER_SIZE = 44;
    
    public void save(String name, int freq, List<Integer> samples) {
        byte data[] = new byte[HEADER_SIZE + samples.size()];
        fillHeader(freq, data, samples.size());
        int i = HEADER_SIZE;
        double coeff = 127 / (deviation(samples) * 2);
        for (Integer v : samples) {
            data[i++] = (byte) (v * coeff + 128);
        }
        writeToFile(name, data);
    }
    
    double deviation(List<Integer> samples) {
        double avg = 0;
        for (Integer v : samples) {
            avg += v;
        }
        avg /= samples.size();
        double dev = 0;
        for (Integer v : samples) {
            double d = v - avg;
            dev += d * d;
        }
        return Math.sqrt(dev / samples.size());
    }
    
    void fillHeader(int freq, byte[] data, int samples) {
        fillString(data, 0, "RIFF");
        fillInteger(data, 4, 4, samples + HEADER_SIZE - 8);
        fillString(data, 8, "WAVEfmt ");
        fillInteger(data, 16, 4, 16); // pcm format
        fillInteger(data, 20, 2, 1); // no compression
        fillInteger(data, 22, 2, 1); // num channels
        fillInteger(data, 24, 4, freq); // sample rate
        fillInteger(data, 28, 4, freq); // byte rate
        fillInteger(data, 32, 2, 1); // bytes per sample
        fillInteger(data, 34, 2, 8); // bit depth
        fillString(data, 36, "data");
        fillInteger(data, 40, 4, samples);
    }
    
    void fillString(byte[] arr, int offset, String s) {
        for (byte b : s.getBytes()) {
            arr[offset] = b;
            offset += 1;
        }
    }
    
    void fillInteger(byte[] arr, int offset, int bytes, int value) {
        for (int i = 0; i < bytes; i++) {
            arr[offset + i] = (byte) (value & 0xFF);
            value >>= 8;
        }
    }
    
    void writeToFile(String name, byte[] data) {
        try (OutputStream out = new FileOutputStream(name)) {
            out.write(data);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
