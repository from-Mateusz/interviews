package cz.mateusz.saltech;

import java.util.HashMap;
import java.util.Map;

public class Anagrams {

    public static String getMaximumXor(String s, String t) {
        // I realize that there are constraints about length/distance of s and t, but I do not know whether every input
        // will be of the same length as previous one
        if(s.length() != s.length()) return "";

        char[] sBits = s.toCharArray();
        char[] tBits = t.toCharArray();

        Map<Integer, Boolean> positionsSwapMemento = new HashMap<>();
        for(int sBitN = 0; sBitN < sBits.length; sBitN++ ) {
            if(sBits[sBitN] != tBits[sBitN]) continue;
            char swappedBit = tBits[sBitN];
            for(int tBitN = sBitN + 1; tBitN < tBits.length; tBitN++) {
                if(swappedBit == tBits[tBitN]) continue;
                if(!positionsSwapMemento.isEmpty() && positionsSwapMemento.containsKey(tBitN)) {
                    continue;
                }
                tBits[sBitN] = tBits[tBitN];
                tBits[tBitN] = swappedBit;
                positionsSwapMemento.put(tBitN, true);
                break;
            }
        }

        int bitsLength = sBits.length;
        StringBuilder result = new StringBuilder();
        for(int bitN = 0; bitN < bitsLength; bitN++) {
            result.append(sBits[bitN] ^ tBits[bitN]);
        }

        return result.toString();
    }

}
