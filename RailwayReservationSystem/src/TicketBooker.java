import java.util.*;
public class TicketBooker {
    // Total - 63 Berths(18 Lower, 18 Middle, 18 Upper, 9 SideUpper)
    // RAC - 18 (For 9 SideLower)
    // Waiting List - 10
    static int availableLowerBerths = 1; // Normally 18
    static int availableMiddleBerths = 1; // Normally 18
    static int availableUpperBerths = 1; // Normally 18
    static int availableSideUpperBerths = 1; // Normally 9
    static int availableRACTickets = 1; // Normally 18
    static int availableWaitingList = 1; // Normally 10

    // ArrayList for BookedTicketList
    static List<Integer> bookedTicketList = new ArrayList<>();

    //Queue for RAC, WL
    static Queue<Integer> racList = new LinkedList<>();
    static Queue<Integer> waitingList = new LinkedList<>();

    // List for Berths
    static List<Integer> lowerBerthsPositions = new ArrayList<>(Arrays.asList(1)); // Normally 1,2,..,18
    static List<Integer> middleBerthsPositions = new ArrayList<>(Arrays.asList(1)); // Normally 1,2,..,18
    static List<Integer> upperBerthsPositions = new ArrayList<>(Arrays.asList(1)); // Normally 1,2,..,18
    static List<Integer> sideUpperBerthsPositions = new ArrayList<>(Arrays.asList(1)); // Normally 1,2,..,9
    static List<Integer> racsPositions = new ArrayList<>(Arrays.asList(1)); // Normally 1,2,..,18
    static List<Integer> waitingListsPositions = new ArrayList<>(Arrays.asList(1)); // Normally 1,2,..,10

    // Map of Passenger ID's to Passengers
    static Map<Integer, Passenger> passengerMap = new HashMap<>();

