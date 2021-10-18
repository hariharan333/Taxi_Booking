import java.util.ArrayList;
import java.util.List;

public class Taxi {
        static int taxi_cnt = 0;
        int id;
        boolean booked;
        char currentSpot;
        int totalEarns ;
        int freeTime ;
        List<String> trips;//all details of all trips by this taxi

        public Taxi()
        {
            booked = false;
            currentSpot = 'A';
            totalEarns = 0;
            freeTime = 6;
            trips = new ArrayList<String>();
            taxi_cnt+=1;
            id = taxi_cnt;
        }
        public void setDetails(boolean booked,char currentSpot,int freeTime,int totalEarns,String tripDetails)
        {
            this.booked = booked;
            this.currentSpot = currentSpot;
            this.freeTime = freeTime;
            this.totalEarns = totalEarns;
            this.trips.add(tripDetails);
        }
        public void printDetails()
        {
            System.out.println("taxi id " + this.id +"      "+this.totalEarns);
            System.out.println("TaxiId      BoookingId      CustomerId      From     To      PickupPoint     DropPoint    Amount");
            for(String trip : trips)
            {
                System.out.println("id = "+id+"         "+trip);
            }
            System.out.println("-------------------------------------------------------------------------------------------------");
        }
        public void printTaxiDetails()
        {
            //print total earnings and taxi details link free time and current Location
            System.out.println("Taxi - "+this.id+"      "+"total earnings = "+this.totalEarns+"         "+"current Location = "+this.currentSpot+"        "+"Free time = "+this.freeTime);
        }
}
