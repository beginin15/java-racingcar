package racing.core.domain;

import racing.core.dto.Trial;
import racing.core.patterns.MoveStrategy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RacingGame {

    public Cars cars;
    public final int numberOfTrials;

    public RacingGame(String[] namesOfCars, int numberOfTrials) {
        this.cars = Cars.of(namesOfCars);
        this.numberOfTrials = numberOfTrials;
    }

    public List<Trial> run(MoveStrategy movement) {
        return IntStream.range(0, numberOfTrials)
                .mapToObj(n -> {
                    cars = cars.runTrial(movement);
                    return new Trial(cars);
                }).collect(Collectors.toList());
    }

    public List<Car> getWinners() {
        return cars.getWinners();
    }
}
