public class Passenger {
    static int id = 1; // Static variable to give id for every new Passenger
    String name;
    int age;
    String berthPreference; // Preference Type (L, M, U, SU)
    int passengerId; // Unique ID for every Passenger which is created automatically
    String allotted; // Allotted Type (L, M, U, SU, RAC, WL)
    int seatNumber; // Seat Number
    public Passenger(String name, int age, String berthPreference){
        this.name = name;
        this.age = age;
        this.berthPreference = berthPreference;
        this.passengerId = id++;
        allotted = "";
        seatNumber = -1;
    }
}