    // Book Ticket
    public void bookTicket(Passenger p, int berthInfo, String allottedBerth){
        p.seatNumber = berthInfo; // Assign the seatNumber
        p.allotted = allottedBerth; // Assign the allotted Berth(L or M or U or SU)
        passengerMap.put(p.passengerId,p); // Add Passenger to the Map
        bookedTicketList.add(p.passengerId); // Add the Passenger ID to bookedTicket List
        System.out.println("---------Booked  Successfully---------"); // Message to Print
    }
    public void addToRAC(Passenger p, int racInfo, String allottedRAC){
        p.seatNumber = racInfo; // Assign the seatNumber
        p.allotted = allottedRAC; // Assign the allotted RAC
        passengerMap.put(p.passengerId,p); // Add Passenger to the Map
        racList.add(p.passengerId); // Add the Passenger ID to the RAC List
        // Remove 1 Position from racsPositions ArrayList
        racsPositions.remove(0);
        // Decrement 1 value from availableRACTickets variable
        availableRACTickets--;
    }
    public void addToWaitingList(Passenger p, int wlInfo, String allottedWL){
        p.seatNumber = wlInfo; // Assign the seatNumber
        p.allotted = allottedWL; // Assign the allotted WL
        passengerMap.put(p.passengerId,p); // Add Passenger to the Map
        waitingList.add(p.passengerId); // Add the Passenger ID to the Waiting List
        // Remove 1 Position from waitingListsPositions ArrayList
        waitingListsPositions.remove(0);
        // Decrement 1 value from availableWaitingList variable
        availableWaitingList--;
    }
    public void cancelTicket(int passengerId){
        // Get the Details of the Passenger is going to Cancel his journey
        Passenger p = passengerMap.get(passengerId); // Get the id from Map and store it in Passenger p
        passengerMap.remove(Integer.valueOf(passengerId)); // Remove the Passenger from Map
        bookedTicketList.remove(Integer.valueOf(passengerId)); // Remove the Passenger from BookedTicket List

        int positionBooked = p.seatNumber; // Take the booked Position which is now free
        System.out.println("Cancelled Successfully"); // Print Message

        // Add the free Position to the corresponding types of list (L, M, U, SU)
        if(p.allotted.equals("L")){ // If it is SU
            availableLowerBerths++; // Increment 1 value in availableLowerBerths variable
            lowerBerthsPositions.add(positionBooked); // Add 1 Position in lowerBerthsPositions ArrayList
        }
        else if(p.allotted.equals("M")){ // If it is M
            availableMiddleBerths++; // Increment 1 value in availableMiddleBerths variable
            middleBerthsPositions.add(positionBooked); // Add 1 Position in middleBerthsPositions ArrayList
        }
        else if(p.allotted.equals("U")){ // If it is U
            availableUpperBerths++; // Increment 1 value in availableUpperBerths variable
            upperBerthsPositions.add(positionBooked); // Add 1 Position in upperBerthsPositions ArrayList
        }
        else{ // If it is SU
            availableSideUpperBerths++; // Increment 1 value in availableSideUpperBerths variable
            sideUpperBerthsPositions.add(positionBooked); // Add 1 Position in sideUpperBerthsPositions ArrayList
        }

        // Check if RAC is there or not
        if(racList.size() > 0){ // If RAC is there
            System.out.println("Passenger from RAC moved to Berth");
            // Get the RAC Passenger
            Passenger passengerFromRAC = passengerMap.get(racList.poll()); // Get the Passenger from Top of the Map
            int positionRAC = passengerFromRAC.seatNumber; // Get the RAC's Passenger's Seat Number
            racsPositions.add(positionRAC); // Add 1 Position in racsPositions ArrayList
            racList.remove(Integer.valueOf(passengerFromRAC.passengerId)); // Remove the Passenger from RAC List
            availableRACTickets++; // Increment 1 value in availableRACTickets variable

            // Move WL to the RAC List if WL is there
            if(waitingList.size() > 0){ // If WL is there
                System.out.println("Passenger from WL moved to RAC");
                // Get the WL Passenger
                Passenger passengerFromWaitingList = passengerMap.get(waitingList.poll()); // Get the Passenger from Top of the Map
                int positionWL = passengerFromWaitingList.seatNumber; // Get the WL Passenger's Seat Number
                waitingListsPositions.add(positionWL); // Add 1 Position in waitingListsPositions ArrayList
                waitingList.remove(Integer.valueOf(passengerFromWaitingList.passengerId)); // Remove the Passenger from Waiting List

                // Move WL Passenger to RAC List
                passengerFromWaitingList.seatNumber = racsPositions.get(0);
                passengerFromWaitingList.allotted = "RAC";
                racsPositions.remove(0);
                racList.add(passengerFromWaitingList.passengerId);

                availableWaitingList++; // Increase WL variable
                availableRACTickets--; // Decrease WL variable
            }
            Main.bookTicket(passengerFromRAC); // Move RAC to the Free Position that is cancelled
        }
    }
    public void showAvailableTickets(){
        System.out.println("--------------------------------------");
        System.out.println("---------Available Tickets are---------");
        System.out.println("--------------------------------------");
        System.out.println("Available Lower Berths : " +availableLowerBerths);
        System.out.println("Available Middle Berths : " +availableMiddleBerths);
        System.out.println("Available Upper Berths : " +availableUpperBerths);
        System.out.println("Available Side Upper Berths : " +availableSideUpperBerths);
        System.out.println("Available RAC's : " +availableRACTickets);
        System.out.println("Available Waiting List : " +availableWaitingList);
        System.out.println("--------------------------------------");
    }
    public void showBookedTickets(){
        if(passengerMap.size() == 0) { // If there is No Booked Ticket
            System.out.println("No Passenger is Booked Yet");
            return;
        }
        // If there are Passengers
        System.out.println("--------------Passenger List--------------");
        for(Passenger p : passengerMap.values()){
            System.out.println("Passenger Id : " +p.passengerId);
            System.out.println("Passenger Name : " +p.name);
            System.out.println("Passenger Age : " +p.age);
            System.out.println("Booking Status : " +p.seatNumber + p.allotted);
            System.out.println("--------------------------------------");
        }
    }
}
