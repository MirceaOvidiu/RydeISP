public class Admin extends User {
    public class Angajat extends User {
        private String role;
        private String department;
        private String employeeId;
        private double salary;

        public Angajat() {
            super();
        }

        public Angajat(String id, String name, String email, String password, String employeeId, String role, String department, double salary) {
            super(id, name, email, password);
            this.employeeId = employeeId;
            this.role = role;
            this.department = department;
            this.salary = salary;
        }

        // Getters
        public String getRole() {
            return role;
        }

        public String getDepartment() {
            return department;
        }

        public String getEmployeeId() {
            return employeeId;
        }

        public double getSalary() {
            return salary;
        }

        // Setters
        public void setRole(String role) {
            this.role = role;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public void setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        @Override
        public void afisare() {
            super.afisare();
            System.out.println("Employee ID: " + employeeId);
            System.out.println("Role: " + role);
            System.out.println("Department: " + department);
            System.out.println("Salary: " + salary);
        }
    }
}
