//2

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

abstract class PairAbstract {
    public abstract PairAbstract add(PairAbstract other);
    public abstract PairAbstract subtract(PairAbstract other);
    public abstract PairAbstract multiply(PairAbstract other);
    public abstract PairAbstract divideByInt(int divisor);
}

class MoneyAbstract extends PairAbstract {
    private long hryvnias;
    private int kopecks;

    public MoneyAbstract(long hryvnias, int kopecks) {
        long total = hryvnias * 100L + kopecks;
        this.hryvnias = total / 100L;
        this.kopecks = (int) (total % 100L);
    }

    private long toTotalKopecks() {
        return hryvnias * 100L + kopecks;
    }

    private static MoneyAbstract fromTotalKopecks(long total) {
        return new MoneyAbstract(total / 100L, (int) (total % 100L));
    }

    @Override
    public PairAbstract add(PairAbstract other) {
        MoneyAbstract m = (MoneyAbstract) other;
        return fromTotalKopecks(this.toTotalKopecks() + m.toTotalKopecks());
    }

    @Override
    public PairAbstract subtract(PairAbstract other) {
        MoneyAbstract m = (MoneyAbstract) other;
        return fromTotalKopecks(this.toTotalKopecks() - m.toTotalKopecks());
    }

    @Override
    public PairAbstract multiply(PairAbstract other) {
        MoneyAbstract m = (MoneyAbstract) other;
        double res = (this.toTotalKopecks() / 100.0) * (m.toTotalKopecks() / 100.0);
        return fromTotalKopecks(Math.round(res * 100.0));
    }

    @Override
    public PairAbstract divideByInt(int divisor) {
        if (divisor == 0) throw new ArithmeticException("Division by zero");
        return fromTotalKopecks(Math.round((double) this.toTotalKopecks() / divisor));
    }

    @Override
    public String toString() {
        String sign = (hryvnias == 0 && kopecks < 0) ? "-" : "";
        return String.format(Locale.US, "%s%d,%02d грн", sign, Math.abs(hryvnias), Math.abs(kopecks));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoneyAbstract money)) return false;
        return hryvnias == money.hryvnias && kopecks == money.kopecks;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hryvnias, kopecks);
    }
}

class ComplexAbstract extends PairAbstract {
    private double real;
    private double imag;
    private static final double EPS = 1e-9;

    public ComplexAbstract(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    @Override
    public PairAbstract add(PairAbstract other) {
        ComplexAbstract c = (ComplexAbstract) other;
        return new ComplexAbstract(this.real + c.real, this.imag + c.imag);
    }

    @Override
    public PairAbstract subtract(PairAbstract other) {
        ComplexAbstract c = (ComplexAbstract) other;
        return new ComplexAbstract(this.real - c.real, this.imag - c.imag);
    }

    @Override
    public PairAbstract multiply(PairAbstract other) {
        ComplexAbstract c = (ComplexAbstract) other;
        return new ComplexAbstract(
                this.real * c.real - this.imag * c.imag,
                this.real * c.imag + this.imag * c.real
        );
    }

    @Override
    public PairAbstract divideByInt(int divisor) {
        if (divisor == 0) throw new ArithmeticException("Division by zero");
        return new ComplexAbstract(this.real / divisor, this.imag / divisor);
    }

    @Override
    public String toString() {
        if (imag >= 0) {
            return String.format(Locale.US, "%.2f + %.2fi", real, imag);
        }
        return String.format(Locale.US, "%.2f - %.2fi", real, Math.abs(imag));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplexAbstract complex)) return false;
        return Math.abs(complex.real - real) < EPS && Math.abs(complex.imag - imag) < EPS;
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imag);
    }
}

public class Task2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);

        System.out.println("Enter Money 1 (hryvnias kopecks):");
        MoneyAbstract m1 = new MoneyAbstract(in.nextLong(), in.nextInt());
        System.out.println("Enter Money 2 (hryvnias kopecks):");
        MoneyAbstract m2 = new MoneyAbstract(in.nextLong(), in.nextInt());
        System.out.println("Enter divisor for Money:");
        int mDiv = in.nextInt();

        System.out.println("Enter Complex 1 (real imag):");
        ComplexAbstract c1 = new ComplexAbstract(in.nextDouble(), in.nextDouble());
        System.out.println("Enter Complex 2 (real imag):");
        ComplexAbstract c2 = new ComplexAbstract(in.nextDouble(), in.nextDouble());
        System.out.println("Enter divisor for Complex:");
        int cDiv = in.nextInt();

        PairAbstract[] items = {m1, m2, c1, c2};
        for (PairAbstract item : items) {
            System.out.println("Element: " + item);
        }

        System.out.println("\n--- Money ---");
        System.out.println("Add: " + m1.add(m2));
        System.out.println("Subtract: " + m1.subtract(m2));
        System.out.println("Multiply: " + m1.multiply(m2));
        System.out.println("Divide by int: " + m1.divideByInt(mDiv));
        System.out.println("Equals: " + m1.equals(m2));

        System.out.println("\n--- Complex ---");
        System.out.println("Add: " + c1.add(c2));
        System.out.println("Subtract: " + c1.subtract(c2));
        System.out.println("Multiply: " + c1.multiply(c2));
        System.out.println("Divide by int: " + c1.divideByInt(cDiv));
        System.out.println("Equals: " + c1.equals(c2));

        in.close();
    }
}