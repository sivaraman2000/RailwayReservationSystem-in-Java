import java.util.*;
public class Main {
    public static void bookTicket(Passenger p){
        TicketBooker booker = new TicketBooker(); // Object for TicketBooker Class

        // If there is no WL, - All Tickets are filled - No Tickets Available
        if(TicketBooker.availableWaitingList == 0){
            System.out.println("No Tickets Available");
            System.out.println("Better Luck Next Time");
            return;
        }

        // Check if Preferred Berth is available
        if((p.berthPreference.equals("L") && TicketBooker.availableLowerBerths > 0) ||
           (p.berthPreference.equals("M") && TicketBooker.availableMiddleBerths > 0) ||
           (p.berthPreference.equals("U") && TicketBooker.availableUpperBerths > 0) ||
           (p.berthPreference.equals("SU") && TicketBooker.availableSideUpperBerths > 0)
        ){
            System.out.println("Preferred Berth Available");
            if(p.berthPreference.equals("L")){ // If Preferred Berth is Lower
                System.out.println("Lower Berth Given");
                // Call the bookTicket() method
                booker.bookTicket(p, (TicketBooker.lowerBerthsPositions.get(0)),"L");
                // Remove 1 Position from lowerBerthsPositions ArrayList
                TicketBooker.lowerBerthsPositions.remove(0);
                // Decrement 1 value of the availableLowerBerths variable
                TicketBooker.availableLowerBerths--;
            }
            else if(p.berthPreference.equals("M")){ // If Preferred Berth is Middle
                System.out.println("Middle Berth Given");
                // Call the bookTicket() method
                booker.bookTicket(p, (TicketBooker.middleBerthsPositions.get(0)),"M");
                // Remove 1 Position from middleBerthsPositions ArrayList
                TicketBooker.middleBerthsPositions.remove(0);
                // Decrement 1 value of the availableMiddleBerths variable
                TicketBooker.availableMiddleBerths--;
            }
            else if(p.berthPreference.equals("U")){ // If Preferred Berth is Upper
                System.out.println("Upper Berth Given");
                // Call the bookTicket() method
                booker.bookTicket(p, (TicketBooker.upperBerthsPositions.get(0)),"U");
                // Remove 1 Position from upperBerthsPositions ArrayList
                TicketBooker.upperBerthsPositions.remove(0);
                // Decrement 1 value of the availableUpperBerths variable
                TicketBooker.availableUpperBerths--;
            }
            else if(p.berthPreference.equals("SU")){ // If Preferred Berth is Side Upper
                System.out.println("Side Upper Berth Given");
                // Call the bookTicket() method
                booker.bookTicket(p, (TicketBooker.sideUpperBerthsPositions.get(0)),"SU");
                // Remove 1 Position from sideUpperBerthsPositions ArrayList
                TicketBooker.sideUpperBerthsPositions.remove(0);
                // Decrement 1 value of the availableSideUpperBerths variable
                TicketBooker.availableSideUpperBerths--;
            }
        }
        // If Preferred Berth is Not Available -> Book the Available Berth
        else if(TicketBooker.availableLowerBerths > 0){ // If Lower Berth is available
            System.out.println("Preferred Berth Not Available");
            System.out.println("Lower Berth Given");
            // Call the bookTicket() method
            booker.bookTicket(p, (TicketBooker.lowerBerthsPositions.get(0)),"L");
            // Remove 1 Position from lowerBerthsPositions ArrayList
            TicketBooker.lowerBerthsPositions.remove(0);
            // Decrement 1 value of the availableLowerBerths variable
            TicketBooker.availableLowerBerths--;
        }
        else if(TicketBooker.availableMiddleBerths > 0){ // If Middle Berth is available
            System.out.println("Preferred Berth Not Available");
            System.out.println("Middle Berth Given");
            // Call the bookTicket() method
            booker.bookTicket(p, (TicketBooker.middleBerthsPositions.get(0)),"M");
            // Remove 1 Position from middleBerthsPositions ArrayList
            TicketBooker.middleBerthsPositions.remove(0);
            // Decrement 1 value of the availableMiddleBerths variable
            TicketBooker.availableMiddleBerths--;
        }
        else if(TicketBooker.availableUpperBerths > 0){ // If Upper Berth is available
            System.out.println("Preferred Berth Not Available");
            System.out.println("Upper Berth Given");
            // Call the bookTicket() method
            booker.bookTicket(p, (TicketBooker.upperBerthsPositions.get(0)),"U");
            // Remove 1 Position from upperBerthsPositions ArrayList
            TicketBooker.upperBerthsPositions.remove(0);
            // Decrement 1 value of the availableUpperBerths variable
            TicketBooker.availableUpperBerths--;
        }
        else if(TicketBooker.availableSideUpperBerths > 0){ // If Side Upper Berth is available
            System.out.println("Preferred Berth Not Available");
            System.out.println("Side Upper Berth Given");
            // Call the bookTicket() method
            booker.bookTicket(p, (TicketBooker.sideUpperBerthsPositions.get(0)),"SU");
            // Remove 1 Position from sideUpperBerthsPositions ArrayList
            TicketBooker.sideUpperBerthsPositions.remove(0);
            // Decrement 1 value from availableSideUpperBerths variable
            TicketBooker.availableSideUpperBerths--;
        }

        // If No Berth is Available -> Move to RAC
        else if (TicketBooker.availableRACTickets > 0){
            System.out.println("Berth is Full");
            System.out.println("RAC is Available");
            // Call the addToRAC() method
            booker.addToRAC(p, (TicketBooker.racsPositions.get(0)),"RAC");
            // Message to Print
            System.out.println("------Added to RAC Successfully------");
        }
        // If RAC is full -> Move to Waiting List
        else { // If WL is available ie) TicketBooker.availableWaitingList > 0
            System.out.println("Berth is Full");
            System.out.println("RAC is also Full");
            System.out.println("Added to Waiting List");
            // Call the addToRAC() method
            booker.addToWaitingList(p,(TicketBooker.waitingListsPositions.get(0)),"WL");
            // Message to Print
            System.out.println("--Added to Waiting List Successfully--");
        }
    }
    public static void cancelTicket(int id){
        TicketBooker booker = new TicketBooker(); // Object for TicketBooker Class
        if (!booker.passengerMap.containsKey(id)) // If given ID is not Present in Map
            System.out.println("Not a Valid Passenger");
        else // If given ID is Present in Map
            booker.cancelTicket(id); // Call the cancelTicket() function with given id
    }
    public static void main(String[] args) {
        TicketBooker booker = new TicketBooker(); // Object for TicketBooker Class
        Scanner scan = new Scanner(System.in); // Scanner Object
        boolean loop = true; // Looping variable
        while(loop){
            // Greeting Message and Available Options
            System.out.println("--------------------------------------");
            System.out.println("Welcome to Railway Reservation System");
            System.out.println("--------------------------------------");
            System.out.println("-----------Your Options are-----------");
            System.out.println(" 1. Book Ticket \n 2. Cancel Ticket \n 3. Show Available Tickets \n 4. Show Booked Tickets \n 5. Exit");
            System.out.println("--------------------------------------");
            // Get Choice from the User
            System.out.println("Enter Your Choice : ");
            int choice = scan.nextInt();
            // Evaluate the Choice
            switch (choice){
                case 1 : // 1. Book Ticket
                    // Get Passenger Details
                    System.out.println("Enter Passenger's name, age and berth preference(L, M, U or SU) : ");
                    String name = scan.next();
                    int age = scan.nextInt();
                    String berthPreference = scan.next(); // L, M, U or SU
                    // Create a Passenger Object for Passenger Class
                    Passenger p = new Passenger(name, age, berthPreference);
                    bookTicket(p); // Invoke bookTicket() function with Passenger object
                    break;
                case 2 : // 2. Cancel Ticket
                    // Get Passenger Id
                    System.out.println("Enter Passenger Id to Cancel : ");
                    int id = scan.nextInt();
                    cancelTicket(id); // Invoke cancelTicket() function with the given id
                    break;
                case 3 : // 3. Show Available Tickets
                    booker.showAvailableTickets();
                    break;
                case 4 : // 4. Show Booked Tickets
                    booker.showBookedTickets();
                    break;
                case 5 : // 5. Exit
                    loop = false;
                    System.out.println("--------------------------------------");
                    System.out.println("----ThankYou for using our Service----");
                    System.out.println("--------------------------------------");
                    break;
                default : // Invalid Choice
                    System.out.println("Invalid Choice");
            }
        }
    }
}
