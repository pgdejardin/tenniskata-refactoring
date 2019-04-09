package fr.xebia.tenniskata

case class TennisGame(playerOne: Player, playerTwo: Player)

object TennisGame {

  def apply(playerOneName: String, playerTwoName: String): TennisGame =
    new TennisGame(
      Player(playerOneName, Score.zero),
      Player(playerTwoName, Score.zero)
    )

  implicit class TennisGameExtension(game: TennisGame) {
    def playerOneScores: TennisGame =
      game.copy(playerOne = game.playerOne.scores)

    def playerTwoScores: TennisGame =
      game.copy(playerTwo = game.playerTwo.scores)

    def score(implicit showState: Show[GameState]): String =
      showState.show(giveState(game))
  }

  private def giveState(game: TennisGame): GameState =
    hasWinner(game)
      .orElse(hasAdvantage(game))
      .orElse(hasDeuce(game))
      .orElse(equality(game))
      .getOrElse(Other(game))

  private def hasWinner(game: TennisGame): Option[Wins] =
    (game.playerOne winsOver game.playerTwo).orElse(game.playerTwo winsOver game.playerOne)

  private def hasAdvantage(game: TennisGame): Option[Advantage] =
    (game.playerOne hasAdvantageOver game.playerTwo).orElse(game.playerTwo hasAdvantageOver game.playerOne)

  private def hasDeuce(game: TennisGame): Option[Deuce.type] =
    (game.playerOne deuce game.playerTwo).orElse(None)

  private def equality(game: TennisGame): Option[Equality] =
    if (game.playerOne.score == game.playerTwo.score) Some(Equality(game.playerOne.score)) else None

}
