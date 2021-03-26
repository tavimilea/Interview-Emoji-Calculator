package com.company.EmojiNumberGenerator;

import java.util.*;
import java.util.stream.Collectors;

public class EmojiNumberGenerator {
    private static final Map<String,String> EMOJI_MAPPING;
    static {
        EMOJI_MAPPING = new HashMap<String, String>();
        EMOJI_MAPPING.put("100", "\uD83D\uDCAF");
        EMOJI_MAPPING.put("10", "\uD83D\uDD1F");
        EMOJI_MAPPING.put("8", "\uD83C\uDFB1");
        EMOJI_MAPPING.put("0", "0️");
        EMOJI_MAPPING.put("1", "1️");
        EMOJI_MAPPING.put("2", "2️");
        EMOJI_MAPPING.put("3", "3️");
        EMOJI_MAPPING.put("4", "4️");
        EMOJI_MAPPING.put("5", "5️");
        EMOJI_MAPPING.put("6", "6️");
        EMOJI_MAPPING.put("7", "7️");
        EMOJI_MAPPING.put("8", "8️");
        EMOJI_MAPPING.put("9", "9️");
        EMOJI_MAPPING.put("-", "➖");
    }
    private static List<String> emojiReplacementOrdKeys;

    public EmojiNumberGenerator() {
        if(emojiReplacementOrdKeys == null) {
            String[] keysArray = EMOJI_MAPPING.keySet().toArray(new String[0]);
            emojiReplacementOrdKeys = Arrays.stream(keysArray)
                    .sorted(new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return o1.length() > o2.length() ? -1 : 0;
                        }
                    })
                    .collect(Collectors.toList());
        }
    }

    /**
     * Replaces every occurrence of a key from EMOJI_MAPPING in input string with the corresponding emoji value.
     * This is done by first ordering the keys inside emojiReplacementOrdKeys.
     * Because of searching ordered keys(by size) in number, it will always replace the least number of emoji possible.
     * @param number a number to be transformed to emoji number
     * @return string representing emoji number
     */
    public String generateEmojiNumber(long number) {
        String emojiNumber = Long.toString(number);
        for(String key : emojiReplacementOrdKeys) {
            emojiNumber = emojiNumber.replace(key, EMOJI_MAPPING.get(key));
        }
        return emojiNumber;
    }
}
