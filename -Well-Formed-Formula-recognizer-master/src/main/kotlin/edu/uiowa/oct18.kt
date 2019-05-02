package edu.uiowa

// define methods that recognizer will have
interface WFF {
   fun addParens(left:String, right:String) // called to add parentheses
   fun recognize(input:String): Boolean     // true if input is Well Formed
   }

// this class implements the recognizer
class EasyWFF(): WFF {

    val parenTable = mutableListOf<Pair<String, String>>()
    val S = EasyStack<String>() as Stack<String>
    val T = EasyTokenizer() as Tokenizer
    // the addParens method is called to add a pair (left,right) to table
    override fun addParens(left: String, right: String) {
        parenTable.add(left to right)
        T.addSymbol(left)
        T.addSymbol(right)
    }
    fun isPair(s1:String, s2:String):Boolean{
        return parenTable.contains(Pair(s1,s2))
    }
    override fun recognize(input: String): Boolean {

        val splitup = T.tokenize(input)
            for(str in splitup){
                //println("This is parentable first element  ${parenTable[i].first}")
                //println("This is parentable second element  ${parenTable[i].second}")
                //println("This is string $str")
                for(i in 0 until parenTable.size){
                    val first = parenTable[i].first
                    val second = parenTable[i].second
                    if(str.contains(first)){        // if it is a opening brace
                        S.push(str)
                    }
                    //println("This is second element: $second in pair at index: $i ")
                    if(str.contains(second)){
                        if(S.isEmpty) return false
                        if(!isPair(S.pop(),str)){       //if it is a closing brace
                            return false
                        }
                    }
                }
            }
        if(S.isEmpty) return true
        else return false
    }
}

