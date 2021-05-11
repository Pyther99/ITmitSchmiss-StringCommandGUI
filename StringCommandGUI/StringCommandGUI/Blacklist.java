package StringCommandGUI;

public class Blacklist {
    public static final int[] blacklistedChars = {
            '꧀', '꧁', '꧂', '꧃',
            '꧄', '꧅', '⸺', '⸻',
            'ⶼ', 'ೠ', 'ఋ', 'ண',
            'ஔ', 'ௗ', 'ூ', 'ெ',
            'ை', 'ொ', 'ோ', 'ௌ',
            'ஹ', 'ಊ', '௵', '௸',
            'ౠ', 'ೊ', 'ೋ', 'ೌ',
            'ೂ', 'ೄ', 'ണ', 'ഞ',
            'ഝ', 'ഛ', 'ഘ', 'ഔ',
            'ഐ', 'ഊ', 'ഈ', 'ആ',
            'അ', 'ൈ', 'ൊ', 'ോ',
            'ൌ', '៘', 'ဪ', 'ෛ',
            'ො', 'ෝ', 'ൺ', 'ඎ',
            'ඐ', 'ꔞ', '෩', 'ෞ',
            'ꦬ', '‱', '෴', '๛',
            '⮨', '⮩', '⮪', '⮫',
            '⮣', '⮢', '⮟', '⮝',
            'ᳬ', 'ᳫ', 'ᳪ', 'ᳩ'
    };

    public static final Character.UnicodeBlock[] blacklistedBlocks = new Character.UnicodeBlock[]{
            Character.UnicodeBlock.TAMIL, Character.UnicodeBlock.TELUGU, Character.UnicodeBlock.KANNADA,
            Character.UnicodeBlock.MALAYALAM, Character.UnicodeBlock.KHMER, Character.UnicodeBlock.SINHALA,
            Character.UnicodeBlock.MYANMAR, Character.UnicodeBlock.JAVANESE, Character.UnicodeBlock.VAI,
            Character.UnicodeBlock.GENERAL_PUNCTUATION
    };

}
