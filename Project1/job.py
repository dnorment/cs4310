class Job():

    finishedAt = None

    def __init__(self, jobNum, remainingTime, finished=False):
        self.jobNum = jobNum
        self.remainingTime = remainingTime
        self.finished = finished
    
    def setFinished(self, time):
        self.finished = True
        self.finishedAt = time
    
    def __str__(self):
        return "Job {0}, {1} remaining".format(self.jobNum, self.remainingTime)