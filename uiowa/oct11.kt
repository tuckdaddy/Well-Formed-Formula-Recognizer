package edu.uiowa

class StackError(): Error()  // subtype of Error, just for Stack
// Stack Interface
interface Stack<T> {
   val debug: String // for debugging, print entire stack
   val size: Int  // the stack's current number of elements
   val isEmpty: Boolean // true if the stack is empty
   fun clear()    // empty the stack entirely
   // both peek() and pop() throw StackError when stack is empty
   fun peek(): T  // return value on top of Stack
   fun pop(): T   // return element on top, plus remove that element
   fun push(item:T)  // push new item on top of stack
   fun hasElem(value:T): Boolean // return true if stack contains value
   }
interface Tokenizer {
   fun addSymbol(s:String) // add symbol pair to Parser
   fun tokenize(input:String): List<String> // break apart
   // input into list of Strings separated by
   // (1) whitespace, and (2) symbols added previously
}

/**** Stack Implementation ****/
class EasyStack<T>(): Stack<T> {
   val store = mutableListOf<T>()  // implements the stack, initially empty
   override val debug: String  get() = "$store"
   override val size: Int  get() = store.size
   override val isEmpty: Boolean  get() = store.isEmpty()
   override fun clear() = store.clear()
   override fun peek(): T {
      if (isEmpty) throw StackError()
      else return store.last()
      }
   override fun pop(): T {
      if (isEmpty) throw StackError()
      val r = store.last()
      store.removeAt(store.size-1)
      return r
      }
   override fun push(item:T) { store.add(item) }
   override fun hasElem(value:T) = store.contains(value)
   }

/**** Implementation of Tokenizer ****/
class EasyTokenizer(): Tokenizer {
   val symbols = mutableListOf<String>()
   override fun addSymbol(s:String) {
      if (s in symbols) return
      // add the symbol to the current list
      symbols.add(s)
      // then sort symbols with longest strings first
      symbols.sortBy { it.length }  // first shortest first,
      symbols.reverse()             // then flip backwards
      }
   private fun subtokenize(input: String): List<String> {
      // input needs to be a string with no whitespace
      val R = mutableListOf<String>()  // used to store output
      var cursor = 0  // start at leftmost character of input
      var currentToken = ""   // we use currentToken in the loop
      while (cursor < input.length) {
         // check if string at cursor matches one of the symbols
         var symbolfound = false
         for (sym in symbols) {
            if (sym.length > input.substring(cursor).length) continue
            if (sym != input.substring(cursor, cursor + sym.length)) continue
            // sym found at start of current cursor position
            if (currentToken.length > 0) {  // pause to add current token
               R.add(currentToken)
               currentToken = ""
               }
            R.add(sym) // then add matching symbol
            symbolfound = true
            cursor += sym.length
            }
         if (!symbolfound) {
            currentToken = currentToken + input[cursor]
            cursor += 1
            }
         }
      if (currentToken.length > 0) R.add(currentToken)
      return R
      }
   override fun tokenize(input: String): List<String> {
      val R = mutableListOf<String>()
      // first break apart input with any whitespace
      val nonwhites = input.trim().split(Regex("\\s+"))
      // next, for each thing in nonwhites, tokenize it by symbols
      for (word in nonwhites) {
         R.addAll(subtokenize(word))
         }
      return R
      }
   }

