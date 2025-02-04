package com.example.util

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.string.shouldStartWith
import io.kotest.matchers.shouldBe

/*
상속 불가: class (kotlin) -> public final class (java) // java도 final 해도 되는건 final 붙이잔걸 기본적으로 권장함. 코틀린은 그래서 기본으로 final임
상속 허용: open class (kotlin) -> public class (java)
상속 부분 허용: sealed 클래스 (자바에서 sealed) // default와 같은데 이건 같은 클래스에서만 되나, 이건 다됨 스프링에서 sealed많이 쓰면 테스트할때 불편
 */

// 맨뒤 {}지우고 () 안에 {}는 body인데 특정한 람다함수 같은것임.따지고보면 생성자 같은것
class CapitalizingUtilTest : FreeSpec({
    // BDD (행위 기반 표현) -> 서비스 만들 때
    // 유틸 함수 -> 함수 이름으로 해도 되지 않을까? (상황에 따라)
    "capitalize(str) : String" - {
        "Input UPPER_CASE_WITH_UNDERSCORES" - {
            val testString = "EXAMPLE_STRING"
            val result = CapitalizingUtil.Capitalize(testString)

            // 이제 한 항목씩 테스트
            "첫 글자는 대문자여야 한다."{
                result shouldStartWith "E"
            }

            "두 번째 글자는 소문자여야 한다." {
                result[1] shouldBe  'x'
            }

            "언더스코어가 있던 위치는 모두 띄어쓰기로 바뀐다." {
                // testString.indices.forEach{...}
                (0..<testString.length).forEach {
                    if (testString[it] == '_') {
                        result[it] shouldBe ' '
                    }
                }
            }

            "원래는 이거 하나만 하면 됩니다." {
                result shouldBeEqual "Example String"
            }
        }

        "input _STARTING_WITH_ONE_UNDERSCORE" -{
            "..." {
                // ...
            }
        }
    }

    "다른 테스트 컨테이터(다른 테스트 유형)" - {
        "테스트 항목1" {}
        "테스트 항목2" {}
    }

})