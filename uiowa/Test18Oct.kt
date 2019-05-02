package edu.uiowa

import org.junit.Test
import org.junit.Assert.*
import kotlin.math.absoluteValue

class Oct18Test {
    @Test
    fun test1() {
        // try cases with one kind of parenthesis, ( and )
        val P: WFF = EasyWFF()
        P.addParens("(", ")")
        assertTrue(P.recognize("(a + b)"))
        }
    @Test
    fun test2() {
        val P: WFF = EasyWFF()
        P.addParens("(", ")")
        assertFalse(P.recognize("("))
        }
    @Test
    fun test3() {
        val P: WFF = EasyWFF()
        P.addParens("(", ")")
        assertFalse(P.recognize(")"))
        }
    @Test
    fun test4() {
        val P: WFF = EasyWFF()
        P.addParens("(", ")")
        assertFalse(P.recognize(")("))
        }
    @Test
    fun test5() {
        val P: WFF = EasyWFF()
        P.addParens("(", ")")
        assertFalse(P.recognize("(a * b))"))
        }
    @Test
    fun test6() {
        val P: WFF = EasyWFF()
        P.addParens("(", ")")
        assertFalse(P.recognize("(x+y"))
        }
    @Test
    fun test7() {
        val P: WFF = EasyWFF()
        P.addParens("(", ")")
        assertTrue(P.recognize("(a*(2+b)/c)"))
        }
    @Test
    fun test8() {
        val P: WFF = EasyWFF()
        // finally we test with two kinds of parenthesis
        P.addParens("(", ")")
        P.addParens("[","]")
        assertTrue(P.recognize("2+(E[1]*E[2])"))
        }
    @Test
    fun test9() {
        val P: WFF = EasyWFF()
        // finally we test with two kinds of parenthesis
        P.addParens("(", ")")
        P.addParens("[","]")
        assertFalse(P.recognize("([)]"))
        }
    @Test
    fun test10() {
        val P: WFF = EasyWFF()
        // finally we test with two kinds of parenthesis
        P.addParens("(", ")")
        P.addParens("[", "]")
        P.addParens("{", "}")
        P.addParens("(.", ".)")
        assertFalse(P.recognize("t > ( j = 5 .)"))
        }
    @Test
    fun test11() {
        val P: WFF = EasyWFF()
        // finally we test with two kinds of parenthesis
        P.addParens("(", ")")
        P.addParens("[", "]")
        P.addParens("{", "}")
        P.addParens("/*", "*/")
        val expr = "f = {j:B[9], r:(e/3)} /* f is a set */"
        assertTrue(P.recognize(expr))
        }
    }

