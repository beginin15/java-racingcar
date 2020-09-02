package racing.core.domain;

import racing.core.exception.ErrorMessage;
import racing.core.patterns.MoveStrategy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Cars {

    private static final int WINNER = 0;
    private final List<Car> cars;

    private Cars(List<Car> cars) {
        validateList(cars);
        this.cars = cars;
    }

    public static Cars of(String[] namesOfCars) {
        List<Car> participants = Arrays.stream(namesOfCars)
                .map(Car::new)
                .collect(Collectors.toList());
        return new Cars(participants);
    }

    public static Cars of(List<Car> cars) {
        return new Cars(cars);
    }

    private void validateList(List<Car> cars) {
        if (cars.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_CARS.getMessage());
        }
    }

    public Cars runTrial(MoveStrategy movement) {
        List<Car> afterCars = cars.stream()
                .map(car -> car.move(movement))
                .collect(Collectors.toList());
        return new Cars(afterCars);
    }

    public List<Car> getWinners() {
        Car firstCar = getFirstOne();
        return cars.stream()
                .filter(car -> car.isSamePosition(firstCar))
                .collect(Collectors.toList());
    }

    private Car getFirstOne() {
        return cars.stream()
                .sorted()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.EMPTY_CARS.getMessage()));
    }

    public List<Car> getCars() {
        return cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cars cars1 = (Cars) o;
        return cars.equals(cars1.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cars);
    }
}
