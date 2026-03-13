develop
import java.util.*;

abstract class Room {
    protected int beds;
    protected int size;
    protected double price;

    public Room(int beds, int size, double price) {
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayRoomDetails() {
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);

class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
main
    }
}

class BookingRequestQueue {

    private java.util.Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new java.util.LinkedList<>();
    }
develop
}

class RoomInventory {
    private Map<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Single", 5);
        availability.put("Double", 3);
        availability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return availability;
    }

    public void updateAvailability(String type, int count) {
        availability.put(type, count);

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
main
    }
}
public class BookMyStayApp {

develop
class RoomSearchService {
    public void searchAvailableRooms(RoomInventory inventory,
                                     Room single,
                                     Room dbl,
                                     Room suite) {

        Map<String, Integer> map = inventory.getRoomAvailability();

        System.out.println("Room Search\n");

        if (map.get("Single") > 0) {
            System.out.println("Single Room:");
            single.displayRoomDetails();
            System.out.println("Available: " + map.get("Single") + "\n");
        }

        if (map.get("Double") > 0) {
            System.out.println("Double Room:");
            dbl.displayRoomDetails();
            System.out.println("Available: " + map.get("Double") + "\n");
        }

        if (map.get("Suite") > 0) {
            System.out.println("Suite Room:");
            suite.displayRoomDetails();
            System.out.println("Available: " + map.get("Suite") + "\n");
        }
    }
}

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

class BookingRequestQueue {
    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(Reservation r) {
        queue.offer(r);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean hasPendingRequests() {
        return !queue.isEmpty();
    }
}

class RoomAllocationService {
    private Set<String> allocatedIds = new HashSet<>();
    private Map<String, Set<String>> assigned = new HashMap<>();

    public void allocateRoom(Reservation r, RoomInventory inv) {

        String type = r.getRoomType();
        Map<String, Integer> map = inv.getRoomAvailability();

        if (map.get(type) == null || map.get(type) <= 0) {
            System.out.println("No rooms available for Guest: " + r.getGuestName());
            return;
        }

        String id = generateRoomId(type);

        allocatedIds.add(id);
        assigned.computeIfAbsent(type, k -> new HashSet<>()).add(id);

        inv.updateAvailability(type, map.get(type) - 1);

        System.out.println("Booking confirmed for Guest: "
                + r.getGuestName() + ", Room ID: " + id);
    }

    private String generateRoomId(String type) {
        int num = assigned.getOrDefault(type, new HashSet<>()).size() + 1;
        return type + "-" + num;
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Room Allocation Processing");

        RoomInventory inventory = new RoomInventory();

        BookingRequestQueue queue = new BookingRequestQueue();

        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Subha", "Single"));
        queue.addRequest(new Reservation("Vanmathi", "Suite"));

        RoomAllocationService service = new RoomAllocationService();

        while (queue.hasPendingRequests()) {
            Reservation r = queue.getNextRequest();
            service.allocateRoom(r, inventory);

    public static void main(String[] args) {

        System.out.println("Booking Request Queue");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        Reservation r1 = new Reservation("Abhi", "Single");
        Reservation r2 = new Reservation("Subha", "Double");
        Reservation r3 = new Reservation("Vanmathi", "Suite");

        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        while (bookingQueue.hasPendingRequests()) {
            Reservation r = bookingQueue.getNextRequest();
            System.out.println(
                    "Processing booking for Guest: "
                            + r.getGuestName()
                            + ", Room Type: "
                            + r.getRoomType()
            );
          main
        }
    }
}