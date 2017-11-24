import java.time.LocalDate;

class SeasonalFee
{
    String eventName;
    LocalDate startDate;
    LocalDate endDate;
    double fee;

    public SeasonalFee(String eventName, LocalDate startDate, LocalDate endDate, double fee)
    {
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fee = fee;
    }

    public String getEventName()
    {
        return eventName;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public double getFee()
    {
        return fee;
    }
}
