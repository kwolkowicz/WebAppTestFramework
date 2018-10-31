package company.com.dtos;

public class FmeJobDto {
    private String timeRequested;
    private String requesterResultPort;
    private String numFeaturesOutput;
    private String requesterHost;
    private String timeStarted;
    private String id;
    private String timeFinished;
    private String priority;
    private String statusMessage;
    private String status;

    public String getTimeRequested() {
        return timeRequested;
    }

    public FmeJobDto setTimeRequested(String timeRequested) {
        this.timeRequested = timeRequested;
        return this;
    }

    public String getRequesterResultPort() {
        return requesterResultPort;
    }

    public FmeJobDto setRequesterResultPort(String requesterResultPort) {
        this.requesterResultPort = requesterResultPort;
        return this;
    }

    public String getNumFeaturesOutput() {
        return numFeaturesOutput;
    }

    public FmeJobDto setNumFeaturesOutput(String numFeaturesOutput) {
        this.numFeaturesOutput = numFeaturesOutput;
        return this;
    }

    public String getRequesterHost() {
        return requesterHost;
    }

    public FmeJobDto setRequesterHost(String requesterHost) {
        this.requesterHost = requesterHost;
        return this;
    }

    public String getTimeStarted() {
        return timeStarted;
    }

    public FmeJobDto setTimeStarted(String timeStarted) {
        this.timeStarted = timeStarted;
        return this;
    }

    public String getId() {
        return id;
    }

    public FmeJobDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getTimeFinished() {
        return timeFinished;
    }

    public FmeJobDto setTimeFinished(String timeFinished) {
        this.timeFinished = timeFinished;
        return this;
    }

    public String getPriority() {
        return priority;
    }

    public FmeJobDto setPriority(String priority) {
        this.priority = priority;
        return this;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public FmeJobDto setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public FmeJobDto setStatus(String status) {
        this.status = status;
        return this;
    }
}
