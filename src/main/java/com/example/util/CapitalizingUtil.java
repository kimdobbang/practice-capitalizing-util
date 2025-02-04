package com.example.util;

public final class CapitalizingUtil {
    // 문자열 입력 받으면 -> 그중에서 A-Z, a-z, _ 구간 및 그 주변만 인식해서 Capitalizing 하는 메서드
    // 유틸 클래스는 설계적으로 final class에 private로 한다.(final 상속방지, private 인스턴스 생성 방지. 우리가 제공하는 static 함수 외 사용 불가)

    private CapitalizingUtil() {

    }

    public static String Capitalize(String string) {
        char[] origin = string.toCharArray();
        char[] target = new char[origin.length];

        // 대문자 되는 조건
        //  : 이전에 띄어쓰기나 언더스코어, 이번엔 알파벳
        // 소문자 되는 조건
        //  : 이전에 띄어쓰기나 언더스코어가 아니었고, 이번에 알파벳
        // _ -> 띄어쓰기로

        // 이전에 띄어쓰기나 언더스코어 -> 이번엔 대문자 (65)
        // 이전에 띄어쓰기나 언더스코어가 아니면 -> 이번엔 소문자 (97)
        // _ -> 띄어쓰기로

        target[0] = toUpperCase(origin[0]);
        for (int i = 1; i < origin.length; i++) {
            if (origin[i] == '_') {
                target[i] = ' ';
                continue;
            }
            if (origin[i - 1] == '_') {
                target[i] = Character.toUpperCase(origin[i]);
            } else {
                target[i] = toLowerCase(origin[i]);
            }
        }

        return String.valueOf(target);

    }

    private static char toUpperCase(char ch) {
        return (char) (ch & ~0x20); // ~0X20 또는 ~32
    }

    private static char toLowerCase(char ch) {
         // ' ' = 32
//        char c = 'A' | ' ';
//        return (char) (ch | 0x20);
        return (char) (ch | ' ');
    }


}
