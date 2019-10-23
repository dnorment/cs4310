class Job():
    """
    Class to simulate a job in a scheduler. Holds job number, remaining time, and whether the job has been completed or not.
    """
    finishedAt = None

    def __init__(self, jobNum, remainingTime, finished=False):
        self.jobNum = jobNum
        self.remainingTime = remainingTime
        self.finished = finished
    
    def setFinished(self, time):
        self.finished = True
        self.finishedAt = time
    
    def work(self, time):
        self.remainingTime -= 1
        if self.remainingTime <= 0:
            self.setFinished(time)
    
    def __str__(self):
        return "Job {0}, {1} remaining".format(self.jobNum, self.remainingTime)
    
    def __eq__(self, other):
        return self.remainingTime == other.remainingTime

    def __gt__(self, other):
        return self.remainingTime > other.remainingTime

    def __lt__(self, other):
        return self.remainingTime < other.remainingTime