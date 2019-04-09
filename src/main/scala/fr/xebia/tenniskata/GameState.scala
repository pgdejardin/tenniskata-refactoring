package fr.xebia.tenniskata

sealed trait GameState

final case class Wins(winner: Player) extends GameState
final case class Advantage(higher: Player) extends GameState
case object Deuce extends GameState
final case class Equality(score: Score) extends GameState
final case class Other(game: TennisGame) extends GameState

class ShowState(implicit showScore: Show[TennisScore]) extends Show[GameState] {
  import Score.display

  override def show(state: GameState): String = state match {
    case Wins(player)      => s"${player.name} wins"
    case Advantage(higher) => s"Advantage ${higher.name}"
    case Deuce             => "Deuce"
    case Equality(score)   => s"${display(score)} all"
    case Other(game)       => s"${display(game.playerOne.score)},${display(game.playerTwo.score)}"
  }
}
