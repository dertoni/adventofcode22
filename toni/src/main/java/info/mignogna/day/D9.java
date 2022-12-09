package info.mignogna.day;

import info.mignogna.util.Utils;

import java.io.IOException;
import java.util.*;

import static info.mignogna.day.D9.CoordComponent.X;
import static info.mignogna.day.D9.CoordComponent.Y;

public class D9 {
    public static void main(String[] args) throws IOException {
        d09(Utils.readFile("day09_example1.txt"), "example1_part1", 2);
        d09(Utils.readFile("day09_example1.txt"), "example1_part2", 10);
        d09(Utils.readFile("day09_example2.txt"), "example2_part2", 10);
        d09(Utils.readFile("day09_1.txt"), "original_part1", 2);
        d09(Utils.readFile("day09_1.txt"), "original_part2", 10);
    }

    private static void d09(List<String> input, String qualifier, int numberOfKnots) {
        List<Command> commands = readCommands(input);
        Set<Coord> visitedPositions = new HashSet<>();

        Knot[] knots = new Knot[numberOfKnots];
        for (int i = 0; i < numberOfKnots; i++) {
            knots[i] = Knot.create();
        }
        visitedPositions.add(knots[knots.length - 1]);

        for (Command command : commands) {
            for (int step = 0; step < command.steps(); step++) {
                knots[0] = knots[0].executeCommand(command);
                for (int i = 1; i < numberOfKnots; i++) {
                    knots[i] = knots[i].followKnot(knots[i - 1]);
                }
                visitedPositions.add(knots[knots.length - 1]);
            }
        }

        System.out.println(qualifier + ">>> Number of visited positions: " + visitedPositions.size());
    }

    private static List<Command> readCommands(List<String> input) {
        return input.stream().map(line -> {
            final String[] parts = line.split(" ");
            return new Command(Direction.valueOf(parts[0]), Integer.parseInt(parts[1]));
        }).toList();
    }

    private static class Knot extends Coord {
        public static Knot create() {
            return new Knot(0, 0);
        }

        private Knot(int x, int y) {
            this(new Coord(x, y));
        }

        private Knot(Coord coord) {
            super(coord);
        }

        public Knot executeCommand(Command command) {
            return new Knot(command.execute(this));
        }

        public Knot followKnot(Knot knot) {
            if (knot.equals(this)) {
                return this;
            }

            int diffXAbs = Math.abs(knot.getX() - this.getX());
            int diffYAbs = Math.abs(knot.getY() - this.getY());

            int diffXDirection = (knot.getX() - this.getX()) > 0 ? 1 : -1;
            int diffYDirection = (knot.getY() - this.getY()) > 0 ? 1 : -1;

            boolean diffXLt2 = diffXAbs < 2;
            boolean diffYLt2 = diffYAbs < 2;

            boolean xSame = knot.getX() == this.getX();
            boolean ySame = knot.getY() == this.getY();

            if (diffXLt2 && diffYLt2) {
                return this;
            }

            if (!xSame && !ySame) {
                return new Knot(this.getX() + diffXDirection, this.getY() + diffYDirection);
            }

            if (!diffXLt2 && diffYLt2) {
                return new Knot(this.getX() + diffXDirection, this.getY());
            }

            if (diffXLt2) {
                return new Knot(this.getX(), this.getY() + diffYDirection);
            }

            throw new IllegalStateException();
        }
    }

    private record Command(Direction direction, int steps) {

        public Coord execute(Coord coord) {
            return direction.executeCommand(coord);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Command command = (Command) o;
            return steps == command.steps && direction == command.direction;
        }

        @Override
        public String toString() {
            return "Command{" +
                    "direction=" + direction +
                    ", steps=" + steps +
                    '}';
        }
    }

    enum CoordComponent {
        X, Y
    }


    private enum Direction {
        L(X, -1),
        R(X, 1),
        U(Y, 1),
        D(Y, -1);

        private final CoordComponent coordComponent;
        private final int difference;

        Direction(CoordComponent coordComponent, int difference) {
            this.coordComponent = coordComponent;
            this.difference = difference;
        }

        public Coord executeCommand(Coord coord) {
            if (Objects.requireNonNull(coordComponent) == X) {
                return coord.changeX(difference);
            } else {
                return coord.changeY(difference);
            }
        }

        @Override
        public String toString() {
            return "Direction{" +
                    "coordComponent=" + coordComponent +
                    ", difference=" + difference +
                    '}';
        }
    }

    private static class Coord {
        private final int x;
        private final int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Coord(Coord coord) {
            this.x = coord.getX();
            this.y = coord.getY();
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Coord changeX(int difference) {
            return new Coord(x + difference, y);
        }

        public Coord changeY(int difference) {
            return new Coord(x, y + difference);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Coord coord)) return false;
            return x == coord.x && y == coord.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Coord{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }


}
