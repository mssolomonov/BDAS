package com;

public class Obfuscator {
    private static String source =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-=_+[]{}\\|;':\",.?`~";
    private static String target =
            "Q~5b@cg$A8^Zd*ef)WS.0st9?GD\"C6:RF|VT[lm{no+BY-4HN=U3}xy]zJ\\2;MI'1p_qrKO,7L(Pa&hi%jk#uv!XE`w";

    public static String process(char[] src, int start, int length, boolean deobfuscate) {
        if (deobfuscate) {
            return deobfuscate(src, start, length);
        } else {
            return obfuscate(src, start, length);
        }
    }

    public static String obfuscate(char[] src, int start, int length) {
        for (int i = start; i < start + length; i++) {
            char c = src[i];
            int index = source.indexOf(c);
            if (index != -1) {
                src[i] = target.charAt(index);
            }
        }
        return new String(src);
    }

    public static String deobfuscate(char[] src, int start, int length) {
        for (int i = start; i < start + length; i++) {
            char c = src[i];
            int index = target.indexOf(c);
            if (index != -1) {
                src[i] = source.charAt(index);
            }
        }
        return new String(src);
    }
}
