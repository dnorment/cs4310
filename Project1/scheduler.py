import math
import numpy as np

class Scheduler():
    
    def __init__(self, jobArray):
        self.jobArray = jobArray
        self.numJobs = len(jobArray)
        self.burstStart = self.burstFinish = 0
        self.currentJob = 0
        self.currentTime = 0
        self.scheduleTable = []
    
    def isFinished(self):
        return all(job.finished for job in self.jobArray)
    
    def tick(self):
        self.currentTime += 1

    def run(self, maxBurstTime=math.inf):
        while not self.isFinished():
            currJob = self.jobArray[self.currentJob % self.numJobs]
            print(str(currJob))
            if not currJob.finished and (self.currentTime - self.burstStart < maxBurstTime):
                self.tick()
                currJob.work(self.currentTime)
                if currJob.finished:
                    self.burstFinish = self.currentTime
                    self.scheduleTable.append([currJob.jobNum, self.burstStart, self.burstFinish])
                    self.burstStart = self.burstFinish
            elif self.currentTime - self.burstStart >= maxBurstTime:
                    self.burstFinish = self.currentTime
                    self.scheduleTable.append([currJob.jobNum, self.burstStart, self.burstFinish])
                    self.burstStart = self.burstFinish
                    self.currentJob += 1
            else:
                self.currentJob += 1
        print(self.scheduleTable)
        print([j.finishedAt for j in self.jobArray])


class FirstComeFirstServe(Scheduler):
    pass


class ShortestJobFirst(FirstComeFirstServe):

    def run(self):
        self.jobArray.sort() #sort by shortest remaining time first
        super().run()


class RoundRobin(Scheduler):

    def run(self, maxTime):
        super().run(maxBurstTime=maxTime)
