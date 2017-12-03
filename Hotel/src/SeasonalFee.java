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

    @Override
    public String toString()
    {
        return String.format("%-20s%-15s%-15s%-12s",
                eventName,
                startDate.getDayOfMonth() + "/" + startDate.getMonthValue() + "/" + startDate.getYear(),
                endDate.getDayOfMonth() + "/" + endDate.getMonthValue() + "/" + endDate.getYear(),
                fee + " PLN");
    }
}
