package com.snackpub.core.control;//: control/VowelsAndConsonants.java
// Demonstrates the switch statement.

import org.apache.tomcat.util.buf.StringUtils;

import java.util.Random;

import static com.snackpub.core.util.Print.print;
import static com.snackpub.core.util.Print.printnb;

public class VowelsAndConsonants {

    interface Pronounce {
        enum VOWEL implements Pronounce {
            WORDS("a", "e", "i", "o", "u");
            private String[] values;

            VOWEL(String... s) {
                values = s;
            }

            public char randomSelection(char s) {
                if (StringUtils.join(values).contains(String.valueOf(s))) {
                    print("vowel");
                    return s;
                }
                return 0;
            }

        }

        enum SOMETIMES_A_VOWEL implements Pronounce {
            SOMETIMES_A_VOWEL("y", "w");
            private String[] values;

            SOMETIMES_A_VOWEL(String... s) {
                values = s;
            }

            public char randomSelection(char s) {
                if (StringUtils.join(values).contains(String.valueOf(s))) {
                    print("Sometimes a vowel");
                    return s;
                }
                return 0;
            }
        }

        enum CONSONANT implements Pronounce {
            CONSONANT();
            public void randomSelection() {
                print("consonant");
            }
        }

        static void main(String[] args) {
            Random rand = new Random(47);
            for (int i = 0; i < 100; i++) {
                int c = rand.nextInt(26) + 'a';
                printnb((char) c + ", " + c + ": ");

                char c1 = VOWEL.WORDS.randomSelection((char) c);
                if (c1 == 0) {
                    char c2 = SOMETIMES_A_VOWEL.SOMETIMES_A_VOWEL.randomSelection((char) c);
                    if (c2 == 0)
                        Pronounce.CONSONANT.CONSONANT.randomSelection();
                }

//            switch (c) {
//                case 'a':
//                case 'e':
//                case 'i':
//                case 'o':
//                case 'u':
//                    print("vowel");
//                    break;
//                case 'y':
//                case 'w':
//                    print("Sometimes a vowel");
//                    break;
//                default:
//                    print("consonant");
//            }
            }
        }
    }
} /* Output:
y, 121: Sometimes a vowel
n, 110: consonant
z, 122: consonant
b, 98: consonant
r, 114: consonant
n, 110: consonant
y, 121: Sometimes a vowel
g, 103: consonant
c, 99: consonant
f, 102: consonant
o, 111: vowel
w, 119: Sometimes a vowel
z, 122: consonant
...
*///:~
