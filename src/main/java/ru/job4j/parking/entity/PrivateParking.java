package ru.job4j.parking.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrivateParking extends AbstractParking {

    private List<Boolean> spots;

    public PrivateParking(int truckSize) {
        super(truckSize);
        spots = new ArrayList<>(Collections.nCopies(100 + 100 * truckSize, false));
    }

    public boolean parkCar(AbstractCar car) {
        int carSize = car.getSize();

        if (carSize == 1) {
            for (int i = 0; i < 100; i++) {
                if (canParkAt(i, carSize)) {
                    parkAt(i, carSize, car);
                    return true;
                }
            }
        } else {
            for (int i = 100; i <= spots.size() - carSize; i++) {
                if (canParkAt(i, carSize)) {
                    parkAt(i, carSize, car);
                    return true;
                }
            }
            for (int i = 0; i <= 100 - carSize; i++) {
                if (canParkAt(i, carSize)) {
                    parkAt(i, carSize, car);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canParkAt(int startIndex, int carSize) {
        for (int i = startIndex; i < startIndex + carSize; i++) {
            if (spots.get(i)) {
                return false;
            }
        }
        return true;
    }

    private void parkAt(int startIndex, int carSize, AbstractCar car) {
        for (int i = startIndex; i < startIndex + carSize; i++) {
            spots.set(i, true);
        }
        car.setParkingSpot(startIndex);
    }

    @Override
    public List<Boolean> getSpot() {
        return this.spots;
    }
}
