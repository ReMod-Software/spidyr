package dev.remod.spidyr.processor

import dev.remod.spidyr.types.Symbol
import dev.remod.spidyr.types.Token

object Tokenizer {
    val access = arrayOf("in","pub","pkg")
    val declare = arrayOf("imp", "ext")
    val end = arrayOf("ret","brk","cont")
    val logic = arrayOf("if","else","while","for")
    val defaultTypes = arrayOf("int", "lng", "byt", "str", "flt", "dbl", "bl", "srt")

    fun tokenize(input: Array<String>): Array<Token> {
        val out = ArrayList<Token>()

        for (str in input) {
            val symbol = Symbol.getNameFromValue(str)
            if (str.toFloatOrNull() != null)
                out.add(Token(
                    "NUMBER",
                    str
                ))
            else if (symbol != null)
                out.add(Token(
                    symbol.name
                ))
            else if (str == ";")
                out.add(Token(
                    "NEWLINE"
                ))
            else if (declare.indexOf(str) != -1)
                out.add(Token(
                    "DECLARE",
                    str
                ))
            else if (defaultTypes.indexOf(str) != -1)
                out.add(Token(
                    "BUILTIN",
                    str
                ))
            else if (logic.indexOf(str) != -1)
                out.add(Token(
                    "LOGIC",
                    str
                ))
            else if (access.indexOf(str) != -1)
                out.add(Token(
                    "ACCESS",
                    str
                ))
            else if (end.indexOf(str) != -1)
                out.add(Token(
                    "END",
                    str
                ))
            else out.add(Token(
                "NAME",
                str
            ))
        }

        return out.toTypedArray()
    }
}