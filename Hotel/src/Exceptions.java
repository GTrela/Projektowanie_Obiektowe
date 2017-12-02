import java.time.LocalDate;

class NoVacantRooms extends Exception {

	public NoVacantRooms(LocalDate in, LocalDate out){
		super("W okresie od "+ in.toString() + " do "+ out.toString()+", nie znaleziono żadnych wolnych pokoi!");
	}
}

class NotEnoughBeds extends Exception {
	public NotEnoughBeds()
	{
		super("W dostępnych pokojach, nie ma wystarczającej ilości wolnych łózek!");
	}
}

class RoomInUse extends Exception {
	public RoomInUse(long i)
	{
		super(String.format("Pokoj o id = %d jest uzywany!",i));
	}
}