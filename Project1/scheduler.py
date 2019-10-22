import math

class Scheduler():
    
    def __init__(self, jobArray):
        self.jobArray = jobArray
        self.numJobs = len(jobArray)
        self.burstStart = self.burstFinish = 0
        self.currentJob = 0
        self.currentTime = 0
        self.scheduleTable = []
    
    def avgTurnaround(self):
        return sum(job.finishedAt for job in self.jobArray) / self.numJobs

    def isFinished(self):
        return all(job.finished for job in self.jobArray)
    
    def tick(self):
        self.currentTime += 1

    def run(self, maxBurstTime=math.inf):
        while not self.isFinished():
            currJob = self.jobArray[self.currentJob % self.numJobs]
            if not currJob.finished and (self.currentTime - self.burstStart < maxBurstTime):
                self.tick()
                currJob.work(self.currentTime)
                if currJob.finished:
                    self.burstFinish = self.currentTime
                    self.scheduleTable.append([currJob.jobNum, self.burstStart, self.burstFinish, currJob.jobNum])
                    self.burstStart = self.burstFinish
            elif self.currentTime - self.burstStart >= maxBurstTime:
                    self.burstFinish = self.currentTime
                    self.scheduleTable.append([currJob.jobNum, self.burstStart, self.burstFinish])
                    self.burstStart = self.burstFinish
                    self.currentJob += 1
            else:
                self.currentJob += 1