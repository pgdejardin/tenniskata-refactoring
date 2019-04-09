package fr.xebia.tenniskata

case class Player(name: String, score: Score)

object Player {
  implicit class PlayerExtension(player: Player) {
    def scores: Player = player.copy(score = Score(player.score.value + 1))

    def winsOver(other: Player): Option[Wins] =
      if (player.score.value >= 4 && player.score.value >= other.score.value + 2)
        Some(Wins(player))
      else None

    def hasAdvantageOver(other: Player): Option[Advantage] =
      if (player.score.value >= 4 && player.score.value >= other.score.value + 1)
        Some(Advantage(player))
      else None

    def deuce(other: Player): Option[Deuce.type] =
      if (player.score.value >= 3 && player.score.value == other.score.value)
        Some(Deuce)
      else None
  }
}
