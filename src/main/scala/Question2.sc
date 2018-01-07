object Question2 {
  val stringMap = Map(
    0 -> List("A", "B", "C"),
    1 -> List("D", "E", "F"),
    2 -> List("G", "H", "I", "J"),
    3 -> List("K", "L", "M"),
    4 -> List("N", "O", "P"),
    5 -> List("Q", "R", "S", "T"),
    6 -> List("U", "V"),
    7 -> List("W"),
    8 -> List("X", "Y"),
    9 -> List("Z")
  )
  
  val path = "/Users/ASRagab/Documents/Interview/YieldMo/words.txt"
  
  val file = io.Source.fromFile(path) //replace with your path
  val dictionary = file.getLines().toList //dictionary is assumed to be sorted, it is a dictionary after all
  val number = "9012" //Probably should not be a numeric value since leading zero is common
  
  def isSorted(as: List[Int]): Boolean = {
    def loop(n: Int): Boolean =
      if (n+1 > as.length) true
      else if (n > n +1) false
      else loop(n+1)
    
    loop(0)
  }
  
  def findValidWords(dictionary: List[String], number: String) = {
    val possibleLetters = number.flatMap(c => stringMap.get(c.asDigit))
      .flatten.toList.map(_.toLowerCase)
    
    def isValidWord(possibleLetters: List[String], word: String): Boolean = {
      val mask = possibleLetters.zipWithIndex.filter {
        case (c, i) => word.contains(c)
      }.map(_._2)
      
      //set size wanted because same letter may be used more than once TODO: Improve
      isSorted(mask) && mask.lengthCompare(word.toSet.size) == 0
    }
  
    dictionary.filter { word => isValidWord(possibleLetters, word) }
  }
  
  findValidWords(dictionary, number)
  findValidWords(dictionary, "05")
  
  //TODO: Consider storing dictionary in B-Tree or Hash and reduce lookup time by extracting first letter
}