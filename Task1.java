//1

import java.util.Locale;
import java.util.Scanner;

class Organization {
    private String name;
    private String address;
    private int employeesCount;

    public Organization(String name, String address, int employeesCount) {
        this.name = name;
        this.address = address;
        this.employeesCount = employeesCount;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getEmployeesCount() {
        return employeesCount;
    }

    public void Show() {
        System.out.printf(Locale.US, "Organization: name='%s', address='%s', employees=%d%n",
                name, address, employeesCount);
    }
}

class InsuranceCompany extends Organization {
    private double reserveFund;

    public InsuranceCompany(String name, String address, int employeesCount, double reserveFund) {
        super(name, address, employeesCount);
        this.reserveFund = reserveFund;
    }

    @Override
    public void Show() {
        System.out.printf(Locale.US, "InsuranceCompany: name='%s', address='%s', employees=%d, reserveFund=%.2f%n",
                getName(), getAddress(), getEmployeesCount(), reserveFund);
    }
}

class OilGasCompany extends Organization {
    private double dailyOutputTons;

    public OilGasCompany(String name, String address, int employeesCount, double dailyOutputTons) {
        super(name, address, employeesCount);
        this.dailyOutputTons = dailyOutputTons;
    }

    @Override
    public void Show() {
        System.out.printf(Locale.US, "OilGasCompany: name='%s', address='%s', employees=%d, dailyOutput=%.2f t%n",
                getName(), getAddress(), getEmployeesCount(), dailyOutputTons);
    }
}

class Factory extends Organization {
    private String productType;

    public Factory(String name, String address, int employeesCount, String productType) {
        super(name, address, employeesCount);
        this.productType = productType;
    }

    @Override
    public void Show() {
        System.out.printf(Locale.US, "Factory: name='%s', address='%s', employees=%d, product='%s'%n",
                getName(), getAddress(), getEmployeesCount(), productType);
    }
}

public class Task1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);
        Organization[] items = new Organization[4];

        System.out.println("Enter Organization (name, address, employees):");
        items[0] = new Organization(in.next(), in.next(), in.nextInt());

        System.out.println("Enter InsuranceCompany (name, address, employees, reserveFund):");
        items[1] = new InsuranceCompany(in.next(), in.next(), in.nextInt(), in.nextDouble());

        System.out.println("Enter OilGasCompany (name, address, employees, dailyOutputTons):");
        items[2] = new OilGasCompany(in.next(), in.next(), in.nextInt(), in.nextDouble());

        System.out.println("Enter Factory (name, address, employees, productType):");
        items[3] = new Factory(in.next(), in.next(), in.nextInt(), in.next());

        System.out.println("\n--- Array output ---");
        for (Organization org : items) {
            org.Show();
        }

        in.close();
    }
}