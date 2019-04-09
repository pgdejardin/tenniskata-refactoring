package fr.xebia.tenniskata

case class Score(value: Int)

object Score {
  def zero: Score = Score(0)

  implicit class ScoreExtension(score: Score) {

    def special: Either[ScoreNotValid.type, TennisScore] = score.value match {
      case 3 => Right(Forty)
      case 2 => Right(Thirty)
      case 1 => Right(Fifteen)
      case 0 => Right(Love)
      case _ => Left(ScoreNotValid)
    }
  }

  def display(score: Score)(implicit ev: Show[TennisScore]): String =
    score.special match {
      case Right(value) => ev.show(value)
      case _ =>
        throw new IllegalArgumentException("Illegal score: " + score.value)
    }
}

sealed trait TennisScore
case object Forty extends TennisScore
case object Thirty extends TennisScore
case object Fifteen extends TennisScore
case object Love extends TennisScore

class ShowScore extends Show[TennisScore] {
  override def show(a: TennisScore): String = a.toString
}
