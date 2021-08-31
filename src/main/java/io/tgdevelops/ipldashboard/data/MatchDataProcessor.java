package io.tgdevelops.ipldashboard.data;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import io.tgdevelops.ipldashboard.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

  private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

  @Override
  public Match process(final MatchInput matchInput) throws Exception {

    Match match = new Match();
    match.setId(Long.parseLong(matchInput.getId()));
    match.setCity(matchInput.getCity());
    match.setDate(LocalDate.parse(matchInput.getDate()));
    match.setMatchWinner(matchInput.getWinner());
    match.setPlayerOfMatch(matchInput.getPlayer_of_match());
    match.setResult(matchInput.getResult());
    match.setResultMargin(matchInput.getResult_margin());
    String firstInningsTeam, secondInningsTeam;
    if ("bat".equals(match.getTossDecision())) {
      firstInningsTeam = matchInput.getToss_winner();
      secondInningsTeam = matchInput.getToss_winner().equals(match.getTeam1()) ? matchInput.getTeam2()
          : matchInput.getTeam1();
    } else {
      firstInningsTeam = matchInput.getToss_winner().equals(match.getTeam1())? matchInput.getTeam2()
          : matchInput.getTeam1();
      secondInningsTeam = matchInput.getToss_winner();
    }
    match.setTeam1(firstInningsTeam);
    match.setTeam2(secondInningsTeam);
    match.setTossDecision(matchInput.getToss_decision());
    match.setTossWinner(matchInput.getToss_winner());
    match.setUmpire1(matchInput.getUmpire1());
    match.setUmpire2(matchInput.getUmpire2());
    match.setEliminator(matchInput.getEliminator());
    match.setNeutralVenue(matchInput.getNeutral_venue());
    match.setMethod(matchInput.getMethod());
    match.setVenue(matchInput.getVenue());

    return match;
  }

}
