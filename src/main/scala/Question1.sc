
object Question1 {
  case class Segment(var startTime: Int, var endTime: Int)
  
  object Segment {
      implicit def ord[A <: Segment]: Ordering[A] =
        Ordering.by(r => (r.startTime, r.endTime))
    
  }
  
  val s1 = Segment(29, 33)
  val s2 = Segment(10, 12)
  val s3 = Segment(18, 20)
  val s4 = Segment(1, 8)
  val s5 = Segment(5, 7)
  val s6 = Segment(24, 27)
  
  val segments = List(s1, s2, s3, s4, s5, s6)
  
  def mergeGaps(segments: List[Segment], gapSize: Int = 5) = {
    require(gapSize > -1)
    
    segments.sorted.foldLeft(List[Segment]()) { (acc, seg) =>
      if (acc.isEmpty)
        acc :+ seg
      else
        (seg.startTime, acc.last.endTime) match {
          case (a, b) if a - b > gapSize => acc :+ seg
          case (a, b) if a - b < 0 => acc
          case _ => acc.dropRight(1) :+ Segment(acc.last.startTime, seg.endTime)
        }
    }
  }
  
  mergeGaps(segments)
  
  //should work with empty List
  mergeGaps(List[Segment]())

  //gapSize = 0
  mergeGaps(segments, 0)
  
  //gapSize = -1 should fail
  //mergeGaps(segments, -1)
  
  //should work with overlapped/repeating segments
  val overlap = Segment(1, 14)
  val overlapped = List(s1, s2, s3, overlap, s5, s6, s6)
  mergeGaps(overlapped)
  
  
}