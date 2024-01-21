// Main Part 2 about Evil Wordle
//===============================


object M2 { 

import io.Source
import scala.util._

// ADD YOUR CODE BELOW
//======================


//(1)
def get_wordle_list(url: String) : List[String] = {
    Try(Source.fromURL(url)("ISO-8859-1").getLines().toList).getOrElse(Nil)
}

// val secrets = get_wordle_list("https://nms.kcl.ac.uk/christian.urban/wordle.txt")
// secrets.length // => 12972
// secrets.filter(_.length != 5) // => Nil

//(2)
def removeN[A](xs: List[A], elem: A, n: Int) : List[A] = xs match {
    case Nil => Nil
    case hd::tl if n == 0 => xs
    case hd::tl if xs.count(_ == elem) < n => xs.filter(_ != elem)
    case hd::tl if (hd == elem) => removeN(tl, elem, n - 1)
    case hd::tl => hd :: removeN(tl, elem, n)
}


// removeN(List(1,2,3,2,1), 3, 1)  // => List(1, 2, 2, 1)
// removeN(List(1,2,3,2,1), 2, 1)  // => List(1, 3, 2, 1)
// removeN(List(1,2,3,2,1), 1, 1)  // => List(2, 3, 2, 1)
// removeN(List(1,2,3,2,1), 0, 2)  // => List(1, 2, 3, 2, 1)

// (3)
abstract class Tip
case object Absent extends Tip
case object Present extends Tip
case object Correct extends Tip

//All the letters in a secret that are not correct in the word 
def pool(secret: String, word: String): List[Char] = secret match {
  case "" => Nil
  case s if s.head != word.head => s.head :: pool(s.tail, word.tail)
  case s => pool(s.tail, word.tail)
}

def aux(secret: List[Char], word: List[Char], pool: List[Char]) : List[Tip] = secret match {
    case Nil => Nil
    case s if s.head == word.head => Correct :: aux(s.tail, word.tail, pool)
    case s if pool.contains(word.head) => Present :: aux(s.tail, word.tail, removeN(pool, word.head, 1))
    case s => Absent :: aux(s.tail, word.tail, pool)
}

def score(secret: String, word: String) : List[Tip] = {
    aux(secret.toList, word.toList, pool(secret, word))
}


// score("chess", "caves") // => List(Correct, Absent, Absent, Present, Correct)
// score("doses", "slide") // => List(Present, Absent, Absent, Present, Present)
// score("chess", "swiss") // => List(Absent, Absent, Absent, Correct, Correct)
// score("chess", "eexss") // => List(Present, Absent, Absent, Correct, Correct)

// (4)
def eval(t: Tip) : Int = t match {
    case Correct => 10
    case Present => 1
    case Absent => 0
}

def iscore(secret: String, word: String) : Int = {
    score(secret,word).map(eval).sum
}

//iscore("chess", "caves") // => 21
//iscore("chess", "swiss") // => 20

// (5)
//The lowest score words
def lowest(secrets: List[String], word: String, current: Int, acc: List[String]) : List[String] = secrets match {
    case Nil => acc
    case hd::tl if current == iscore(hd,word) => lowest(tl, word, current, acc :+ hd)
    case hd::tl if iscore(hd, word) < current => lowest(tl, word, current = iscore(hd,word), List[String](hd))
    case hd::tl => lowest(tl, word, current, acc)
}

def evil(secrets: List[String], word: String) : List[String] = {
    lowest(secrets, word, Int.MaxValue, Nil)
}


//evil(secrets, "stent").length
//evil(secrets, "hexes").length
//evil(secrets, "horse").length
//evil(secrets, "hoise").length
//evil(secrets, "house").length

// (6)
def frequencies(secrets: List[String]) : Map[Char, Double] = {
    val total = secrets.length * 5
    val lst_of_sums = for x <- ('a' to 'z').toList yield 1 - secrets.map(_.count(_ == x)).sum.toDouble / total
    ('a' to 'z').toList.zip(lst_of_sums).toMap
}

// (7)
def rank(frqs: Map[Char, Double], s: String) : Double = s match{
    case "" => 0.0
    case _ => frqs.getOrElse(s.head, 0.0) + rank(frqs, s.tail)
}

//It isnt always the case the lowest is 1 - we wanna find the lowest and return all elems that is it
def ranked_evil(secrets: List[String], word: String) : List[String] = {
    val freq = frequencies(secrets)
    val evil_map = evil(secrets, word).map(e => e -> rank(freq, e)).toMap
    val current_max = evil_map.maxBy(_._2)
    evil_map.filter(_._2 == current_max._2).keys.toList
}


}